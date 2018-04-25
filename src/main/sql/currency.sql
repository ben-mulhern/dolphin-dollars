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
    CHECK description <> ' ' ,
	
    active CHAR(1) 
    NOT NULL 
    DEFAULT 'Y' 
    CHECK active IN ('Y','N')

);