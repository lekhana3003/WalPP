create table users(
  id int PRIMARY KEY AUTO_INCREMENT,
  user_id int UNIQUE KEY,
  intial_balance FLOAT,
  balance FLOAT
);

create table transactions(
  id int PRIMARY KEY AUTO_INCREMENT,
  amount FLOAT,
  transaction_type VARCHAR(50),
  transactionId VARCHAR(20),
  transaction_details VARCHAR(50),
  user_id int,
  party_name VARCHAR(50),
  timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);