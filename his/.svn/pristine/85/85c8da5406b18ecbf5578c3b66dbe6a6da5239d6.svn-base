package com.his.service;

import java.util.List;

import com.his.entity.Staff;

public interface StaffService {

	/**
	 * 员工登录
	 * @param staff
	 * @return
	 */
	Staff staffLogin(Staff staff);
	
	/**
	 *  根据员工id查询员工信息
	 * @param id
	 * @return
	 */
	Staff findStaffById(Integer id);
	
	/**
	 *  根据id修改员工信息
	 * @param staff
	 * @return
	 */
	int updateStaffById(Staff staff);
	
	/**
	 * 添加员工信息
	 * @param staff
	 * @return
	 */
	int addStaff(Staff staff);
	
	/**
	 * 添加员工的薪水
	 * @param staff
	 * @return
	 */
	int updateStaff(Staff staff);
	
	/**
	 * 	查找所有员工当日工作情况
	 * @return
	 */
	List<Staff> findStaffWork();
}
