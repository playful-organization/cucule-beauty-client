package com.cucule.common.global;

public class CuculeConstants {
    public static final String SPACE = " ";
    public static final String LOGIN_ID_REGEX = "[0-9a-zA-Z!]{4,32}";
    public static final String LOGIN_PASSWORD_REGEX = "[0-9a-zA-Z!]{4,32}";
    public static final String RAITEN_DATE_REGEX = "2[0-9]{3}年(0[1-9]|1[0-2])月(0[1-9]|[1-2][0-9]|3[0-1])日\\(.\\)";
    public static final String LAST_NAME_KANA_REGEX = "[ｦ-ﾟぁ-んァ-ヶ-ー 　]{1,32}";
    public static final String FIRST_NAME_KANA_REGEX = "([ｦ-ﾟぁ-んァ-ヶ-ー 　]{1,32}|)";

}
