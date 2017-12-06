package br.ufjf.dcc196.ana.trabalho2.adapter;

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

import br.ufjf.dcc196.ana.trabalho2.R;
import br.ufjf.dcc196.ana.trabalho2.contract.BienalContract;
import br.ufjf.dcc196.ana.trabalho2.helper.BienalDBHelper;
import br.ufjf.dcc196.ana.trabalho2.model.Livro;

public class LivroAdapter extends CursorAdapter {
    private BienalDBHelper bienalDBHelper;

    public LivroAdapter(Context context, Cursor c) {
        super(context, c, 0);
        bienalDBHelper = new BienalDBHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.livro_layout,viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtTitulo = (TextView) view.findViewById(R.id.txtTitulo);
        TextView txtAno = (TextView) view.findViewById(R.id.txtAno);
        TextView txtAutor = (TextView) view.findViewById(R.id.txtAutor);
        String titulo = cursor.getString(cursor.getColumnIndexOrThrow(BienalContract.Livro.COLUMN_NAME_TITULO));
        String autor = cursor.getString(cursor.getColumnIndexOrThrow(BienalContract.Livro.COLUMN_NAME_AUTOR));
        int ano = cursor.getInt(cursor.getColumnIndexOrThrow(BienalContract.Livro.COLUMN_NAME_ANO));
        txtTitulo.setText(titulo);
        txtAutor.setText(autor);
        txtAno.setText(String.valueOf(ano));
    }

    public void atualizar(){
        try {
            SQLiteDatabase db = bienalDBHelper.getReadableDatabase();
            String[] visao = {
                    BienalContract.Livro._ID,
                    BienalContract.Livro.COLUMN_NAME_TITULO,
                    BienalContract.Livro.COLUMN_NAME_AUTOR,
                    BienalContract.Livro.COLUMN_NAME_ANO,
            };

            String sort = BienalContract.Livro.COLUMN_NAME_AUTOR + " ASC";
            Cursor c = db.query(BienalContract.Livro.TABLE_NAME, visao, null, null, null, null, sort);
            this.changeCursor(c);

        } catch (Exception e) {
            Log.e("BIENAL", e.getLocalizedMessage());
            Log.e("BIENAL", e.getStackTrace().toString());
        }
    }

    public void inserir(Livro livro){
        try {
            SQLiteDatabase db = bienalDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(BienalContract.Livro.COLUMN_NAME_TITULO, livro.getTitulo());
            values.put(BienalContract.Livro.COLUMN_NAME_AUTOR, livro.getAutor());
            values.put(BienalContract.Livro.COLUMN_NAME_ANO, livro.getAno());
            values.put(BienalContract.Livro.COLUMN_NAME_EDITORA, livro.getEditora());
            long id = db.insert(BienalContract.Livro.TABLE_NAME, null, values);
            atualizar();
        } catch (Exception e) {
            Log.e("BIENAL", e.getLocalizedMessage());
            Log.e("BIENAL", e.getStackTrace().toString());
        }
    }

    public Livro buscar(long id){
        Livro livro = new Livro();
        try {
            SQLiteDatabase db = bienalDBHelper.getReadableDatabase();
            String[] visao = {
                    BienalContract.Livro._ID,
                    BienalContract.Livro.COLUMN_NAME_TITULO,
                    BienalContract.Livro.COLUMN_NAME_AUTOR,
                    BienalContract.Livro.COLUMN_NAME_ANO,
                    BienalContract.Livro.COLUMN_NAME_EDITORA
            };
            String selecao = BienalContract.Livro._ID + " = " + id;
            Cursor c = db.query(BienalContract.Livro.TABLE_NAME, visao, selecao, null, null, null, null);
            c.moveToFirst();
            livro.setId(c.getInt(c.getColumnIndex(BienalContract.Livro._ID)));
            livro.setTitulo(c.getString(c.getColumnIndex(BienalContract.Livro.COLUMN_NAME_TITULO)));
            livro.setAutor(c.getString(c.getColumnIndex(BienalContract.Livro.COLUMN_NAME_AUTOR)));
            livro.setAno(c.getString(c.getColumnIndex(BienalContract.Livro.COLUMN_NAME_ANO)));
            livro.setEditora(c.getString(c.getColumnIndex(BienalContract.Livro.COLUMN_NAME_EDITORA)));

        } catch (Exception e) {
            Log.e("BIENAL", e.getLocalizedMessage());
            Log.e("BIENAL", e.getStackTrace().toString());
        }
        return livro;
    }
}