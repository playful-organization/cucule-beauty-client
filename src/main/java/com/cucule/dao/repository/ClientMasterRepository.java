package com.cucule.dao.repository;

import com.cucule.dao.entity.ClientMaster;
import com.cucule.dao.entity.StaffMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientMasterRepository extends JpaRepository<ClientMaster, String> {
    @Query("SELECT c FROM ClientMaster c WHERE c.loginId = :loginId AND c.loginPassword = :loginPassword")
    List<ClientMaster> findByLoginIdAndPassword(@Param("loginId") String loginId, @Param("loginPassword") String longinPassword);
}
