package com.his.service;

import java.util.List;

import com.his.entity.Queue;

public interface QueueService {
	
	/**
	 * 添加挂号队列信息
	 * @param queue
	 * @return
	 */
	int addQueue(Queue queue);
	
	/**
	 * 查询挂号队列中有多少条数据
	 * @return
	 */
	int findQueueCount();
	
	/**
	 * 根据挂号单编号删除挂号队列
	 * @param regId
	 * @return
	 */
	int delQueue(Integer regId);
	
	/**
	 * 根据科室编号查询所有挂号单
	 * @param depId
	 * @return
	 */
	List<Queue> findAllByDepId(Integer depId);
}
