package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.cadastro.model.Aluno;

/**
 * Created by android7543 on 10/05/18.
 */

public class AlunoDAO extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String TABELA = "ALUNO";
    private static final String DATABASE = "CADASTROCAELUM";

    public AlunoDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql;

        sql = "CREATE TABLE " + this.TABELA
            + " (ID INTEGER NOT NULL PRIMARY KEY, "
            + "  NOME TEXT NOT NULL,"
            + "  TELEFONE TEXT,"
            + "  ENDERECO TEXT,"
            + "  SITE TEXT,"
            + "  NOTA REAL);";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA );
        onCreate(sqLiteDatabase);
    }

    public Long salvar(Aluno aluno){
        ContentValues values = new ContentValues();

        values.put( "NOME", aluno.getNome() );
        values.put( "TELEFONE", aluno.getTelefone() );
        values.put( "ENDERECO", aluno.getEndereco() );
        values.put( "SITE", aluno.getSite() );
        values.put( "NOTA", aluno.getNota() );

        Long retorno = getWritableDatabase().insert(TABELA, null, values);

        this.close();

        return retorno;
    }

    public List<Aluno> listar(){
        List<Aluno> alunos = new ArrayList<>();

        String sql = "SELECT * FROM ALUNO";

        Cursor c = getReadableDatabase().rawQuery(sql, null);

        while ( c.moveToNext() ){
            Aluno aluno = new Aluno();

            aluno.setId( c.getLong( c.getColumnIndex("ID") ) );
            aluno.setNome(c.getString( c.getColumnIndex("NOME") ) );
            aluno.setTelefone(c.getString( c.getColumnIndex("TELEFONE") ) );
            aluno.setEndereco(c.getString( c.getColumnIndex("ENDERECO") ) );
            aluno.setSite(c.getString( c.getColumnIndex("SITE") ) );
            aluno.setNota(c.getDouble( c.getColumnIndex("NOTA") ) );

            alunos.add(aluno);
        }

        c.close();
        this.close();

        return alunos;
    }

    public Integer deletar(Aluno aluno) {
        String[] args = {aluno.getId().toString()};
        int retorno = getWritableDatabase().delete(TABELA, "id=?",args );
        this.close();
        return retorno;
    }

    public Integer alterar(Aluno aluno){
        String[] args = {aluno.getId().toString()};

        ContentValues values = new ContentValues();
        values.put( "NOME", aluno.getNome() );
        values.put( "TELEFONE", aluno.getTelefone() );
        values.put( "ENDERECO", aluno.getEndereco() );
        values.put( "SITE", aluno.getSite() );
        values.put( "NOTA", aluno.getNota() );

        int r = getWritableDatabase().update(TABELA, values,"id=?", args);

        this.close();

        return r;
    }
}
