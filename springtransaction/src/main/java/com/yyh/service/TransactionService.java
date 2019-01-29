package com.yyh.service;

import com.yyh.dto.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class TransactionService {
    @Resource
    private AccountService accountService;

    @Transactional
    public boolean transfer(int fromId, int toId, double value){
        Account from = accountService.getAccount(fromId);
        from.setBalance(from.getBalance().subtract(BigDecimal.valueOf(value)));
        accountService.updateAccout(from);
        Account to = accountService.getAccount(toId);
        to.setBalance(to.getBalance().add(BigDecimal.valueOf(value)));
        accountService.updateAccout(to);
        return false;
    }
}
