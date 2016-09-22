package com.gnahznib.counthbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

import com.gnahznib.util.PropertiesUtil;


public class CountHBase {
	private static Configuration conf = null;
	private static HConnection conn = null;
	static {
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.property.clientPort", PropertiesUtil.getHBaseConfig("zkPort"));
		conf.set("hbase.zookeeper.quorum", PropertiesUtil.getHBaseConfig("hbase.zookeeper.quorum"));
		conf.set("hbase.master", PropertiesUtil.getHBaseConfig("hbase.master"));
		conf.set("zookeeper.znode.parent", PropertiesUtil.getHBaseConfig("zookeeper.znode.parent"));
		try {
			conn = HConnectionManager.createConnection(conf);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public long readerTable() throws IOException{
		HTableInterface table = conn.getTable("INTERNET_DATA");//SYSTEM.STATS  INTERNET_DATA
		Scan scan = new Scan();
		long sum = 0;
		ResultScanner scanner = table.getScanner(scan);
		for(Result result : scanner){
			while(result.advance()){
				// System.out.println("Cell:" + result.current());
				//System.out.println(result.current().toString());
				sum++;
			}
		}
		System.out.println("HBase数据量：" + sum);
		
		return sum;
	}
}
