CREATE TABLE currency
(

    PRIMARY KEY (currency_code),
    
    currency_code CHAR(3)
    NOT NULL,
    
    symbol CHAR(1)
    NOT NULL
    DEFAULT  ' ',
    
    description VARCHAR(50)
    NOT NULL
    CHECK (description <> ' '),
    
    active CHAR(1) 
    NOT NULL 
    DEFAULT 'Y' 
    CHECK active IN ('Y','N')

);

CREATE TABLE ledger
(
  PRIMARY KEY (ledger_id),

  ledger_id CHAR(10) NOT NULL
    CONSTRAINT ledger_id_not_blank
    CHECK (ledger_id <> ''),

  description VARCHAR(50) NOT NULL
    CONSTRAINT description_not_blank
    CHECK (description <> '')
              
);
 

CREATE TABLE user_detail                                                          
(                                                                              
--Primary Key                                                                                         
  PRIMARY KEY (user_id),                                             
                                                                         
--Columns                                                              
  user_id VARCHAR(50) NOT NULL,
  name VARCHAR(50) NOT NULL DEFAULT ' ',
  password VARCHAR(200) NOT NULL,
  salt CHAR(64) NOT NULL,
  active CHAR(1) NOT NULL DEFAULT 'Y',
  email_address VARCHAR(200) NOT NULL,
  admin CHAR(1) NOT NULL DEFAULT 'N',
  
  --Constraints
  CONSTRAINT active_is_yes_or_no
  CHECK (active IN ('Y','N')),

  CONSTRAINT admin_is_yes_or_no
  CHECK (admin IN ('Y','N'))

);                                                                            

CREATE TABLE block                                                          
(                                                                              
--Primary Key                                                                                         
  PRIMARY KEY (block_id),                                             
                                                                         
--Columns                                                              
  block_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  description VARCHAR(200) NOT NULL DEFAULT ' ',
  time_stamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  effective_date DATE NOT NULL DEFAULT CURRENT_DATE,

--Constraints
  CONSTRAINT positive_block_id CHECK (block_id > 0)
);                                                                           

CREATE TABLE block_detail                                                          
(                                                                              
--Primary Key                                                                                         
  PRIMARY KEY (block_id,entry_number),                                             
                                                                         
--Columns                                                              
  block_id INT NOT NULL,
  entry_number INT NOT NULL,
  cash_value NUMERIC(10,2) NOT NULL DEFAULT 0,
  user_id INT NOT NULL,
  block_detail_description VARCHAR(200) NOT NULL DEFAULT ' ',
  written_off CHAR(1) NOT NULL DEFAULT 'Y',
  currency_code CHAR(3) NOT NULL,
  ledger_id CHAR(10) NOT NULL,

--Constraints
  CONSTRAINT written_off_is_yes_or_no
  CHECK (written_off IN ('Y','N')),
  
  CONSTRAINT foreign_key_block_id
  FOREIGN KEY (block_id)
  REFERENCES block (block_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT,
  
  CONSTRAINT foreign_key_user_id
  FOREIGN KEY (user_id)
  REFERENCES user_detail (user_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT,
  
  CONSTRAINT foreign_key_ledger_id
  FOREIGN KEY (ledger_id)
  REFERENCES ledger (ledger_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT
);                                                                           