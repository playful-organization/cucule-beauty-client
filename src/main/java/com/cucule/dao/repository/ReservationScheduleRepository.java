package com.cucule.dao.repository;

import com.cucule.dao.entity.ReservationSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationScheduleRepository extends JpaRepository<ReservationSchedule, String> {
    @Query("SELECT r FROM ReservationSchedule r WHERE r.clientId = :clientId AND r.startTime  >= :startTime AND r.endTime <= :endTime ORDER BY r.staffMaster.priority ASC")
    List<ReservationSchedule> findByClientId(@Param("clientId") String clientId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}

