package com.chao.connpool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class MyConnectionPool implements DataSource{
	
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
				conn = MyConnectionPool.connMysql();
				Connection deconn = new DeMyConnectionPool(conn, pool);
				pool.add(deconn);
				
			} catch (Exception e) {
				
				throw new ExceptionInInitializerError("数据库连接失败，请检查数据库配置信息！");
			}
			
		}
	}
		
	
	@Override
	public Connection getConnection() throws SQLException {
		if(pool.size()>0){
			return pool.removeFirst();
		}else{
			throw new RuntimeException("服务器繁忙，请稍后再试！");
		}
	}
	

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
