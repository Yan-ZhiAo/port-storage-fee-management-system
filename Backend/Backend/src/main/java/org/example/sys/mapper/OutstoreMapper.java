package org.example.sys.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.sys.entity.Instore;
import org.example.sys.entity.Outstore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr.wu
 * @since 2024-06-23
 */
public interface OutstoreMapper extends BaseMapper<Outstore> {
    @Select("SELECT * FROM instore WHERE in_storeid = #{out_storeid}")
    List<Instore> findByOutStoreId(String outStoreId);
}
