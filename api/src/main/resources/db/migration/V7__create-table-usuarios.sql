CREATE TABLE usuarios (
id INTEGER NOT NULL,
login VARCHAR(100) NOT NULL UNIQUE,
senha VARCHAR(255) NOT NULL,

primary key(id)
);