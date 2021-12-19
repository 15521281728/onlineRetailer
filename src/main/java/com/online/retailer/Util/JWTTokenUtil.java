package com.online.retailer.Util;

import com.online.retailer.model.MyUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import jdk.jshell.spi.ExecutionControl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.*;


@Service
public class JWTTokenUtil {
    public static final String appSecret = "LongGeIsTheMostHandsomeAndKnowledgeableInTheDormitory";
    public static final String subject = "User";
    private static final int expireDay = 7;
    private static final int RegenerateTokenDay = 3;
    //创建token
    public static String createToken(MyUser myUser){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,expireDay);
        String token = Jwts.builder()
                .claim("username",myUser.getUsername())
                .claim("password", DigestUtils.md5DigestAsHex(myUser.getPassword().getBytes()))
                .claim("id",myUser.getId())
                .claim("identityList",myUser.getIdentityList())
//                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, getSecretKey())
                .compact();
        return token;
    }
    //解码token
    public static Claims decodedToken(String token){
        try {
            final Claims claims = Jwts.parser().setSigningKey(appSecret)
                    .parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            System.out.println("检查token时发生的错误"+e);
            return null;
        }
    }

    public static int getUserId(String token){
        return decodedToken(token).get("id", int.class);
    }
    public static int getTokenId(String token){
        return Integer.valueOf(decodedToken(token).getId());
    }
    //是否修改过密码
    public static boolean isUpdatePassword(Claims decodedToken,String nowPassword){
        String oldPassword = decodedToken.get("password",String.class);
        nowPassword = DigestUtils.md5DigestAsHex(nowPassword.getBytes());
        return oldPassword.equals(nowPassword)?false:true;
    }
    //是否需要重新生成token,如果token过期则需要
    public static boolean isNeedRegenerateToken(Claims claims){
        Date timeoutDate = claims.getExpiration();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,expireDay - RegenerateTokenDay);
        return timeoutDate.before(calendar.getTime())?true:false;
    }

    private static byte[] getSecretKey() {
        //log.info("secretKey = " + secretKey);
        if(StringUtils.isBlank(appSecret)) {
            return null;
        }
        return Decoders.BASE64.decode(appSecret);
    }
}
