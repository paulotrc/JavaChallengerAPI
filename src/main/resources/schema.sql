DROP TABLE IF EXISTS usuario;

CREATE TABLE TB_USUARIO (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              login VARCHAR(250) NOT NULL,
                              email VARCHAR(70) NOT NULL,
                              nome VARCHAR(250) NOT NULL,
                              senha VARCHAR(25) NOT NULL,
                              ts_created DATE ,
                              ts_modified DATE,
                              ts_last_login DATE
);

CREATE TABLE TB_TELEFONE_USUARIO (
                         id INT AUTO_INCREMENT  PRIMARY KEY,
                         numero VARCHAR(250) NOT NULL,
                         ddd VARCHAR(70) NOT NULL,
                         usuario_id INT NOT NULL
);