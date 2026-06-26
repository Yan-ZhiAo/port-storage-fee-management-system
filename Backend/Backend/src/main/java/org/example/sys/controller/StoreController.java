package org.example.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.vo.Result;
import org.example.sys.entity.Company;
import org.example.sys.entity.Store;
import org.example.sys.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mr.wu
 * @since 2024-06-23
 */
@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private IStoreService storeService;

    @GetMapping("/list")
    public Result<Map<String,Object>> getStore(@RequestParam(value = "storeId",required = false) String storeId,
                                               @RequestParam(value = "storeBelongcompany",required = false) String storeBelongcompany,
                                               @RequestParam(value = "pageNo") Long pageNo,
                                               @RequestParam(value = "pageSize") Long pageSize){

        LambdaQueryWrapper<Store> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(storeId), Store::getStoreId, storeId);
        wrapper.eq(StringUtils.hasLength(storeBelongcompany), Store::getStoreBelongcompany, storeBelongcompany);

        Page<Store> page = new Page<>(pageNo, pageSize);
        storeService.page(page, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());

        return Result.success(data);
    }

    @PostMapping
    public Result<?> addStore(@RequestBody Store store){
        storeService.save(store);
        return Result.success("新增仓库信息成功");
    }

    @PutMapping
    public Result<?> updateStore(@RequestBody Store store){
        storeService.updateById(store);
        return Result.success("修改仓库信息成功");
    }

    @GetMapping("/{storeId}")
    public Result<Store> getStoreById(@PathVariable("storeId") String storeId){
        Store store = storeService.getById(storeId);
        return Result.success(store);
    }

    @DeleteMapping("/{storeId}")
    public Result<Store> deleteStoreById(@PathVariable("storeId") String storeId){
        storeService.removeById(storeId);
        return Result.success("删除仓库信息成功");
    }
}
