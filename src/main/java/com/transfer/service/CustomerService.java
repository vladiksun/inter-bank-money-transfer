package com.transfer.service;

import com.transfer.entity.Customer;
import com.transfer.exception.ApplicationException;

import java.util.List;

public interface CustomerService {

    /**
     * Creates customer
     * @param customer
     * @return created customer
     * @throws ApplicationException
     */
    Customer createCustomer(Customer customer) throws ApplicationException;

    /**
     * Soft deletes customer
     * @param customerId
     * @return customer
     * @throws ApplicationException
     */
    Customer softDeleteCustomer(long customerId) throws ApplicationException;

    /**
     * Get customer by id
     * @param customerId
     * @return customer
     * @throws ApplicationException
     */
    Customer getCustomerById(long customerId) throws ApplicationException;

    /**
     * Get customers of account
     * @param accountNumber
     * @return <code>List<Customer></code>
     * @throws ApplicationException
     */
    List<Customer> getCustomersOfAccount(String accountNumber) throws ApplicationException;

    /**
     * Get customer by the last and first name
     * @param firstName
     * @param lastName
     * @return <code>List<Customer></code>
     * @throws ApplicationException
     */
    Customer getCustomersByFirstAndLastName(String firstName, String lastName) throws ApplicationException;

}
