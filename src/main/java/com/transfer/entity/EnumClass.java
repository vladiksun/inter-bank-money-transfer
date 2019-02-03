package com.transfer.entity;

/**
 * Interface to be implemented by enums that has a field to be stored in data base
 * @param <T> type of value stored in the database
 *
 */
public interface EnumClass<T> {
    T getId();
}