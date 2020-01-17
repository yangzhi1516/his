package com.his.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.IQueueDao;
import com.his.entity.Queue;
import com.his.service.QueueService;

@Service("queueService")
public class QueueServiceImpl implements QueueService{
	
	@Autowired
	private IQueueDao iqueueDao;	

	@Override
	public int addQueue(Queue queue) {
		// TODO Auto-generated method stub
		return iqueueDao.addQueue(queue);
	}

	@Override
	public int findQueueCount() {
		// TODO Auto-generated method stub
		return iqueueDao.findQueueCount();
	}

	@Override
	public int delQueue(Integer regId) {
		// TODO Auto-generated method stub
		return iqueueDao.delQueue(regId);
	}

	@Override
	public List<Queue> findAllByDepId(Integer depId) {
		// TODO Auto-generated method stub
		return iqueueDao.findAllByDepId(depId);
	}
	
	public void setIqueueDao(IQueueDao iqueueDao) {
		this.iqueueDao = iqueueDao;
	}

}
