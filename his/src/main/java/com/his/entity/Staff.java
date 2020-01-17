package com.his.entity;

import java.util.Date;
/**
 * 职员表
 * @author 荣泽尚
 *
 */
public class Staff {
	
    @Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", staffCode=" + staffCode + ", staffPassword=" + staffPassword
				+ ", staffName=" + staffName + ", staffSex=" + staffSex + ", staffAge=" + staffAge + ", staffTelephone="
				+ staffTelephone + ", staffDays=" + staffDays + ", staffDepartmentId=" + staffDepartmentId
				+ ", staffRoleId=" + staffRoleId + ", staffSalaryId=" + staffSalaryId + ", staffAttendanceId="
				+ staffAttendanceId + ", staffWorkId=" + staffWorkId + ", staffModifydate=" + staffModifydate
				+ ", staffCreatedate=" + staffCreatedate + ", staffLevelId=" + staffLevelId + ", staffCost=" + staffCost
				+ ", staffState=" + staffState + ", attendance=" + attendance + "]";
	}

	private Integer staffId;//职工编号

    private String staffCode;//职工工号(用于登录)

    private String staffPassword;//登录的密码

    private String staffName;//职工姓名

    private String staffSex;//职工性别

    private Integer staffAge;//职工年龄

    private String staffTelephone;//职工电话

    private Integer staffDays;//签到天数

    private Integer staffDepartmentId;//所属科室

    private Integer staffRoleId;//职位

    private Integer staffSalaryId;//职工薪水(实发工资由签到情况决定)

    private Integer staffAttendanceId;//出勤情况

    private Integer staffWorkId;//工作情况

    private Date staffModifydate;//修改时间

    private Date staffCreatedate;//创建时间

    private Integer staffLevelId;//文化水平

    private Long staffCost;//医生诊疗费

    private Integer staffState;//是否在职
    
    private Attendance attendance;	//签到表
    
    private Work work;	//工作表
    
    
    
    public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}

	public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword == null ? null : staffPassword.trim();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public String getStaffSex() {
        return staffSex;
    }

    public void setStaffSex(String staffSex) {
        this.staffSex = staffSex == null ? null : staffSex.trim();
    }

    public Integer getStaffAge() {
        return staffAge;
    }

    public void setStaffAge(Integer staffAge) {
        this.staffAge = staffAge;
    }

    public String getStaffTelephone() {
        return staffTelephone;
    }

    public void setStaffTelephone(String staffTelephone) {
        this.staffTelephone = staffTelephone == null ? null : staffTelephone.trim();
    }

    public Integer getStaffDays() {
        return staffDays;
    }

    public void setStaffDays(Integer staffDays) {
        this.staffDays = staffDays;
    }

    public Integer getStaffDepartmentId() {
        return staffDepartmentId;
    }

    public void setStaffDepartmentId(Integer staffDepartmentId) {
        this.staffDepartmentId = staffDepartmentId;
    }

    public Integer getStaffRoleId() {
        return staffRoleId;
    }

    public void setStaffRoleId(Integer staffRoleId) {
        this.staffRoleId = staffRoleId;
    }

    public Integer getStaffSalaryId() {
		return staffSalaryId;
	}

	public void setStaffSalaryId(Integer staffSalaryId) {
		this.staffSalaryId = staffSalaryId;
	}

	public Integer getStaffAttendanceId() {
        return staffAttendanceId;
    }

    public void setStaffAttendanceId(Integer staffAttendanceId) {
        this.staffAttendanceId = staffAttendanceId;
    }

    public Integer getStaffWorkId() {
        return staffWorkId;
    }

    public void setStaffWorkId(Integer staffWorkId) {
        this.staffWorkId = staffWorkId;
    }

    public Date getStaffModifydate() {
        return staffModifydate;
    }

    public void setStaffModifydate(Date staffModifydate) {
        this.staffModifydate = staffModifydate;
    }

    public Date getStaffCreatedate() {
        return staffCreatedate;
    }

    public void setStaffCreatedate(Date staffCreatedate) {
        this.staffCreatedate = staffCreatedate;
    }

    public Integer getStaffLevelId() {
        return staffLevelId;
    }

    public void setStaffLevelId(Integer staffLevelId) {
        this.staffLevelId = staffLevelId;
    }

    public Long getStaffCost() {
        return staffCost;
    }

    public void setStaffCost(Long staffCost) {
        this.staffCost = staffCost;
    }

    public Integer getStaffState() {
        return staffState;
    }

    public void setStaffState(Integer staffState) {
        this.staffState = staffState;
    }
}