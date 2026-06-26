package org.example.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.vo.Result;
import org.example.sys.entity.Company;
import org.example.sys.entity.Instore;
import org.example.sys.service.IInstoreService;
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
@RequestMapping("/instore")
public class InstoreController {
    @Autowired
    private IInstoreService instoreService;

    @GetMapping("/list")
    public Result<Map<String,Object>> getInstore(@RequestParam(value = "inId",required = false) String inId,
                                                 @RequestParam(value = "inStoreid",required = false) String inStoreid,
                                                 @RequestParam(value = "pageNo") Long pageNo,
                                                 @RequestParam(value = "pageSize") Long pageSize){

        LambdaQueryWrapper<Instore> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(inId), Instore::getInId, inId);
        wrapper.eq(StringUtils.hasLength(inStoreid), Instore::getInStoreid, inStoreid);

        Page<Instore> page = new Page<>(pageNo, pageSize);
        instoreService.page(page, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());

        return Result.success(data);
    }

    @PostMapping
    public Result<?> addInstore(@RequestBody Instore instore){
        instoreService.save(instore);
        return Result.success("新增入库信息成功");
    }

    @PutMapping
    public Result<?> updateInstore(@RequestBody Instore instore){
        instoreService.updateById(instore);
        return Result.success("修改入库信息成功");
    }

    @GetMapping("/{inId}")
    public Result<Instore> getInstoreById(@PathVariable("inId") String inId){
        Instore instore = instoreService.getById(inId);
        return Result.success(instore);
    }

    @DeleteMapping("/{inId}")
    public Result<Instore> deleteInstoreById(@PathVariable("inId") String inId){
        instoreService.removeById(inId);
        return Result.success("删除入库信息成功");
    }
}
