package com.chao.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.chao.transfer.C3P0Util;

public class ManagerThreadLocal {
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	//�õ�һ������
	public static Connection getConnection(){
		Connection conn = tl.get();
		if(conn==null){
			conn = C3P0Util.getConnection();
			tl.set(conn);
		}
		return conn;
	}
	
	//��ʼ����
	public static void  startTransaction() {
		try {
			getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�ύ����
	public static void commitTransaction(){
		try {
			getConnection().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//����ع�
	public static void rollbackTransaction(){
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�ͷ�����
	public static void release(){
		try {
			getConnection().close();
			tl.remove();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
