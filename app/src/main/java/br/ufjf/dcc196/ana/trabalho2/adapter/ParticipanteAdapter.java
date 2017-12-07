package br.ufjf.dcc196.ana.trabalho2.adapter;

import br.ufjf.dcc196.ana.trabalho2.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import br.ufjf.dcc196.ana.trabalho2.contract.ParticipanteContract;
import br.ufjf.dcc196.ana.trabalho2.helper.BienalDBHelper;
import br.ufjf.dcc196.ana.trabalho2.model.Participante;

public class ParticipanteAdapter extends CursorAdapter {
    private BienalDBHelper bienalDBHelper;

    public ParticipanteAdapter(Context context, Cursor c) {
        super(context, c, 0);
        bienalDBHelper = new BienalDBHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.participante_layout,viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtNome = (TextView)view.findViewById(R.id.txtNome);
        TextView txtEmail = (TextView)view.findViewById(R.id.txtEmail);
        String nome = cursor.getString(cursor.getColumnIndexOrThrow(ParticipanteContract.Participante.COLUMN_NAME_NOME));
        String email = cursor.getString(cursor.getColumnIndexOrThrow(ParticipanteContract.Participante.COLUMN_NAME_EMAIL));
        txtNome.setText(nome);
        txtEmail.setText(email);
    }

    public void atualizar(){
       try {
            SQLiteDatabase db = bienalDBHelper.getReadableDatabase();
            String[] visao = {
                    ParticipanteContract.Participante._ID,
                    ParticipanteContract.Participante.COLUMN_NAME_NOME,
                    ParticipanteContract.Participante.COLUMN_NAME_EMAIL,
                    ParticipanteContract.Participante.COLUMN_NAME_HORA_ENTRADA,
                    ParticipanteContract.Participante.COLUMN_NAME_HORA_SAIDA
            };

            Cursor c = db.query(ParticipanteContract.Participante.TABLE_NAME, visao, null, null, null, null, null);
            this.changeCursor(c);

        } catch (Exception e) {
            Log.e("BIENAL PARTICIPANTE", e.getLocalizedMessage());
            Log.e("BIENAL PARTICIPANTE", e.getStackTrace().toString());
        }
    }

    public void inserir(Participante participante){
        try {
            SQLiteDatabase db = bienalDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(ParticipanteContract.Participante.COLUMN_NAME_NOME, participante.getNome());
            values.put(ParticipanteContract.Participante.COLUMN_NAME_EMAIL, participante.getEmail());;
            long id = db.insert(ParticipanteContract.Participante.TABLE_NAME, null, values);
            atualizar();
        } catch (Exception e) {
            Log.e("BIENAL", e.getLocalizedMessage());
            Log.e("BIENAL", e.getStackTrace().toString());
        }
    }

    public Participante buscar(long id){
        Participante participante = new Participante();
        try {
            SQLiteDatabase db = bienalDBHelper.getReadableDatabase();
            String[] visao = {
                    ParticipanteContract.Participante._ID,
                    ParticipanteContract.Participante.COLUMN_NAME_NOME,
                    ParticipanteContract.Participante.COLUMN_NAME_EMAIL,
                    ParticipanteContract.Participante.COLUMN_NAME_HORA_ENTRADA,
                    ParticipanteContract.Participante.COLUMN_NAME_HORA_SAIDA
            };
            String selecao = ParticipanteContract.Participante._ID + " = " + id;
            Cursor c = db.query(ParticipanteContract.Participante.TABLE_NAME, visao, selecao, null, null, null, null);
            c.moveToFirst();
            participante.setId(c.getInt(c.getColumnIndex(ParticipanteContract.Participante._ID)));
            participante.setNome(c.getString(c.getColumnIndex(ParticipanteContract.Participante.COLUMN_NAME_NOME)));
            participante.setEmail(c.getString(c.getColumnIndex(ParticipanteContract.Participante.COLUMN_NAME_EMAIL)));
            participante.setHoraEntrada(c.getString(c.getColumnIndex(ParticipanteContract.Participante.COLUMN_NAME_HORA_ENTRADA)));
            participante.setHoraSaida(c.getString(c.getColumnIndex(ParticipanteContract.Participante.COLUMN_NAME_HORA_SAIDA)));
        } catch (Exception e) {
            Log.e("BIENAL", e.getLocalizedMessage());
            Log.e("BIENAL", e.getStackTrace().toString());
        }
        return participante;
    }

    public void alterarHoraEntrada(Participante participante){
        try {
            SQLiteDatabase db = bienalDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(ParticipanteContract.Participante.COLUMN_NAME_HORA_ENTRADA, participante.getHoraEntrada());

            String where = ParticipanteContract.Participante._ID + " = " + participante.getId();
            long id = db.update(ParticipanteContract.Participante.TABLE_NAME, values, where, null);
            atualizar();
        } catch (Exception e) {
            Log.e("BIENAL", e.getLocalizedMessage());
            Log.e("BIENAL", e.getStackTrace().toString());
        }
    }

    public void alterarHoraSaida(Participante participante){
        try {
            SQLiteDatabase db = bienalDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(ParticipanteContract.Participante.COLUMN_NAME_HORA_SAIDA, participante.getHoraSaida());

            String where = ParticipanteContract.Participante._ID + " = " + participante.getId();
            long id = db.update(ParticipanteContract.Participante.TABLE_NAME, values, where, null);
            atualizar();
        } catch (Exception e) {
            Log.e("BIENAL", e.getLocalizedMessage());
            Log.e("BIENAL", e.getStackTrace().toString());
        }
    }
}
