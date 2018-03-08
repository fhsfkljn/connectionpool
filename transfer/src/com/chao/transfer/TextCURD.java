package com.chao.transfer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

public class TextCURD {
	
	@Test
	//ArrayHandler�ʺϻ�ȡһ����¼������������¼ÿ�е�ֵ��װ��object[]������
	public void test1() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDs());
		Object[] query = qr.query("select * from users", new ArrayHandler());
		for(Object object:query){
			System.out.println(object);
		}
		//System.out.println(query);
	}
	
	@Test
	//ArrayListHandler�ʺϻ�ȡ������¼������������¼ÿ�е�ֵ��װ��object[]�����У�Ȼ���ٰ������װ��һ��List��
	public void test2() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDs());
		List<Object[]> query = qr.query("select * from users", new ArrayListHandler());
		for (Object[] objects : query) {
			for (Object object : objects) {
				System.out.println(object);
			}
			System.out.println("---------------");
		}
	}
	
	@Test
	//ColumnListHandlerȡĳһ�����ݣ������װ��list��
	public void test3() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDs());
		List<Object> query = qr.query("select * from users", new ColumnListHandler());
		for (Object object : query) {
			System.out.println(object);
		}
	}
	
	@Test
	//KeyedHandlerȡ������¼��ÿ����װ��һ��Map�У��ٰ����Map��װ����һ��Map�У���Map�е�key��ָ���ֶε�ֵ
	public void test4() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDs());
		Map<Object, Map<String, Object>> query = qr.query("select * from users", new KeyedHandler());
		for (Map.Entry<Object, Map<String, Object>> m : query.entrySet()) {
			for (Map.Entry<String, Object> mm : m.getValue().entrySet()) {
				System.out.println(mm.getKey()+":"+mm.getValue());
			}
			System.out.println("------------");
		}
	}
	
	@Test
	//MapHandlerȡһ����¼���ѵ�ǰ��¼��������ֵ�ŵ�һ��Map��
	public void test5() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDs());
		Map<String, Object> query = qr.query("select * from users", new MapHandler());
		for (Map.Entry<String, Object> m : query.entrySet()) {
			System.out.println(m.getKey()+":"+m.getValue());
		}
	}
	
	@Test
	//MapListHandlerȡ������¼���ѵõ���Map��װ��һ��list��
	public void test6() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDs());
		List<Map<String, Object>> query = qr.query("select * from users", new MapListHandler());
		for (Map<String, Object> map : query) {
			for (Map.Entry<String, Object> m : map.entrySet()) {
				System.out.println(m.getKey()+":"+m.getValue());
			}
			System.out.println("--------------");
		}
	}
	
	@Test
	//ScalarHandlerȡ���е��м�¼
	public void test7() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDs());
		Object query = qr.query("select * from users", new ScalarHandler(2));
		System.out.println(query);
	}
	
	@Test
	//BeanHandler
	public void test8() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDs());
		User query = qr.query("select * from users", new BeanHandler<User>(User.class));
		System.out.println(query);
	}
	
	@Test
	//BeanListHandler
	public void test9() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDs());
		List<User> query = qr.query("select * from users", new BeanListHandler<User>(User.class));
		for (User user : query) {
			System.out.println(user);
		}
	}
	
	
}
