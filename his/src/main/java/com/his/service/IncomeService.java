package com.his.service;

import java.util.List;

import com.his.entity.Income;

public interface IncomeService {
	/**
	 * 查询所有收入
	 * @return
	 */
	List<Income> checkIncome();
	
	/**
	 * 	添加当日收入
	 * @param income
	 * @return
	 */
	int addIncome();
	
	/**
	 * 查看历史收入
	 */
	List<Income> checkAll();
}
