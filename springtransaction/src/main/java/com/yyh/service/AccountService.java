package com.yyh.service;

import com.yyh.dto.Account;
import com.yyh.mapper.AccountMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public boolean updateAccout(Account account){
        int result = accountMapper.updateByPrimaryKey(account);
        return result == 1;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean createAccount(Account account){
        int result = accountMapper.insertSelective(account);
        return result == 1;
    }

    public Account getAccount(int id){
        return accountMapper.selectByPrimaryKey(id);
    }

}
