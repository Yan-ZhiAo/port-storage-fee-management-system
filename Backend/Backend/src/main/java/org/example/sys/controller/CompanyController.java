package org.example.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.vo.Result;
import org.example.sys.entity.Company;
import org.example.sys.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private ICompanyService companyService;

    @GetMapping("/list")
    public Result<Map<String,Object>> getCompany(@RequestParam(value = "companyName",required = false) String companyName,
                                                 @RequestParam(value = "companyBelong",required = false) String companyBelong,
                                                 @RequestParam(value = "pageNo") Long pageNo,
                                                 @RequestParam(value = "pageSize") Long pageSize){

        LambdaQueryWrapper<Company> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(companyName), Company::getCompanyName, companyName);
        wrapper.eq(StringUtils.hasLength(companyBelong), Company::getCompanyBelong, companyBelong);

        Page<Company> page = new Page<>(pageNo, pageSize);
        companyService.page(page, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());

        return Result.success(data);
    }

    @PostMapping
    public Result<?> addCompany(@RequestBody Company company){
        companyService.save(company);
        return Result.success("新增公司信息成功");
    }

    @PutMapping
    public Result<?> updateCompany(@RequestBody Company company){
        companyService.updateById(company);
        return Result.success("修改公司信息成功");
    }

    @GetMapping("/{companyId}")
    public Result<Company> getCompanyById(@PathVariable("companyId") String companyId){
        Company company = companyService.getById(companyId);
        return Result.success(company);
    }

    @DeleteMapping("/{companyId}")
    public Result<Company> deleteCompanyById(@PathVariable("companyId") String companyId){
        companyService.removeById(companyId);
        return Result.success("删除公司信息成功");
    }
}
