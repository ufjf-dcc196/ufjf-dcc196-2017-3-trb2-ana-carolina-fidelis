package br.ufjf.dcc196.ana.trabalho2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufjf.dcc196.ana.trabalho2.R;
import br.ufjf.dcc196.ana.trabalho2.adapter.ParticipanteAdapter;
import br.ufjf.dcc196.ana.trabalho2.helper.PessoaHelper;
import br.ufjf.dcc196.ana.trabalho2.model.Participante;

public class ParticipantesActivity extends AppCompatActivity {
    private EditText txtNome;
    private EditText txtEmail;
    private Button btnSalvar;
    private ParticipanteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participantes);

        adapter = new ParticipanteAdapter(getBaseContext(), null);
        txtNome = (EditText)findViewById(R.id.txtNomeParticipante);
        txtEmail = (EditText) findViewById(R.id.txtEmailParticipante);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

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
                    Participante p = new Participante(nome, email);
                    //PessoaHelper.getInstance().inserir(p);
                    adapter.inserir(p);
                    txtNome.setText("");
                    txtEmail.setText("");
                    txtNome.requestFocus();
                    Toast.makeText(getApplicationContext(), "Participante salvo com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
