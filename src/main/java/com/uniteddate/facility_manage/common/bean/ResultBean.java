package com.uniteddate.facility_manage.common.bean;

import com.uniteddate.facility_manage.common.constant.CodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "封装类")
public class ResultBean<T> {
    @ApiModelProperty(value = "状态码")
    private int code;
    @ApiModelProperty(value = "提示")
    private String message;
    @ApiModelProperty(value = "内容")
    private  T data;

    public static ResultBean succeed(){
        return succeed(CodeEnum.SUCCESS.getMessage());
    }
    public static ResultBean succeed(String message){
        return succeed(message,null);
    }
    public static ResultBean succeed(Object data){
        return  succeed(CodeEnum.SUCCESS.getMessage(),data);
    }
    public static ResultBean succeed(String message, Object data){
        ResultBean resultBean=new ResultBean();
        resultBean.setCode(CodeEnum.SUCCESS.getCode());
        resultBean.setMessage(message);
        resultBean.setData(data);
        return resultBean;
    }

    public static ResultBean defeated(){
        return defeated(CodeEnum.FAIL.getCode(),CodeEnum.FAIL.getMessage());
    }

    public static ResultBean defeated(String message){
        return defeated(-1,message);
    }
    public static ResultBean defeated(int code){
        String errorMsg="";
        switch (code){
            case -1 :errorMsg="操作失败!"; break;
            case 101 : errorMsg="权限不足"; break;
            case 102 : errorMsg="参数错误!"; break;
            case 103 : errorMsg="请登录!"; break;
            case 104 :  errorMsg="缺少token!"; break;
            case 105 : errorMsg="密码错误!"; break;
            case 106 : errorMsg="账号不存在!"; break;
            case 107 : errorMsg="验证码错误!"; break;
            case 108 : errorMsg="密码与确认密码不一致!"; break;
            case 109 :  errorMsg="该用户已禁用!"; break;
            case 110 :errorMsg="该组织已禁用!"; break;
            case 111 : errorMsg="该角色已禁用!"; break;
            case 112 :  errorMsg="新密码不能与旧密码一样!"; break;
            case 113 :  errorMsg="用户已存在!"; break;
            case 114 : errorMsg="暂无获取到相应数据!"; break;
            case 115 : errorMsg="已过期!"; break;
            case 116 : errorMsg="校验参数无效!"; break;
            case 117 : errorMsg="手机号码格式错误!";break;
            case 601 : errorMsg="已经存在连接该端口的线缆，请不要重复连接！";break;
            case 602 : errorMsg="暂未绑定模型！";break;
            case 603 : errorMsg="不可连接相同设备的不同端口！";break;
            case 604 : errorMsg="编号已存在！";break;
            case 605 : errorMsg="面积不足！";break;
            case 606 : errorMsg="承重不足！";break;
            case 607 : errorMsg="故障告警或智能预警的pointDataId为必填!";break;
            case 608 : errorMsg="工单暂未完成，不能消警!";break;
            default:  errorMsg="操作失败!";
        }

        return defeated(code,errorMsg,null);
    }


    public static ResultBean defeated(int code, String message){
        return defeated(code,message,null);
    }
    public static ResultBean defeated(int code, String message, Object data){
        ResultBean resultBean=new ResultBean();
        resultBean.setCode(code);
        resultBean.setMessage(message);
        resultBean.setData(data);
        return resultBean;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
