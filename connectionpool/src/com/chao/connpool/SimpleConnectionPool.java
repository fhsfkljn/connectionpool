package com.chao.connpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collections;
import java.util.LinkedList;

//实现简单的数据库连接池，用来展示基本原理
public class SimpleConnectionPool {
	
	//获得数据库的连接
	private static Connection connMysql() throws Exception{
		String url = "jdbc:mysql://localhost:3306/text?useSSL=false";
		String user = "root";
		String password = "8520123456";
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url,user,password);
	}
	
	//创建一个存放连接的池子,线程安全
	private static LinkedList<Connection> pool = (LinkedList<Connection>) Collections.synchronizedList(new LinkedList<Connection>());
	
	static{
		for(int i=0;i<10;i++){
			Connection conn;
			try {
				conn = SimpleConnectionPool.connMysql();
				pool.add(conn);
				
			} catch (Exception e) {
				
				throw new ExceptionInInitializerError("数据库连接失败，请检查数据库配置信息！");
			}
			
		}
	}
	
	//得到数据库连接
	public static Connection getConnection(){
		if(pool.size()>0){
			return pool.removeFirst();
		}else{
			throw new RuntimeException("服务器繁忙，请稍后再试！");
		}
	}
	
	//释放连接
	public static void release(Connection conn){
		pool.addLast(conn);
	}
}
