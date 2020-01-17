package com.his.service;

import java.util.List;

import com.his.entity.Nursing;

public interface NursingService {

	/**
	 * 添加护士护理的信息
	 * @param nursing
	 * @return
	 */
	int addNursing(Nursing nursing);
	
	/**
	 * 根据护士id查看全部护理内容
	 * @param nursId
	 * @return
	 */
	List<Nursing> findNursingByNursId(Integer nursId);
	/**
	 * 	按病人id查看护理
	 * @param id
	 * @return
	 */
	List<Nursing> findNursingByPatientId(Integer id);
}
