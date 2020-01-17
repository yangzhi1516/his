package com.his.dao;

import java.util.List;

import com.his.entity.History;

public interface IHistoryDao {
	/**
	 * 	诊治医生添加病史
	 * @return
	 */
	Integer addHistory(History history);
	/**
	 *  其他医生查看病人病史
	 * @param id
	 * @return
	 */
	List<History> findHistoryByPatientId(Integer id);
}
