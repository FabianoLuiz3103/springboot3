-- 12345678
INSERT INTO usuarios (email, senha) VALUES ('fabianojesus1991@gmail.com', '$2a$12$.bPF9WkGJjzfKsxe10UhFeu5wyPj8LYVKVKm7ZRv6jjC.zkkAHg5W');
-- 310331
INSERT INTO usuarios (email, senha) VALUES ('faah772@gmail.com', '$2a$12$K8vGtJq3q4fz7BRRXSOg/u.txCUIv3pXE1LBsJZPWoeFLhXs9mUDS');

INSERT INTO roles (name) VALUES ('ROLE_ADMINISTRATOR');
INSERT INTO roles (name) VALUES ('ROLE_CUSTOMER');


INSERT INTO users_roles (role_id, user_id) VALUES (1,1);
INSERT INTO users_roles (role_id, user_id) VALUES (2,2);


INSERT INTO tbl_departamento (nome) VALUES ('Tecnologia da Informação (TI)')
INSERT INTO tbl_departamento (nome) VALUES ('Recursos Humanos (RH)')
INSERT INTO tbl_colaborador (primeiro_nome, sobrenome, cpf, email, telefone, data_admissao, avatar, sts_integracao, qtd_respondidas, pontuacao, qtd_certas, porc_progresso, porc_acertos, id_departamento) VALUES ('João', 'Silva', '123.456.789-00', 'fabianojesus1991@gmail.com', '123456789', '2024-06-18', 'avatar1.png', 'integrado', 50, 80, 40, 75.0, 50.0, 1);
INSERT INTO tbl_colaborador (primeiro_nome, sobrenome, cpf, email, telefone, data_admissao, avatar, sts_integracao, qtd_respondidas, pontuacao, qtd_certas, porc_progresso, porc_acertos, id_departamento) VALUES ('Fabiano', 'L.S.S', '122.433.789-11', 'fabiano331@gmail.com', '123456889', '2024-06-18', 'avatar200.png', 'integrado', 50, 80, 40, 75.0, 50.0, 2);
INSERT INTO tbl_colaborador_rh (email_rh, id_colaborador) VALUES ('faah772@gmail.com', 2)