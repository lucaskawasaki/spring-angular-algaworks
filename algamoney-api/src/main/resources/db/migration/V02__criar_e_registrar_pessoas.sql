CREATE TABLE pessoa(
	ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL,
	logradouro VARCHAR(100),
	numero VARCHAR(10),
	complemento VARCHAR(100),
	bairro VARCHAR(100),
	cep VARCHAR(10),
	cidade VARCHAR(100),
	estado VARCHAR(2)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('Neymar Junior', true, 'Av. Champs Elysees', '3000', 'Apto 55', 'Centro', '12.222.444', 'Paris', 'SP');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('Thiago Neves', true, 'Av. Cruzeiro', '2013', 'Apto 44', 'Bairro da Rapousa', '12.333.555', 'Belo Horizonte', 'MG');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('Daniel Alvez', true, 'Av. Paulista', '2007', 'Apto 33', 'Bairro do Morumbi', '12.444.666', 'São Paulo', 'SP');