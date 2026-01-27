package com.Ag.interceptor;

import ch.qos.logback.core.util.StringUtil;
import com.Ag.pojo.JwtUtils;
import com.Ag.pojo.Result;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override // 运行前运行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        log.info("请求的url:{}", url);

        // --- 新增：放行对 /upload/ 路径的请求 (通常用于公开访问的上传文件) ---
        if (url.contains("/upload/")) {
            log.info("请求的是上传文件, 放行: {}", url);
            return true; // 直接放行，不再检查token
        }
        // -------------------------------------------------------------

        if (url.contains("login")) {
            log.info("执行登录操作,放行");
            return true;
        }

        String jwt = request.getHeader("token");
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token信息为空,返回未登记状态.");
            Result error = Result.error("NOT_LOGIN");

            String notlogin = JSON.toJSONString(error);
            response.getWriter().write(notlogin);
            return false;
        }

        try {
            // 3. 解析 JWT（关键）
            Claims claims = JwtUtils.parseJWT(jwt);

            // 4. 取出用户名（JWT 的 subject）
            String username = claims.get("username", String.class);
            String role = (String) claims.get("role");
            long id = claims.get("id", Long.class);

            // 5. 存入 request，供后续使用
            request.setAttribute("username", username);
            request.setAttribute("id", id);
            request.setAttribute("role", role);

            log.info("当前登录用户: {}", username);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败,返回登录状态");
            Result error = Result.error("NOT_LOGIN");

            String notlogin = JSON.toJSONString(error);
            response.getWriter().write(notlogin);
            return false;
        }

        log.info("令牌合法,放行");
        return true;
    }

    // 可选：实现 postHandle 或 afterCompletion 方法
    // @Override
    // public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    //     // 在 Controller 方法执行后，视图渲染前执行
    // }

    // @Override
    // public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    //     // 在整个请求完成后执行
    // }
}