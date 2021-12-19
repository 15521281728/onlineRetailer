package com.online.retailer.Filter;

import com.online.retailer.mapper.CustomerMapper;
import com.online.retailer.mapper.IdentityMapper;
import com.online.retailer.mapper.ManagerMapper;
import com.online.retailer.model.Customer;
import com.online.retailer.model.Identity;
import com.online.retailer.model.Manager;
import com.online.retailer.model.MyUser;
import com.online.retailer.Util.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JWTTokenUtil tokenManager;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private IdentityMapper identityMapper;

    private MyUser myUser;
    public TokenLoginFilter(AuthenticationManager authenticationManager, JWTTokenUtil tokenManager) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login")); // 定义登录请求接口
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException {
        String username = httpServletRequest.getParameter("username");
        myUser = getUserInfoByUsername(username);
        List<Identity> identityList = myUser.getIdentityList();
        if (identityList == null){
            identityList = identityMapper.findManagerIdentityByAccount(username);
        }
//        identityList.add("customer");
        List<SimpleGrantedAuthority> collect = identityList.stream()
                .map(identityRose -> new SimpleGrantedAuthority(identityRose.getIdentityRole()))
                .collect(Collectors.toList());

        User user = new User(myUser.getUsername(),myUser.getPassword(),collect);
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
    }

    /**
     * 认证成功
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//        MyUser myUser = new MyUser();
//        myUser.setUsername(httpServletRequest.getParameter("username"));
//        myUser.setIdentityList(authentication.getAuthorities());
        String token = JWTTokenUtil.createToken(myUser);
        httpServletResponse.getWriter().write(token);
    }

    /**
     * 认证失败
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest httpServletRequestuest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.getWriter().write("认证失败");
    }
    /*请一定要保证username的唯一性！同时新增用户时请增加这里*/
    private MyUser getUserInfoByUsername(String username){
        MyUser user = new MyUser();

        Customer customer = customerMapper.selectCustomerByAccount(username);
        List<Identity> identityList;
        if (customer != null){
            user.setUsername(customer.getCustomerAccount());
            user.setPassword(customer.getCustomerPassword());
            user.setName(customer.getCustomerName());
            identityList = identityMapper.findCustomerIdentityByAccount(username);
            user.setIdentityList(identityList);
        }
        Manager manager = managerMapper.selectManagerByUsername(username);
        if (manager != null){
            user.setUsername(manager.getManagerUsername());
            user.setPassword(manager.getManagerPassword());
            user.setName(manager.getManagerName());
            identityList = identityMapper.findManagerIdentityByAccount(username);
            user.setIdentityList(identityList);
        }
        return user;
    }
}
