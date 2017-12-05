package br.ufjf.dcc196.ana.trabalho2.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import br.ufjf.dcc196.ana.trabalho2.R;
import br.ufjf.dcc196.ana.trabalho2.helper.LivroHelper;
import br.ufjf.dcc196.ana.trabalho2.helper.PessoaHelper;
import br.ufjf.dcc196.ana.trabalho2.model.Livro;
import br.ufjf.dcc196.ana.trabalho2.model.Pessoa;

public class ReservaActivity extends AppCompatActivity {
    private Spinner spParticipante;
    private Spinner spLivro;
    private Button btnSalvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        spParticipante = (Spinner) findViewById(R.id.spParticipante);
        spLivro = (Spinner) findViewById(R.id.spLivro);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        ArrayAdapter<Pessoa> adaptador = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                PessoaHelper.getInstance().listar());
        adaptador.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spParticipante.setAdapter(adaptador);

        ArrayAdapter<Livro> adaptador2 = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                LivroHelper.getInstance().listar());
        adaptador.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spLivro.setAdapter(adaptador2);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Livro livro = (Livro) spLivro.getSelectedItem();
                Pessoa participante = (Pessoa) spParticipante.getSelectedItem();

                livro.addReserva(participante);
                spLivro.setSelection(0);
                spParticipante.setSelection(0);
                Toast.makeText(ReservaActivity.this, "Reserva realizada com sucesso.", Toast.LENGTH_SHORT).show();
                }
        });
    }
}
