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
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("store_id")
    private String storeId;

    private Double storeTon;

    private Integer storeCasenum;

    private String storeBelongport;

    private String storeBelongcompany;

    @TableLogic
    private Boolean storeExist;

    private LocalDateTime updateTime;

    private String updateMemeber;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Double getStoreTon() {
        return storeTon;
    }

    public void setStoreTon(Double storeTon) {
        this.storeTon = storeTon;
    }

    public Integer getStoreCasenum() {
        return storeCasenum;
    }

    public void setStoreCasenum(Integer storeCasenum) {
        this.storeCasenum = storeCasenum;
    }

    public String getStoreBelongport() {
        return storeBelongport;
    }

    public void setStoreBelongport(String storeBelongport) {
        this.storeBelongport = storeBelongport;
    }

    public String getStoreBelongcompany() {
        return storeBelongcompany;
    }

    public void setStoreBelongcompany(String storeBelongcompany) {
        this.storeBelongcompany = storeBelongcompany;
    }

    public Boolean getStoreExist() {
        return storeExist;
    }

    public void setStoreExist(Boolean storeExist) {
        this.storeExist = storeExist;
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
        return "Store{" +
            "storeId = " + storeId +
            ", storeTon = " + storeTon +
            ", storeCasenum = " + storeCasenum +
            ", storeBelongport = " + storeBelongport +
            ", storeBelongcompany = " + storeBelongcompany +
            ", storeExist = " + storeExist +
            ", updateTime = " + updateTime +
            ", updateMemeber = " + updateMemeber +
        "}";
    }
}
