package com.his.entity;

import java.io.Serializable;
import java.util.Date;
/**
 *  病史表
 * @author 杨培源
 *
 */
public class History implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer historyId;	//病史编号

    private String historyInformation;	//病史内容

    private Integer historyPatientId;	//病人编号

    private Integer historyStaffId;	//诊治医生编号

    private Date historyCreatedate;	//诊治时间
    
    //对应关系
    private Staff staff;  //诊治医生对象
    private Patient patient;	//病人对象

    
    public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public String getHistoryInformation() {
        return historyInformation;
    }

    public void setHistoryInformation(String historyInformation) {
        this.historyInformation = historyInformation == null ? null : historyInformation.trim();
    }

    public Integer getHistoryPatientId() {
        return historyPatientId;
    }

    public void setHistoryPatientId(Integer historyPatientId) {
        this.historyPatientId = historyPatientId;
    }

    public Integer getHistoryStaffId() {
        return historyStaffId;
    }

    public void setHistoryStaffId(Integer historyStaffId) {
        this.historyStaffId = historyStaffId;
    }

    public Date getHistoryCreatedate() {
        return historyCreatedate;
    }

    public void setHistoryCreatedate(Date historyCreatedate) {
        this.historyCreatedate = historyCreatedate;
    }
}