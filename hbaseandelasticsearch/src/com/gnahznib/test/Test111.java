package com.gnahznib.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class Test111 {
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
        String sqlmicmd = "ls";
        sess.execCommand(sqlmicmd);
        InputStream stdout = new StreamGobbler(sess.getStdout());//放入流中，方便打印
        BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
        System.out.println(br);
	}
	
	@Test
	public void test02(){
		String str = "123";
		Long lon = Long.valueOf(str);
		System.out.println(lon);
	}
	
	/*
	public static void main(String[] args) throws Exception {
		String readFile = "d:\\Hadoop.txt";
		String writeFile = "d:\\o.txt";
		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(readFile)));// 读取文件所在路径
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(writeFile)));// 写入文件所在路径
		String input = null;
		while ((input = in.readLine()) != null) {
			String str[] = input.split(":");
			out.write(str[0]);
			out.newLine();
			out.flush();
		}
		System.out.println(readFile + "文件处理完毕!输出文件" + writeFile);
		out.close();
		in.close();
	}*/
}