package com.his.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.IAttendanceDao;
import com.his.dao.IStaffDao;
import com.his.entity.Attendance;
import com.his.entity.Staff;
import com.his.service.AttendanceService;

@Service("attendanceService")
public class AttendanceServiceImpl implements AttendanceService{
	
	@Autowired
	private IAttendanceDao iAttendanceDao;	
	
	public void setiAttendanceDao(IAttendanceDao iAttendanceDao) {
		this.iAttendanceDao = iAttendanceDao;
	}
	@Override
	public int staffSignin(Attendance attendance) {
		return iAttendanceDao.staffSignin(attendance);
	}
	@Override
	public List<Staff> checkAttendance() {
		// TODO Auto-generated method stub
		return iAttendanceDao.checkAttendance();
	}

}
