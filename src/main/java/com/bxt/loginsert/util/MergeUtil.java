package com.bxt.loginsert.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * list和string转换器
 */
public class MergeUtil {

    public static String mergeLongListToString(List<Long> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        list.forEach(obj -> {
            sb.append(obj.toString()).append(",");
        });
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static String mergeIntegerListToString(List<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        list.forEach(obj -> {
            sb.append(obj.toString()).append(",");
        });
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static String mergeIntegerListToString(Set<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        list.forEach(obj -> {
            sb.append(obj.toString()).append(",");
        });
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static List<Long> divisionStrToLongList(String str) {
        if (StringUtils.isBlank(str)) {
            return new ArrayList<>();
        }

        List<Long> resultList = new ArrayList<>();
        String[] split = str.split(",");
        for (String longStr : split) {
            Long longValue = Long.valueOf(longStr.replace(" ", ""));
            resultList.add(longValue);
        }
        return resultList;
    }

    public static List<Integer> divisionStrToIntegerList(String str) {
        if (StringUtils.isBlank(str)) {
            return new ArrayList<>();
        }

        List<Integer> resultList = new ArrayList<>();
        String[] split = str.split(",");
        for (String longStr : split) {
            Integer longValue = Integer.valueOf(longStr.replace(" ", ""));
            resultList.add(longValue);
        }
        return resultList;
    }

    public static List<String> divisionStrToStringList(String string) {
        if (StringUtils.isBlank(string)) {
            return new ArrayList<>();
        }

        List<String> resultList = new ArrayList<>();
        String[] split = string.split(",");
        for (String str : split) {
            resultList.add(str.replace(" ", ""));
        }
        return resultList;
    }
}
