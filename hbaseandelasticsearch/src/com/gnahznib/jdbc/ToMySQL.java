package com.gnahznib.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ToMySQL {

	private String dbDriver = "com.mysql.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://ip:3306/countdata";// 根据实际情况变化
	private String dbUser = "root";
	private String dbPass = "root";

	public Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);// 注意是三个参数
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void insert(String time, String HBase, String HBases, String ElasticSearch, String ElasticSearchs, String readdisk, String readdisks) throws SQLException {
		String sql = "insert into countdata (time,HBase,HBases,ElasticSearch,ElasticSearchs,ElasticDisk,ElasticDisks)values('" + time + "','" + HBase + "','" + HBases + "','" + ElasticSearch + "','" + ElasticSearchs + "','" + readdisk + "','" + readdisks + "')";
		Connection cnn = getConn();
		Statement statement = cnn.createStatement();
		statement.execute(sql);
	}
	
	public String select(String name, String beforedata) throws SQLException{
		String sql = "select " + name + " from countdata where time='"+ beforedata +"'";
		Connection cnn = getConn();
		Statement statement = cnn.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		ResultSetMetaData metaData = resultSet.getMetaData();
		int columnCount = metaData.getColumnCount();
		String value = null;
		//JSONArray jsonArray = new JSONArray();
		while (resultSet.next()){
			//JSONObject jsonObject = new JSONObject();
			
			// 遍历每一列
			for(int i = 1; i <= columnCount; i++){
				String columnLabel = metaData.getColumnLabel(i);
				value = resultSet.getString(columnLabel);
				//jsonObject.put(columnLabel, value);
				//System.out.println(value);
			}
			//jsonArray.put(jsonObject);
			
			//System.out.println(jsonArray);
		}
		//return jsonArray.toString();
		
		return value;
	}
	
	public JSONArray readMySQL() throws SQLException, JSONException{
		String sql = "select * from countdata";
		Connection cnn = getConn();
		Statement statement = cnn.createStatement();
		// statement.execute(sql);
		ResultSet rs = statement.executeQuery(sql);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		// 获取列数据
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		
		// 遍历ResultSet中的每一条数据
		while (rs.next()){
			jsonObject = new JSONObject();
			
			// 遍历每一列
			for(int i = 1; i <= columnCount; i++){
				String columnLabel = metaData.getColumnLabel(i);
				String value = rs.getString(columnLabel);
				jsonObject.put(columnLabel, value);
			}
			jsonArray.put(jsonObject);
			
			//System.out.println(jsonArray);
		}
		/*System.out.println(jsonArray.length());
		if(jsonArray.length()>0){
			  for(int i=0;i<jsonArray.length();i++){
			    JSONObject job = jsonArray.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
			    System.out.println(job.get("time")) ;  // 得到 每个对象中的属性值
			    System.out.println(job.get("HBase")) ;  // 得到 每个对象中的属性值
			    System.out.println(job.get("HBases")) ;  // 得到 每个对象中的属性值
			    System.out.println(job.get("ElasticSearch")) ;  // 得到 每个对象中的属性值
			    System.out.println(job.get("ElasticDisk")) ;  // 得到 每个对象中的属性值
			    System.out.println(job.get("ElasticDisks")) ;  // 得到 每个对象中的属性值
			  }
			}*/
		return jsonArray;
	}
	
//	public static void main(String[] args) throws SQLException {
//		ToMySQL toMySQL = new ToMySQL();
//		//System.out.println(toMySQL.select());
//	}
}
