package com.his.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.IWorkDao;
import com.his.entity.Work;
import com.his.service.WorkService;
@Service("workService")
public class WorkServiceImpl implements WorkService {
	@Autowired
	private IWorkDao iWorkDao;
	
	public void setiWorkDao(IWorkDao iWorkDao) {
		this.iWorkDao = iWorkDao;
	}

	@Override
	public int updateWorkById(Work work) {
		// TODO Auto-generated method stub
		return iWorkDao.updateWorkById(work);
	}

	@Override
	public Work findTodayWorkById(Integer id) {
		// TODO Auto-generated method stub
		return iWorkDao.findTodayWorkById(id);
	}

	@Override
	public int addWork(Work work) {
		// TODO Auto-generated method stub
		return iWorkDao.addWork(work);
	}

	@Override
	public List<Work> findWorkById(Integer id) {
		// TODO Auto-generated method stub
		return iWorkDao.findWorkById(id);
	}
}
