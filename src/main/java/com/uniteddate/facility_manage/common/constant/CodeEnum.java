package com.uniteddate.facility_manage.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 56844 on 2018/6/8.
 */
public enum CodeEnum {
    SUCCESS("SUCCESS", 200, "操作成功!"),
    FAIL("FAIL", -1, "操作失败!"),
    ;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage(String code) {
        return map.get(code) + "";
    }

    public void setCode(int code) {
        this.code = code;
    }

    private String message;
    private int code;
    private Map map = new HashMap<String, String>();

    private CodeEnum(String name, int code, String message) {
        this.name = name;
        this.code = code;
        this.message = message;
        map.put(code, message);
    }
}
