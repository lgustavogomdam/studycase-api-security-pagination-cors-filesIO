-- Script to create the table 'medico'

CREATE TABLE IF NOT EXISTS medico (

    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL,
    codigo_crm VARCHAR(255) NOT NULL UNIQUE,
    especialidade_medica int NOT NULL
);