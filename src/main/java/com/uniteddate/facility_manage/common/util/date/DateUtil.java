package com.uniteddate.facility_manage.common.util.date;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateUtil {

    public static Long getNow(){
        return LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }
}
