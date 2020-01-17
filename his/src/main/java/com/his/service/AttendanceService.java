package com.his.service;

import java.util.List;

import com.his.entity.Attendance;
import com.his.entity.Staff;

public interface AttendanceService {
	/**
	 * 	员工签到
	 * @return
	 */
	int staffSignin(Attendance attendance);
	
	/**
	 *  院长查看员工签到情况
	 * @return
	 */
	List<Staff> checkAttendance();
}
