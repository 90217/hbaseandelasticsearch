package com.gnahznib.test;


public class TestDemo {

/*
public static void main(String[] args) throws Exception {  
    Workbook wb = Workbook.getWorkbook(new File("d:/test.xls")); // 获得原始文档  
    WritableWorkbook workbook = Workbook.createWorkbook(new File("d:/test_modified.xls"),wb); // 创建一个可读写的副本  
      
      
    /** 
     * 定义与设置Sheet 
      
    WritableSheet sheet = workbook.getSheet(0);  
    sheet.setName("修改后"); // 给sheet页改名  
    workbook.removeSheet(2); // 移除多余的标签页  
    workbook.removeSheet(3);  
      
    sheet.mergeCells(0, 0, 4, 0); // 合并单元格  
    sheet.setRowView(0, 600); // 设置行的高度  
    sheet.setColumnView(0, 30); // 设置列的宽度  
    sheet.setColumnView(1, 20); // 设置列的宽度  
      
     WritableCell cell = null;  
     WritableCellFormat wcf = null;  
     Label label = null;  
     WritableCellFeatures wcfeatures = null;  
      
     // 更改标题字体  
     cell = sheet.getWritableCell(0,0);  
     WritableFont titleWf = new WritableFont(WritableFont.createFont("仿宋_GB2312"),// 字体  
                                             20,//WritableFont.DEFAULT_POINT_SIZE,  // 字号  
                                             WritableFont.NO_BOLD,                  // 粗体  
                                             false,                                 // 斜体  
                                             UnderlineStyle.NO_UNDERLINE,           // 下划线  
                                             Colour.BLUE2,                          // 字体颜色  
                                             ScriptStyle.NORMAL_SCRIPT);  
     wcf = new WritableCellFormat(titleWf);  
     wcf.setBackground(Colour.GRAY_25);// 设置单元格的背景颜色  
     wcf.setAlignment(Alignment.CENTRE); // 设置对齐方式  
     wcf.setBorder(Border.ALL, BorderLineStyle.THICK); // 添加边框  
     cell.setCellFormat(wcf);  
      
     // 将B3的字体改为仿宋_GB2312  
     cell = sheet.getWritableCell(1,2);  
     WritableFont fs = new WritableFont(WritableFont.createFont("仿宋_GB2312"),  
                                       11);  
     wcf = new WritableCellFormat(fs);  
     cell.setCellFormat(wcf);  
      
     // 将B4的字号改为20  
     cell = sheet.getWritableCell(1,3);  
     WritableFont size20 = new WritableFont(WritableFont.createFont("宋体"),   
                                           20);  
     wcf = new WritableCellFormat(size20);  
     cell.setCellFormat(wcf);  
      
     // 将B5的字体改为加粗  
     cell = sheet.getWritableCell(1,4);  
     WritableFont bold = new WritableFont(WritableFont.createFont("宋体"),   
                                           11,  
                                           WritableFont.BOLD);  
     wcf = new WritableCellFormat(bold);  
     cell.setCellFormat(wcf);  
      
     // 将B6的字体改为倾斜  
     cell = sheet.getWritableCell(1,5);  
     WritableFont italic = new WritableFont(WritableFont.createFont("宋体"),   
                                            11,  
                                            WritableFont.NO_BOLD,  
                                            true);  
     wcf = new WritableCellFormat(italic);  
     cell.setCellFormat(wcf);  
      
     // 将B7字体加下划线  
     cell = sheet.getWritableCell(1,6);  
     WritableFont underline = new WritableFont(WritableFont.createFont("宋体"),   
                                               11,  
                                               WritableFont.NO_BOLD,  
                                               false,  
                                               UnderlineStyle.SINGLE);  
     wcf = new WritableCellFormat(underline);  
     cell.setCellFormat(wcf);  
      
     // 将B8的文字改为“待修改文字-已修改”  
     cell = sheet.getWritableCell(1,7);  
     if (cell.getType() == CellType.LABEL)  
     {  
         Label lc = (Label) cell;  
         lc.setString(lc.getString() + " - 已修改");  
     }  
      
     // 将B9文字对齐方式改为垂直居中、右对齐  
     cell = sheet.getWritableCell(1,8);  
     WritableFont align = new WritableFont(WritableFont.createFont("宋体"),   
                                              11);  
     wcf = new WritableCellFormat(align);  
     wcf.setAlignment(Alignment.RIGHT); // 设置为右对齐  
     wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 设置为垂直居中  
     cell.setCellFormat(wcf);  
      
     // 将E3文字改为自动换行  
     cell = sheet.getWritableCell(4,2);  
     WritableFont justify = new WritableFont(WritableFont.createFont("宋体"),   
                                              11);  
     wcf = new WritableCellFormat(justify);  
     wcf.setAlignment(Alignment.JUSTIFY);  
     cell.setCellFormat(wcf);  
      
      
     // 将B12的数字有效位数从5位改为7位  
     cell = sheet.getWritableCell(1,11);  
     NumberFormat sevendps = new NumberFormat("#.0000000");  
     wcf = new WritableCellFormat(sevendps);  
     cell.setCellFormat(wcf);  
      
     // 将B13改为4位科学计数法表示  
     cell = sheet.getWritableCell(1,12);  
     NumberFormat exp4 = new NumberFormat("0.####E0");  
     wcf = new WritableCellFormat(exp4);  
     cell.setCellFormat(wcf);  
      
     // 将B14改为默认数字表示  
     cell = sheet.getWritableCell(1,13);  
     cell.setCellFormat(WritableWorkbook.NORMAL_STYLE);  
      
     // 将B15数字类型的值17改为22  
     cell = sheet.getWritableCell(1,14);  
     if (cell.getType() == CellType.NUMBER)  
     {  
         Number n = (Number) cell; 
        // n.setValue(42);  
     }  
      
     // 将B16的值2.71进行加法运算2.71 + 0.1  
     cell = sheet.getWritableCell(1,15);  
     if (cell.getType() == CellType.NUMBER)  
     {  
         Number n = (Number) cell;  
        // n.setValue(n.getValue() + 0.1);  
     }  
      
     // 将B19日期格式改为默认  
     cell = sheet.getWritableCell(1,18);  
     wcf = new WritableCellFormat(DateFormats.FORMAT9);  
     cell.setCellFormat(wcf);  
      
     // 将B20日期格式改为dd MMM yyyy HH:mm:ss  
     cell = sheet.getWritableCell(1,19);  
     DateFormat df = new DateFormat("dd MMM yyyy HH:mm:ss");  
     wcf = new WritableCellFormat(df);  
     cell.setCellFormat(wcf);  
      
     // 将B21的日期设置为 2011-6-1 11:18:50  
     cell = sheet.getWritableCell(1,20);  
     if (cell.getType() == CellType.DATE)  
     {  
         DateTime dt = (DateTime) cell;  
         Calendar cal = Calendar.getInstance();  
         cal.set(2011, 5, 1, 11, 18, 50);  
         Date d = cal.getTime();  
         dt.setDate(d);  
     }  
      
      
     // 将B24文字添加链接http://www.baidu.com  
     WritableHyperlink link = new WritableHyperlink(1, 23, new URL("http://www.baidu.com"));  
     sheet.addHyperlink(link);  
      
     // 更改URL链接  
     WritableHyperlink hyperlinks[] = sheet.getWritableHyperlinks();  
     for (int i = 0; i < hyperlinks.length; i++) {  
         WritableHyperlink wh = hyperlinks[i];  
         if (wh.getColumn() == 1 && wh.getRow() == 24) {  
             // 将B25文字链接取消  
             sheet.removeHyperlink(wh,true);//true：保留文字；false：删除文字  
         }else if(wh.getColumn() == 1 && wh.getRow() == 25){  
             try {  
                 // 将B26链接更改为http://wuhongyu.javaeye.com  
                 wh.setURL(new URL("http://wuhongyu.javaeye.com"));  
             } catch (MalformedURLException e) {  
                 e.printStackTrace();  
             }  
         }  
     }  
      
      
     // 利用公式取得B29、B30的值  
     Formula f1 = new Formula(1, 28, "SUM(C29:D29)");  
     sheet.addCell(f1);  
     Formula f2 = new Formula(1, 29, "AVERAGE(C30:G30)");  
     sheet.addCell(f2);  
      
     // 在B32处添加图片，图片大小占10行3列，只支持png格式  
     File file = new File("d:\\shu05.png");  
     WritableImage image = new WritableImage(1, 31, 3, 10, file);  
     sheet.addImage(image);  
      
     // 在A44出添加内容"Added drop down validation"，并为其添加注释  
     label = new Label(0, 43, "Added drop down validation");  
     wcfeatures = new WritableCellFeatures();  
     wcfeatures.setComment("右边列是个下拉列表");  
     label.setCellFeatures(wcfeatures);  
     sheet.addCell(label);  
       
     // 在B44处添加一个下拉列表并添加注释  
     Blank b = new Blank(1, 43);  
     wcfeatures = new WritableCellFeatures();  
     ArrayList al = new ArrayList();  
     al.add("why");  
     al.add("landor");  
     al.add("tjm");  
     wcfeatures.setDataValidationList(al);  
     wcfeatures.setComment("这是一个注释");  
     b.setCellFeatures(wcfeatures);  
     sheet.addCell(b);  
       
     // 为A46添加注释。  
     // 此处比较麻烦，试了多次发现必须将cell强制类型转换、添加CellFeatures再修改注释才可用，不知有没有更好的办法。  
     cell = sheet.getWritableCell(0,45);  
     wcfeatures = new WritableCellFeatures();  
     wcfeatures.setComment("这个注释不会被显示，删了这行还不行,MD");  
     cell.setCellFeatures(wcfeatures);  
       
     label = (Label) cell;  
//   label.setCellFeatures(wcfeatures);// 直接这样写会报一个警告（“注释已存在”），但那个注释仍会被显示。  
     label.addCellFeatures();  
     label.getWritableCellFeatures().setComment("终于加上注释了，哈哈哈哈");  
       
       
//  if (cell instanceof Number) {  
//      Number num = (Number) cell;  
//      num.setCellFeatures(wcfeatures);  
//  } else if (cell instanceof jxl.write.Boolean) {  
//      jxl.write.Boolean bool = (jxl.write.Boolean) cell;  
//      bool.setCellFeatures(wcfeatures);  
//  } else if (cell instanceof jxl.write.DateTime) {  
//      jxl.write.DateTime dt = (jxl.write.DateTime) cell;  
//      dt.setCellFeatures(wcfeatures);  
//  } else {  
//      Label _label = (Label) cell;  
//      _label.setCellFeatures(wcfeatures);  
//  }  
       
     workbook.write();  
     workbook.close();  
     wb.close();  
}  */

}