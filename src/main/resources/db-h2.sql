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
DROP SEQUENCE IF EXISTS seq_account_id
;

CREATE SEQUENCE seq_customer_id START WITH 1
;

CREATE TABLE customer (
  customer_id LONG PRIMARY KEY NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(50),

  CONSTRAINT pk_customer PRIMARY KEY (customer_id)
)
;
CREATE UNIQUE INDEX idx_ce ON customer(first_name, last_name);


CREATE TABLE account (
  account_id LONG PRIMARY KEY NOT NULL,
  type VARCHAR(50) NOT NULL,
  description VARCHAR(50),
  balance DECIMAL(19,4) NOT NULL DEFAULT 0,
  credit_line DECIMAL(19,4) NOT NULL DEFAULT 0,

  CONSTRAINT pk_account PRIMARY KEY (account_id)
)
;

CREATE TABLE customer_account_link (
  customer_id LONG NOT NULL,
  account_id LONG NOT NULL,

  CONSTRAINT pk_c2a PRIMARY KEY(customer_id, account_id),
  CONSTRAINT fk_c2a_customer_id FOREIGN KEY (customer_id)
                                REFERENCES customer (customer_id)
                                ON DELETE CASCADE,
  CONSTRAINT fk_c2a_account_id FOREIGN KEY (account_id)
                               REFERENCES account (account_id)
                               ON DELETE CASCADE,
)
;


CREATE TABLE transaction_log (
  tx_id LONG PRIMARY KEY NOT NULL,
  account_id LONG NOT NULL,
  amount DECIMAL(19,4) NOT NULL,
  balance DECIMAL(19,4) NOT NULL,
  time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),

  CONSTRAINT pk_transaction_log PRIMARY KEY (tx_id),
  CONSTRAINT fk_tx2a_account_id FOREIGN KEY (account_id)
                                REFERENCES account (account_id),
)
;