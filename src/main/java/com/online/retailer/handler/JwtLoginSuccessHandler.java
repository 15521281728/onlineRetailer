package com.online.retailer.handler;
//import com.alibaba.fastjson.JSON;
//import com.securityjwtdemo.common.Constants;
//import com.securityjwtdemo.common.JsonResult;
//import com.securityjwtdemo.entity.security.JwtUserDetails;
//import com.securityjwtdemo.utils.JwtUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Data: 2019/10/30
 * @Des: 登陆验证成功处理
 */
@Component
public class JwtLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

//    @Autowired
//    WebAsyncManagerIntegrationFilter webAsyncManagerIntegrationFilter;
    private final int expireTime = 60*60*24;
//    private
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
//        RequestCache requestCache = new HttpSessionRequestCache();
//        SavedRequest saveRequest = requestCache.getRequest(request,response);
//        String redirectUrl = saveRequest.getRedirectUrl();

//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String token = JWTTokenService.createToken(myUser, expireTime);
//        Cookie cookie = new Cookie("mytoken",token);
//        response.addCookie(cookie);

        response.sendRedirect("myLoginSuccessHandler");
    }
}