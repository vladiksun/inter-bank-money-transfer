package com.transfer.it;

import com.testsupport.TestContainer;
import com.transfer.entity.Account;
import com.transfer.entity.AccountType;
import com.transfer.entity.Customer;
import com.transfer.entity.UserTransaction;
import com.transfer.exception.InsufficientFundsException;
import com.transfer.exception.InvalidParameterException;
import com.transfer.service.AccountService;
import com.transfer.service.CustomerService;
import com.transfer.service.TransactionService;
import com.transfer.utils.AppProperties;
import org.joda.money.CurrencyUnit;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.*;

import static org.junit.Assert.*;

import java.math.BigDecimal;

public class TransactionServiceTest {

    @ClassRule
    public static TestContainer cont = TestContainer.Common.INSTANCE;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private AccountService accountService;
    private CustomerService customerService;
    private TransactionService transactionService;
    private AppProperties appProperties;

    @Before
    public void setUp() {
        accountService = cont.getBean(AccountService.class);
        customerService = cont.getBean(CustomerService.class);
        transactionService = cont.getBean(TransactionService.class);
        appProperties = cont.getBean(AppProperties.class);
    }

    @Test
    public void testTransferFundsSameCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("TEST_NAME_1");
        customer.setLastName("TEST_LAST_NAME_1");

        Customer savedCustomer = customerService.createCustomer(customer);

        Account account1 = new Account();
        account1.setAccountNumber("998");
        account1.setType(AccountType.CHECKING.toString());
        account1.setDescription("Just test account");
        account1.setCurrencyCode(CurrencyUnit.USD.toString());
        account1.setBalance(new BigDecimal(500000));

        Account account2 = new Account();
        account2.setAccountNumber("999");
        account2.setType(AccountType.SAVINGS.toString());
        account2.setDescription("Just test account");
        account2.setCurrencyCode(CurrencyUnit.USD.toString());
        account2.setBalance(new BigDecimal(500000));

        account1 = accountService.createAccount(account1, savedCustomer.getCustomerId());
        account2 = accountService.createAccount(account2, savedCustomer.getCustomerId());

        UserTransaction transaction = new UserTransaction(new BigDecimal(250000), account1.getAccountNumber(), account2.getAccountNumber());

        transactionService.transferFunds(transaction);

        account1 = accountService.getAccountDetails(account1.getAccountNumber());
        account2 = accountService.getAccountDetails(account2.getAccountNumber());

        assertEquals(new BigDecimal(250000).setScale(appProperties.getAppCurrencyScale(), appProperties.getAppRoundingMode()), account1.getBalance());
        assertEquals(new BigDecimal(750000).setScale(appProperties.getAppCurrencyScale(), appProperties.getAppRoundingMode()), account2.getBalance());
    }

    @Test
    public void testShouldThrowExceptionIfInsufficientFunds() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("TEST_NAME_2");
        customer.setLastName("TEST_LAST_NAME_2");

        Customer savedCustomer = customerService.createCustomer(customer);

        Account account1 = new Account();
        account1.setAccountNumber("898");
        account1.setType(AccountType.CHECKING.toString());
        account1.setDescription("Just test account");
        account1.setCurrencyCode(CurrencyUnit.USD.toString());
        account1.setBalance(new BigDecimal(40000));

        Account account2 = new Account();
        account2.setAccountNumber("899");
        account2.setType(AccountType.SAVINGS.toString());
        account2.setDescription("Just test account");
        account2.setCurrencyCode(CurrencyUnit.USD.toString());
        account2.setBalance(new BigDecimal(500000));

        account1 = accountService.createAccount(account1, savedCustomer.getCustomerId());
        account2 = accountService.createAccount(account2, savedCustomer.getCustomerId());

        UserTransaction transaction = new UserTransaction(new BigDecimal(50000), account1.getAccountNumber(), account2.getAccountNumber());

        assertThatThrownBy(() -> {
            transactionService.transferFunds(transaction);
        }).hasCauseInstanceOf(InsufficientFundsException.class);
    }

    @Test
    public void testShouldFailIfCurrencyIsDifferent() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("TEST_NAME_3");
        customer.setLastName("TEST_LAST_NAME_3");

        Customer savedCustomer = customerService.createCustomer(customer);

        Account account1 = new Account();
        account1.setAccountNumber("798");
        account1.setType(AccountType.CHECKING.toString());
        account1.setDescription("Just test account");
        account1.setCurrencyCode(CurrencyUnit.USD.toString());
        account1.setBalance(new BigDecimal(40000));

        Account account2 = new Account();
        account2.setAccountNumber("799");
        account2.setType(AccountType.SAVINGS.toString());
        account2.setDescription("Just test account");
        account2.setCurrencyCode(CurrencyUnit.JPY.toString());
        account2.setBalance(new BigDecimal(500000));

        account1 = accountService.createAccount(account1, savedCustomer.getCustomerId());
        account2 = accountService.createAccount(account2, savedCustomer.getCustomerId());

        UserTransaction transaction = new UserTransaction(new BigDecimal(20), account1.getAccountNumber(), account2.getAccountNumber());

        assertThatThrownBy(() -> {
            transactionService.transferFunds(transaction);
        }).hasCauseInstanceOf(InvalidParameterException.class);
    }

    @Test
    public void testShouldFailIfTryingToTransferNull() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("TEST_NAME_4");
        customer.setLastName("TEST_LAST_NAME_4");

        Customer savedCustomer = customerService.createCustomer(customer);

        Account account1 = new Account();
        account1.setAccountNumber("698");
        account1.setType(AccountType.CHECKING.toString());
        account1.setDescription("Just test account");
        account1.setCurrencyCode(CurrencyUnit.USD.toString());
        account1.setBalance(new BigDecimal(40000));

        Account account2 = new Account();
        account2.setAccountNumber("699");
        account2.setType(AccountType.SAVINGS.toString());
        account2.setDescription("Just test account");
        account2.setCurrencyCode(CurrencyUnit.USD.toString());
        account2.setBalance(new BigDecimal(500000));

        account1 = accountService.createAccount(account1, savedCustomer.getCustomerId());
        account2 = accountService.createAccount(account2, savedCustomer.getCustomerId());

        UserTransaction transaction = new UserTransaction(new BigDecimal(0), account1.getAccountNumber(), account2.getAccountNumber());

        catchThrowableOfType(() -> {
            transactionService.transferFunds(transaction);
        }, InvalidParameterException.class);
    }
}
