package br.com.caelum.cadastro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.helper.FormularioAlunoHelper;
import br.com.caelum.cadastro.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private FormularioAlunoHelper helper;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ) {
            case R.id.menu_formulario_ok:

                Aluno aluno = helper.getAlunoFormulario();

                AlunoDAO alunoDAO = new AlunoDAO(this);

                if(aluno.getId() == null){
                    if( helper.temNome() ) {

                        if ( alunoDAO.salvar(aluno) > 0) {
                            Toast.makeText(FormularioAlunoActivity.this, "Aluno " + aluno.getNome() + " salvo", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            helper.mostraErro();
                        }
                    }else{
                        helper.mostraErro();
                    }
                }else{
                    if( helper.temNome() ) {
                        if ( alunoDAO.alterar(aluno) > 0) {
                            Toast.makeText(FormularioAlunoActivity.this, "Aluno alterado", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            helper.mostraErro();
                        }
                    }else{
                        helper.mostraErro();
                    }
                }

                return false;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        this.helper = new FormularioAlunoHelper(this);


        Intent intent = getIntent();

        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        if(aluno.getId() != null){
            helper.setAlunoFormulario(aluno);
        }

    }
}
