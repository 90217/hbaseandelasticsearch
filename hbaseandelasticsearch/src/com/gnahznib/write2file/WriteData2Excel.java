package com.gnahznib.write2file;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.gnahznib.jdbc.ToMySQL;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class WriteData2Excel {
	// 新建指定名称的Exel表格
	public static WritableWorkbook book = null;

	//private static int count = 0;
	// 获取sheet
	private static WritableSheet sheet = null;
	private static int y = 0;
	private static Label label1 = null;
	private static Label label2 = null;
	private static Label label3 = null;
	private static Label label4 = null;
	private static Label label5 = null;
	private static Label label6 = null;
	private static Label label7 = null;
	private static Label label8 = null;
	private static Label label9 = null;
	private static Label label10 = null;
	private static Label label11 = null;
	private static Label label12 = null;
	private static Label label13 = null;
	private static Label label14 = null;

	private static WriteData2Excel writeData2Excel;
	// private static FileOutputStream fos = null;
	static {
		try {
			if (book == null) {
				book = Workbook.createWorkbook(new File("d:/数据增长.xls"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 创建sheet
		book.createSheet("统计信息", 0);
		// 写入数据Label(列, 行, "写入数据"), 完成表头
		sheet = book.getSheet(0);
		label1 = new Label(0, 0, "日期");
		label2 = new Label(1, 0, "HBase数据（条）");
		label3 = new Label(2, 0, "HBase数据增长量（条）");
		label4 = new Label(3, 0, "ES数据（条）");
		label5 = new Label(4, 0, "ES数据增长量（条）");
		label6 = new Label(5, 0, "ES磁盘（GB）");
		label7 = new Label(6, 0, "ES磁盘增长量（GB）");
		try {
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			sheet.addCell(label5);
			sheet.addCell(label6);
			sheet.addCell(label7);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeData2Excel() throws Exception{
		ToMySQL toMySQL = new ToMySQL();
		JSONArray readMySQL = null;
		try {
			readMySQL = toMySQL.readMySQL();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//int counts = readMySQL.length() + 1;
		if(readMySQL.length()>0){
			  for(int i=0;i<readMySQL.length();i++){
			    JSONObject json = readMySQL.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
			    /*System.out.println(json.get("time")) ;  // 得到 每个对象中的属性值
			    System.out.println(json.get("HBase")) ;  // 得到 每个对象中的属性值
			    System.out.println(json.get("HBases")) ;  // 得到 每个对象中的属性值
			    System.out.println(json.get("ElasticSearch")) ;  // 得到 每个对象中的属性值
			    System.out.println(json.get("ElasticDisk")) ;  // 得到 每个对象中的属性值
			    System.out.println(json.get("ElasticDisks")) ;  // 得到 每个对象中的属性值
			    */
			    label8 = new Label(0, i + 1, json.get("time").toString());
				label9 = new Label(1, i + 1,json.get("HBase").toString());
				label10 = new Label(2, i + 1, json.get("HBases").toString());
				label11 = new Label(3, i + 1, json.get("ElasticSearch").toString());
				label12 = new Label(4, i + 1, json.get("ElasticSearchs").toString());
				label13 = new Label(5, i + 1, json.get("ElasticDisk").toString());
				label14 = new Label(6, i + 1, json.get("ElasticDisks").toString());
				sheet.addCell(label8);
				sheet.addCell(label9);
				sheet.addCell(label10);
				sheet.addCell(label11);
				sheet.addCell(label12);
				sheet.addCell(label13);
				sheet.addCell(label14);
			  }
			}
		book.write();
		book.close();
	}

	public void writeData2Excel1(String time, String hbasedata,
			String hbasedatas, String elasticsearch, String elasticsearchs) throws Exception, WriteException{
		for(int x = 1; x <=7; x++){
			// 写入统计数据
				label6 = new Label(0, y, time);
				label7 = new Label(1, y, hbasedata);
				label8 = new Label(2, y, hbasedatas);
				label9 = new Label(3, y, elasticsearch);
				label10 = new Label(4, y, elasticsearchs);
				sheet.addCell(label6);
				sheet.addCell(label7);
				sheet.addCell(label8);
				sheet.addCell(label9);
				sheet.addCell(label10);
		}
		//book.write();
		//book.close();
	}
	
	public static void main(String[] args) throws Exception {
		writeData2Excel = new WriteData2Excel();
		writeData2Excel.writeData2Excel();
	}
}
