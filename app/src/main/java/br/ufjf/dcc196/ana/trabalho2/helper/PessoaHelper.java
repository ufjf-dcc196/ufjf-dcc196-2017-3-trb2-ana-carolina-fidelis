package br.ufjf.dcc196.ana.trabalho2.helper;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.dcc196.ana.trabalho2.model.Participante;


public class PessoaHelper {
    private static PessoaHelper instance;
    private static List<Participante> participantes;

    public static PessoaHelper getInstance(){
        if(instance == null){
            instance = new PessoaHelper();
        }
        init();
        return instance;
    }

    private static void init(){
        if(participantes == null){
            participantes = new ArrayList<>();
            participantes.add(new Participante(1,"Pedro Henrique", "pedro@net.com.br"));
            participantes.add(new Participante(2,"Ana Maria", "maria@net.com.br"));
            participantes.add(new Participante(3, "Júlio da Silva", "julio@net.com.br"));
            participantes.add(new Participante(4, "Barack Obama", "obama@net.com.br"));
        }
    }

    public void inserir(Participante p){
        p.setId(proximoID());
        participantes.add(p);
    }

    public List<Participante> listar(){
        return participantes;
    }

    public Participante buscar(int id){
        Participante participante = null;
        for(Participante p: participantes){
            if(p.getId() == id){
                participante = p;
            }
        }
        return participante;
    }

    private int proximoID(){
        return participantes.size()+ 1;
    }

}
