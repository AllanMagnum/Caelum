package br.com.caelum.agendaalunos.br.com.caelum.agendaalunos.modelo;

import java.io.Serializable;

/**
 * Created by android7543 on 10/05/18.
 */

public class Aluno implements Serializable {

    private Long id;
    private String nome;
    private String telefone;
    private String endereco;
    private String site;
    private Double nota;
    private String caminhofoto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getCaminhofoto() {
        return caminhofoto;
    }

    public void setCaminhofoto(String caminhofoto) {
        this.caminhofoto = caminhofoto;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.nome;
    }
}
