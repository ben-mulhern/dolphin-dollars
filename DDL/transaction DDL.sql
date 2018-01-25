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