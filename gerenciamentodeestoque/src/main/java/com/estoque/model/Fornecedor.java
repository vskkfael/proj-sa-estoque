package com.estoque.model;

public class Fornecedor {
    private Integer idFornecedor;
    private String razaoSocial;
    private String numCNPJ;
    private String numTel;

    public Fornecedor() {

    }

    public Fornecedor(Integer idFornecedor, String razaoSocial, String numCNPJ, String numTel) {
        this.idFornecedor = idFornecedor;
        this.razaoSocial = razaoSocial;
        this.numCNPJ = numCNPJ;
        this.numTel = numTel;
    }

    public Fornecedor(String razaoSocial, String numCNPJ, String numTel) {
        this.razaoSocial = razaoSocial;
        this.numCNPJ = numCNPJ;
        this.numTel = numTel;
    }

    // Getters e Setters

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNumCNPJ() {
        return numCNPJ;
    }

    public void setNumCNPJ(String numCNPJ) {
        this.numCNPJ = numCNPJ;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    // Padrozina os dados
    @Override
    public String toString() {
        return String.format("ID: %-2d | Razão Social: %-5.80s | CNPJ: %-18s | Contato: %-15s",
            idFornecedor, razaoSocial, formatarCnpj(numCNPJ), formatarTel(numTel));
    }

    // Métodos para formatar os dados
    private String formatarCnpj(String cnpj) {
        return cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14);
    }

    private String formatarTel(String tel) {
        if(tel.length() == 11){
            return "(" + tel.substring(0,2) + ")" + " " + tel.substring(2,3) + " " + tel.substring(3, 7) + "-" + tel.substring(7, 11);
        } else {
            return "(" + tel.substring(0,2) + ")" + " " + tel.substring(2,6) + "-" + tel.substring(6, 10);
        }
    }
}
