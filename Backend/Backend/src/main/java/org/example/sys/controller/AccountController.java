package org.example.sys.controller;

import org.example.common.vo.Result;
import org.example.sys.entity.Account;
import org.example.sys.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mr.wu
 * @since 2024-06-23
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public Result<List<Account>> getAllAccount(){
        List<Account> list = accountService.list();
        return Result.success(list,"查询成功");
    }

    @PostMapping("/login")
    public Result<Map<String,Object>> login (@RequestBody Account account){
        Map<String,Object> data = accountService.login(account);

        if (data != null){
            return Result.success(data);
        }

        return Result.fail(20002, "用户名或密码错误");
    }

    @GetMapping("/info")
    public Result<Map<String,Object>> getAccountInfo(@RequestParam("token") String token){
        Map<String, Object> data = accountService.getUsetInfo(token);

        if (data != null){
            return Result.success(data);
        }

        return Result.fail(20003, "登录信息无效，请重新登录");
    }

    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token){
        accountService.logout(token);
        return Result.success();
    }
}
