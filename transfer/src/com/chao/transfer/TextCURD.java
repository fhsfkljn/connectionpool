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
	//ArrayHandler适合获取一条记录，并将该条记录每列的值封装进object[]数组中
	public void test1() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDs());
		Object[] query = qr.query("select * from users", new ArrayHandler());
		for(Object object:query){
			System.out.println(object);
		}
		//System.out.println(query);
	}
	
	@Test
	//ArrayListHandler适合获取多条记录，并将该条记录每列的值封装进object[]数组中，然后再把数组封装到一个List中
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
	//ColumnListHandler取某一列数据，将其封装到list中
	public void test3() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDs());
		List<Object> query = qr.query("select * from users", new ColumnListHandler());
		for (Object object : query) {
			System.out.println(object);
		}
	}
	
	@Test
	//KeyedHandler取多条记录，每条封装到一个Map中，再把这个Map封装到另一个Map中，大Map中的key是指定字段的值
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
	//MapHandler取一条记录，把当前记录列名和列值放到一个Map中
	public void test5() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDs());
		Map<String, Object> query = qr.query("select * from users", new MapHandler());
		for (Map.Entry<String, Object> m : query.entrySet()) {
			System.out.println(m.getKey()+":"+m.getValue());
		}
	}
	
	@Test
	//MapListHandler取多条记录，把得到的Map封装到一个list中
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
	//ScalarHandler取单行单列记录
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
