package com.transfer.it;

import com.testsupport.TestContainer;
import com.transfer.entity.Account;
import com.transfer.entity.AccountType;
import com.transfer.entity.Customer;
import com.transfer.exception.IllegalAccountTypeException;
import com.transfer.service.AccountService;
import com.transfer.service.CustomerService;
import org.joda.money.CurrencyUnit;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AccountServiceTest {

    @ClassRule
    public static TestContainer cont = TestContainer.Common.INSTANCE;

    private AccountService accountService;
    private CustomerService customerService;

    @Before
    public void setUp() {
        accountService = cont.getBean(AccountService.class);
        customerService = cont.getBean(CustomerService.class);
    }

    @Test
    public void testShouldCreateAccount() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("TEST_NAME_1");
        customer.setLastName("TEST_LAST_NAME_1");

        Customer savedCustomer = customerService.createCustomer(customer);

        Account account = new Account();
        account.setAccountNumber(999L);
        account.setType(AccountType.CHECKING.toString());
        account.setDescription("Just test account");
        account.setCurrencyCode(CurrencyUnit.USD.toString());
        account.setBalance(new BigDecimal(500000));

        Account savedAccount = accountService.createAccount(account, savedCustomer.getCustomerId());

        assertNotNull(savedAccount);
        assertEquals(account.getAccountNumber(), savedAccount.getAccountNumber());
    }

    @Test(expected = IllegalAccountTypeException.class)
    public void shouldThrowExceptionIfAccountOfWrongType() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("TEST_NAME_2");
        customer.setLastName("TEST_LAST_NAME_2");

        Customer savedCustomer = customerService.createCustomer(customer);

        Account account = new Account();
        account.setAccountNumber(999L);
        account.setType("UNKNOWN");
        account.setDescription("Just test account");
        account.setCurrencyCode(CurrencyUnit.USD.toString());
        account.setBalance(new BigDecimal(500000));

        Account savedAccount = accountService.createAccount(account, savedCustomer.getCustomerId());

        assertNotNull(savedAccount);
        assertEquals(savedAccount.getAccountNumber(), account.getAccountNumber());
    }
}
