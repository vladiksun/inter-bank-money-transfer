package com.transfer.service.impl;

import com.transfer.entity.Account;
import com.transfer.exception.AccountNotFoundException;
import org.apache.commons.lang.StringUtils;

public class Validator {

    public static void validateAccountExists(Account account, String accountNumber) throws AccountNotFoundException {
        if (account == null) {
            throw new AccountNotFoundException(String.format("Account with number = [ %d ] is not found", accountNumber));
        }
    }

    // just simple validation
    public static boolean isAccountNumberValid(String accountNumber) {
        return !StringUtils.isBlank(accountNumber);
    }
}
