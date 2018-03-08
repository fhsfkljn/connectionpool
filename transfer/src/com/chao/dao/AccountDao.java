package com.chao.dao;

import com.chao.domain.Account;

public interface AccountDao {
	
	/**
	 * �����˻���Ϣ�޸Ľ��
	 * @param account
	 */
	public void updateAccount(Account account) throws Exception;
	
	/**
	 * �����û������õ��˻�
	 * @param username
	 * @return Account
	 * @throws Exception
	 */
	public Account getAccount(String username)throws Exception;
}
