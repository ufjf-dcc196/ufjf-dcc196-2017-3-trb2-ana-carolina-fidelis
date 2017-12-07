package br.ufjf.dcc196.ana.trabalho2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

import br.ufjf.dcc196.ana.trabalho2.R;
import br.ufjf.dcc196.ana.trabalho2.model.Participante;

public class ParticipanteDadoActivity extends AppCompatActivity {
    private TextView txtNome;
    private TextView txtEmail;
    private TextView txtEntrada;
    private TextView txtSaida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participante_dado);

        txtNome = (TextView) findViewById(R.id.txtNome);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtEntrada = (TextView) findViewById(R.id.txtEntrada);
        txtSaida = (TextView) findViewById(R.id.txtSaida);

        Participante participante = (Participante) getIntent().getSerializableExtra("participante");

       txtNome.setText(participante.getNome());
        txtEmail.setText(participante.getEmail());
        if(participante.getHoraEntrada() == null){
            txtEntrada.setText("--:--");
        }else{
           txtEntrada.setText(participante.getHoraEntrada());
        }

        if(participante.getHoraSaida() == null){
            txtSaida.setText("--:--");
        }else{
            txtSaida.setText(participante.getHoraSaida());
        }

    }
}
