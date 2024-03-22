-- Criação da tabela endereco
CREATE TABLE IF NOT EXISTS endereco (

    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    numero INTEGER NOT NULL,
    complemento VARCHAR(255),
    bairro VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    uf INT NOT NULL,
    cep VARCHAR(9) NOT NULL,
    id_medico BIGINT,
    id_paciente BIGINT,
    FOREIGN KEY (id_medico) REFERENCES medico(id),
    FOREIGN KEY (id_paciente) REFERENCES paciente(id)
);