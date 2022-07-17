package com.dating.interfaces.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Data
public class UserLoginDTO implements Serializable {
    private static final long serialVersionUID = -2046292514894385730L;

    private String token;
    private Boolean isNew;
}
