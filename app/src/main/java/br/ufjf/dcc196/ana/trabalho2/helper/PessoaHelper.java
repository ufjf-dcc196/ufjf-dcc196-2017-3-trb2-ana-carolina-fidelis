package br.ufjf.dcc196.ana.trabalho2.helper;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.dcc196.ana.trabalho2.model.Pessoa;


public class PessoaHelper {
    private static PessoaHelper instance;
    private static List<Pessoa> pessoas;

    public static PessoaHelper getInstance(){
        if(instance == null){
            instance = new PessoaHelper();
        }
        init();
        return instance;
    }

    private static void init(){
        if(pessoas == null){
            pessoas = new ArrayList<>();
            pessoas.add(new Pessoa(1,"Pedro Henrique", "pedro@net.com.br"));
            pessoas.add(new Pessoa(2,"Ana Maria", "maria@net.com.br"));
            pessoas.add(new Pessoa(3, "Júlio da Silva", "julio@net.com.br"));
            pessoas.add(new Pessoa(4, "Barack Obama", "obama@net.com.br"));
        }
    }

    public void inserir(Pessoa p){
        p.setId(proximoID());
        pessoas.add(p);
    }

    public List<Pessoa> listar(){
        return pessoas;
    }

    public Pessoa buscar(int id){
        Pessoa pessoa = null;
        for(Pessoa p: pessoas){
            if(p.getId() == id){
                pessoa = p;
            }
        }
        return pessoa;
    }

    private int proximoID(){
        return pessoas.size()+ 1;
    }

}
