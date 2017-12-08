package br.ufjf.dcc196.ana.trabalho2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import br.ufjf.dcc196.ana.trabalho2.R;
import br.ufjf.dcc196.ana.trabalho2.adapter.LivroAdapter;
import br.ufjf.dcc196.ana.trabalho2.adapter.LivroSimpleAdapter;
import br.ufjf.dcc196.ana.trabalho2.adapter.ParticipanteAdapter;
import br.ufjf.dcc196.ana.trabalho2.adapter.ParticipanteSimpleAdapter;
import br.ufjf.dcc196.ana.trabalho2.adapter.ReservaAdapter;
import br.ufjf.dcc196.ana.trabalho2.model.Livro;
import br.ufjf.dcc196.ana.trabalho2.model.Participante;

public class ReservaActivity extends AppCompatActivity {
    private Spinner spParticipante;
    private Spinner spLivro;
    private Button btnSalvar;
    private ParticipanteSimpleAdapter participanteSimpleAdapter;
    private LivroSimpleAdapter livroSimpleAdapter;
    private LivroAdapter livroAdapter;
    private ParticipanteAdapter participanteAdapter;
    private ReservaAdapter reservaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        spParticipante = (Spinner) findViewById(R.id.spParticipante);
        spLivro = (Spinner) findViewById(R.id.spLivro);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        participanteSimpleAdapter = new ParticipanteSimpleAdapter(getBaseContext(), null);
        participanteAdapter = new ParticipanteAdapter(getBaseContext(), null);
        livroSimpleAdapter =  new LivroSimpleAdapter(getBaseContext(), null);
        livroAdapter = new LivroAdapter(getBaseContext(), null);
        reservaAdapter = new ReservaAdapter(getBaseContext(), null);

        participanteSimpleAdapter.atualizar();
        spParticipante.setAdapter(participanteSimpleAdapter);

        livroSimpleAdapter.atualizar();
        spLivro.setAdapter(livroSimpleAdapter);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spParticipante.getSelectedItemPosition()==-1){
                    Toast.makeText(ReservaActivity.this, "Selecione o participante.", Toast.LENGTH_SHORT).show();
                }
                if(spLivro.getSelectedItemPosition()==-1){
                    Toast.makeText(ReservaActivity.this, "Selecione o livro.", Toast.LENGTH_SHORT).show();
                }else{
                    Livro livro = livroAdapter.buscar(spLivro.getSelectedItemId());
                    Participante participante = participanteAdapter.buscar(spParticipante.getSelectedItemId());

                    reservaAdapter.inserir(livro, participante);

                    spLivro.setSelection(0);
                    spParticipante.setSelection(0);
                    Toast.makeText(ReservaActivity.this, "Reserva realizada com sucesso.", Toast.LENGTH_SHORT).show();
                }

                }
        });
    }
}
