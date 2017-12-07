package br.ufjf.dcc196.ana.trabalho2.adapter;


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
import br.ufjf.dcc196.ana.trabalho2.contract.LivroContract;
import br.ufjf.dcc196.ana.trabalho2.contract.ParticipanteContract;
import br.ufjf.dcc196.ana.trabalho2.helper.BienalDBHelper;

public class ParticipanteSimpleAdapter extends CursorAdapter {
    private BienalDBHelper bienalDBHelper;

    public ParticipanteSimpleAdapter(Context context, Cursor c) {
        super(context, c, 0);
        bienalDBHelper = new BienalDBHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.simple_layout,viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtNome = (TextView) view.findViewById(R.id.txtSimple);
        String nome = cursor.getString(cursor.getColumnIndexOrThrow(ParticipanteContract.Participante.COLUMN_NAME_NOME));
        txtNome.setText(nome);
    }

    public void atualizar(){
        try {
            SQLiteDatabase db = bienalDBHelper.getReadableDatabase();
            String[] visao = {
                    ParticipanteContract.Participante._ID,
                    ParticipanteContract.Participante.COLUMN_NAME_NOME,
            };
            String sort = ParticipanteContract.Participante.COLUMN_NAME_NOME + " ASC";
            Cursor c = db.query(ParticipanteContract.Participante.TABLE_NAME, visao, null, null, null, null, sort);
            this.changeCursor(c);
        }
        catch (Exception e) {
            Log.e("BIENAL", e.getLocalizedMessage());
            Log.e("BIENAL", e.getStackTrace().toString());
        }
    }
}
