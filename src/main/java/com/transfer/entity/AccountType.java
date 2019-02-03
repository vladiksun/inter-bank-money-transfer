package com.transfer.entity;

public enum AccountType implements EnumClass<String> {
    CHECKING("CHECKING"),
    SAVINGS("SAVINGS");

    private String id;

    AccountType(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    public static AccountType fromId(String id) {
        for (AccountType at : AccountType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
