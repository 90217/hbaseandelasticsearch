package com.gnahznib.countelasticsearch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHits;

import com.gnahznib.util.PropertiesUtil;

public class CountElasticSearch {
	private static TransportClient client = null;
	private static Properties elasticSearch = null;
	private static String[] nodeList = null;
	static{
		elasticSearch = new Properties();
		elasticSearch.setProperty("nodeList", PropertiesUtil.getElasticSearchConfig("nodeList"));
		elasticSearch.setProperty("clusterName", PropertiesUtil.getElasticSearchConfig("clusterName"));
		elasticSearch.setProperty("sniff", PropertiesUtil.getElasticSearchConfig("pingTimeOut"));
		if (client == null) {
			Settings settings = ImmutableSettings
					.settingsBuilder()
					.put("client.transport.sniff", PropertiesUtil.getElasticSearchConfig("sniff"))
					.put("discovery.zen.ping.timeout", PropertiesUtil.getElasticSearchConfig("pingTimeOut"))
					.put("cluster.name", PropertiesUtil.getElasticSearchConfig("clusterName"))
					.build();
			client = new TransportClient(settings);
		}
		nodeList = PropertiesUtil.getElasticSearchConfig(
				"nodeList").split(",");
	}
	
	public void initESClient() {
		/*if (client == null) {
			Settings settings = ImmutableSettings
					.settingsBuilder()
					.put("client.transport.sniff", PropertiesUtil.getElasticSearchConfig("sniff"))
					.put("discovery.zen.ping.timeout", PropertiesUtil.getElasticSearchConfig("pingTimeOut"))
					.put("cluster.name", PropertiesUtil.getElasticSearchConfig("clusterName"))
					.build();
			client = new TransportClient(settings);*/
			
			for (String node : nodeList) {
				String tmp[] = node.split(":");
				client.addTransportAddress(new InetSocketTransportAddress(
						tmp[0], Integer.valueOf(tmp[1])));
			}
		//}
	}
	
	public long countElasticSearch(){
		CountElasticSearch countElasticSearch = new CountElasticSearch();
		countElasticSearch.initESClient();
		SearchResponse response = client.prepareSearch("doc-*")
                .setTypes("doc")
                .execute().actionGet();
        SearchHits hits = response.getHits();
        //System.out.println("ElasticSearch索引数：" + hits.getTotalHits());
        long str = hits.getTotalHits();
        
        //countElasticSearch.closeESClient();
        Date now = new Date(); 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式


        String hehe = dateFormat.format( now ); 
        System.out.println(hehe); 
        System.out.println("ES索引数据量：" + str);
        
        return str;
	}
	
	public void closeESClient() {
		client.close();
	}
}
