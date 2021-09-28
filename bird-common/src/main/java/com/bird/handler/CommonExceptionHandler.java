package com.bird.handler;

import com.bird.entity.CommonResult;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CommonExceptionHandler {

    /**
     * @Author lipu
     * @Date 2021/9/27 18:58
     * @Description JSR303 表单异常
     */
    @ExceptionHandler(BindException.class)
    public CommonResult<String> bindHandler(BindException e) {
        String defaultMessage = e.getAllErrors().get(0).getDefaultMessage();
        return CommonResult.error(defaultMessage);
    }

    /**
     * @Author lipu
     * @Date 2021/9/27 18:58
     * @Description JSR303 请求体Body异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<String> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return CommonResult.error(defaultMessage);
    }

    /**
     * @Author lipu
     * @Date 2021/9/27 18:59
     * @Description JSR303 单参异常
     */
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
        log.error(e.getMessage());
        return CommonResult.error(e.getMessage());
    }


}
