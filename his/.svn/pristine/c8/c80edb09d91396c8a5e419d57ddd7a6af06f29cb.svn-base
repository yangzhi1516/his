package com.his.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 	员工考勤表
 * @author 杨培源
 *
 */
public class Attendance implements Serializable{
    @Override
	public String toString() {
		return "Attendance [attendanceId=" + attendanceId + ", attendanceCreatedate=" + attendanceCreatedate
				+ ", attendanceStaffId=" + attendanceStaffId + ", attendanceDays=" + attendanceDays
				+ ", attendanceState=" + attendanceState + "]";
	}

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private Integer attendanceId;	//考勤编号
	
    private Date attendanceCreatedate;	//签到日期

    private Integer attendanceStaffId;	//签到职员编号

    private Integer attendanceDays;		//签到天数
    
    private Integer attendanceState;  //签到状态

	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attendance(Integer attendanceId, Date attendanceCreatedate, Integer attendanceStaffId,
			Integer attendanceDays, Integer attendanceState) {
		super();
		this.attendanceId = attendanceId;
		this.attendanceCreatedate = attendanceCreatedate;
		this.attendanceStaffId = attendanceStaffId;
		this.attendanceDays = attendanceDays;
		this.attendanceState = attendanceState;
	}

	public Integer getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Integer attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Date getAttendanceCreatedate() {
		return attendanceCreatedate;
	}

	public void setAttendanceCreatedate(Date attendanceCreatedate) {
		this.attendanceCreatedate = attendanceCreatedate;
	}

	public Integer getAttendanceStaffId() {
		return attendanceStaffId;
	}

	public void setAttendanceStaffId(Integer attendanceStaffId) {
		this.attendanceStaffId = attendanceStaffId;
	}

	public Integer getAttendanceDays() {
		return attendanceDays;
	}

	public void setAttendanceDays(Integer attendanceDays) {
		this.attendanceDays = attendanceDays;
	}

	public Integer getAttendanceState() {
		return attendanceState;
	}

	public void setAttendanceState(Integer attendanceState) {
		this.attendanceState = attendanceState;
	}
    
    
}