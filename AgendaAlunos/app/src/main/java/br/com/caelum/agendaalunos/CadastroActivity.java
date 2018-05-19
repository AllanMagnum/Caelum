package br.com.caelum.agendaalunos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import br.com.caelum.agendaalunos.br.com.caelum.agendaalunos.modelo.Aluno;
import br.com.caelum.agendaalunos.dao.AlunoDAO;
import br.com.caelum.agendaalunos.helper.CadastroHelper;

public class CadastroActivity extends AppCompatActivity {

    private CadastroHelper helper;
    private String caminhoArquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.helper = new CadastroHelper(this);

        Aluno aluno = (Aluno) getIntent().getSerializableExtra("aluno");
        if (aluno != null)
            this.helper.setAluno(aluno);

        FloatingActionButton btnFoto = this.helper.getBotao();
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoArquivo = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg"; //getFilesDir()().....
                Uri localArquivo = Uri.fromFile(new File(caminhoArquivo));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, localArquivo); //PutExtra usada para passar paramento opcionais.........
                startActivityForResult(intent, 1234);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
            helper.colocaImagemNaTela(caminhoArquivo);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro, menu); //Cria o menu na tela....

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_cadastro_ok:
                Aluno aluno = this.helper.getAluno();
                if (this.helper.validaCampos()) {
                    AlunoDAO dao = new AlunoDAO(this);

                    if (aluno.getId() == null) {
                        if (dao.salvar(aluno) == -1)
                            Toast.makeText(this, "Problema para salvar o aluno.!", Toast.LENGTH_LONG).show();

                        Toast.makeText(this, "Aluno cadastrado!", Toast.LENGTH_LONG);
                    } else {
                        dao.alterar(aluno);
                    }
                    finish();
                }
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // finish() - voltar para a lista de alunos nao criar a lista novamente e sim matar a cadastro, pois o android vai criando uma pila de activity.
}
