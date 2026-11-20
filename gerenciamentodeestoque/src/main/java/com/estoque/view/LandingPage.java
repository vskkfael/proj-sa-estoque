package com.estoque.view;

import java.util.Scanner;

public class LandingPage {
        private final Scanner scanner;
        private final FornecedorView fornecedorView;
        private final CategoriaView categoriaView;
        private final ProdutoView produtoView;

        public LandingPage(){
            this.scanner = new Scanner(System.in, "UTF-8");
            this.fornecedorView = new FornecedorView();
            this.categoriaView = new CategoriaView();
            this.produtoView = new ProdutoView();
        }

        public void menuSistema(){
            int opcao = -1;

            while (opcao != 0) {
            System.out.println("\n========== MENU ==========");
                System.out.println("1. Fornecedor");
                System.out.println("2. Categoria");
                System.out.println("3. Produto");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");

                try {
                    opcao = Integer.parseInt(scanner.nextLine());
                    switch (opcao) {
                        case 1 -> fornecedorView.exibirMenu();
                        case 2 -> categoriaView.exibirMenu();
                        case 3 -> produtoView.exibirMenu();
                        case 0 -> System.out.println("Saindo do sistema...");
                        default -> System.out.println("Opção inválida! Tente novamente!");
                    }
                } catch (NumberFormatException e){
                    System.out.println("Erro: Digite apenas números válidos.");
                } catch (Exception e){
                    System.out.println("Erro na operação: " + e.getMessage());
                }
        }
    }
}
