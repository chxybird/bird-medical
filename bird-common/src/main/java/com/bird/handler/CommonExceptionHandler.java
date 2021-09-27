package com.bird.handler;

import com.bird.entity.CommonResult;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @Author lipu
 * @Date 2021/9/27 16:54
 * @Description 全局异常处理器
 */
@RestControllerAdvice
public class CommonExceptionHandler {

    //表单提交异常
    @ExceptionHandler(BindException.class)
    public CommonResult<String> bindHandler(BindException e) {
        String defaultMessage = e.getAllErrors().get(0).getDefaultMessage();
        return CommonResult.error(defaultMessage);
    }

    //JSON提交异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<String> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return CommonResult.error(defaultMessage);
    }

    //单参提交异常
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResult<String> constraintViolationHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        StringBuilder stringBuilder = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            stringBuilder.append(constraintViolation.getMessage());
        }
        String defaultMessage = stringBuilder.toString();
        return CommonResult.error(defaultMessage);
    }

    /**
     * @Author lipu
     * @Date 2021/9/27 17:56
     * @Description 通用异常
     */
    @ExceptionHandler(Exception.class)
    public CommonResult<String> commonHandler(Exception e) {
        return CommonResult.error(e.getMessage());
    }


}
