package com.estoque.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.estoque.controller.ProdutoController;
import com.estoque.model.Produto;

public class ProdutoView {
    private final ProdutoController controller;
    private final Scanner scanner;

    public ProdutoView() {
        this.controller = new ProdutoController();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu(){
    int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n========== GERENCIADOR DE PRODUTOS ==========");
                System.out.println("1. Cadastrar Produtos");
                System.out.println("2. Listar Produtos");
                System.out.println("3. Atualizar Produtos");
                System.out.println("4. Excluir Produto");
                System.out.println("5. Buscar por ID");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
        
        try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> cadastrar();
                    case 2 -> listar();
                    case 3 -> atualizar();
                    case 4 -> excluir();
                    case 5 -> buscarId(opcao);
                    case 0 -> System.out.println("Saindo do sistema...");
                    default -> System.out.println("Opção inválida! Tente novamente!");
                }
            } catch (NumberFormatException e){
                System.out.println("Erro: Digite apenas números válidos.");
            } catch (Exception e) {
                System.out.println("Erro na operação: " + e.getMessage());
            }
        }
   }

   public void cadastrar() throws SQLException {
        System.out.println("\n--- Cadastrar Produto ---");
        System.out.print("Categoria: ");
        int categoria = Integer.parseInt(scanner.nextLine());

        System.out.print("Fornecedor: ");
        int fornecedor = Integer.parseInt(scanner.nextLine());

        System.out.print("Produto: ");
        String produto = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

            controller.cadastrarProduto(categoria, fornecedor, produto, quantidade);
        System.out.println("Produto cadastrado com sucesso!");
   }

   public void listar() throws SQLException {
        System.out.println("\n--- Lista de Produtos ---");
        List<Produto> produto = controller.listarProduto();

        if (produto.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            produto.forEach(System.out::println);
        }
   }

   public void atualizar() throws SQLException {
        System.out.println("\n--- Atualizar Produto ---");
        System.out.print("Informe o ID do produto a atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Produto produtoExistente = controller.buscarPorID(id);
        if(produtoExistente == null){
            System.out.println("Produto não encontrado com o ID informado");
            return;
        }

        System.out.print("Nova Categoria (" + produtoExistente.getIdCategoria() + "): ");
        Integer categoria = Integer.parseInt(scanner.nextLine());
        if (categoria == null) categoria = produtoExistente.getIdCategoria();

        System.out.print("Novo Fornecedor (" + produtoExistente.getIdFornecedor() + "): ");
        Integer fornecedor = Integer.parseInt(scanner.nextLine());
        if(fornecedor == null) fornecedor = produtoExistente.getIdFornecedor();

        System.out.print("Novo Produto (" + produtoExistente.getNmProduto() + "): ");
        String produto = scanner.nextLine();

        System.out.println("Nova Quantidade (" + produtoExistente.getEstoqueMin() + "): ");
        Integer estoqueMin = Integer.parseInt(scanner.nextLine());
        if(estoqueMin == null) estoqueMin = produtoExistente.getEstoqueMin();

        boolean sucesso = controller.atualizarProduto(categoria, fornecedor, produto, estoqueMin);
        if(sucesso) {
            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar o fornecedor!");
        }
   }

   public void excluir() throws SQLException {
        System.out.println("\n--- Excluir Produto---");
        System.out.print("Informe o ID do produto a excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean sucesso = controller.excluirProduto(id);
        if(sucesso) {
            System.out.println("Produto excluído com sucesso!");
        } else {
            System.out.println("Produto não encontrado ou erro ao excluir");
        }
    }

    public void buscarId(int id) throws SQLException {
    System.out.println("\n--- Buscar por ID---");
    System.out.print("Informe o ID que deseja consultar: ");
    int idBusca = Integer.parseInt(scanner.nextLine());

    Produto sucessoBusca = controller.buscarPorID(idBusca);

        if(sucessoBusca == null){
            System.out.println("ID informado não existe!");
        } else {
            System.out.println(
                "ID: " + sucessoBusca.getIdProduto() + " | " +
                "Categoria: " + sucessoBusca.getIdCategoria() + " | " +
                "Fornecedor: " + sucessoBusca.getIdFornecedor() + " | " +
                "Produto: " + sucessoBusca.getNmProduto() + " | " +
                "Quantidade: " + sucessoBusca.getEstoqueMin() + " | " +
                "Situação: " + sucessoBusca.getSituacao()
            );
        }
        controller.buscarPorID(idBusca);
   }   
}
