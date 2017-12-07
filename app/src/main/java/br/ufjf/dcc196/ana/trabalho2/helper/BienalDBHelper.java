package br.ufjf.dcc196.ana.trabalho2.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.ufjf.dcc196.ana.trabalho2.contract.LivroContract;
import br.ufjf.dcc196.ana.trabalho2.contract.ParticipanteContract;
import br.ufjf.dcc196.ana.trabalho2.contract.ReservaContract;

public class BienalDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "Bienal.db";

    public BienalDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LivroContract.SQL_CREATE_LIVRO);
        Log.i("BIENAL", "Tabela Livro criada.");
        sqLiteDatabase.execSQL(ParticipanteContract.SQL_CREATE_PARTICIPANTE);
        Log.i("BIENAL  ", "Tabela Participante criada.");
        sqLiteDatabase.execSQL(ReservaContract.SQL_CREATE_RESERVA);
        Log.i("BIENAL", "Tabela Reserva criada");
        Log.i("BIENAL", "Versão: " + DATABASE_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int antigo, int novo) {
        sqLiteDatabase.execSQL(LivroContract.SQL_DROP_LIVRO);
        sqLiteDatabase.execSQL(ParticipanteContract.SQL_DROP_PARTICIPANTE);
        sqLiteDatabase.execSQL(ReservaContract.SQL_DROP_RESERVA);
        Log.i("BIENAL", "Banco de dados atualizados da versão " + antigo + " para versão " + novo);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int antigo, int novo) {
        Log.i("BIENAL", "Banco de dados atualizados da versão " + antigo + " para versão " + novo);
        onUpgrade(db,antigo,novo);
    }

}
