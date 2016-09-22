package com.gnahznib.test;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelTest01 {
	// 新建指定名称的Exel表格
		private static WritableWorkbook book = null;

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
		// private static FileOutputStream fos = null;
		static {
			try {
				if (book == null) {
					book = Workbook.createWorkbook(new File("d:/经研院数据31.xls"));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 创建sheet
			book.createSheet("统计信息", 0);

			sheet = book.getSheet(0);
			// System.out.println(sheet.getColumns());

		}
		
		@Test
		public void test01() throws RowsExceededException, WriteException, IOException{
			for(int i =0 ; i < 110; i++){
				if (i == 0) {
					// sheet.get
					label1 = new Label(0, y, "日期");
					label2 = new Label(1, y, "HBase数据（条）");
					label3 = new Label(2, y, "HBase数据增长量（条）");
					label4 = new Label(3, y, "ElasticSearch数据（条）");
					label5 = new Label(4, y, "ElasticSearch数据增长量（条）");
					sheet.addCell(label1);
					sheet.addCell(label2);
					sheet.addCell(label3);
					sheet.addCell(label4);
					sheet.addCell(label5);
					y++;
				}

				// 写入统计数据
				if (i > 0) {
					label6 = new Label(0, i, "time");
					label7 = new Label(1, i, "hbasedata");
					label8 = new Label(2, i, "hbasedatas");
					label9 = new Label(3, i, "elasticsearch");
					label10 = new Label(4, i, "elasticsearchs");
					sheet.addCell(label6);
					sheet.addCell(label7);
					sheet.addCell(label8);
					sheet.addCell(label9);
					sheet.addCell(label10);
					
				}
			
			}
		}
		
		@After
		public void afters() throws IOException, WriteException{
			book.write();
			book.close();
		}
}
