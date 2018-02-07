CREATE TABLE user_detail                                                          
(                                                                              
--Primary Key                                                                                         
  PRIMARY KEY (user_id),                                             
                                                                         
--Columns                                                              
  user_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  name VARCHAR(50) NOT NULL DEFAULT ' ',
  salutation VARCHAR(50) NOT NULL DEFAULT ' ',
  password VARCHAR(200) NOT NULL,
  salt CHAR(64) NOT NULL,
  active CHAR(1) NOT NULL DEFAULT 'Y',
  email_address VARCHAR(200) NOT NULL,
  
  --Constraints
  CONSTRAINT active_is_yes_or_no
  CHECK active IN ('Y','N')

);                                                                           