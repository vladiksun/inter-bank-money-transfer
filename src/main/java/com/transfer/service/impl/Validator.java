package com.transfer.service.impl;

import com.transfer.entity.Account;
import com.transfer.entity.Customer;
import com.transfer.exception.AccountNotFoundException;
import com.transfer.exception.CustomerNotFoundException;
import org.apache.commons.lang.StringUtils;

public class Validator {

    public static void validateAccountExists(Account account, String accountNumber) throws AccountNotFoundException {
        if (account == null) {
            throw new AccountNotFoundException(String.format("Account with number = [ %s ] is not found", accountNumber));
        }
    }

    public static void validateCustomerExists(Customer customer, long customerID) throws CustomerNotFoundException {
        if (customer == null) {
            throw new CustomerNotFoundException(String.format("Customer with number = [ %d ] is not found", customerID));
        }
    }

    // just simple validation
    public static boolean isAccountNumberValid(String accountNumber) {
        return !StringUtils.isBlank(accountNumber);
    }
}
