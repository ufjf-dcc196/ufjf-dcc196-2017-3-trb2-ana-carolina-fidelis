package br.ufjf.dcc196.ana.trabalho2.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import br.ufjf.dcc196.ana.trabalho2.R;
import br.ufjf.dcc196.ana.trabalho2.model.Pessoa;

public class ParticipanteDadoActivity extends AppCompatActivity {
    private Button btnVoltar;
    private TextView txtNome;
    private TextView txtEmail;
    private TextView txtEntrada;
    private TextView txtSaida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participante_dado);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        txtNome = (TextView) findViewById(R.id.txtNome);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtEntrada = (TextView) findViewById(R.id.txtEntrada);
        txtSaida = (TextView) findViewById(R.id.txtSaida);

        Pessoa participante = (Pessoa) getIntent().getSerializableExtra("participante");

       txtNome.setText(participante.getNome());
        txtEmail.setText(participante.getEmail());
        if(participante.getHoraEntrada() == null){
            txtEntrada.setText("--:--");
        }else{
           Calendar entrada = participante.getHoraEntrada();
           txtEntrada.setText(entrada.get(Calendar.HOUR) + ":" + entrada.get(Calendar.MINUTE) + ":" + entrada.get(Calendar.SECOND));
        }

        if(participante.getHoraSaida() == null){
            txtSaida.setText("--:--");
        }else{
            Calendar saida = participante.getHoraSaida();
            txtSaida.setText(saida.get(Calendar.HOUR) + ":" + saida.get(Calendar.MINUTE) + ":" + saida.get(Calendar.SECOND));
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent principal = new Intent(ParticipanteDadoActivity.this, PrincipalActivity.class);
                startActivity(principal);
                finish();
            }
        });

    }
}
