package br.com.lucasagw.mobile.todo;

import java.io.Serializable;
import java.util.Objects;

public class Tarefa implements Serializable {

    private String titulo;
    private String descricao;
    private int prioridade;

    public Tarefa(String nome, String descricao, int prioridade) {
        this.titulo = nome;
        this.descricao = descricao;
        this.prioridade = prioridade;
    }

    public Tarefa() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String nome) {
        this.titulo = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return prioridade == tarefa.prioridade &&
                Objects.equals(titulo, tarefa.titulo) &&
                Objects.equals(descricao, tarefa.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descricao, prioridade);
    }
}
