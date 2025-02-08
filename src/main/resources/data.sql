INSERT INTO CURRENCY (MULTIPLIER, SYMBOL)
VALUES (100, 'BGN'),
       (100, 'RON');

INSERT INTO TAX_SCHEME (NAME)
VALUES ('BG_VAT'),
       ('RO_VAT');

INSERT INTO PUBLIC.TAX_CATEGORY (PERCENT, NAME)
VALUES (9, 'VAT_9'),
       (19, 'VAT_19'),
       (20, 'VAT_20');

INSERT INTO PUBLIC.TAX_SCHEME_TAX_CATEGORIES (TAX_CATEGORIES_NAME, TAX_SCHEME_NAME)
VALUES ('VAT_19', 'RO_VAT'),
       ('VAT_20', 'BG_VAT'),
       ('VAT_9', 'BG_VAT'),
       ('VAT_9', 'RO_VAT');

INSERT INTO MARKET (CURRENCY_SYMBOL, NAME, TAX_SCHEME_NAME)
VALUES ('BGN', 'BG', 'BG_VAT'),
       ('RON', 'RO', 'RO_VAT');

INSERT INTO STORE (MARKET_NAME, NAME)
VALUES ('RO', 'ELECT.RO'),
       ('RO', 'FRESH.RO'),
       ('BG', 'ELECT.BG');

INSERT INTO STORE_SECTION (STORE_NAME, DESCRIPTION, NAME)
VALUES ('ELECT.RO', 'Descriere atractivă a categoriei telefoane mobile', 'Telefoane Mobile'),
       ('ELECT.BG', 'Привлекателно описание на категорията мобилни телефони', 'Мобилни телефони'),
       ('FRESH.RO', 'Descriere generică a categoriei legume proaspete', 'Legume Proaspete');