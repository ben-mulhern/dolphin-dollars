CREATE TABLE currency
(

	currency_code CHAR(3) 
		NOT NULL
		PRIMARY KEY,
	
	symbol CHAR(1) 
		NOT NULL,
	
	description VARCHAR(50),
	
	active CHAR(1) 
		NOT NULL 
		DEFAULT 'Y' 
		CHECK ACTIVE IN ('Y','N')

)
