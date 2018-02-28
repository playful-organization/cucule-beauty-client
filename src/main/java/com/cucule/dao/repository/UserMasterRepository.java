package com.cucule.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cucule.dao.entity.UserMaster;

@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, String> {
}
