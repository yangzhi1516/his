package com.his.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 住院信息表
 * @author 荣泽尚
 *
 */
public class Hospitalization implements Serializable{
	@Override
	public String toString() {
		return "Hospitalization [hospitalizationId=" + hospitalizationId + ", hospitalizationDays="
				+ hospitalizationDays + ", hospitalizationPatientId=" + hospitalizationPatientId
				+ ", hospitalizationStaffId=" + hospitalizationStaffId + ", hospitalizationCost=" + hospitalizationCost
				+ ", hospitalizationPay=" + hospitalizationPay + ", hospitalizationRest=" + hospitalizationRest
				+ ", hospitalizationCostState=" + hospitalizationCostState + ", hospitalizationState="
				+ hospitalizationState + ", hospitalizationExitdate=" + hospitalizationExitdate
				+ ", hospitalizationRoomId=" + hospitalizationRoomId + ", hospitalizationReason="
				+ hospitalizationReason + ", hospitalizationCreatedate=" + hospitalizationCreatedate
				+ ", hospitalizationIndate=" + hospitalizationIndate + ", room=" + room + ", staff=" + staff
				+ ", patient=" + patient + "]";
	}

	private static final long serialVersionUID = 1L;

	private Integer hospitalizationId;	//入院编号

    private Integer hospitalizationDays;	//住院天数

    private Integer hospitalizationPatientId;	//住院病人编号

    private Integer hospitalizationStaffId;	//治疗病人的住院医生编号

    private BigDecimal hospitalizationCost;	//总的住院费用

    private BigDecimal hospitalizationPay;	//已缴纳费用

    private BigDecimal hospitalizationRest;	//仍未缴纳的费用

    private Integer hospitalizationCostState;	//是否缴纳所有住院费

    private Integer hospitalizationState;	//是否出院

    private Date hospitalizationExitdate;	//出院时间

    private Integer hospitalizationRoomId;	//病人所在房间
    
    private String hospitalizationReason; 	//病人申请住院的原因
    
    private Date hospitalizationCreatedate;	//病人申请住院的时间
    
    private Date hospitalizationIndate;//入院的时间
    
    private Room room;//房间对象
    
    private Staff staff;//员工对象
    
    private Patient patient;//病人对象
    
    

    public Date getHospitalizationIndate() {
		return hospitalizationIndate;
	}

	public void setHospitalizationIndate(Date hospitalizationIndate) {
		this.hospitalizationIndate = hospitalizationIndate;
	}

	public Date getHospitalizationCreatedate() {
		return hospitalizationCreatedate;
	}

	public void setHospitalizationCreatedate(Date hospitalizationCreatedate) {
		this.hospitalizationCreatedate = hospitalizationCreatedate;
	}

	public String getHospitalizationReason() {
		return hospitalizationReason;
	}

	public void setHospitalizationReason(String hospitalizationReason) {
		this.hospitalizationReason = hospitalizationReason;
	}

	public Integer getHospitalizationId() {
        return hospitalizationId;
    }

    public void setHospitalizationId(Integer hospitalizationId) {
        this.hospitalizationId = hospitalizationId;
    }

    public Integer getHospitalizationDays() {
        return hospitalizationDays;
    }

    public void setHospitalizationDays(Integer hospitalizationDays) {
        this.hospitalizationDays = hospitalizationDays;
    }

    public Integer getHospitalizationPatientId() {
        return hospitalizationPatientId;
    }

    public void setHospitalizationPatientId(Integer hospitalizationPatientId) {
        this.hospitalizationPatientId = hospitalizationPatientId;
    }

    public Integer getHospitalizationStaffId() {
        return hospitalizationStaffId;
    }

    public void setHospitalizationStaffId(Integer hospitalizationStaffId) {
        this.hospitalizationStaffId = hospitalizationStaffId;
    }

    public BigDecimal getHospitalizationCost() {
        return hospitalizationCost;
    }

    public void setHospitalizationCost(BigDecimal hospitalizationCost) {
        this.hospitalizationCost = hospitalizationCost;
    }

    public BigDecimal getHospitalizationPay() {
        return hospitalizationPay;
    }

    public void setHospitalizationPay(BigDecimal hospitalizationPay) {
        this.hospitalizationPay = hospitalizationPay;
    }

    public BigDecimal getHospitalizationRest() {
        return hospitalizationRest;
    }

    public void setHospitalizationRest(BigDecimal hospitalizationRest) {
        this.hospitalizationRest = hospitalizationRest;
    }

    

    public Integer getHospitalizationCostState() {
		return hospitalizationCostState;
	}

	public void setHospitalizationCostState(Integer hospitalizationCostState) {
		this.hospitalizationCostState = hospitalizationCostState;
	}

	public Integer getHospitalizationState() {
        return hospitalizationState;
    }

    public void setHospitalizationState(Integer hospitalizationState) {
        this.hospitalizationState = hospitalizationState;
    }

    public Date getHospitalizationExitdate() {
        return hospitalizationExitdate;
    }

    public void setHospitalizationExitdate(Date hospitalizationExitdate) {
        this.hospitalizationExitdate = hospitalizationExitdate;
    }

    public Integer getHospitalizationRoomId() {
        return hospitalizationRoomId;
    }

    public void setHospitalizationRoomId(Integer hospitalizationRoomId) {
        this.hospitalizationRoomId = hospitalizationRoomId;
    }

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

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
    
}