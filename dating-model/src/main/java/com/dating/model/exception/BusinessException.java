package com.dating.model.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -2976178166658701630L;

    private ErrorResult errorResult;

    public BusinessException(ErrorResult errorResult) {
        super(errorResult.getErrMessage());
        this.errorResult = errorResult;
    }
}
