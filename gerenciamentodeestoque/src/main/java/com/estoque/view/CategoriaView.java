package com.estoque.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.estoque.controller.CategoriaController;
import com.estoque.model.Categoria;

public class CategoriaView {
    private final CategoriaController controller;
    private final Scanner scanner;

   public CategoriaView() {
    this.controller = new CategoriaController();
    this.scanner = new Scanner(System.in);
   }

   public void exibirMenu(){
    int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n========== GERENCIADOR DE CATEGORIAS ==========");
                System.out.println("1. Cadastrar Categoria");
                System.out.println("2. Listar Categoria");
                System.out.println("3. Atualizar Categoria");
                System.out.println("4. Excluir Categoria");
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
        System.out.println("\n--- Cadastrar Categoria ---");
        System.out.print("Nome da categoria: ");
        String nmCategoria = scanner.nextLine();

        System.out.print("Descrição breve: ");
        String dsCategoria = scanner.nextLine();

        controller.cadastrarCategoria(nmCategoria, dsCategoria);
        System.out.println("Categoria cadastrada com sucesso!");
   }

   public void listar() throws SQLException {
        System.out.println("\n--- Lista de Categorias ---");
        List<Categoria> categoria = controller.listarCategoria();

        if (categoria.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
        } else {
            categoria.forEach(System.out::println);
        }
   }

   public void atualizar() throws SQLException {
        System.out.println("\n--- Atualizar Categoria ---");
        System.out.print("Informe o ID da categoria a atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Categoria categoriaExistente = controller.buscarPorID(id);
        if(categoriaExistente == null){
            System.out.println("Categoria não encontrado com o ID informado");
            return;
        }

        System.out.print("Nova categoria (" + categoriaExistente.getNomeCategoria() + "): ");
        String nmCategoria = scanner.nextLine();
        if (nmCategoria.isBlank()) nmCategoria = categoriaExistente.getNomeCategoria();

        System.out.print("Nova descrição (" + categoriaExistente.getDsCategoria() + "): ");
        String dsCategoria = scanner.nextLine();
        if(dsCategoria.isBlank()) dsCategoria = categoriaExistente.getDsCategoria();

        boolean sucesso = controller.atualizarCategoria(id, nmCategoria, dsCategoria);
        if(sucesso) {
            System.out.println("Categoria atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar a categoria!");
        }
   }

   public void excluir() throws SQLException {
        System.out.println("\n--- Excluir Categoria---");
        System.out.print("Informe o ID da categoria a excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean sucesso = controller.excluirCategoria(id);
        if(sucesso) {
            System.out.println("Categoria excluída com sucesso!");
        } else {
            System.out.println("Categoria não encontrada ou erro ao excluir");
        }
   }

   public void buscarId(int id) throws SQLException {
    System.out.println("\n--- Buscar por ID---");
    System.out.print("Informe o ID que deseja consultar: ");
    int idBusca = Integer.parseInt(scanner.nextLine());

    Categoria sucessoBusca = controller.buscarPorID(idBusca);

        if(sucessoBusca == null){
            System.out.println("ID informado não existe!");
        } else {
            System.out.println(
                "ID: " + sucessoBusca.getIdCategoria() + " | " +
                "Categoria: " + sucessoBusca.getNomeCategoria() + " | " +
                "Descrição: " + sucessoBusca.getDsCategoria() + " | "
            );
        }
        controller.buscarPorID(idBusca);
   }   
}
