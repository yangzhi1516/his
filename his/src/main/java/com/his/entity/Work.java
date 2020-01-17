package com.his.entity;

import java.util.Date;

public class Work {
    private Integer workId;	//工作情况编号

    private Integer workNum;	//诊治人数

    private Integer workStaffId;	//医生编号

    private Long workIncome;	//诊疗费用

    private Date workCreatedate; //当日工作时间

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getWorkNum() {
        return workNum;
    }

    public void setWorkNum(Integer workNum) {
        this.workNum = workNum;
    }

    public Integer getWorkStaffId() {
        return workStaffId;
    }

    public void setWorkStaffId(Integer workStaffId) {
        this.workStaffId = workStaffId;
    }

    public Long getWorkIncome() {
        return workIncome;
    }

    public void setWorkIncome(Long workIncome) {
        this.workIncome = workIncome;
    }

    public Date getWorkCreatedate() {
        return workCreatedate;
    }

    public void setWorkCreatedate(Date workCreatedate) {
        this.workCreatedate = workCreatedate;
    }
}