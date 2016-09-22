package com.gnahznib.clock;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.gnahznib.countelasticsearch.CountElasticSearch;
import com.gnahznib.countelasticsearch.ReadDisk;
import com.gnahznib.counthbase.CountHBase;
import com.gnahznib.jdbc.ToMySQL;
import com.gnahznib.util.Subduction;
import com.gnahznib.write2file.WriteData2Excel;

public class TestTimer {
    static int count = 0;
    
    public static void showTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
            	Subduction subduction = new Subduction();
            	
            	CountElasticSearch countElasticSearch = new CountElasticSearch();
        		long elasticcSearchIndex = countElasticSearch.countElasticSearch();
        		
        		String elastic = subduction.elastic();
        		// 此处
        		Long elasticcSearchIndexs = null;
        		if(elastic == null){
        			elasticcSearchIndexs = (long) 0;
        		}else{
        			Long oldelastic = new Long(elastic);
        			elasticcSearchIndexs = elasticcSearchIndex - oldelastic;
        		}
        		String elasticcSearchIndexs_str = Long.toString(elasticcSearchIndexs);
        		
        		ReadDisk readDisk = new ReadDisk();
        		Float readdisk = null;
        		
    			try {
					readdisk = readDisk.readDisk();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
    			
    			String disk = subduction.disk();
    			Float olddisk = null;
    			if(disk == null){
    				olddisk = (float) 0;
    			}else{
    				olddisk = new Float(subduction.disk());
    			}
				//if(olddisk == 0)
				Float readdisks_long = readdisk - olddisk;
				String readdisk_str= Float.toString(readdisk);
				String readdisks = Float.toString(readdisks_long);
        		
        		CountHBase countHBase = new CountHBase();
        		long hbaseData = 0;
        		try {
        			hbaseData = countHBase.readerTable();
        		} catch (IOException e) {	
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		
        		String hbase = subduction.hbase();
        		Long oldhbase = null;
        		if(hbase == null){
        			oldhbase = (long) 0;
        		}else{
        			oldhbase = new Long(subduction.hbase());
        		}
        		Long hbases = hbaseData - oldhbase;
        		String hbaseDatas_str = Long.toString(hbases);
        		
        		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        		String time = df.format(new Date());
        		
        		String elasticcSearchIndex_str = Long.toString(elasticcSearchIndex);
        		//Long elasticcSearchIndex_long = new Long(elasticcSearchIndex_str);
        		String hbaseData_str = Long.toString(hbaseData);
        		//Long hbaseData_long = new Long(hbaseData_str);
        		
        		ToMySQL toMySQL = new ToMySQL();
        		try {
        			toMySQL.insert(time, hbaseData_str, hbaseDatas_str, elasticcSearchIndex_str, elasticcSearchIndexs_str, readdisk_str, readdisks);
        		} catch (SQLException e1) {
        			e1.printStackTrace();
        		}
        		
        		WriteData2Excel data2Excel = new WriteData2Excel();
                try {
					data2Excel.writeData2Excel();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        };

        //设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        //定制每天的21:09:00执行，
        calendar.set(year, month, day, 20, 0, 0);
        //calendar.set(year, month, day, 14, 30, 00);
        Date date = calendar.getTime();
        Timer timer = new Timer();
        
        int period = 24 * 60 * 60 * 1000;
        // int period = 24 * 60 * 1000;
        //每天的date时刻执行task，每隔2秒重复执行
        timer.schedule(task, date, period);
        //每天的date时刻执行task, 仅执行一次
        //timer.schedule(task, date);
    }

    public static void main(String[] args){
        showTimer();
        
    }
}