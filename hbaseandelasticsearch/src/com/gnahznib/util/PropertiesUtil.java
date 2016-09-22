package com.gnahznib.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description:读取配置文件
 * @author ZhangXingBin
 *
 */
public class PropertiesUtil {
		
	public static String getHBaseConfig(String key) {
		try {
			Properties properties = new Properties();
			properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("hbase.properties"));
			return properties.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String getKafkaProducerConfig(String key) {
		try {
			Properties properties = new Properties();
			properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("producer.properties"));
			return properties.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String getKafkaConsumerConfig(String key) {
		try {
			Properties properties = new Properties();
			properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("consumer.properties"));
			return properties.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String getElasticSearchConfig(String key){
		try {
			Properties properties = new Properties();
			properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("elasticSearch.properties"));
			return properties.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static  Properties getPropFromFile(String source) throws Exception{
		//动态获取运行时工程根目录
		InputStream stream  = PropertiesUtil.class.getClassLoader().getResourceAsStream(source);
		Properties property=new Properties();
		try {
			property.load(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return property;
		}
}
