package br.ufjf.dcc196.ana.trabalho2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.ufjf.dcc196.ana.trabalho2.R;
import br.ufjf.dcc196.ana.trabalho2.adapter.ReservaAdapter;
import br.ufjf.dcc196.ana.trabalho2.model.Livro;
import br.ufjf.dcc196.ana.trabalho2.model.Participante;

public class ReservaListaActivity extends AppCompatActivity {
    private ListView lstReservas;
    private TextView txtTitulo;
    private TextView txtAutor;
    private TextView txtEditora;
    private TextView txtAno;
    private ReservaAdapter reservaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_lista);

        lstReservas = (ListView) findViewById(R.id.lstReservas);
        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtAutor = (TextView) findViewById(R.id.txtAutor);
        txtEditora = (TextView) findViewById(R.id.txtEditora);
        txtAno = (TextView) findViewById(R.id.txtAno);
        reservaAdapter = new ReservaAdapter(getBaseContext(), null);


        Livro livro = (Livro) getIntent().getSerializableExtra("livro");
        txtTitulo.setText(livro.getTitulo());
        txtAutor.setText(livro.getAutor());
        txtEditora.setText(livro.getEditora());
        txtAno.setText(livro.getAno());

        reservaAdapter.atualizar(livro.getId());
        lstReservas.setAdapter(reservaAdapter);
    }


}
