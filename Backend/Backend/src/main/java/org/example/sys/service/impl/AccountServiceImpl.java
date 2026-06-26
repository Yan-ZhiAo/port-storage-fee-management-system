package org.example.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.common.utils.JwtUtil;
import org.example.sys.entity.Account;
import org.example.sys.mapper.AccountMapper;
import org.example.sys.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.wu
 * @since 2024-06-23
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Map<String, Object> login(Account account) {
        LambdaQueryWrapper<Account> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Account::getUsername, account.getUsername());
        Account loginAccount = this.baseMapper.selectOne(wrapper);

        if (loginAccount != null && passwordEncoder.matches(account.getPassword(), passwordEncoder.encode(loginAccount.getPassword()))) {
            //String key = "account" + UUID.randomUUID();

            loginAccount.setPassword(null);
            //redisTemplate.opsForValue().set(key, loginAccount, 30, TimeUnit.MINUTES);

            String token = jwtUtil.createToken(loginAccount);

            Map<String, Object> data = new HashMap<>();
            data.put("token", token);

            return data;
        }

        return null;
    }

    @Override
    public Map<String, Object> getUsetInfo(String token) {
        //Object obj = redisTemplate.opsForValue().get(token);
        Account loginAccount = null;

        try {
            loginAccount = jwtUtil.parseToken(token, Account.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (loginAccount != null) {
            //Account loginAccount = JSON.parseObject(JSON.toJSONString(obj), Account.class);
            Map<String, Object> data = new HashMap<>();
            data.put("username", loginAccount.getUsername());

            return data;
        }

        return null;
    }

    @Override
    public void logout(String token) {
        //redisTemplate.delete(token);
    }
}
