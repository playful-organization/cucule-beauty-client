package com.cucule.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cucule.dao.entity.StaffMaster;

@Repository
public interface StaffMasterRepository extends JpaRepository<StaffMaster, String> {
    @Query("SELECT s FROM StaffMaster s WHERE s.clientId = :clientId")
    List<StaffMaster> findByClientId(@Param("clientId") String clientId);

    @Query("SELECT s FROM StaffMaster s WHERE s.clientId = :clientId AND s.priority =:priority")
    StaffMaster findByClientIdAndPriority(@Param("clientId") String clientId, @Param("priority") Integer priority);
}
