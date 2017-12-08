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
import br.ufjf.dcc196.ana.trabalho2.helper.BienalDBHelper;

public class LivroSimpleAdapter extends CursorAdapter{
    private BienalDBHelper bienalDBHelper;

    public LivroSimpleAdapter(Context context, Cursor c) {
        super(context, c, 0);
        bienalDBHelper = new BienalDBHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.simple_layout,viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtTitulo = (TextView) view.findViewById(R.id.txtSimple);
        String titulo = cursor.getString(cursor.getColumnIndexOrThrow(LivroContract.Livro.COLUMN_NAME_TITULO));
        txtTitulo.setText(titulo);
    }

    public void atualizar(){
        try {
            SQLiteDatabase db = bienalDBHelper.getReadableDatabase();
            String[] visao = {
                    LivroContract.Livro._ID,
                    LivroContract.Livro.COLUMN_NAME_TITULO,
            };
            String sort = LivroContract.Livro.COLUMN_NAME_TITULO + " ASC";
            Cursor c = db.query(LivroContract.Livro.TABLE_NAME, visao, null, null, null, null, sort);
            this.changeCursor(c);
        }
        catch (Exception e) {
            Log.e("BIENAL LIVRO - ", e.getLocalizedMessage());
            Log.e("BIENAL LIVRO - ", e.getStackTrace().toString());
        }
    }
}
