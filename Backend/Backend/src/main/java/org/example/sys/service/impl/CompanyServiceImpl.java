package org.example.sys.service.impl;

import org.example.sys.entity.Company;
import org.example.sys.mapper.CompanyMapper;
import org.example.sys.service.ICompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.wu
 * @since 2024-06-23
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

}
