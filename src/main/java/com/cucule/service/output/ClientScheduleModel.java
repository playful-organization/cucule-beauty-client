package com.cucule.service.output;

import com.cucule.common.global.CuculeNeedTimeMap;
import com.cucule.common.util.CuculeDateUtils;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.List;

@Data
public class ClientScheduleModel {
    public static final String LINE = System.lineSeparator();
    public static final String SINGLE = "'";
    public static final String CONMA = ",";

    private List<Reservation> reservationList;
    private String currentDate;
    private String currentDateParam;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        for (Reservation reservation : this.reservationList) {
            sb.append(SINGLE);
            sb.append(reservation.getPriority());
            sb.append(SINGLE);
            sb.append(" : { ");
            sb.append("title : ");
            sb.append(SINGLE);
            sb.append(reservation.getStaffName());
            sb.append(SINGLE);
            sb.append(CONMA);
            sb.append("schedule:[");
            for (ReservationDetail reservationDetail : reservation.getReservationDetailList()) {
                sb.append(" { ");
                sb.append(" start : ");
                sb.append(SINGLE);
                // 2016-10-21 11:00:00.0'
                sb.append(StringUtils.substring(reservationDetail.getStartTime().toString(), 11, 16));
                sb.append(SINGLE);
                sb.append(CONMA);
                sb.append(" end : ");
                sb.append(SINGLE);
                sb.append(StringUtils.substring(reservationDetail.getEndTime().toString(), 11, 16));
                sb.append(SINGLE);
                sb.append(CONMA);
                sb.append(" text : ");
                sb.append(SINGLE);
                sb.append(reservationDetail.getUserFullName());
                sb.append(SINGLE);
                sb.append(CONMA);
                sb.append(" data : ");
                sb.append(SINGLE);
                sb.append(reservationDetail.getReservationId());
                sb.append(CONMA);
                sb.append(reservation.getStaffName());
                sb.append(CONMA);
                sb.append(reservationDetail.getUserLastNameKana());
                sb.append(CONMA);
                sb.append(reservationDetail.getUserFirstNameKana());
                sb.append(CONMA);
                sb.append(reservationDetail.getUserLastNameKanji());
                sb.append(CONMA);
                sb.append(reservationDetail.getUserFirstNameKanji());
                sb.append(CONMA);
                sb.append(CuculeDateUtils.dateToString(reservationDetail.getStartTime()));
                sb.append(CONMA);
                sb.append(StringUtils.substring(reservationDetail.getStartTime().toString(), 11, 16));
                sb.append(CONMA);
                sb.append(StringUtils.substring(reservationDetail.getEndTime().toString(), 11, 16));
                sb.append(CONMA);
                sb.append(CuculeNeedTimeMap.fetchNeedTime(reservationDetail.getNeedTime()));
                sb.append(CONMA);
                sb.append(reservationDetail.getNeedTime());
                sb.append(CONMA);
                sb.append(reservation.getStaffId());
                sb.append(SINGLE);
                sb.append(" }, ");
            }
            sb.append(" ] } ,");
        }

        return sb.toString();
    }
}
