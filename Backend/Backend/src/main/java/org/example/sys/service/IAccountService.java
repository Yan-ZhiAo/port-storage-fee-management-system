package org.example.sys.service;

import org.example.sys.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr.wu
 * @since 2024-06-23
 */
public interface IAccountService extends IService<Account> {

    Map<String, Object> login(Account account);

    Map<String, Object> getUsetInfo(String token);

    void logout(String token);
}
