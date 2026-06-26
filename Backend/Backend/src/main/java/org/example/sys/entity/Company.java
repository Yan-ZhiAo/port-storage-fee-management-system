package org.example.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr.wu
 * @since 2024-06-23
 */
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("company_id")
    private String companyId;

    private String companyName;

    private String companyBelong;

    private String companyPrincipal;

    private String companyPhone;

    private Double companyCost;

    @TableLogic
    private Boolean companyExist;

    private LocalDateTime updateTime;

    private String updateMemeber;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyBelong() {
        return companyBelong;
    }

    public void setCompanyBelong(String companyBelong) {
        this.companyBelong = companyBelong;
    }

    public String getCompanyPrincipal() {
        return companyPrincipal;
    }

    public void setCompanyPrincipal(String companyPrincipal) {
        this.companyPrincipal = companyPrincipal;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public Double getCompanyCost() {
        return companyCost;
    }

    public void setCompanyCost(Double companyCost) {
        this.companyCost = companyCost;
    }

    public Boolean getCompanyExist() {
        return companyExist;
    }

    public void setCompanyExist(Boolean companyExist) {
        this.companyExist = companyExist;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateMemeber() {
        return updateMemeber;
    }

    public void setUpdateMemeber(String updateMemeber) {
        this.updateMemeber = updateMemeber;
    }

    @Override
    public String toString() {
        return "Company{" +
            "companyId = " + companyId +
            ", companyName = " + companyName +
            ", companyBelong = " + companyBelong +
            ", companyPrincipal = " + companyPrincipal +
            ", companyPhone = " + companyPhone +
            ", companyCost = " + companyCost +
            ", companyExist = " + companyExist +
            ", updateTime = " + updateTime +
            ", updateMemeber = " + updateMemeber +
        "}";
    }
}
