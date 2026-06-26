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
public class Outstore implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("out_id")
    private String outId;

    private String outDate;

    private Integer outCasenum;

    private Double outTon;

    private String outStoreid;

    private Double outCost;

    @TableLogic
    private Boolean outExist;

    private LocalDateTime updateTime;

    private String updateMemeber;

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public Integer getOutCasenum() {
        return outCasenum;
    }

    public void setOutCasenum(Integer outCasenum) {
        this.outCasenum = outCasenum;
    }

    public Double getOutTon() {
        return outTon;
    }

    public void setOutTon(Double outTon) {
        this.outTon = outTon;
    }

    public String getOutStoreid() {
        return outStoreid;
    }

    public void setOutStoreid(String outStoreid) {
        this.outStoreid = outStoreid;
    }

    public Double getOutCost() {
        return outCost;
    }

    public void setOutCost(Double outCost) {
        this.outCost = outCost;
    }

    public Boolean getOutExist() {
        return outExist;
    }

    public void setOutExist(Boolean outExist) {
        this.outExist = outExist;
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
        return "Outstore{" +
            "outId = " + outId +
            ", outDate = " + outDate +
            ", outCasenum = " + outCasenum +
            ", outTon = " + outTon +
            ", outStoreid = " + outStoreid +
            ", outCost = " + outCost +
            ", outExist = " + outExist +
            ", updateTime = " + updateTime +
            ", updateMemeber = " + updateMemeber +
        "}";
    }
}
