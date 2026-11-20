package com.estoque;

import com.estoque.view.CategoriaView;
import com.estoque.view.FornecedorView;
import com.estoque.view.ProdutoView;

public class Main {
    public static void main(String[] args) {
        FornecedorView fornecedorView = new FornecedorView();
        fornecedorView.exibirMenu();
        
        CategoriaView categoriaView = new CategoriaView();
        categoriaView.exibirMenu();

        ProdutoView produtoView = new ProdutoView();
        produtoView.exibirMenu();
    }
}