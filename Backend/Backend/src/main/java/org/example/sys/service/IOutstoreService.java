package org.example.sys.service;

import org.example.sys.entity.Outstore;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr.wu
 * @since 2024-06-23
 */
public interface IOutstoreService extends IService<Outstore> {

    void calculateAndUpdateOutCost(Outstore outstore);
}
