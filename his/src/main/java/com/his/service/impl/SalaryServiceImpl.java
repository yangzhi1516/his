package com.his.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.ISalaryDao;
import com.his.entity.Salary;
import com.his.service.SalaryService;

@Service("salaryService")
public class SalaryServiceImpl implements SalaryService {
	@Autowired
	private ISalaryDao iSalartDao;
	
	public void setiSalartDao(ISalaryDao iSalartDao) {
		this.iSalartDao = iSalartDao;
	}

	@Override
	public int addSalary(Salary salary) {
		// TODO Auto-generated method stub
		return iSalartDao.addSalary(salary);
	}
	//修改工资情况
	@Override
	public int updateSalary() {
		// TODO Auto-generated method stub
		return iSalartDao.updateSalary();
	}
	
	//查看工资表信息
	@Override
	public Salary findSalaryById(Integer id) {
		// TODO Auto-generated method stub
		return iSalartDao.findSalaryById(id);
	}

}
