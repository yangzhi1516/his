package com.his.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 护士护理表
 * @author 荣泽尚
 *
 */
public class Nursing implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer nursingId;	//护士护理编号

    private String nursingInformation;	//护理内容

    private Integer nursingPatientId;	//病人编号

    private Date nursingCreatedate;	//护理时间
    
    private Integer nursingStaffId;//护士编号
    
    private Patient patient;//病人对象
    
	public Integer getNursingId() {
        return nursingId;
    }

    public void setNursingId(Integer nursingId) {
        this.nursingId = nursingId;
    }

    public String getNursingInformation() {
        return nursingInformation;
    }

    public void setNursingInformation(String nursingInformation) {
        this.nursingInformation = nursingInformation == null ? null : nursingInformation.trim();
    }

    public Integer getNursingPatientId() {
        return nursingPatientId;
    }

    public void setNursingPatientId(Integer nursingPatientId) {
        this.nursingPatientId = nursingPatientId;
    }

    public Date getNursingCreatedate() {
        return nursingCreatedate;
    }

    public void setNursingCreatedate(Date nursingCreatedate) {
        this.nursingCreatedate = nursingCreatedate;
    }

	public Integer getNursingStaffId() {
		return nursingStaffId;
	}

	public void setNursingStaffId(Integer nursingStaffId) {
		this.nursingStaffId = nursingStaffId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
    
}