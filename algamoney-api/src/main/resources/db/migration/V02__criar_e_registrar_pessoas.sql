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
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('Ronaldo Total', true, 'Av. Corinthians ', '2007', 'Apto 33', 'Bairro do Sao Jorge', '12.444.666', 'São Paulo', 'SP');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('Alexandre Pato', false, 'Av. Internacional ', '2011', 'Apto 78', 'Bairro do Beira Rio', '12.444.879', 'Porto Alegre', 'RS');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('Paolo Maldini', true, 'Av. Milan ', '2045', 'Apto 56', 'Bairro do Milan', '12.444.412', 'Milano', 'RJ');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('Zinidine Zidane', false, 'Av. Real Madrid ', '4578', 'Apto 78', 'Bairro do Merengue', '12.789.412', 'Madrid', 'SP');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('Philipe Coutinho', true, 'Av. Barcelona ', '457', 'Apto 78', 'Bairro do Munich', '12.456.412', 'Rio de Janeiro', 'RJ');