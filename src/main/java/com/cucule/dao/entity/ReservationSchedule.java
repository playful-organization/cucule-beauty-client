package com.cucule.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "reservation_schedule")
public class ReservationSchedule {

    @Id
    @Column(name = "reservation_id")
    private String reservationId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "staff_id")
    private String staffId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "reservation_date")
    private Date reservationDate;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "need_time")
    private Integer needTime;

    @Column(name = "insert_time")
    private Date insertTime;

    @Column(name = "update_time")
    private Date updateTime;

    @ManyToOne
    @OrderBy("priority ASC")
    @JoinColumn(name = "staff_id", insertable = false, updatable = false)
    private StaffMaster staffMaster;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserMaster userMaster;

}
