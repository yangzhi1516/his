package com.his.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 医嘱表
 * @author 荣泽尚
 *
 */
public class Advice implements Serializable{
	
	@Override
	public String toString() {
		return "Advice [adviceId=" + adviceId + ", adviceCreatedate=" + adviceCreatedate + ", advicePatientId="
				+ advicePatientId + ", adviceStaffId=" + adviceStaffId + ", adviceNurseId=" + adviceNurseId
				+ ", adviceState=" + adviceState + ", adviceModifydate=" + adviceModifydate + ", adviceInformation="
				+ adviceInformation + "]";
	}

	private static final long serialVersionUID = 1L;
	
	private Integer adviceId;  //医嘱编号

    private Date adviceCreatedate;		//创建日期	

    private Integer advicePatientId;	//病人编号

    private Integer adviceStaffId;		//写医嘱的医生编号

    private Integer adviceNurseId;		//执行医嘱的护士编号

    private Integer adviceState;		//是否已经执行医嘱

    private Date adviceModifydate;		//执行时间

    private String adviceInformation;	//医嘱内容
    
    private Patient patient;	//病人对象
    
    

    public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Integer getAdviceId() {
        return adviceId;
    }

    public void setAdviceId(Integer adviceId) {
        this.adviceId = adviceId;
    }

    public Date getAdviceCreatedate() {
        return adviceCreatedate;
    }

    public void setAdviceCreatedate(Date adviceCreatedate) {
        this.adviceCreatedate = adviceCreatedate;
    }

    public Integer getAdvicePatientId() {
        return advicePatientId;
    }

    public void setAdvicePatientId(Integer advicePatientId) {
        this.advicePatientId = advicePatientId;
    }

    public Integer getAdviceStaffId() {
        return adviceStaffId;
    }

    public void setAdviceStaffId(Integer adviceStaffId) {
        this.adviceStaffId = adviceStaffId;
    }

    public Integer getAdviceNurseId() {
        return adviceNurseId;
    }

    public void setAdviceNurseId(Integer adviceNurseId) {
        this.adviceNurseId = adviceNurseId;
    }

    public Integer getAdviceState() {
        return adviceState;
    }

    public void setAdviceState(Integer adviceState) {
        this.adviceState = adviceState;
    }

    public Date getAdviceModifydate() {
        return adviceModifydate;
    }

    public void setAdviceModifydate(Date adviceModifydate) {
        this.adviceModifydate = adviceModifydate;
    }

    public String getAdviceInformation() {
        return adviceInformation;
    }

    public void setAdviceInformation(String adviceInformation) {
        this.adviceInformation = adviceInformation == null ? null : adviceInformation.trim();
    }
}