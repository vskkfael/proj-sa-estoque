-- Verifica se ele existe e faz a exclusão caso existam
DROP DATABASE IF EXISTS DB_STORAGE;

-- Criando a base da dados e usando como principal
CREATE DATABASE DB_STORAGE;
USE DB_STORAGE;

-- Tabela de Fornecedor (TB_SGE_FORNECEDOR)
CREATE TABLE TB_SGE_FORNECEDOR (
    id_fornecedor 	INT 		NOT NULL,
    razao_social 	VARCHAR(80) NOT NULL,
    nr_cnpj 		CHAR(14) 	NOT NULL,
    nr_tel 			CHAR(14) 	NULL
);

-- Tabela de Categoria (TB_SGE_CATEGORIA)
CREATE TABLE TB_SGE_CATEGORIA (
    id_categoria INT 		  NOT NULL,
    nm_categoria VARCHAR(80)  NOT NULL,
    ds_categoria VARCHAR(200) NOT NULL
);

-- Tabela de Produtos (TB_SGE_PRODUTO)
CREATE TABLE TB_SGE_PRODUTO (
    id_produto 		INT 		NOT NULL,
    id_categoria 	INT 		NOT NULL,
    id_fornecedor 	INT 		NOT NULL,
    nm_produto 		VARCHAR(80) NOT NULL,
    qt_estoque_min 	INT 		NOT NULL
);

-- Tabela de Movimentação (TB_SGE_MOVIMENTACAO)
CREATE TABLE TB_SGE_MOVIMENTACAO (
    id_movimentacao 	INT 		NOT NULL,
    id_produto 			INT 		NOT NULL,
    id_usuario		 	INT 		NOT NULL,
    tp_movimentacao 	CHAR(1) 	NOT NULL,
    qt_movimentacao 	INT 		NOT NULL,
    dt_movimentacao 	DATETIME 	NOT NULL
);

-- Tabela de Cargo (TB_SGE_CARGO)
CREATE TABLE TB_SGE_CARGO(
	id_cargo 	 INT 		 NOT NULL,
    nm_cargo	 VARCHAR(60) NOT NULL,
    nv_permissao CHAR(2)	 NOT NULL
);

-- Tabela de Usuario (TB_SGE_USUARIO)
CREATE TABLE TB_SGE_USUARIO(
	id_usuario	INT				NOT NULL,
    id_cargo	INT				NOT NULL,
    nm_usuario	VARCHAR(80)		NOT NULL,
    ds_login	VARCHAR(60) 	NOT NULL,
    ds_senha	VARCHAR(200)	NOT NULL,
    nr_cpf		CHAR(11)		NOT NULL,
    nr_tel		CHAR(14)		NOT NULL
);

-- Tabela de Matéria Prima (TB_SGE_MATERIA)
CREATE TABLE TB_SGE_MATERIA(
	id_materia			INT			NOT NULL,
    nm_materia			VARCHAR(80)	NOT NULL,
    tp_unidade_medida	VARCHAR(10) NOT NULL
);

-- Tabela de Lote (TB_SGE_LOTE)
CREATE TABLE TB_SGE_LOTE(
	id_lote			INT			NOT NULL,
    id_fornecedor 	INT			NOT NULL,
    id_materia		INT			NOT NULL,
    dt_entrega		DATETIME	NOT NULL
);
	
-- Tabela de Suporte (TB_SGE_SUPORTE)
CREATE TABLE TB_SGE_SUPORTE(
	id_suporte	INT				NOT NULL,
    id_usuario	INT				NOT NULL,
    tp_suporte	VARCHAR(200)	NOT NULL,
    ds_queixa	VARCHAR(255)	NOT NULL,
    dt_suporte	DATETIME		NOT NULL
    
);

CREATE TABLE TB_A_SGE_MATERIA_PRODUTO(
	id_produto	INT	NOT NULL,
    id_materia	INT	NOT NULL
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
ALTER TABLE TB_SGE_MOVIMENTACAO
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
    
    -- FK da Matéria Prima (TB_SGE_MATERIA)
    ALTER TABLE TB_SGE_MATERIA
		ADD CONSTRAINT PRIMARY KEY(id_materia),
        MODIFY COLUMN id_materia INT NOT NULL AUTO_INCREMENT;
        
	-- FK do Lote (TB_SGE_LOTE)
    ALTER TABLE TB_SGE_LOTE
		ADD CONSTRAINT PRIMARY KEY (id_lote),
        MODIFY COLUMN id_lote INT NOT NULL AUTO_INCREMENT,
		-- FK oriunda Fornecedor
        ADD CONSTRAINT FK_FORNECEDOR_LOTE FOREIGN KEY (id_fornecedor)
			REFERENCES TB_SGE_FORNECEDOR (id_fornecedor),
		-- FK oriunda Materia Prima
		ADD CONSTRAINT FK_MATERIA_LOTE FOREIGN KEY (id_materia)
			REFERENCES TB_SGE_MATERIA (id_materia);
            
	-- FK do Suporte (TB_SGE_SUPORTE)
    ALTER TABLE TB_SGE_SUPORTE
		ADD CONSTRAINT PRIMARY KEY (id_suporte),
        MODIFY COLUMN id_suporte INT NOT NULL AUTO_INCREMENT,
        -- FK oriunda Usuario
        ADD CONSTRAINT FK_USUARIO_SUPORTE FOREIGN KEY (id_usuario)
			REFERENCES TB_SGE_USUARIO (id_usuario);
            
	-- Associativa FK do Materia Prima & Produto (TB_A_SGE_MATERIA_PRODUTO)
    ALTER TABLE TB_A_SGE_MATERIA_PRODUTO
		ADD CONSTRAINT PRIMARY KEY (id_produto, id_materia),
		-- FK da Materia Prima
		ADD CONSTRAINT PF_MATERIA_A_MATERIA_PRODUTO FOREIGN KEY (id_materia)
			REFERENCES TB_SGE_MATERIA (id_materia),
		-- FK do Produto
        ADD CONSTRAINT PF_PRODUTO_A_MATERIA_PRODUTO FOREIGN KEY (id_produto)
			REFERENCES TB_SGE_PRODUTO (id_produto);