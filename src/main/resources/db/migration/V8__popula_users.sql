-- Migration para inserção de dados na tabela users
INSERT INTO users (user_name, full_name, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES ('admin', 'Administrador', '$2a$10$H3Z/uhj1Edw44epByNoqoueKnq27WmCJC03HCA646QMjMS0uVE96a', TRUE, TRUE, TRUE, TRUE);
--username: admin
--password: admin