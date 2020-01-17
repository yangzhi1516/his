package com.his.service;

import java.util.List;

import com.his.entity.Work;

public interface WorkService {

	/**
	 * 	根据医生的编号修改工作表
	 * @param staffid
	 * @return
	 */
	int updateWorkById(Work work);
	
	/**
	 * 	查看指定id医生的当日的工作表
	 * @param id
	 * @return
	 */
	Work findTodayWorkById(Integer id);
	
	/**
	 * 	添加工作表
	 * @param work
	 * @return
	 */
	int addWork(Work work);
	
	/**
	 * 	查询医生所有的工作表
	 * @param id
	 * @return
	 */
	List<Work> findWorkById(Integer id);
}
