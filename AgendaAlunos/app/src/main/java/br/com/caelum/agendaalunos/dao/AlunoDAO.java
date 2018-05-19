package br.com.caelum.agendaalunos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatatypeMismatchException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.agendaalunos.br.com.caelum.agendaalunos.modelo.Aluno;

/**
 * Created by android7543 on 10/05/18.
 */

public class AlunoDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 2; //vesao do meu banco de dados......
    private static final String TABELA = "ALUNOS";
    private static final String DATABASE = "CadastroCaelum";

    public AlunoDAO (Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABELA +
                " (ID INTEGER PRIMARY KEY, " +
                " NOME TEXT NOT NULL," +
                " TELEFONE TEXT," +
                " ENDERECO TEXT," +
                " SITE TEXT," +
                " NOTA REAL," +
                " CAMINHO_FOTO TEXT);";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { // i= versao do meu banco , i1 = nova versao do banco...
//        String sql = "DROP TABLE IF EXISTS " + TABELA;
        String sql = "";

        switch (i1) {
            case 2:
                sql = "ALTER TABLE " + TABELA + " ADD COLUMN CAMINHO_FOTO TEXT;";
                sqLiteDatabase.execSQL(sql);
            default:
        }

//        onCreate(sqLiteDatabase);
    }

    public long salvar(Aluno aluno) {
        ContentValues values = new ContentValues();

        values.put("NOME", aluno.getNome());
        values.put("TELEFONE", aluno.getTelefone());
        values.put("ENDERECO", aluno.getEndereco());
        values.put("SITE", aluno.getSite());
        values.put("NOTA", aluno.getNota());
        values.put("CAMINHO_FOTO", aluno.getCaminhofoto());

        return getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Aluno> getLista() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + TABELA;

        Cursor cursor = null;
        try {
            cursor = getReadableDatabase().rawQuery(sql, null);

            Aluno aluno;
            while (cursor.moveToNext()) {
                aluno = new Aluno();
                aluno.setId(cursor.getLong(cursor.getColumnIndex("ID")));
                aluno.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                aluno.setTelefone(cursor.getString(cursor.getColumnIndex("TELEFONE")));
                aluno.setEndereco(cursor.getString(cursor.getColumnIndex("ENDERECO")));
                aluno.setSite(cursor.getString(cursor.getColumnIndex("SITE")));
                aluno.setNota(cursor.getDouble(cursor.getColumnIndex("NOTA")));
                aluno.setCaminhofoto(cursor.getString(cursor.getColumnIndex("CAMINHO_FOTO")));

                lista.add(aluno);
            }
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();

            this.close();
        }

        return lista;
    }

    public int deletar(Aluno aluno) {
        String[] args  = {aluno.getId().toString()};
        return getWritableDatabase().delete(this.TABELA, "id=?", args);
    }

    public int alterar(Aluno aluno) {
        ContentValues values = new ContentValues();
        values.put("NOME", aluno.getNome());
        values.put("TELEFONE", aluno.getTelefone());
        values.put("ENDERECO", aluno.getEndereco());
        values.put("SITE", aluno.getSite());
        values.put("NOTA", aluno.getNota());
        values.put("CAMINHO_FOTO", aluno.getCaminhofoto());

        String[] args = {aluno.getId().toString()};
        return getWritableDatabase().update(this.TABELA, values, "id=?", args);
    }

    public boolean exitsAluno(String telefone) {
        String sql = "SELECT TELEFONE FROM " + TABELA + " WHERE TELEFONE = ?";

        SQLiteDatabase d = getReadableDatabase();

        String[] args = {telefone};
        Cursor c = d.rawQuery(sql, args);
        int totalAlunos = c.getCount();

        return (totalAlunos > 0);
    }
}
