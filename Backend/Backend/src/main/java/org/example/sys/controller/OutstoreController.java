package org.example.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.vo.Result;
import org.example.sys.entity.Outstore;
import org.example.sys.service.IOutstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
@RequestMapping("/outstore")
public class OutstoreController {
    @Autowired
    private IOutstoreService outstoreService;

    @GetMapping("/list")
    public Result<Map<String,Object>> getOutstore(@RequestParam(value = "outId", required = false) String outId,
                                                  @RequestParam(value = "outStoreid", required = false) String outStoreid,
                                                  @RequestParam(value = "pageNo") Long pageNo,
                                                  @RequestParam(value = "pageSize") Long pageSize) {

        LambdaQueryWrapper<Outstore> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(outId), Outstore::getOutId, outId);
        wrapper.eq(StringUtils.hasLength(outStoreid), Outstore::getOutStoreid, outStoreid);

        Page<Outstore> page = new Page<>(pageNo, pageSize);
        outstoreService.page(page, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());

        return Result.success(data);
    }

    @PostMapping
    public Result<?> addOutstore(@RequestBody Outstore outstore) {
        outstoreService.calculateAndUpdateOutCost(outstore);
        outstoreService.save(outstore);
        return Result.success("新增出库信息成功");
    }

    @PutMapping
    public Result<?> updateOutstore(@RequestBody Outstore outstore) {
        outstoreService.updateById(outstore);
        return Result.success("修改出库信息成功");
    }

    @GetMapping("/{outId}")
    public Result<Outstore> getOutstoreById(@PathVariable("outId") String outId) {
        Outstore outstore = outstoreService.getById(outId);
        return Result.success(outstore);
    }

    @DeleteMapping("/{outId}")
    public Result<?> deleteOutstoreById(@PathVariable("outId") String outId) {
        outstoreService.removeById(outId);
        return Result.success("删除出库信息成功");
    }
}
