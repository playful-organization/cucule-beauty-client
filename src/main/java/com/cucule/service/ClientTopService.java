package com.cucule.service;

import com.cucule.common.util.CuculeDateUtils;
import com.cucule.dao.mapper.ReservationMapper;
import com.cucule.service.output.ClientTopServiceOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientTopService {

    @Autowired
    ReservationMapper reservationMapper;

    public ClientTopServiceOutput initClientTop(String clientId) {

        int afterDays = 0;
        List<Map<String, String>> oneWeekScheduleList = new ArrayList<>();
        while (afterDays <= 6) {
            oneWeekScheduleList.add(calcWeekMap(clientId, afterDays));
            afterDays++;
        }
        List<Map<String, String>> twoWeekScheduleList = new ArrayList<>();
        while (afterDays <= 13) {
            twoWeekScheduleList.add(calcWeekMap(clientId, afterDays));
            afterDays++;
        }

        ClientTopServiceOutput output = new ClientTopServiceOutput();
        output.setOneWeekScheduleList(oneWeekScheduleList);
        output.setTwoWeekScheduleList(twoWeekScheduleList);
        return output;
    }

    private Map<String, String> calcWeekMap(String clientId, int afterDays) {
        Map<String, String> weekScheduleMap;
        weekScheduleMap = new HashMap<>();
        weekScheduleMap.put("weekType", CuculeDateUtils.calcAfterNWeekType(afterDays).toString());
        weekScheduleMap.put("monthDate", CuculeDateUtils.calcAfterNDateE(afterDays));
        weekScheduleMap.put("timestamp", CuculeDateUtils.calcAfterNDate(afterDays));
        String yearMonthDay = CuculeDateUtils.calcAfterNDateForSql(afterDays);
        Date startTime = CuculeDateUtils.parseStringToDate(yearMonthDay + " 00:00:00");
        Date endTime = CuculeDateUtils.parseStringToDate(yearMonthDay + " 23:59:59");
        Integer numOfReservation = reservationMapper.countForWeekSummary(clientId, startTime, endTime);
        weekScheduleMap.put("numOfReservation", numOfReservation + "件");
        return weekScheduleMap;
    }
}
