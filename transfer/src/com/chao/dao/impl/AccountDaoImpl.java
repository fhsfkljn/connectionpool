package com.chao.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.chao.dao.AccountDao;
import com.chao.domain.Account;
import com.chao.transfer.C3P0Util;
import com.chao.util.ManagerThreadLocal;

public class AccountDaoImpl implements AccountDao{

	@Override
	public void updateAccount(Account account) throws Exception {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update account set money=? where username=?",account.getMoney(),account.getUsername());
		
	}

	@Override
	public Account getAccount(String username) throws Exception {
		QueryRunner qr = new QueryRunner();
		Account query = qr.query(ManagerThreadLocal.getConnection(), "select * from account where username=?", new BeanHandler<Account>(Account.class),username);
		return query;
		
	}

}
