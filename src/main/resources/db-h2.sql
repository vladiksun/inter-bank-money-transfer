DROP TABLE IF EXISTS customer_account_link
;
DROP TABLE IF EXISTS customer
;
DROP TABLE IF EXISTS account
;
DROP TABLE IF EXISTS transaction_log
;
DROP SEQUENCE IF EXISTS seq_customer_id
;
DROP SEQUENCE IF EXISTS seq_account_number
;

CREATE SEQUENCE seq_customer_id START WITH 1
;

CREATE TABLE customer (
  customer_id LONG AUTO_INCREMENT PRIMARY KEY  NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(50),
  soft_deleted VARCHAR(50) DEFAULT NULL,

  CONSTRAINT pk_customer PRIMARY KEY (customer_id)
)
;
CREATE UNIQUE INDEX idx_ce ON customer(first_name, last_name);


CREATE TABLE account (
  account_number LONG PRIMARY KEY NOT NULL,
  type VARCHAR(50) NOT NULL,
  description VARCHAR(50),
  balance DECIMAL(19,4) NOT NULL DEFAULT 0,
  currency_code VARCHAR(5) NOT NULL,
  soft_deleted VARCHAR(50) DEFAULT NULL,

  CONSTRAINT pk_account PRIMARY KEY (account_number)
)
;

CREATE TABLE customer_account_link (
  customer_id LONG NOT NULL,
  account_number LONG NOT NULL,

  CONSTRAINT pk_c2a PRIMARY KEY(customer_id, account_number),
  CONSTRAINT fk_c2a_customer_id FOREIGN KEY (customer_id)
                                REFERENCES customer (customer_id)
                                ON DELETE CASCADE,
  CONSTRAINT fk_c2a_account_number FOREIGN KEY (account_number)
                               REFERENCES account (account_number)
                               ON DELETE CASCADE,
)
;


CREATE TABLE transaction_log (
  tx_id LONG AUTO_INCREMENT PRIMARY KEY  NOT NULL,
  account_number LONG NOT NULL,
  amount DECIMAL(19,4) NOT NULL,
  balance DECIMAL(19,4) NOT NULL,
  time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),

  CONSTRAINT pk_transaction_log PRIMARY KEY (tx_id),
  CONSTRAINT fk_tx2a_account_number FOREIGN KEY (account_number)
                                REFERENCES account (account_number)
)
;

INSERT INTO customer (customer_id, first_name, last_name, email, soft_deleted) VALUES (1, 'TEST_FIRST_NAME_1', 'TEST_LAST_NAME_1', 'TEST_EMAIL_1@gmail.com', NULL);
INSERT INTO account (account_number, type, description, balance, currency_code, soft_deleted) VALUES (1001, 'CHECKING', NULL, 100000, 'USD', NULL);
INSERT INTO account (account_number, type, description, balance, currency_code, soft_deleted) VALUES (1004, 'SAVINGS', NULL, 40000, 'USD', NULL);
INSERT INTO customer_account_link (customer_id, account_number) VALUES (1, 1001);
INSERT INTO customer_account_link (customer_id, account_number) VALUES (1, 1004);


INSERT INTO customer (customer_id, first_name, last_name, email, soft_deleted) VALUES (2, 'TEST_FIRST_NAME_2', 'TEST_LAST_NAME_2', 'TEST_EMAIL_2@gmail.com', NULL);
INSERT INTO account (account_number, type, description, balance, currency_code, soft_deleted) VALUES (1002, 'CHECKING', NULL, 100000, 'USD', NULL);
INSERT INTO account (account_number, type, description, balance, currency_code, soft_deleted) VALUES (1005, 'SAVINGS', NULL, 40000, 'USD', NULL);
INSERT INTO customer_account_link (customer_id, account_number) VALUES (2, 1002);
INSERT INTO customer_account_link (customer_id, account_number) VALUES (2, 1005);


INSERT INTO customer (customer_id, first_name, last_name, email, soft_deleted) VALUES (3, 'TEST_FIRST_NAME_3', 'TEST_LAST_NAME_3', 'TEST_EMAIL_3@gmail.com', NULL);
INSERT INTO account (account_number, type, description, balance, currency_code, soft_deleted) VALUES (1003, 'CHECKING', NULL, 100000, 'USD', NULL);
INSERT INTO account (account_number, type, description, balance, currency_code, soft_deleted) VALUES (1006, 'SAVINGS', NULL, 40000, 'USD', NULL);
INSERT INTO customer_account_link (customer_id, account_number) VALUES (2, 1003);
INSERT INTO customer_account_link (customer_id, account_number) VALUES (2, 1006);




