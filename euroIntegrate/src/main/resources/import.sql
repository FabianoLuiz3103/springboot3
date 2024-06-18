-- 12345678
INSERT INTO usuarios (email, senha) VALUES ('fabianojesus1991@gmail.com', '$2a$12$.bPF9WkGJjzfKsxe10UhFeu5wyPj8LYVKVKm7ZRv6jjC.zkkAHg5W');
-- 310331
INSERT INTO usuarios (email, senha) VALUES ('faah772@gmail.com', '$2a$12$K8vGtJq3q4fz7BRRXSOg/u.txCUIv3pXE1LBsJZPWoeFLhXs9mUDS');

INSERT INTO roles (name) VALUES ('ROLE_ADMINISTRATOR');
INSERT INTO roles (name) VALUES ('ROLE_CUSTOMER');


INSERT INTO users_roles (role_id, user_id) VALUES (1,1);
INSERT INTO users_roles (role_id, user_id) VALUES (2,2);