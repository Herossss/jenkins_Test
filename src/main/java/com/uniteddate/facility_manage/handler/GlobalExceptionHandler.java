package com.uniteddate.facility_manage.handler;

import com.uniteddate.facility_manage.common.bean.ResultBean;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.lang.model.type.UnknownTypeException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.util.MissingResourceException;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author dong <feng.db@uniteddata.com>
 * @Version 1.0.0
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class,Exception.class})
    public ResultBean globalExceptionHandler(Exception e){
        return ResultBean.defeated(e.getMessage());
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, HttpClientErrorException.MethodNotAllowed.class, MethodNotAllowedException.class})
    public ResultBean methodNotSupport(Exception e){
        return ResultBean.defeated("请求方式错误，请检查接口的请求方式,支持GET,PUT,POST,DELETE！");
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResultBean exceptionHandler(Exception e) {
        return ResultBean.defeated("请求资源不存在！");
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResultBean arithmeticExceptionHandler(Exception e) {
        return ResultBean.defeated("出现算数异常啦！");
    }

    @ExceptionHandler(MissingResourceException.class)
    public ResultBean missingResourceExceptionHandler(Exception e) {
        return ResultBean.defeated("出现资源丢失异常啦！");
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public ResultBean classNotFoundExceptionHandler(Exception e) {
        return ResultBean.defeated("出现类无法找到异常啦！");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResultBean nullPointerExceptionHandler(Exception e) {
        return ResultBean.defeated("出现空指针异常啦！");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResultBean illegalArgumentExceptionHandler(Exception e) {
        return ResultBean.defeated("出现非法声明异常啦！");
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResultBean arrayIndexOutOfBoundsExceptionHandler(Exception e) {
        return ResultBean.defeated("出现数组越界异常啦");
    }

    @ExceptionHandler(UnknownTypeException.class)
    public ResultBean unknownTypeExceptionHandler(Exception e) {
        return ResultBean.defeated("出现未知类型异常啦，请检查数据参数类型是否正确！");
    }

    @ExceptionHandler(EOFException.class)
    public  ResultBean eofExceptionHandler(Exception e){
        return ResultBean.defeated("出现文件流未关闭异常！");
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResultBean fileNotFoundException(Exception e){
        return ResultBean.defeated("出现文件未找到异常！");
    }
}

