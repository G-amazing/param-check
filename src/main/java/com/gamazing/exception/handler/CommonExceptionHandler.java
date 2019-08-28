package com.gamazing.exception.handler;

import com.gamazing.exception.CommonException;
import com.gamazing.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler({CommonException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response commonException(final CommonException e) {
        return Response.fail(e.getStatus(), e.getMessage());
    }

}
