INSERT INTO PERMISSAO (ID, NOME, ROLE) VALUES ((SELECT NEXTVAL('SEQ_PERMISSAO')), 'ADMINISTRADOR', 'ADMIN');
INSERT INTO PERMISSAO (ID, NOME, ROLE) VALUES ((SELECT NEXTVAL('SEQ_PERMISSAO')), 'SUPORTE', 'SUPORTE');

INSERT INTO USUARIO (ID, CPF, EMAIL, NOME, SENHA, FK_PERMISSAO) VALUES (1, '05644172970', 'JOAO@USUARIO.COM', 'JOAO DA SILVA', '$2a$10$e4I0XXj8o0/UVVnmPnH6qOzqgqKPASnJG5rirWV4Lnml8xkbJcQ/2', (SELECT ID FROM PERMISSAO WHERE ROLE = 'ADMIN'));