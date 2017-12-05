package br.ufjf.dcc196.ana.trabalho2.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import br.ufjf.dcc196.ana.trabalho2.R;
import br.ufjf.dcc196.ana.trabalho2.helper.LivroHelper;
import br.ufjf.dcc196.ana.trabalho2.model.Livro;

public class LivroListaActivity extends AppCompatActivity {

    private ListView lstLivros;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livro_lista);

        lstLivros = (ListView) findViewById(R.id.lstLivros);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadLivro = new Intent(LivroListaActivity.this, LivroActivity.class);
                startActivity(cadLivro);
                finish();
            }
        });

        final ArrayAdapter<Livro> adaptador = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                LivroHelper.getInstance().listar());

        lstLivros.setAdapter(adaptador);

        //clicar a lista de livros
        lstLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Livro livro = adaptador.getItem(position);
                Intent in = new Intent(LivroListaActivity.this, ReservaListaActivity.class);
                in.putExtra("livro", livro);
                startActivity(in);
            }
        });
    }


}
