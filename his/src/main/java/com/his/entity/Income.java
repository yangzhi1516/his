package com.his.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Income implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer incomeId;	//收益编号

    private BigDecimal incomeRegistration;	//挂号收益

    private BigDecimal incomeWork;	//诊疗收益

    private BigDecimal incomeHospitalization;	//住院收益

    private BigDecimal incomeTotal;	//一天总收入
    
    private Date incomeCreatedate;//当天时间
    
    private BigDecimal incomePrescription;//药费收入

    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public BigDecimal getIncomeRegistration() {
        return incomeRegistration;
    }

    public void setIncomeRegistration(BigDecimal incomeRegistration) {
        this.incomeRegistration = incomeRegistration;
    }

    public BigDecimal getIncomeWork() {
        return incomeWork;
    }

    public void setIncomeWork(BigDecimal incomeWork) {
        this.incomeWork = incomeWork;
    }

    public BigDecimal getIncomeHospitalization() {
        return incomeHospitalization;
    }

    public void setIncomeHospitalization(BigDecimal incomeHospitalization) {
        this.incomeHospitalization = incomeHospitalization;
    }

    public BigDecimal getIncomeTotal() {
        return incomeTotal;
    }

    public void setIncomeTotal(BigDecimal incomeTotal) {
        this.incomeTotal = incomeTotal;
    }
    
    public Date getIncomeCreatedate() {
		return incomeCreatedate;
	}

	public void setIncomeCreatedate(Date incomeCreatedate) {
		this.incomeCreatedate = incomeCreatedate;
	}
	
	public BigDecimal getIncomePrescription() {
		return incomePrescription;
	}

	public void setIncomePrescription(BigDecimal incomePrescription) {
		this.incomePrescription = incomePrescription;
	}
}