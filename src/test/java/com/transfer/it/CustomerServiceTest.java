package com.transfer.it;

import com.testsupport.TestContainer;
import com.transfer.entity.Customer;
import com.transfer.exception.InvalidParameterException;
import com.transfer.service.CustomerService;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

public class CustomerServiceTest {

    @ClassRule
    public static TestContainer cont = TestContainer.Common.INSTANCE;

    private CustomerService customerService;

    @Before
    public void setUp() {
        customerService = cont.getBean(CustomerService.class);
    }

    @Test
    public void testCreateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("TEST_NAME_1");
        customer.setLastName("TEST_LAST_NAME_1");

        Customer savedCustomer = customerService.createCustomer(customer);

        assertNotNull(savedCustomer);
        assertEquals(customer.getFirstName(), savedCustomer.getFirstName());
        assertEquals(customer.getLastName(), savedCustomer.getLastName());
    }

    @Test(expected = InvalidParameterException.class)
    public void testShouldThrowExceptionIfFirstNameIsNotSpecified() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName(" ");
        customer.setLastName("TEST_LAST_NAME_2");

        customerService.createCustomer(customer);
    }

    @Test(expected = InvalidParameterException.class)
    public void testShouldThrowExceptionIfLastNameIsNotSpecified() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("TEST_NAME_3");
        customer.setLastName("");

        customerService.createCustomer(customer);
    }
}
