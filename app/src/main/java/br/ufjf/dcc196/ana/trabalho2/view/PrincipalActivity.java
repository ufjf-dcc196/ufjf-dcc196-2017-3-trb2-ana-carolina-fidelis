package br.ufjf.dcc196.ana.trabalho2.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;

import br.ufjf.dcc196.ana.trabalho2.R;
import br.ufjf.dcc196.ana.trabalho2.adapter.ParticipanteAdapter;
import br.ufjf.dcc196.ana.trabalho2.model.Participante;

public class PrincipalActivity extends AppCompatActivity {
    private Button btnParticipantes;
    private Button btnLivros;
    private Button btnReservas;
    private ListView lstParticipantes;
    private ParticipanteAdapter participanteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnParticipantes = (Button) findViewById(R.id.btnParticipantes);
        btnLivros = (Button) findViewById(R.id.btnLivros);
        btnReservas = (Button)findViewById(R.id.btnReservas);

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

        lstParticipantes = (ListView) findViewById(R.id.lstParticipantes);
        participanteAdapter = new ParticipanteAdapter(getBaseContext(), null);
        participanteAdapter.atualizar();

        lstParticipantes.setAdapter(participanteAdapter);

        //clicar no participante para visualizar seus dados
       lstParticipantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Participante participante = participanteAdapter.buscar(id);
                Intent in = new Intent(PrincipalActivity.this, ParticipanteDadoActivity.class);
                in.putExtra("participante", participante);
                startActivity(in);
            }
        });

        //modificar a hora de entrada e de saida
        lstParticipantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Participante participante = participanteAdapter.buscar(id);
                Calendar c = Calendar.getInstance();
                if(participante.getHoraEntrada() == null){
                    participante.setHoraEntrada(c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE));
                    participanteAdapter.alterarHoraEntrada(participante);
                    Toast.makeText(PrincipalActivity.this, "Entrada registrada.", Toast.LENGTH_SHORT).show();
                }else if(participante.getHoraSaida() == null){
                    participante.setHoraSaida(c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE));
                    participanteAdapter.alterarHoraSaida(participante);
                    Toast.makeText(PrincipalActivity.this, "Saída registrada.", Toast.LENGTH_SHORT).show();
                }else{
                    participante.setHoraEntrada(null);
                    participante.setHoraSaida(null);
                    participanteAdapter.alterarHoraEntrada(participante);
                    participanteAdapter.alterarHoraSaida(participante);
                    Toast.makeText(PrincipalActivity.this, "Registro de entrada e saída apagados.", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        participanteAdapter.notifyDataSetChanged();
        participanteAdapter.atualizar();

        super.onResume();
    }
}
