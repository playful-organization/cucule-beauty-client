package com.cucule.common.util;

import com.ibm.icu.text.Transliterator;
import org.apache.commons.lang.StringUtils;

/**
 * Created by user on 2016/11/27.
 */
public class CuculeStringUtils {
    public static String replaceFullWidthToHarfWidth(String targetStr) {
        if (StringUtils.isBlank(targetStr)) {
            return targetStr;
        }
        Transliterator transliterator = Transliterator.getInstance("Fullwidth-Halfwidth");
        String result = transliterator.transliterate(targetStr);
        return result;
    }

    public static String replaceHarfWidthToFullWidth(String targetStr) {
        if (StringUtils.isBlank(targetStr)) {
            return targetStr;
        }
        Transliterator transliterator = Transliterator.getInstance("Halfwidth-Fullwidth");
        String result = transliterator.transliterate(targetStr);
        return result;
    }

    public static String replaceHiraToKana(String targetStr) {
        if (StringUtils.isBlank(targetStr)) {
            return targetStr;
        }
        Transliterator transliterator = Transliterator.getInstance("Hiragana-Katakana");
        String result = transliterator.transliterate(targetStr);
        return result;
    }

    public static String replaceKanaToHira(String targetStr) {
        if (StringUtils.isBlank(targetStr)) {
            return targetStr;
        }
        Transliterator transliterator = Transliterator.getInstance("Katakana-Hiragana");
        String result = transliterator.transliterate(targetStr);
        return result;
    }

    public static String replaceHiraAndHarfKanaToFullWidthKana(String targetStr) {
        String result = replaceHarfWidthToFullWidth(replaceHiraToKana(targetStr));
        return result;
    }

    public static String allTrim(String targetStr) {
        String result = StringUtils.strip(targetStr, " 　");
        return result;
    }
}
