package com.dating.app.handler;

import com.dating.model.exception.BusinessException;
import com.dating.model.exception.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity processException(Exception e) {
        // log.error(e.getMessage(), e);

        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            if (businessException.getErrorResult() != null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(businessException.getErrorResult());
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResult.error());
    }
}
