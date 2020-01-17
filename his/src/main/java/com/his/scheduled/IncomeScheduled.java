package com.his.scheduled;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.his.service.IncomeService;
@Component
public class IncomeScheduled {
	
	@Autowired
	private IncomeService incomeService;
	@Scheduled(cron = "0 59 23 * * ?")
	protected void executeInternal() {
		System.out.println("当天收入计算中...."); 
		Integer count = incomeService.addIncome();
			if(count>0) {
				System.out.println("当天收入已计算.");
			}else {
				System.out.println("当天收入计算失败!");
			}
	}

}
