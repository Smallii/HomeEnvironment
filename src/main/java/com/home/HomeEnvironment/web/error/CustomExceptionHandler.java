package com.home.HomeEnvironment.web.error;

import com.home.HomeEnvironment.util.CustomException;
import com.home.HomeEnvironment.util.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 业务异常处理
 * 这里所说的业务异常处理是指处理在业务处理过程中抛出的异常，
 * 比如我们在Controller中抛出异常。
 * 对于这些异常，Spring为我们提供了很好的处理方式，我们可以通过@ControllerAdvice注解定义异常处理类，
 * 配合@EceptionHandler注解定义异常处理方法，来捕获在Controller中抛出的异常
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public Response handleException(CustomException exception) {
        return new Response.Builder().setStatus(500).setMessage(exception.getMessage()).build();
    }
}
