package com.chao.service;

public interface AccountService {
	
	/**
	 * в╙ук
	 * @param fromName
	 * @param toName
	 * @param money
	 */
	public void transfer(String fromName,String toName,int money) throws Exception;
}
