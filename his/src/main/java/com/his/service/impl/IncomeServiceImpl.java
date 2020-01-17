package com.his.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.IIncomeDao;
import com.his.entity.Income;
import com.his.service.IncomeService;
@Service("incomeService")
public class IncomeServiceImpl implements IncomeService {
	@Autowired
	private IIncomeDao incomedao;
	@Override
	public List<Income> checkIncome() {
		return incomedao.checkIncome();
	}
	@Override
	public int addIncome() {
		return incomedao.addIncome();
	}
	@Override
	public List<Income> checkAll() {
		return incomedao.checkAll();
	}

}
