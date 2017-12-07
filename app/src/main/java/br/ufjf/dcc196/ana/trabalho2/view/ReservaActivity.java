package br.ufjf.dcc196.ana.trabalho2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import br.ufjf.dcc196.ana.trabalho2.R;
import br.ufjf.dcc196.ana.trabalho2.adapter.LivroSimpleAdapter;
import br.ufjf.dcc196.ana.trabalho2.adapter.ParticipanteSimpleAdapter;
import br.ufjf.dcc196.ana.trabalho2.helper.LivroHelper;
import br.ufjf.dcc196.ana.trabalho2.helper.PessoaHelper;
import br.ufjf.dcc196.ana.trabalho2.model.Livro;
import br.ufjf.dcc196.ana.trabalho2.model.Participante;

public class ReservaActivity extends AppCompatActivity {
    private Spinner spParticipante;
    private Spinner spLivro;
    private Button btnSalvar;
    private ParticipanteSimpleAdapter participanteAdapter;
    private LivroSimpleAdapter livroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        spParticipante = (Spinner) findViewById(R.id.spParticipante);
        spLivro = (Spinner) findViewById(R.id.spLivro);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        participanteAdapter = new ParticipanteSimpleAdapter(getBaseContext(), null);
        livroAdapter = new LivroSimpleAdapter(getBaseContext(), null);

        participanteAdapter.atualizar();
        spParticipante.setAdapter(participanteAdapter);

        livroAdapter.atualizar();
        spLivro.setAdapter(livroAdapter);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Livro livro = (Livro) spLivro.getSelectedItem();
                Participante participante = (Participante) spParticipante.getSelectedItem();

                livro.addReserva(participante);
                spLivro.setSelection(0);
                spParticipante.setSelection(0);
                Toast.makeText(ReservaActivity.this, "Reserva realizada com sucesso.", Toast.LENGTH_SHORT).show();
                }
        });
    }
}
