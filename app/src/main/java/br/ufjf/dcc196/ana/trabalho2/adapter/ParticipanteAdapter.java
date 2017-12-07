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
        String nome = cursor.getString(cursor.getColumnIndexOrThrow(BienalContract.Participante.COLUMN_NAME_NOME));
        String email = cursor.getString(cursor.getColumnIndexOrThrow(BienalContract.Participante.COLUMN_NAME_EMAIL));
        txtNome.setText(nome);
        txtEmail.setText(email);
    }

    public void atualizar(){
        try {
            SQLiteDatabase db = bienalDBHelper.getReadableDatabase();
            String[] visao = {
                    BienalContract.Participante._ID,
                    BienalContract.Participante.COLUMN_NAME_NOME,
                    BienalContract.Participante.COLUMN_NAME_EMAIL,
                    BienalContract.Participante.COLUMN_NAME_HORA_ENTRADA,
                    BienalContract.Participante.COLUMN_NAME_HORA_SAIDA
            };

            String sort = BienalContract.Participante.COLUMN_NAME_NOME + " ASC";
            Cursor c = db.query(BienalContract.Participante.TABLE_NAME, visao, null, null, null, null, sort);
            this.changeCursor(c);

        } catch (Exception e) {
            Log.e("BIENAL", e.getLocalizedMessage());
            Log.e("BIENAL", e.getStackTrace().toString());
        }
    }

    public void inserir(Participante participante){
        try {
            SQLiteDatabase db = bienalDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(BienalContract.Participante.COLUMN_NAME_NOME, participante.getNome());
            values.put(BienalContract.Participante.COLUMN_NAME_EMAIL, participante.getEmail());;
            long id = db.insert(BienalContract.Participante.TABLE_NAME, null, values);
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
                    BienalContract.Participante._ID,
                    BienalContract.Participante.COLUMN_NAME_NOME,
                    BienalContract.Participante.COLUMN_NAME_EMAIL,
                    BienalContract.Participante.COLUMN_NAME_HORA_ENTRADA,
                    BienalContract.Participante.COLUMN_NAME_HORA_SAIDA
            };
            String selecao = BienalContract.Participante._ID + " = " + id;
            Cursor c = db.query(BienalContract.Participante.TABLE_NAME, visao, selecao, null, null, null, null);
            c.moveToFirst();
            participante.setId(c.getInt(c.getColumnIndex(BienalContract.Livro._ID)));
            participante.setNome(c.getString(c.getColumnIndex(BienalContract.Participante.COLUMN_NAME_NOME)));
            participante.setEmail(c.getString(c.getColumnIndex(BienalContract.Participante.COLUMN_NAME_EMAIL)));
            participante.setHoraEntrada(c.getString(c.getColumnIndex(BienalContract.Participante.COLUMN_NAME_HORA_ENTRADA)));
            participante.setHoraSaida(c.getString(c.getColumnIndex(BienalContract.Participante.COLUMN_NAME_HORA_SAIDA)));
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
            values.put(BienalContract.Participante.COLUMN_NAME_HORA_ENTRADA, participante.getHoraEntrada());

            String where = BienalContract.Participante._ID+ " = ";
            String [] args = {Integer.toString(participante.getId())};
            long id = db.update(BienalContract.Participante.TABLE_NAME, values, where, args);
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
            values.put(BienalContract.Participante.COLUMN_NAME_HORA_SAIDA, participante.getHoraSaida());

            String where = BienalContract.Participante._ID+ " = ";
            String [] args = {Integer.toString(participante.getId())};
            long id = db.update(BienalContract.Participante.TABLE_NAME, values, where, args);
            atualizar();
        } catch (Exception e) {
            Log.e("BIENAL", e.getLocalizedMessage());
            Log.e("BIENAL", e.getStackTrace().toString());
        }
    }
}
