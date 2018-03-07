package com.chao.connpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collections;
import java.util.LinkedList;

//ʵ�ּ򵥵����ݿ����ӳأ�����չʾ����ԭ��
public class SimpleConnectionPool {
	
	//������ݿ������
	private static Connection connMysql() throws Exception{
		String url = "jdbc:mysql://localhost:3306/text?useSSL=false";
		String user = "root";
		String password = "8520123456";
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url,user,password);
	}
	
	//����һ��������ӵĳ���,�̰߳�ȫ
	private static LinkedList<Connection> pool = (LinkedList<Connection>) Collections.synchronizedList(new LinkedList<Connection>());
	
	static{
		for(int i=0;i<10;i++){
			Connection conn;
			try {
				conn = SimpleConnectionPool.connMysql();
				pool.add(conn);
				
			} catch (Exception e) {
				
				throw new ExceptionInInitializerError("���ݿ�����ʧ�ܣ��������ݿ�������Ϣ��");
			}
			
		}
	}
	
	//�õ����ݿ�����
	public static Connection getConnection(){
		if(pool.size()>0){
			return pool.removeFirst();
		}else{
			throw new RuntimeException("��������æ�����Ժ����ԣ�");
		}
	}
	
	//�ͷ�����
	public static void release(Connection conn){
		pool.addLast(conn);
	}
}
