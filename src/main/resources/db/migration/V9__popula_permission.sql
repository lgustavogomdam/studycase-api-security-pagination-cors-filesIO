-- Migration para inserção de dados na tabela permission
INSERT INTO permission (type_permission)
VALUES
    ('ADMIN'),
    ('USER');
