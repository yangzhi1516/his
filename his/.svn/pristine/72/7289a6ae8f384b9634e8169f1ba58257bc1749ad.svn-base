package com.his.scheduled;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.his.service.IncomeService;
import com.his.service.SalaryService;
@Component
public class SalaryScheduled {
	
	@Autowired
	private SalaryService salaryService;
	@Scheduled(cron = "0 59 23 * * ?")
	protected void executeInternal() {
		System.out.println("当月员工工资计算中...."); 
		Integer count = salaryService.updateSalary();
			if(count>0) {
				System.out.println("当天员工工资已计算.");
			}else {
				System.out.println("当天员工工资计算失败!");
			}
	}

}
