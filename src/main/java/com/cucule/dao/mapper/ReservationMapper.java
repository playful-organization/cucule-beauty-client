package com.cucule.dao.mapper;

import com.cucule.dao.domain.ReservationSummary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

@Mapper
public interface ReservationMapper {

    //  SELECT s.client_id              AS clientId,
    //         s.staff_id               AS staffId,
    //         s.priority,
    //         s.staff_name             AS staffName,
    //         ru.reservation_id        AS reservationId
    //         ru.start_time            AS startTime,
    //         ru.need_time             AS needTime,
    //         ru.end_time              AS endTime,
    //         ru.user_last_name_kana   AS userLastNameKana,
    //         ru.user_first_name_kana  AS userFirstNameKana,
    //         ru.user_last_name_kanji  AS userLastNameKanji,
    //         ru.user_first_name_kanji AS userFirstNameKanji
    //  FROM   staff_master s
    //         LEFT JOIN (SELECT r.reservation_id,
    //                           r.client_id,
    //                           r.staff_id,
    //                           r.start_time,
    //                           r.need_time,
    //                           r.end_time,
    //                           u.user_last_name_kana,
    //                           u.user_first_name_kana,
    //                           u.user_last_name_kanji,
    //                           u.user_first_name_kanji
    //                    FROM   reservation_schedule r
    //                           LEFT JOIN user_master u
    //                                  ON u.user_id = r.user_id
    //                    WHERE  r.client_id = ?
    //                       AND r.start_time >= ?
    //                       AND r.end_time <= ?) ru
    //                ON s.staff_id = ru.staff_id
    //  WHERE  s.client_id = ?
    //  ORDER  BY s.priority ASC
    @Select("SELECT s.client_id as clientId, s.staff_id as staffId, s.priority, s.staff_name as staffName, ru.reservation_id as reservationId,ru.start_time as startTime, ru.need_time as needTime, ru.end_time as endTime, ru.user_last_name_kana as userLastNameKana, ru.user_first_name_kana as userFirstNameKana, ru.user_last_name_kanji as userLastNameKanji, ru.user_first_name_kanji as userFirstNameKanji FROM staff_master s left join (SELECT r.reservation_id, r.client_id, r.staff_id, r.start_time, r.need_time, r.end_time, u.user_last_name_kana, u.user_first_name_kana, u.user_last_name_kanji, u.user_first_name_kanji FROM reservation_schedule r left join user_master u on u.user_id = r.user_id  WHERE r.client_id = #{clientId} AND r.start_time >= #{startTime} AND r.end_time <= #{endTime} ) ru on s.staff_id = ru.staff_id  WHERE s.client_id = #{clientId} ORDER BY s.priority ASC")
    List<ReservationSummary> findByClientId(@Param("clientId") String clientId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    //  SELECT count(*)
    //  FROM reservation_schedule r
    //  WHERE r.client_id = ?
    //    AND start_time >= ?
    //    AND r.end_time <= ?
    @Select("SELECT count(*) FROM reservation_schedule r WHERE r.client_id = #{clientId} AND start_time >= #{startTime} AND r.end_time <= #{endTime}")
    Integer countForWeekSummary(@Param("clientId") String clientId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    //    UPDATE reservation_schedule
    //    SET    staff_id = ?,
    //    reservation_date = ?,
    //    start_time = ?,
    //    end_time = ?,
    //    need_time = ?,
    //    update_time = ?
    //    WHERE  reservation_id = ?
    @Update("UPDATE reservation_schedule SET staff_id = #{staffId}, reservation_date = #{reservationDate}, start_time = #{startTime}, end_time = #{endTime}, need_time = #{needTime},update_time = #{updateTime} WHERE reservation_id = #{reservationId}")
    boolean updateSchedule(@Param("staffId") String staffId, @Param("reservationDate") Date reservationDate, @Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("needTime") Integer needTime, @Param("updateTime") Date updateTime, @Param("reservationId") String reservationId);

}


