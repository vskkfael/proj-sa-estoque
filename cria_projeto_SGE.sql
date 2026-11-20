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