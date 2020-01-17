package com.his.dao;

import java.util.List;

import com.his.entity.Advice;

public interface IAdviceDao {

	/**
	 * 添加医嘱
	 * @param advice
	 * @return
	 */
	int addAdvice(Advice advice);
	
	/**
	 * 护士执行医嘱
	 * @param advice
	 * @return
	 */
	int updateAdviceByNursStaff(Advice advice);
	
	
	/**
	 *  查询所有未执行医嘱的病人
	 * @return
	 */
	List<Advice> findAdviceByState();
}
