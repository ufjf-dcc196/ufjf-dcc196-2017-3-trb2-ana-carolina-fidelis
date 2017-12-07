package br.ufjf.dcc196.ana.trabalho2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Livro implements Serializable {
    private int id;
    private String titulo;
    private String editora;
    private String ano;
    private String autor;
    private ArrayList<Participante> reservas = new ArrayList<>();

    public Livro() {

    }


    public Livro(String titulo, String autor, String editora, String ano) {
        super();
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.ano = ano;
    }

    public Livro(int id, String titulo, String autor, String editora, String ano) {
        super();
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.ano = ano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public List<Participante> getReservas(){
        return this.reservas;
    }

    public void addReserva(Participante participante){
        this.reservas.add(participante);
    }

    @Override
    public String toString() {
        return titulo;
    }
}

