CREATE TABLE block_detail                                                          
(                                                                              
--Primary Key                                                                                         
  PRIMARY KEY (block_id,entry_number),                                             
                                                                         
--Columns                                                              
  block_id INT NOT NULL,
  entry_number INT NOT NULL,
  cash_value NUMERIC(10,2) NOT NULL DEFAULT 0,
  user_id (whatever format user_detail has),
  block_detail_description VARCHAR(200) NOT NULL DEFAULT ' ',
  written_off CHAR(1) NOT NULL DEFAULT 'Y',
  currency_code CHAR(3) NOT NULL,
  ledger_id CHAR(10) NOT NULL,

--Constraints
  CONSTRAINT written_off_is_boolean
  CHECK written_off IN ('Y','N')
  
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
  ON UPDATE RESTRICT,
);                                                                           