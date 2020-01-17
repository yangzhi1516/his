package com.his.entity;

/**
 * 挂号队列表
 * @author 荣泽尚
 *
 */
public class Queue {
    private Integer queueId;//挂号队列编号

    private Integer queueRregistrationId;//当前就诊的挂号

    private Integer queueDepartmentId;//科室编号
    
    private Registration registration;//挂号单的集合
    
    private Department department;//科室的对象

    public Integer getQueueId() {
        return queueId;
    }

    public void setQueueId(Integer queueId) {
        this.queueId = queueId;
    }

    public Integer getQueueRregistrationId() {
		return queueRregistrationId;
	}

	public void setQueueRregistrationId(Integer queueRregistrationId) {
		this.queueRregistrationId = queueRregistrationId;
	}

    public Integer getQueueDepartmentId() {
        return queueDepartmentId;
    }

    public void setQueueDepartmentId(Integer queueDepartmentId) {
        this.queueDepartmentId = queueDepartmentId;
    }

	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
    
}