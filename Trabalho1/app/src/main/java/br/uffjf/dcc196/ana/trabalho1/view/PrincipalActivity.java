package br.uffjf.dcc196.ana.trabalho1.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;

import br.uffjf.dcc196.ana.trabalho1.R;
import br.uffjf.dcc196.ana.trabalho1.helper.PessoaHelper;
import br.uffjf.dcc196.ana.trabalho1.model.Pessoa;

public class PrincipalActivity extends AppCompatActivity {
    private Button btnParticipantes;
    private Button btnLivros;
    private Button btnReservas;
    private ListView lstParticipantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnParticipantes = (Button) findViewById(R.id.btnParticipantes);
        btnLivros = (Button) findViewById(R.id.btnLivros);
        btnReservas = (Button)findViewById(R.id.btnReservas);
        lstParticipantes = (ListView) findViewById(R.id.lstParticipantes);

        btnParticipantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadParticipante = new Intent(PrincipalActivity.this, ParticipantesActivity.class);
                cadParticipante.putExtra("msg", "Informe os dados do participante.");
                startActivity(cadParticipante);

            }
        });

        btnLivros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadLivro = new Intent(PrincipalActivity.this, LivroActivity.class);
                cadLivro.putExtra("msg", "Informe os dados do livro.");
                startActivity(cadLivro);
            }
        });

        btnReservas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent cadReserva = new Intent(PrincipalActivity.this, ReservaActivity.class);
                cadReserva.putExtra("msg", "Informe os dados da reserva.");
                startActivity(cadReserva);
            }
        });

        //carregar participantes
        final ArrayAdapter<Pessoa> adaptador = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                PessoaHelper.getInstance().listar());

        lstParticipantes.setAdapter(adaptador);

        //clicar no participante para visualizar seus dados
        lstParticipantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pessoa pessoa = adaptador.getItem(position);
                Intent in = new Intent(PrincipalActivity.this, ParticipanteDadoActivity.class);
                in.putExtra("participante", pessoa);
                startActivity(in);
            }
        });

        //modificar a hora de entrada e de saida
        lstParticipantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Pessoa pessoa = adaptador.getItem(position);
                if(pessoa.getHoraEntrada() == null){
                    pessoa.setHoraEntrada(Calendar.getInstance());
                    Toast.makeText(PrincipalActivity.this, "Entrada registrada.", Toast.LENGTH_SHORT).show();
                }else if(pessoa.getHoraSaida() == null){
                    pessoa.setHoraSaida(Calendar.getInstance());
                    Toast.makeText(PrincipalActivity.this, "Saída registrada.", Toast.LENGTH_SHORT).show();
                }else{
                    pessoa.setHoraEntrada(null);
                    pessoa.setHoraSaida(null);
                    Toast.makeText(PrincipalActivity.this, "Registro de entrada e saída apagados.", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }
}
