package com.online.retailer.Filter;

import com.online.retailer.mapper.CustomerMapper;
import com.online.retailer.mapper.ManagerMapper;
import com.online.retailer.model.Identity;
import com.online.retailer.model.MyUser;
import com.online.retailer.Util.JWTTokenUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticateFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final String responseAuthorizationHeader = "Authorization";

    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    ManagerMapper managerMapper;

    public JwtAuthenticateFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);
        return this.authenticationManager.authenticate(token);

    }
/*多增用户请一定要修改这里*/
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        //获取角色
        List<String> collect = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        List<Identity> identityList = new ArrayList<>();
        for (String item : collect){
            JSONObject jsonObject = JSONObject.fromObject(item);
            Identity identity = (Identity) JSONObject.toBean(jsonObject,Identity.class);
            identityList.add(identity);
        }
        //创建token
        MyUser myUser = new MyUser();
        myUser.setUsername(user.getUsername());
        myUser.setIdentityList(identityList);
        int id = -1;
        if (identityList.get(0).getIdentityRole() == "ROLE_customer"){
            id = customerMapper.selectCustomerByAccount(user.getUsername()).getCustomerId();
        }
        else if(identityList.get(0).getIdentityRole() == "ROLE_manager"){
            id = managerMapper.selectManagerByUsername(user.getUsername()).getManagerId();
        }
        myUser.setId(id);
        String token = JWTTokenUtil.createToken(myUser);

        response.setHeader(responseAuthorizationHeader,"Bearer"+token);
    }
}
