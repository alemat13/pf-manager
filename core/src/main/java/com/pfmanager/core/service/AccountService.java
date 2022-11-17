package com.pfmanager.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfmanager.core.entity.Account;
import com.pfmanager.core.entity.Bank;
import com.pfmanager.core.entity.Currency;
import com.pfmanager.core.repository.AccountRepository;
import com.pfmanager.core.repository.BankRepository;
import com.pfmanager.core.repository.CurrencyRepository;

@Service
public class AccountService {
    private @Autowired AccountRepository accountRepository;
    private @Autowired BankRepository bankRepository;
    private @Autowired CurrencyRepository currencyRepository;
    
    public Iterable<Account> findAllAccounts() {
        return this.accountRepository.findAll();
    }

    public Account findAccountById(Long id) {
        return this.accountRepository.findById(id).orElseThrow();
    }
    
    public Account saveAccount(Account account) {
        return this.accountRepository.save(account);
    }
    
    public Iterable<Bank> findAllBanks() {
        return this.bankRepository.findAll();
    }

    public Bank findBankById(Long id) {
        return this.bankRepository.findById(id).orElseThrow();
    }
    
    public Bank saveBank(Bank bank) {
        return this.bankRepository.save(bank);
    }
    
    public Iterable<Currency> findAllCurrencys() {
        return this.currencyRepository.findAll();
    }

    public Currency findCurrencyById(Long id) {
        return this.currencyRepository.findById(id).orElseThrow();
    }
    
    public Currency saveCurrency(Currency currency) {
        return this.currencyRepository.save(currency);
    }

    public Iterable<Account> saveAllAcounts(List<Account> accounts) {
        return this.accountRepository.saveAll(accounts);
    }
}
