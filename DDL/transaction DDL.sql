CREATE TABLE transaction                                                          
(                                                                              
        --Primary Key                                                                                         
        PRIMARY KEY (transaction_id),                                             
                                                                                 
        --Columns                                                              
        transaction_id INT(10) NOT NULL GENERATED ALWAYS AS IDENTITY UNIQUE,
		description VARCHAR(50) NULL,
		time_stamp TIMESTAMP,
		effective_date DATE,
                             
                                                                                 
        CONSTRAINT TRAN_UNIQUE                                     
        UNIQUE (short_code_identifier)                                                                              