CREATE TABLE IF NOT EXISTS users (

    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    user_name varchar(255) NOT NULL UNIQUE,
    full_name varchar(255) DEFAULT NULL,
    password varchar(255) NOT NULL,
    account_non_expired BOOLEAN DEFAULT FALSE,
    account_non_locked BOOLEAN DEFAULT FALSE,
    credentials_non_expired BOOLEAN DEFAULT FALSE,
    enabled BOOLEAN DEFAULT FALSE
);