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
 