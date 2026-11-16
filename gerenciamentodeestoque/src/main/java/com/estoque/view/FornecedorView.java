package com.estoque.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.estoque.controller.FornecedorController;
import com.estoque.model.Fornecedor;

public class FornecedorView {
    private final FornecedorController controller;
   private final Scanner scanner;

   public FornecedorView() {
    this.controller = new FornecedorController();
    this.scanner = new Scanner(System.in);
   }

   public void exibirMenu(){
    int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n========== GERENCIADOR DE FORNECEDORES ==========");
                System.out.println("1. Cadastrar Fornecedor");
                System.out.println("2. Listar Fornecedor");
                System.out.println("3. Atualizar Fornecedor");
                System.out.println("4. Excluir Fornecedor");
                System.out.println("5. Buscar por CNPJ");
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
        System.out.println("\n--- Cadastrar Fornecedor ---");
        System.out.print("Razão Social: ");
        String razaoSocial = scanner.nextLine();

        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        String numCNPJ = cnpj.replaceAll("\\D", "");

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        String numTelefone = telefone.replaceAll("\\D", "");

        controller.cadastrarFornecedor(razaoSocial, numCNPJ, numTelefone);
        System.out.println("Fornecedor cadastrado com sucesso!");
   }

   public void listar() throws SQLException {
        System.out.println("\n--- Lista de Fornecedores ---");
        List<Fornecedor> fornecedor = controller.listarFornecedor();

        if (fornecedor.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado.");
        } else {
            fornecedor.forEach(System.out::println);
        }
   }

   public void atualizar() throws SQLException {
        System.out.println("\n--- Atualizar Fornecedor ---");
        System.out.print("Informe o ID do fornecedor a atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Fornecedor fornecedorExistente = controller.buscarPorID(id);
        if(fornecedorExistente == null){
            System.out.println("Cliente não encontrado com o ID informado");
            return;
        }

        System.out.print("Novo razão social (" + fornecedorExistente.getRazaoSocial() + "): ");
        String razaoSocial = scanner.nextLine();
        if (razaoSocial.isBlank()) razaoSocial = fornecedorExistente.getRazaoSocial();

        System.out.print("Novo CNPJ (" + fornecedorExistente.getNumCNPJ() + "): ");
        String numCNPJ = scanner.nextLine();
        if(numCNPJ.isBlank()) numCNPJ = fornecedorExistente.getNumCNPJ();

        System.out.print("Novo Telefone (" + fornecedorExistente.getNumTel() + "): ");
        String numTelefone = scanner.nextLine();

        // Condicional para verificar o tamanho do telefone.
        String contato;
        if(numTelefone.isBlank()){
            contato = fornecedorExistente.getNumTel();
        } else {
            contato = numTelefone.replaceAll("\\D", "");
            if(contato.length() <= 10 || contato.length() > 11) {
                System.out.println("Erro: Digite um telefone válido (DDD + número).");
                return;
            }
        }

        boolean sucesso = controller.atualizarFornecedor(id, razaoSocial, numCNPJ, numCNPJ);
        if(sucesso) {
            System.out.println("Fornecedor atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar o fornecedor!");
        }
   }

   public void excluir() throws SQLException {
        System.out.println("\n--- Excluir Fornecedor---");
        System.out.print("Informe o ID do fornecedor a excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean sucesso = controller.excluirFornecedor(id);
        if(sucesso) {
            System.out.println("Fornecedor excluído com sucesso!");
        } else {
            System.out.println("Fornecedor não encontrado ou erro ao excluir");
        }
   }

   public void buscarId(int id) throws SQLException {
    System.out.println("\n--- Buscar por ID---");
    System.out.print("Informe o CNPJ que deseja consultar: ");
    String cnpjBusca = scanner.nextLine();
    String cnpjSucesso = cnpjBusca.replaceAll("\\D", "");

    Fornecedor sucessoBusca = controller.buscarCNPJ(cnpjSucesso);

        if(sucessoBusca == null){
            System.out.println("CNPJ informado não existe!");
        } else {
            System.out.println(
                "ID: " + sucessoBusca.getIdFornecedor() + " | " +
                "Razão Social: " + sucessoBusca.getRazaoSocial() + " | " +
                "CNPJ: " + sucessoBusca.getNumCNPJ() + " | " +
                "Telefone: " + sucessoBusca.getNumTel()
            );
        }
        controller.buscarCNPJ(cnpjSucesso);
   }   
}
