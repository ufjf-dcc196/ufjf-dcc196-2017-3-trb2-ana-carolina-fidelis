package br.uffjf.dcc196.ana.trabalho1.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.uffjf.dcc196.ana.trabalho1.R;
import br.uffjf.dcc196.ana.trabalho1.helper.PessoaHelper;
import br.uffjf.dcc196.ana.trabalho1.model.Pessoa;

public class ParticipantesActivity extends AppCompatActivity {
    private EditText txtNome;
    private EditText txtEmail;
    private Button btnSalvar;
    private Button btnVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participantes);

        txtNome = (EditText)findViewById(R.id.txtNomeParticipante);
        txtEmail = (EditText) findViewById(R.id.txtEmailParticipante);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = txtNome.getText().toString();
                String email = txtEmail.getText().toString();
                if(nome.equals("")){
                    Toast.makeText(getApplicationContext(), "Informe o nome do participante.", Toast.LENGTH_SHORT).show();
                    txtNome.requestFocus();
                }else if(email.equals("")){
                    Toast.makeText(getApplicationContext(), "Informe o email do participante.", Toast.LENGTH_SHORT).show();
                    txtEmail.requestFocus();
                }else{
                    Pessoa p = new Pessoa(nome, email);
                    PessoaHelper.getInstance().inserir(p);
                    txtNome.setText("");
                    txtEmail.setText("");
                    txtNome.requestFocus();
                    Toast.makeText(getApplicationContext(), "Participante salvo com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent principal = new Intent(ParticipantesActivity.this, PrincipalActivity.class);
                //startActivity(principal);
                finish();
            }
        });
    }
}
