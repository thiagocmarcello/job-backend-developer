INSERT INTO PERMISSAO (ID, NOME, ROLE) VALUES (100, 'ADMINISTRADOR', 'ADMIN');
INSERT INTO PERMISSAO (ID, NOME, ROLE) VALUES (200, 'SUPORTE', 'SUPORTE');

INSERT INTO USUARIO (ID, CPF, EMAIL, NOME, SENHA, FK_PERMISSAO) VALUES (1, '05644172970', 'ADMIN@TESTE.COM.BR', 'JOAO DA SILVA', '$2a$10$e4I0XXj8o0/UVVnmPnH6qOzqgqKPASnJG5rirWV4Lnml8xkbJcQ/2', 100);