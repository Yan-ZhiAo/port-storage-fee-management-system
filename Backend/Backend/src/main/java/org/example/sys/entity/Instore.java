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
public class Instore implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("in_id")
    private String inId;

    private String inTime;

    private String inCarid;

    private Double inTon;

    private Integer inCasenum;

    private String inStoreid;

    private Double realTon;

    @TableLogic
    private Boolean inExist;

    private LocalDateTime updateTime;

    private String updateMemeber;

    private Boolean processedForOutstore = false;

    public Boolean getProcessedForOutstore() {
        return processedForOutstore;
    }

    public void setProcessedForOutstore(Boolean processedForOutstore) {
        this.processedForOutstore = processedForOutstore;
    }

    public String getInId() {
        return inId;
    }

    public void setInId(String inId) {
        this.inId = inId;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getInCarid() {
        return inCarid;
    }

    public void setInCarid(String inCarid) {
        this.inCarid = inCarid;
    }

    public Double getInTon() {
        return inTon;
    }

    public void setInTon(Double inTon) {
        this.inTon = inTon;
    }

    public Integer getInCasenum() {
        return inCasenum;
    }

    public void setInCasenum(Integer inCasenum) {
        this.inCasenum = inCasenum;
    }

    public String getInStoreid() {
        return inStoreid;
    }

    public void setInStoreid(String inStoreid) {
        this.inStoreid = inStoreid;
    }

    public Double getRealTon() {
        return realTon;
    }

    public void setRealTon(Double realTon) {
        this.realTon = realTon;
    }

    public Boolean getInExist() {
        return inExist;
    }

    public void setInExist(Boolean inExist) {
        this.inExist = inExist;
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
        return "Instore{" +
            "inId = " + inId +
            ", inTime = " + inTime +
            ", inCarid = " + inCarid +
            ", inTon = " + inTon +
            ", inCasenum = " + inCasenum +
            ", inStoreid = " + inStoreid +
            ", realTon = " + realTon +
            ", inExist = " + inExist +
            ", updateTime = " + updateTime +
            ", updateMemeber = " + updateMemeber +
        "}";
    }
}
