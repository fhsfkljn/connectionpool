package com.chao.dao;

import com.chao.domain.Account;

public interface AccountDao {
	
	/**
	 * 根据账户信息修改金额
	 * @param account
	 */
	public void updateAccount(Account account) throws Exception;
	
	/**
	 * 根据用户姓名得到账户
	 * @param username
	 * @return Account
	 * @throws Exception
	 */
	public Account getAccount(String username)throws Exception;
}
