package com.gnahznib.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.junit.Test;

public class ExcelDemo {
	@Test
	public void test01() throws IOException, RowsExceededException, WriteException{
		// 新建指定名称的Exel表格
		WritableWorkbook book = Workbook.createWorkbook(new File("d:/1111.xls"));
		// 创建sheet
		//WritableSheet sheet = book.createSheet("sheet1", 0);  
		// 获取sheet
		WritableSheet sheets = book.getSheet(0);
		// 设置sheet名称
		sheets.setName("统计信息"); // 给sheet页改名  
		          
		// sheet.mergeCells(0, 0, 4, 0); // 合并单元格  
		// sheet.setRowView(0, 600); // 设置行的高度  
		// sheet.setColumnView(0, 30); // 设置列的宽度  
		// sheet.setColumnView(1, 20); // 设置列的宽度  
		
		// 写入数据Label(列, 行, "写入数据")
		Label label1 = new Label(0, 0, "日期");
		Label label2 = new Label(1, 0, "HBase数据（条）");
		Label label3 = new Label(2, 0, "HBase数据增长量（条）");
		Label label4 = new Label(3, 0, "ElasticSearch数据（条）");
		Label label5 = new Label(4, 0, "ElasticSearch数据增长量（条）");
		sheets.addCell(label1);
		sheets.addCell(label2);
		sheets.addCell(label3);
		sheets.addCell(label4);
		sheets.addCell(label5);
		
		// 改变cell属性
		//WritableCellFormat wcf = new WritableCellFormat();
		
		
		book.write();
		book.close();
	}
	
	@Test
	public void test2(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		System.out.println(df.format(new Date()));
	}
	
	@Test
	public void test3(){
		WritableWorkbook wwb = null;
		String fileName = "d:/excels.xls";
		try {
			// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
			wwb = Workbook.createWorkbook(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (wwb != null) {
			// 创建一个可写入的工作表
			// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
			WritableSheet ws = wwb.createSheet("sheet1", 0);
//			System.out.println(ws.getColumns());
//			System.out.println(ws.getRows());
			// 下面开始添加单元格
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 5; j++) {
					// 这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行
					Label labelC = new Label(j, i, "这是第" + (i + 1) + "行，第"
							+ (j + 1) + "列");
					try {
						// 将生成的单元格添加到工作表中
						ws.addCell(labelC);
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					}

				}
			}

			try {
				// 从内存中写入文件中
				wwb.write();
				// 关闭资源，释放内存
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
	}
}
