package com.his.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.IStaffDao;
import com.his.entity.Staff;
import com.his.service.StaffService;

@Service("staffService")
public class StaffServiceImpl implements StaffService {

	@Autowired
	private IStaffDao iStaffDao;
	
	@Override
	public Staff staffLogin(Staff staff) {
		// TODO Auto-generated method stub
		return iStaffDao.staffLogin(staff);
	}
	
	public void setiStaffDao(IStaffDao iStaffDao) {
		this.iStaffDao = iStaffDao;
	}

	@Override
	public Staff findStaffById(Integer id) {
		// TODO Auto-generated method stub
		return iStaffDao.findStaffById(id);
	}

	@Override
	public int updateStaffById(Staff staff) {
		// TODO Auto-generated method stub
		return iStaffDao.updateStaffById(staff);
	}

	@Override
	public int addStaff(Staff staff) {
		// TODO Auto-generated method stub
		return iStaffDao.addStaff(staff);
	}

	@Override
	public int updateStaff(Staff staff) {
		// TODO Auto-generated method stub
		return iStaffDao.updateStaff(staff);
	}

	@Override
	public List<Staff> findStaffWork() {
		// TODO Auto-generated method stub
		return iStaffDao.findStaffWork();
	}

}
