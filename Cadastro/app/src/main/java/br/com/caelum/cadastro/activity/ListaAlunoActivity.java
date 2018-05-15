package br.com.caelum.cadastro.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastro.ContextActionBar;
import br.com.caelum.cadastro.Permissao;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.model.Aluno;

public class ListaAlunoActivity extends AppCompatActivity {
    private ListView listaAlunos;

    @Override
    protected void onStart() {
        super.onStart();

        this.carregaLista();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aluno);

     //   Permissao.fazPermissao(this);

        this.listaAlunos = (ListView) findViewById(R.id.listaAlunos);

        registerForContextMenu(this.listaAlunos);


        FloatingActionButton btnAdicionar = (FloatingActionButton) findViewById(R.id.btnAdicionar);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ListaAlunoActivity.this, FormularioAlunoActivity.class);

                startActivity(intent);
            }
        });

        this.listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Aluno aluno = (Aluno) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(ListaAlunoActivity.this, FormularioAlunoActivity.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });

        this.listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Aluno aluno = (Aluno) adapterView.getItemAtPosition(i);

                ContextActionBar actionBar = new ContextActionBar(aluno, ListaAlunoActivity.this);
                ListaAlunoActivity.this.startSupportActionMode(actionBar);

                return true;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add("Ligar");
        menu.add("Enviar SMS");
        menu.add("Achar no Mapa");
        menu.add("Navegar no site");
        MenuItem deletar = menu.add("Deletar");

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Aluno aluno = (Aluno) ListaAlunoActivity.this.listaAlunos.getItemAtPosition(info.position);

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                new AlertDialog.Builder(ListaAlunoActivity.this).
                        setIcon(android.R.drawable.ic_dialog_alert).
                        setTitle("Deletar").
                        setMessage("Deseja mesmo deletar?").
                        setPositiveButton("Quero", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int whih){
                                AlunoDAO alunoDAO = new AlunoDAO(ListaAlunoActivity.this);

                                if(alunoDAO.deletar(aluno) > 0){
                                    Toast.makeText(ListaAlunoActivity.this, "Aluno deletado", Toast.LENGTH_SHORT);
                                    ListaAlunoActivity.this.carregaLista();
                                }else {
                                    Toast.makeText(ListaAlunoActivity.this, "Aluno nao deletado", Toast.LENGTH_SHORT);
                                }
                            }
                        }).setNegativeButton("Nao", null).show();
                return true;
            }
        });
    }

    public void carregaLista(){
        AlunoDAO alunoDAO = new AlunoDAO(this);
        List<Aluno> alunos = alunoDAO.listar() ;

        int layout = android.R.layout.simple_expandable_list_item_1;

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, layout, alunos);


        this.listaAlunos.setAdapter(adapter);
    }

    public ListView getListaAlunos() {
        return listaAlunos;
    }

    public void setListaAlunos(ListView listaAlunos) {
        this.listaAlunos = listaAlunos;
    }


}
