package com.gnahznib.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class NoticeTask extends TimerTask{

	@Override
	public void run() {
		System.out.println("指定时间点执行" + getCurrentTime());
	}

	private String getCurrentTime() {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");  
	     return sdf.format(new Date()); 
	}

}
