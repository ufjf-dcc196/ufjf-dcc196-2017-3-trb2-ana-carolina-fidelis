package br.uffjf.dcc196.ana.trabalho1.helper;

import java.util.ArrayList;
import java.util.List;

import br.uffjf.dcc196.ana.trabalho1.model.Livro;

public class LivroHelper {
    private static LivroHelper instance;
    private static List<Livro> livros;

    public static LivroHelper getInstance(){
        if(instance == null){
            instance = new LivroHelper();
        }
        init();
        return instance;
    }

    private static void init(){
        if(livros == null){
            livros = new ArrayList<>();
            livros.add(new Livro(1, "Dom Quixote", "Miguel de Cervantes", "Saraiva", "1605"));
            livros.add(new Livro(2, "Guerra e Paz", "Liev Tolstói", "Abril", "1869"));
            livros.add(new Livro(3, "A Montanha Mágica", "Thomas Mann", "Saraiva", "1924"));
        }
    }

    private int proximoID(){
        return livros.size()+1;
    }

    public void inserir(Livro livro){
        livro.setId(proximoID());
        livros.add(livro);
    }

    public Livro buscar(int id){
        Livro livro = null;
        for (Livro l: livros){
            if(l.getId() == id){
                livro = l;
            }
        }
        return livro;
    }

    public List<Livro> listar(){
        return livros;
    }

}
