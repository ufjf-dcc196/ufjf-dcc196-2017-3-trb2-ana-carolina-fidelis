package br.ufjf.dcc196.ana.trabalho2.contract;

import android.provider.BaseColumns;

public class ParticipanteContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";

    public static final String SQL_CREATE_PARTICIPANTE = "CREATE TABLE " + Participante.TABLE_NAME + " (" +
            Participante._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Participante.COLUMN_NAME_NOME + TEXT_TYPE + SEP +
            Participante.COLUMN_NAME_EMAIL + TEXT_TYPE + SEP +
            Participante.COLUMN_NAME_HORA_ENTRADA + TEXT_TYPE + SEP +
            Participante.COLUMN_NAME_HORA_SAIDA + TEXT_TYPE + ")";

    public static final String SQL_DROP_PARTICIPANTE = "DROP TABLE IF EXISTS " + Participante.TABLE_NAME;

    public static final  class Participante implements BaseColumns {
        public static final String TABLE_NAME = "participante";
        public static final String COLUMN_NAME_NOME = "nome";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_HORA_ENTRADA = "horaEntrada";
        public static final String COLUMN_NAME_HORA_SAIDA = "horaSaida";
    }

    public ParticipanteContract(){}
}
