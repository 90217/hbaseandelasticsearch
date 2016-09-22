package com.gnahznib.test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.junit.Test;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class ToLinux {
	
	@Test
	public void test01() throws IOException{
		String hostname = "ip";  //192.168.18.38主机名称
        String username = "root";//主机用户名
        String password = "root";//主机密码
        Connection conn = new Connection(hostname);//创建一个连接
        conn.connect();//创建的连接请求连接
        boolean isAuthenticated = conn.authenticateWithPassword(username, password);
        if (isAuthenticated == false)//如果连接失败抛出异常
            throw new IOException("Authentication failed.");
        Session sess = conn.openSession();//创建一个会话
        sess.requestDumbPTY();
        String sqlmicmd = "curl -XGET 'http://" + hostname + ":9200/_cluster/stats?human'";//&pretty
        sess.execCommand(sqlmicmd);
        InputStream stdout = new StreamGobbler(sess.getStdout());//放入流中，方便打印
        byte[] buff = new byte[1024 * 6];
        int len = -1;  
        
        String res = null;
        while(-1 != (len = stdout.read(buff))) {  
            //将字节数组转换为字符串  
            res = new String(buff, 0, len); 
            String[] split = res.split(",");
            String str = split[18];
            String[] split2 = str.split("\"");
            String str2 = split2[5];
            String str3 = str2.substring(0, str2.length() - 2);
            //String sub = str2.substring(str2.length() - 2, str2.length() - 1);
            String sub = "t";
            float long2 = 0;
            
            if(sub.equals("m")){
            	Float long1 = new Float(str3);
            	Float long21 = long1/1024;
                 
                BigDecimal bg = new BigDecimal(long21);
                long2 = (float) bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }else if(sub.equals("t")){
            	Float long1 = new Float(str3);
            	long2 = long1 * 1024;
            }else{
            	long2 = new Float(str3);
            }
            System.out.println(long2);
            break;
        }  
        stdout.close();
        sess.close();
        conn.close();
	}

}