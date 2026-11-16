-- Criando o Banco de Dados
DROP DATABASE IF EXISTS DB_STORAGE;

-- Criando a base da dados e usando como principal
CREATE DATABASE DB_STORAGE;
USE DB_STORAGE;

-- Tabela de Fornecedor (TB_SGE_FORNECEDOR)
CREATE TABLE TB_SGE_FORNECEDOR(
	id_fornecedor 	INT 		NOT NULL,
    razao_social 	VARCHAR(80) NOT NULL,
    nr_cnpj			CHAR(14)	NOT NULL,
    nr_tel			CHAR(14)	NOT NULL
);

-- Tabela de Categoria (TB_SGE_CATEGORIA)
CREATE TABLE TB_SGE_CATEGORIA(
	id_categoria	INT			 NOT NULL,
    nm_categoria	VARCHAR(80)  NOT NULL,
    ds_categoria	VARCHAR(200) NOT NULL
);

-- Tabela de Produtos (TB_SGE_PRODUTO)
CREATE TABLE TB_SGE_PRODUTO(
	id_produto		INT			NOT NULL,
    id_categoria	INT			NOT NULL,
    id_fornecedor	INT			NOT NULL,
    nm_produto		VARCHAR(80) NOT NULL,
    qt_produto		INT			NOT NULL,
    qt_estoque_min	INT			NOT NULL
);

-- Tabela de Movimentação (TB_SGE_MOVIMENTACAO)
CREATE TABLE TB_SGE_MOVIMENTACAO(
	id_movimentacao INT			NOT NULL,
    id_produto		INT			NOT NULL,
    id_usuario		INT			NOT NULL,
    tp_movimentacao	CHAR(1)		NOT NULL,
    qt_movimentacao	INT			NOT NULL,
    dt_movimentacao	DATETIME	NOT NULL
);

-- Tabela de Cargo (TB_SGE_CARGO)
CREATE TABLE TB_SGE_CARGO(
	id_cargo 	INT 		NOT NULL,
    nm_cargo	VARCHAR(60) NOT NULL
);

-- Tabela de Usuario (TB_SGE_USUARIO)
CREATE TABLE TB_SGE_USUARIO(
	id_usuario	INT			NOT NULL,
    id_cargo	INT			NOT NULL,
    nm_usuario	VARCHAR(80)	NOT NULL,
    nr_cpf		CHAR(11)	NOT NULL,
    nr_tel		CHAR(14)	NOT NULL
);

-- Tabela de Login (TB_SGE_LOGIN)
CREATE TABLE TB_SGE_LOGIN(
	id_login	INT			NOT NULL,
    id_usuario	INT			NOT NULL,
    usr_login	VARCHAR(80)	NOT NULL,
    usr_senha	VARCHAR(60)	NOT NULL,
    st_login	CHAR(2)		NOT NULL
);

-- FK do Fornecedor (TB_SGE_FORNECEDOR)
ALTER TABLE TB_SGE_FORNECEDOR
	ADD CONSTRAINT PRIMARY KEY (id_fornecedor),
    MODIFY COLUMN id_fornecedor INT NOT NULL AUTO_INCREMENT,
    -- Torna o CNPJ e Telefoen únicos
    ADD CONSTRAINT UN_CNPJ_FORNECEDOR UNIQUE (nr_cnpj),
    ADD CONSTRAINT UN_TELEFONE_FORNECEDOR UNIQUE (nr_tel);

-- FK da Categoria (TB_SGE_CATEGORIA)
ALTER TABLE TB_SGE_CATEGORIA
	ADD CONSTRAINT PRIMARY KEY (id_categoria),
    MODIFY COLUMN id_categoria INT NOT NULL AUTO_INCREMENT;
    
-- FK do Produto (TB_SGE_PRODUTO)
ALTER TABLE TB_SGE_PRODUTO
	ADD CONSTRAINT PRIMARY KEY (id_produto),
	MODIFY COLUMN id_produto INT NOT NULL AUTO_INCREMENT,
    -- FK oriunda da CATEGORIA
    ADD CONSTRAINT FK_CATEGORIA_PRODUTO FOREIGN KEY (id_categoria)
		REFERENCES TB_SGE_CATEGORIA(id_categoria),
	-- FK oriunda do FORNECEDOR
	ADD CONSTRAINT FK_FORNECEDOR_PRODUTO FOREIGN KEY (id_fornecedor)
		REFERENCES TB_SGE_FORNECEDOR(id_fornecedor);
        
-- FK da Movimentação (TB_SGE_MOVIMENTACAO)
ALTER TABLE TB_SGE_MOVtb_sge_movimentacaoIMENTACAO
	ADD CONSTRAINT PRIMARY KEY (id_movimentacao),
    MODIFY COLUMN id_movimentacao INT NOT NULL AUTO_INCREMENT,
    -- FK oriunda do PRODUTO
    ADD CONSTRAINT FK_PRODUTO_MOVIMENTACAO FOREIGN KEY (id_produto)
		REFERENCES TB_SGE_PRODUTO (id_produto),
	-- FK oriunda do USUARIO
	ADD CONSTRAINT FK_USUARIO_MOVIMENTACAO FOREIGN KEY (id_usuario)
		REFERENCES TB_SGE_USUARIO (id_usuario),
	-- CHECK para verificação de "Entrada" e "Saída"
	ADD CONSTRAINT CK_TIPO_MOVIMENTACAO CHECK(tp_movimentacao IN('E', 'S'));
    
-- FK do Cargo (TB_SGE_CARGO)
ALTER TABLE TB_SGE_CARGO
	ADD CONSTRAINT PRIMARY KEY (id_cargo),
    MODIFY COLUMN id_cargo INT NOT NULL AUTO_INCREMENT;
    
-- FK do Usuario (TB_SGE_USUARIO)
ALTER TABLE TB_SGE_USUARIO
	ADD CONSTRAINT PRIMARY KEY (id_usuario),
    MODIFY COLUMN id_usuario INT NOT NULL AUTO_INCREMENT,
    -- FK oriunda do CARGO
    ADD CONSTRAINT FK_CARGO_USUARIO FOREIGN KEY (id_cargo)
		REFERENCES TB_SGE_CARGO (id_cargo),
	-- UN oriunda CPF e Telefone
    ADD CONSTRAINT UN_CPF_USUARIO UNIQUE (nr_cpf),
    ADD CONSTRAINT UN_TELEFONE_USUARIO UNIQUE (nr_tel);
    
-- FK do Login (TB_SGE_LOGIN)
ALTER TABLE TB_SGE_LOGIN
	ADD CONSTRAINT PRIMARY KEY (id_login),
    MODIFY COLUMN id_login INT NOT NULL AUTO_INCREMENT,
    -- FK oriunda do USUARIO
    ADD CONSTRAINT FK_LOGIN_USUARIO FOREIGN KEY (id_usuario)
		REFERENCES TB_SGE_USUARIO (id_usuario),
	-- CHECK de verificação "Ativo" e "Inativo"
    ADD CONSTRAINT CK_STATUS_LOGIN CHECK(st_login IN('AT', 'IN'));