package com.his.dao;

import java.util.List;

import com.his.entity.Department;

public interface IDepartmentDao {
	
	/**
	 *  查询所有科室信息
	 * @return
	 */
	List<Department> findAllDepartment();
	
	/**
	 *  根据科室id查找科室信息
	 * @param id
	 * @return
	 */
	Department findDepartmentById(int id);
}
