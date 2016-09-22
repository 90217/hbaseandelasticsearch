package com.gnahznib.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class BeforeToday {
	@Test
	public void test01(){
		Date dNow = new Date();   //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
		dBefore = calendar.getTime();   //得到前一天的时间

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd"); //设置时间格式
		String defaultStartDate = sdf.format(dBefore);    //格式化前一天
		
		System.out.println("前一天的时间是：" + defaultStartDate);
	}
}
