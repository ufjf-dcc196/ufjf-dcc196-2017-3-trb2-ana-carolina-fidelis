package br.ufjf.dcc196.ana.trabalho2.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.ufjf.dcc196.ana.trabalho2.R;
import br.ufjf.dcc196.ana.trabalho2.adapter.LivroAdapter;
import br.ufjf.dcc196.ana.trabalho2.model.Livro;

public class LivroListaActivity extends AppCompatActivity {

    private ListView lstLivros;
    private LivroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livro_lista);

        adapter = new LivroAdapter(getBaseContext(), null);
        lstLivros = (ListView) findViewById(R.id.lstLivros);
        lstLivros.setAdapter(adapter);
        adapter.atualizar();

        //clicar a lista de livros
        lstLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Livro livro = adapter.buscar(id);

                if(livro!= null){
                    Intent in = new Intent(LivroListaActivity.this, ReservaListaActivity.class);
                    in.putExtra("livro", livro);
                    startActivity(in);
                }

            }
        });
    }


}
