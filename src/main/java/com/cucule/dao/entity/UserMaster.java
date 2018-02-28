package com.cucule.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_master")
public class UserMaster {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_first_name_kana")
    private String userFirstNameKana;

    @Column(name = "user_last_name_kana")
    private String userLastNameKana;

    @Column(name = "user_first_name_kanji")
    private String userFirstNameKanji;

    @Column(name = "user_last_name_kanji")
    private String userLastNameKanji;

    @Column(name = "insert_time")
    private Date insertTime;

    @Column(name = "update_time")
    private Date updateTime;
}
