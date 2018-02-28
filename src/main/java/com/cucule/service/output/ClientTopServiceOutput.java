package com.cucule.service.output;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ClientTopServiceOutput {
    List<Map<String, String>> oneWeekScheduleList;
    List<Map<String, String>> twoWeekScheduleList;

}
