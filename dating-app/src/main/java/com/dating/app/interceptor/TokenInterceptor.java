package com.dating.app.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.dating.app.utils.ThreadLocalUserUtil;
import com.dating.commons.utils.JwtUtils;
import com.dating.model.domain.User;
import com.dating.model.exception.ErrorResult;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        boolean verifyToken = JwtUtils.verifyToken(token);
        if (!verifyToken) {
            response.setStatus(401);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "application/json");
            response.setContentType("application/json;charset=UTF-8");

            PrintWriter writer = response.getWriter();
            writer.write(JSONObject.toJSONString(ErrorResult.unauthorized()));
            writer.flush();
            writer.close();

            return false;
        }

        Claims claims = JwtUtils.getClaims(token);
        String mobile = (String) claims.get("mobile");
        Integer id = (Integer) claims.get("id");

        User user = new User();
        user.setId(Long.valueOf(id));
        user.setMobile(mobile);

        ThreadLocalUserUtil.set(user);

        return true;
    }
}
