package com.gnahznib.util;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.gnahznib.jdbc.ToMySQL;

public class Subduction {
	public String hbase(){
		Date dNow = new Date();   //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
		dBefore = calendar.getTime();   //得到前一天的时间

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd"); //设置时间格式
		String defaultStartDate = sdf.format(dBefore);    //格式化前一天
		ToMySQL toMySQL = new ToMySQL();
		String hbase_value =null;
		try {
			hbase_value = toMySQL.select("HBase", defaultStartDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hbase_value;
	}
	
	public String elastic(){
		Date dNow = new Date();   //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
		dBefore = calendar.getTime();   //得到前一天的时间

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd"); //设置时间格式
		String defaultStartDate = sdf.format(dBefore);    //格式化前一天
		ToMySQL toMySQL = new ToMySQL();
		String elastic_value =null;
		try {
			elastic_value = toMySQL.select("ElasticSearch", defaultStartDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return elastic_value;
	}
	
	public String disk(){
		Date dNow = new Date();   //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
		dBefore = calendar.getTime();   //得到前一天的时间

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd"); //设置时间格式
		String defaultStartDate = sdf.format(dBefore);    //格式化前一天
		ToMySQL toMySQL = new ToMySQL();
		String disk_value =null;
		try {
			disk_value = toMySQL.select("ElasticDisk", defaultStartDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return disk_value;
	}
}
