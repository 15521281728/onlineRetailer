package com.online.retailer.interception;

import com.alibaba.fastjson.JSON;
import com.online.retailer.Util.JWTTokenUtil;
import com.online.retailer.Util.UserInfoUtil;
import com.online.retailer.annotation.CheckToken;
import com.online.retailer.model.MyUser;
import io.jsonwebtoken.Claims;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.xml.transform.Result;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
public class VerifyTokenInterceptor implements HandlerInterceptor {
    @Autowired
    UserInfoUtil userInfoUtil;
    private static int num = 0;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查含有需要检查token的映射
        if (!method.isAnnotationPresent(CheckToken.class)) {
            return true;
        }
        String token = "";
        //获取token
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String value = cookie.getValue();
            if (cookie.getName().equals("myToken")) {
                token = cookie.getValue();
            }

        }
        //检验token是否存在，不存在则请重新登陆
        if (token == null || token == "") {
            result(response, "token过期或失效，请重新登陆");
            return false;
        }
        Claims tokenInfo = JWTTokenUtil.decodedToken(token);
        System.out.println("被启动了 " + num++ + " 次，请求地址为: " + request.getRequestURI());
        //检查是否修改了密码，修改了密码就去重新登陆获取token
        MyUser myUser = userInfoUtil.getUserInfoByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        String nowPassword = myUser.getPassword();
        if (!JWTTokenUtil.isUpdatePassword(tokenInfo, nowPassword)) {
            //如果token仅是需要重新刷新了，则给回一个token
            if (JWTTokenUtil.isNeedRegenerateToken(tokenInfo)) {

                String token1 = JWTTokenUtil.createToken(myUser);
                Cookie cookie = new Cookie("myToken", token1);
                response.addCookie(cookie);
            }
            return true;
        }
        else {
            result(response, "密码已改变，token需要重新获取，请重新登陆，另，正常看不到的吧，你是狗吧");
            return false;
        }
    }

    private void result(HttpServletResponse response, String message) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(200);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(JSON.toJSONString(message));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
        }
    }
}
