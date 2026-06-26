package org.example;

import io.jsonwebtoken.Claims;
import org.example.common.utils.JwtUtil;
import org.example.sys.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTest {
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void testCreateJwt(){
        Account account = new Account();
        account.setUsername("admin");
        account.setEmail("12345632484");
        String token = jwtUtil.createToken(account);
        System.out.println(token);
    }

    @Test
    public void testParseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJlZGM2ODQ3NC1jNzMxLTQ1ZTgtOTQ0ZC01ZGEyYWY3MGY1MTEiLCJzdWIiOiJ7XCJlbWFpbFwiOlwiMTIzNDU2MzI0ODRcIixcInVzZXJuYW1lXCI6XCJhZG1pblwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTcxOTI4MDQ0NywiZXhwIjoxNzE5MjgyMjQ3fQ.sIxRm6ZoNHeJh3UQz0RMq5sBgBgaBriwrGEIYQWdjDs";
        Claims claims = jwtUtil.parseToken(token);
        System.out.println(claims);
    }
}
