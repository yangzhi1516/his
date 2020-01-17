package com.his.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.IAdviceDao;
import com.his.entity.Advice;
import com.his.service.AdviceService;

@Service("adviceService")
public class AdviceServiceImpl implements AdviceService {

	@Autowired
	private IAdviceDao iAdviceDao;
	@Override
	public int addAdvice(Advice advice) {
		// TODO Auto-generated method stub
		return iAdviceDao.addAdvice(advice);
	}

	@Override
	public int updateAdviceByNursStaff(Advice advice) {
		// TODO Auto-generated method stub
		return iAdviceDao.updateAdviceByNursStaff(advice);
	}
	
	public void setiAdviceDao(IAdviceDao iAdviceDao) {
		this.iAdviceDao = iAdviceDao;
	}

	@Override
	public List<Advice> findAdviceByState() {
		// TODO Auto-generated method stub
		return iAdviceDao.findAdviceByState();
	}
}
