package com.cucule.common.global;

import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

public class CuculeNeedTimeMap {
    private static Map<Integer, String> needTimeMap = new HashMap<>();

    public static String fetchNeedTime(Integer needTime) {
        if (CollectionUtils.isEmpty(needTimeMap)) {
            initNeedTime();
        }
        return needTimeMap.get(needTime);
    }

    private static void initNeedTime() {
        needTimeMap.put(30, "0:30");
        needTimeMap.put(60, "1:00");
        needTimeMap.put(90, "1:30");
        needTimeMap.put(120, "2:00");
        needTimeMap.put(150, "2:30");
        needTimeMap.put(180, "3:00");
        needTimeMap.put(210, "3:30");
        needTimeMap.put(240, "4:00");
        needTimeMap.put(270, "4:30");
        needTimeMap.put(300, "5:00");
        needTimeMap.put(330, "5:30");
        needTimeMap.put(360, "6:00");
        needTimeMap.put(390, "6:30");
        needTimeMap.put(420, "7:00");
        needTimeMap.put(450, "7:30");
        needTimeMap.put(480, "8:00");
        needTimeMap.put(510, "8:30");
        needTimeMap.put(540, "9:00");
        needTimeMap.put(570, "9:30");
        needTimeMap.put(600, "10:00");
        needTimeMap.put(630, "10:30");
        needTimeMap.put(660, "11:00");
        needTimeMap.put(690, "11:30");
        needTimeMap.put(720, "12:00");
        needTimeMap.put(750, "12:30");
        needTimeMap.put(780, "13:00");
        needTimeMap.put(810, "13:30");
        needTimeMap.put(840, "14:00");
        needTimeMap.put(870, "14:30");
        needTimeMap.put(900, "15:00");
        needTimeMap.put(930, "15:30");
        needTimeMap.put(960, "16:00");
        needTimeMap.put(990, "16:30");
        needTimeMap.put(1020, "17:00");
        needTimeMap.put(1050, "17:30");
        needTimeMap.put(1080, "18:00");
        needTimeMap.put(1110, "18:30");
        needTimeMap.put(1140, "19:00");
        needTimeMap.put(1170, "19:30");
        needTimeMap.put(1200, "20:00");
        needTimeMap.put(1230, "20:30");
        needTimeMap.put(1260, "21:00");
        needTimeMap.put(1290, "21:30");
        needTimeMap.put(1320, "22:00");
        needTimeMap.put(1350, "22:30");
        needTimeMap.put(1380, "23:00");
        needTimeMap.put(1410, "23:30");
        needTimeMap.put(1440, "24:00");
    }
}
