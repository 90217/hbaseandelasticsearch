package com.gnahznib.test;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import org.junit.Test;

public class PointTime {
	@Test
	public void pointTime(){
		Timer timer = new Timer();  
        Calendar calendar = Calendar.getInstance();  
        // 指定01:00:00点执行  
        calendar.set(Calendar.HOUR_OF_DAY, 99);  
        calendar.set(Calendar.MINUTE, 40);  
        calendar.set(Calendar.SECOND, 0);  
        Date date = calendar.getTime(); 
        
        
        
       // timer = new Timer();  
        timer.schedule(new NoticeTask(),date);  
	}
	public void schedule(){
		
	}
}
