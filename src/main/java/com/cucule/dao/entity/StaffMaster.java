package com.cucule.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "staff_master")
public class StaffMaster {

    @Id
    @Column(name = "staff_id")
    private String staffId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "staff_name")
    private String staffName;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "insert_time")
    private Date insertTime;

    @Column(name = "update_time")
    private Date updateTime;

}
