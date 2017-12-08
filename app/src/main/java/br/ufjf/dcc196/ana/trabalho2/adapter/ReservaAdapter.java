package br.ufjf.dcc196.ana.trabalho2.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.ufjf.dcc196.ana.trabalho2.R;
import br.ufjf.dcc196.ana.trabalho2.contract.ParticipanteContract;
import br.ufjf.dcc196.ana.trabalho2.contract.ReservaContract;
import br.ufjf.dcc196.ana.trabalho2.helper.BienalDBHelper;
import br.ufjf.dcc196.ana.trabalho2.model.Livro;
import br.ufjf.dcc196.ana.trabalho2.model.Participante;


public class ReservaAdapter extends CursorAdapter {
    private BienalDBHelper bienalDBHelper;

    public ReservaAdapter(Context context, Cursor c) {
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

    public void atualizar(int idLivro){
        String rawQuery = ReservaContract.SQL_CONSULTA_RESERVAS +" WHERE " + ReservaContract.Reserva.COLUMN_NAME_LIVRO + "=" + idLivro;
        try {
            SQLiteDatabase db = bienalDBHelper.getReadableDatabase();
            String[] visao = {
                    ReservaContract.Reserva._ID,
                    ReservaContract.Reserva.COLUMN_NAME_LIVRO,
                    ReservaContract.Reserva.COLUMN_NAME_PARTICIPANTE,

            };
            Cursor c = db.rawQuery(rawQuery,null);

            this.changeCursor(c);
        }
        catch (Exception e) {
            Log.e("BIENAL RESERVA -", e.getLocalizedMessage());
            Log.e("BIENAL RESERVA -", e.getStackTrace().toString());
        }
    }

    public void inserir(Livro livro, Participante participante){
        try {
            SQLiteDatabase db = bienalDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(ReservaContract.Reserva.COLUMN_NAME_PARTICIPANTE, participante.getId());
            values.put(ReservaContract.Reserva.COLUMN_NAME_LIVRO, livro.getId());
            long id = db.insert(ReservaContract.Reserva.TABLE_NAME, null, values);
            atualizar(livro.getId());

        } catch (Exception e) {
            Log.e("BIENAL RESERVA - ", e.getLocalizedMessage());
            Log.e("BIENAL RESERVA - ", e.getStackTrace().toString());
        }
    }
}
