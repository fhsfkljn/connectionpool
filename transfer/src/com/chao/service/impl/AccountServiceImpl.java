package com.chao.service.impl;

import com.chao.dao.AccountDao;
import com.chao.dao.impl.AccountDaoImpl;
import com.chao.domain.Account;
import com.chao.service.AccountService;
import com.chao.util.ManagerThreadLocal;

public class AccountServiceImpl implements AccountService{

	@Override
	public void transfer(String fromName, String toName, int money){
		AccountDao ad = new AccountDaoImpl();
		
		try {
			//�ֱ�õ����ߵ��˻�
			ManagerThreadLocal.startTransaction();
			
			Account fromAccount = ad.getAccount(fromName);
			Account toAccount = ad.getAccount(toName);
			
			//�ֱ���������ת�ʺ�Ľ��
			fromAccount.setMoney(fromAccount.getMoney()-money);
			toAccount.setMoney(toAccount.getMoney()+money);
			
			//���ת�˲���
			ad.updateAccount(fromAccount);
			ad.updateAccount(toAccount);
			
			ManagerThreadLocal.commitTransaction();
		} catch (Exception e) {
			ManagerThreadLocal.rollbackTransaction();
			e.printStackTrace();
		} finally {
			ManagerThreadLocal.release();
		}
		
	}

}
