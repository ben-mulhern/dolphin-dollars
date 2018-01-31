CREATE TABLE currency
(

	PRIMARY KEY PK_currency_code (currency_code),
	
	currency_code CHAR(3)
		NOT NULL,
	
	symbol CHAR(1)
		DEFAULT ' '
		NOT NULL,
	
	description VARCHAR(50)
		CHECK description <> ' ' 
		NOT NULL,
	
	active CHAR(1) 
		NOT NULL 
		DEFAULT 'Y' 
		CHECK ACTIVE IN ('Y','N')

);
