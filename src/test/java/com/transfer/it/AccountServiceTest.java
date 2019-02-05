package com.transfer.it;

import com.testsupport.TestContainer;
import com.transfer.entity.Account;
import com.transfer.entity.AccountType;
import com.transfer.entity.Customer;
import com.transfer.exception.IllegalAccountTypeException;
import com.transfer.service.AccountService;
import com.transfer.service.CustomerService;
import com.transfer.utils.AppProperties;
import org.joda.money.CurrencyUnit;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class AccountServiceTest {

    @ClassRule
    public static TestContainer cont = TestContainer.Common.INSTANCE;

    private AccountService accountService;
    private CustomerService customerService;
    private AppProperties appProperties;

    @Before
    public void setUp() {
        accountService = cont.getBean(AccountService.class);
        customerService = cont.getBean(CustomerService.class);
        appProperties = cont.getBean(AppProperties.class);
    }

    @Test
    public void testShouldCreateAccount() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("TEST_NAME_1");
        customer.setLastName("TEST_LAST_NAME_1");

        Customer savedCustomer = customerService.createCustomer(customer);

        Account account = new Account();
        account.setAccountNumber("999");
        account.setType(AccountType.CHECKING.toString());
        account.setDescription("Just test account");
        account.setCurrencyCode(CurrencyUnit.USD.toString());
        account.setBalance(new BigDecimal(500000));

        Account savedAccount = accountService.createAccount(account, savedCustomer.getCustomerId());

        assertNotNull(savedAccount);
        assertEquals(account.getAccountNumber(), savedAccount.getAccountNumber());
    }

    @Test(expected = IllegalAccountTypeException.class)
    public void testShouldThrowExceptionIfAccountOfWrongType() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("TEST_NAME_2");
        customer.setLastName("TEST_LAST_NAME_2");

        Customer savedCustomer = customerService.createCustomer(customer);

        Account account = new Account();
        account.setAccountNumber("998");
        account.setType("UNKNOWN");
        account.setDescription("Just test account");
        account.setCurrencyCode(CurrencyUnit.USD.toString());
        account.setBalance(new BigDecimal(500000));

        Account savedAccount = accountService.createAccount(account, savedCustomer.getCustomerId());

        assertNotNull(savedAccount);
        assertEquals(savedAccount.getAccountNumber(), account.getAccountNumber());
    }

    @Test
    public void testAddCustomerToAccount() throws Exception {
        Customer customer1 = new Customer();
        customer1.setFirstName("TEST_NAME_3");
        customer1.setLastName("TEST_LAST_NAME_3");

        Customer customer2 = new Customer();
        customer2.setFirstName("TEST_NAME_4");
        customer2.setLastName("TEST_LAST_NAME_4");

        Customer savedCustomer1 = customerService.createCustomer(customer1);
        Customer savedCustomer2 = customerService.createCustomer(customer2);

        Account account = new Account();
        account.setAccountNumber("997");
        account.setType(AccountType.CHECKING.toString());
        account.setDescription("Just test account");
        account.setCurrencyCode(CurrencyUnit.USD.toString());
        account.setBalance(new BigDecimal(500000).setScale(appProperties.getAppCurrencyScale(),
                                                                    appProperties.getAppRoundingMode()));

        Account savedAccount = accountService.createAccount(account, savedCustomer1.getCustomerId());

        accountService.addCustomerToAccount(savedCustomer2.getCustomerId(), savedAccount.getAccountNumber());

        List<Account> accounts = accountService.getAccountsOfCustomer(savedCustomer2.getCustomerId());

        assertTrue(accounts.contains(savedAccount));
    }
}
