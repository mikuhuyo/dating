package com.dating.app.utils;

import com.dating.model.domain.User;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public class ThreadLocalUserUtil {
    private static final ThreadLocal<User> TL = new ThreadLocal<>();


    /**
     * 将用户对象, 存入Threadlocal
     *
     * @param user
     */
    public static void set(User user) {
        TL.set(user);
    }

    /**
     * 从当前线程, 获取用户对象
     *
     * @return
     */
    public static User get() {
        return TL.get();
    }

    /**
     * 从当前线程, 获取用户对象的id
     *
     * @return
     */
    public static Long getUserId() {
        return TL.get().getId();
    }

    /**
     * 从当前线程, 获取用户对象的手机号码
     *
     * @return
     */
    public static String getMobile() {
        return TL.get().getMobile();
    }
    
    public static void removeUser() {
        TL.remove();
    }
}
