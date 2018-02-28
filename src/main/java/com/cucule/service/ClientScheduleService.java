package com.cucule.service;

import com.cucule.common.global.CuculeConstants;
import com.cucule.common.logging.CuculeLogger;
import com.cucule.dao.domain.ReservationSummary;
import com.cucule.dao.entity.ReservationSchedule;
import com.cucule.dao.entity.StaffMaster;
import com.cucule.dao.entity.UserMaster;
import com.cucule.dao.mapper.ReservationMapper;
import com.cucule.dao.repository.ReservationScheduleRepository;
import com.cucule.dao.repository.StaffMasterRepository;
import com.cucule.dao.repository.UserMasterRepository;
import com.cucule.service.input.ChangeClientScheduleInput;
import com.cucule.service.input.EditClientScheduleInput;
import com.cucule.service.input.RegisterClientScheduleInput;
import com.cucule.service.output.ClientScheduleModel;
import com.cucule.service.output.Reservation;
import com.cucule.service.output.ReservationDetail;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

@Service
public class ClientScheduleService {

    @Autowired
    ReservationScheduleRepository reservationScheduleRepository;
    @Autowired
    UserMasterRepository userMasterRepository;

    @Autowired
    StaffMasterRepository staffMasterRepository;

    @Autowired
    ReservationMapper reservationMapper;

    public ClientScheduleModel initClientSchedule(String clientId, String date) throws ParseException {

        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyyMMddHH:mm:ss").withLocale(Locale.JAPAN);
        Date startTime = dtf.parseDateTime(date + "00:00:00").toDate();
        Date endTime = dtf.parseDateTime(date + "23:59:59").toDate();

        List<ReservationSummary> reservationSummarys = reservationMapper.findByClientId(clientId, startTime, endTime);

        //List<ReservationSchedule> reservationScheduleResults = this.reservationScheduleRepository.findByClientId(clientId, startTime, endTime);
        ClientScheduleModel output = createInitOutput(clientId, reservationSummarys);
        return output;
    }

    @Transactional
    public boolean registerClientSchedule(RegisterClientScheduleInput input) throws Exception {
        UserMaster userMaster = new UserMaster();

        String userId = UUID.randomUUID().toString();
        userMaster.setUserId(userId);
        userMaster.setUserLastNameKana(input.getLastNameKana());
        userMaster.setUserFirstNameKana(input.getFirstNameKana());
        userMaster.setUserLastNameKanji(input.getLastNameKanji());
        userMaster.setUserFirstNameKanji(input.getFirstNameKanji());

        userMasterRepository.save(userMaster);
        ReservationSchedule reservationSchedule = new ReservationSchedule();

        reservationSchedule.setReservationId(UUID.randomUUID().toString());
        // TODO ここSessionからとる？
        reservationSchedule.setClientId(input.getClientId());
        reservationSchedule.setStaffId(input.getStaffId());
        reservationSchedule.setUserId(userId);
        reservationSchedule.setReservationDate(input.getStartTime());
        reservationSchedule.setStartTime(input.getStartTime());
        reservationSchedule.setEndTime(input.getEndTime());
        reservationSchedule.setNeedTime(input.getNeedTime());
        reservationScheduleRepository.save(reservationSchedule);
        // TODO 失敗した時の判断
        return true;
    }

    @Transactional
    public boolean editClientSchedule(EditClientScheduleInput input) throws Exception {
        boolean isExists = reservationScheduleRepository.exists(input.getReservationId());
        boolean result = false;
        if (isExists) {
            result = reservationMapper.updateSchedule(input.getStaffId(), input.getStartTime(), input.getStartTime(), input.getEndTime(), input.getNeedTime(), new Date(), input.getReservationId());
        } else {
            // TODO Exception投げて画面にエラーメッセージ出す。
            CuculeLogger.error("既にキャンセルされています。");
        }
        // TODO 失敗した時の判断
        return result;
    }

    @Transactional
    public boolean changeClientSchedule(ChangeClientScheduleInput input) throws Exception {
        boolean result = false;
        // TODO すでに削除されたとかで見つからなかったらExceptionになるはずだからいずれ対処
        ReservationSchedule reservationSchedule = reservationScheduleRepository.findOne(input.getReservationId());
        // TODO ドラックで移動してきた時はstaffId取得できないので、priorityで取得
        StaffMaster staffMaster = staffMasterRepository.findByClientIdAndPriority(input.getClientId(), input.getPriority());

        Date reservationDate = reservationSchedule.getReservationDate();

        DateTime startTime = new DateTime(reservationDate);
        String st = input.getStartTime().toString();

        startTime = startTime.plusHours(Integer.parseInt(StringUtils.substringBefore(st, ".")));
        if ("5".equals(StringUtils.substringAfter(st, "."))) {
            startTime = startTime.plusMinutes(30);
        }
        DateTime endTime = new DateTime(reservationDate);
        String et = input.getEndTime().toString();
        endTime = endTime.plusHours(Integer.parseInt(StringUtils.substringBefore(et, ".")));
        if ("5".equals(StringUtils.substringAfter(et, "."))) {
            endTime = endTime.plusMinutes(30);
        }

        System.out.println(st);
        System.out.println(et);

        result = reservationMapper.updateSchedule(staffMaster.getStaffId(), reservationDate, startTime.toDate(), endTime.toDate(), input.getNeedTime(), new Date(), input.getReservationId());

        return result;
    }

    @Transactional
    public boolean cancelClientSchedule(String reservationId) throws Exception {
        reservationScheduleRepository.delete(reservationId);
        // TODO 失敗した時の判断
        return true;
    }

    private ClientScheduleModel createInitOutput(String clientId, List<ReservationSummary> reservationSummarys) {
        Reservation reservation = new Reservation();
        List<Reservation> reservationList = new ArrayList<>();
        List<ReservationDetail> reservationDetailList = new ArrayList<>();
        boolean isDifferentStaff = false;
        boolean isFirstRecord = true;
        Integer oneBeforePriority = null;
        String oneBeforeStaffName = "";
        String oneBeforeStaffId = "";

        for (ReservationSummary reservationSummary : reservationSummarys) {
            Integer priority = reservationSummary.getPriority();
            String userFirstName = reservationSummary.getUserFirstNameKanji();
            String userLastName = reservationSummary.getUserLastNameKanji();

            if (StringUtils.isBlank(userFirstName) || StringUtils.isBlank(userLastName)) {
                // LastNameKana is should be not null
                userFirstName = reservationSummary.getUserFirstNameKana();
                userLastName = reservationSummary.getUserLastNameKana();
            }

            StringBuffer userFullName = new StringBuffer();
            userFullName.append(userLastName);
            userFullName.append(CuculeConstants.SPACE);
            userFullName.append(userFirstName);

            String reservationId = reservationSummary.getReservationId();
            Date startTime = reservationSummary.getStartTime();
            Date endTime = reservationSummary.getEndTime();
            Integer needTime = reservationSummary.getNeedTime();
            String userLastNameKana = reservationSummary.getUserLastNameKana();
            String userFirstNameKana = reservationSummary.getUserFirstNameKana();
            String userLastNameKanji = reservationSummary.getUserLastNameKanji();
            String userFirstNameKanji = reservationSummary.getUserFirstNameKanji();

            isDifferentStaff = priority.equals(oneBeforePriority) ? false : true;

            if (isDifferentStaff && !isFirstRecord) {
                reservation = new Reservation();
                reservation.setStaffId(oneBeforeStaffId);
                reservation.setStaffName(oneBeforeStaffName);
                reservation.setPriority(oneBeforePriority);
                reservation.setReservationDetailList(reservationDetailList);
                reservationList.add(reservation);
                reservationDetailList = new ArrayList<>();
            }
            ReservationDetail reservationDetail = new ReservationDetail();
            reservationDetail.setReservationId(reservationId);
            reservationDetail.setStartTime(startTime);
            reservationDetail.setNeedTime(needTime);
            reservationDetail.setEndTime(endTime);
            reservationDetail.setUserFullName(userFullName.toString());
            reservationDetail.setUserLastNameKana(userLastNameKana);
            reservationDetail.setUserFirstNameKana(userFirstNameKana);
            reservationDetail.setUserLastNameKanji(userLastNameKanji);
            reservationDetail.setUserFirstNameKanji(userFirstNameKanji);
            // 0件予約のスタッフの為
            if (reservationDetail.getStartTime() != null) {
                reservationDetailList.add(reservationDetail);
            }

            oneBeforeStaffName = reservationSummary.getStaffName();
            oneBeforePriority = reservationSummary.getPriority();
            oneBeforeStaffId = reservationSummary.getStaffId();
            isFirstRecord = false;
        }
        // 予約が無かった時の対処
        //        if (CollectionUtils.isEmpty(reservationSummarys)) {
        //            List<StaffMaster> staffMasterResults = this.staffMasterRepository.findByClientId(clientId);
        //            for (StaffMaster staffMasterResult : staffMasterResults) {
        //                reservation = new Reservation();
        //                reservation.setStaffId(staffMasterResult.getStaffId());
        //                reservation.setPriority(staffMasterResult.getPriority());
        //                reservation.setStaffName(staffMasterResult.getStaffName());
        //                reservation.setReservationDetailList(reservationDetailList);
        //                reservationList.add(reservation);
        //            }
        //        } else {
        // 最後の予約レコードを追加
        reservation = new Reservation();
        reservation.setStaffId(oneBeforeStaffId);
        reservation.setStaffName(oneBeforeStaffName);
        reservation.setPriority(oneBeforePriority);
        reservation.setReservationDetailList(reservationDetailList);
        reservationList.add(reservation);
        //   }
        ClientScheduleModel output = new ClientScheduleModel();
        output.setReservationList(reservationList);
        return output;
    }
}
