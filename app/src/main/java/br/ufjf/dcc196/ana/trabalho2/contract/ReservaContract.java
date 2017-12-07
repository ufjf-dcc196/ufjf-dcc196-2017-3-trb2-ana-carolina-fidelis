package br.ufjf.dcc196.ana.trabalho2.contract;


import android.provider.BaseColumns;

public class ReservaContract {
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";
    public static final String SQL_CREATE_RESERVA = "CREATE TABLE " + Reserva.TABLE_NAME + " (" +
            Reserva._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Reserva.COLUMN_NAME_PARTICIPANTE + INT_TYPE + SEP +
            Reserva.COLUMN_NAME_LIVRO+ INT_TYPE + SEP +
            " FOREIGN KEY ("+ Reserva.COLUMN_NAME_PARTICIPANTE + ") REFERENCES "+
            ParticipanteContract.Participante.TABLE_NAME + "("+ParticipanteContract.Participante._ID + ")" + SEP+
            " FOREIGN KEY ("+ Reserva.COLUMN_NAME_LIVRO + ") REFERENCES " +
            LivroContract.Livro.TABLE_NAME +"("+LivroContract.Livro._ID + "))";
    public static final String SQL_DROP_RESERVA = "DROP TABLE IF EXISTS " + Reserva.TABLE_NAME;

    public static final  class Reserva implements BaseColumns {
        public static final String TABLE_NAME = "reserva";
        public static final String COLUMN_NAME_PARTICIPANTE = "participante_id";
        public static final String COLUMN_NAME_LIVRO = "livro_id";
    }


    public ReservaContract(){}
}
