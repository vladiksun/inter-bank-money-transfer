package com.transfer.service;

import com.transfer.entity.Customer;
import com.transfer.exception.ApplicationException;

import java.util.List;

public interface CustomerService {
    //TODO: javadoc
    // makes a new customer and enters it into db,
    Customer createCustomer(Customer customer) throws ApplicationException;
    //TODO: javadoc
    // removes customer from db
    void removeCustomer(long customerId) throws ApplicationException;
    //TODO: javadoc
    // returns the details of a customer
    Customer getCustomerById(long customerId) throws ApplicationException;
    //TODO: javadoc
    List<Customer> getCustomersByAccount(long accountId) throws ApplicationException;

    //TODO: javadoc
    // returns an ArrayList of CustomerDetails objects
    // that correspond to the customers for the specified
    // last name; if now customers are found the ArrayList
    // is empty
    List<Customer> getCustomersByLastName(String lastName) throws ApplicationException;

}
