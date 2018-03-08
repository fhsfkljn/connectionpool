package com.chao.test;

import com.chao.service.AccountService;
import com.chao.service.impl.AccountServiceImpl;

public class AccountTest {
	public static void main(String[] args)throws Exception {
		AccountService as = new AccountServiceImpl();
		as.transfer("tom", "jerry", 100);
	}
}
