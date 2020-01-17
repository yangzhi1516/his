package com.his.service;

import com.his.entity.Salary;

public interface SalaryService {

	/**
	 *  添加员工工资情况
	 * @param salary
	 * @return
	 */
	int addSalary(Salary salary);
	
	/**
	 *  修改工资情况
	 * @param id
	 * @return
	 */
	int updateSalary();
	
	/**
	 *  查询工资表
	 * @param id
	 * @return
	 */
	Salary findSalaryById(Integer id);
}
