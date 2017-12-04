package br.uffjf.dcc196.ana.trabalho1.model;


import java.io.Serializable;
import java.util.Calendar;

public class Pessoa implements Serializable{
    private int id;
    private String nome;
    private String email;
    private Calendar horaEntrada;
    private Calendar horaSaida;

    public Pessoa() {
    }

    public Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public Pessoa(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Calendar horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Calendar getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Calendar horaSaida) {
        this.horaSaida = horaSaida;
    }

    @Override
    public String toString() {
        return nome;
    }
}
