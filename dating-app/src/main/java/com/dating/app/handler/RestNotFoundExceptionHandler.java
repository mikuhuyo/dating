package com.dating.app.handler;

import com.dating.model.exception.ErrorResult;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@RestController
public class RestNotFoundExceptionHandler implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public ResponseEntity handleError() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResult.notFound());
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
