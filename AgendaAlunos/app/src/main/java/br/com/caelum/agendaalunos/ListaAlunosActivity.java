package br.com.caelum.agendaalunos;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.net.URI;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.agendaalunos.adapter.ListaAlunosAdapter;
import br.com.caelum.agendaalunos.br.com.caelum.agendaalunos.modelo.Aluno;
import br.com.caelum.agendaalunos.converter.AlunoConverter;
import br.com.caelum.agendaalunos.dao.AlunoDAO;
import br.com.caelum.agendaalunos.servico.WebClient;

public class ListaAlunosActivity extends AppCompatActivity { //funçao da Activity e direcionar para açoes......

    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        Permissao.verificaPermissoes(this);

        this.listaAlunos =  (ListView) findViewById(R.id.lista_alunos);

        registerForContextMenu(this.listaAlunos);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(ListaAlunosActivity.this, "Posiçao: " + i, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListaAlunosActivity.this, CadastroActivity.class);
                Aluno aluno = (Aluno) adapterView.getItemAtPosition(i);
                intent.putExtra("aluno", aluno);

                startActivity(intent);
            }
        });

//        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String nome = (String) adapterView.getItemAtPosition(i);
//
//                Toast.makeText(ListaAlunosActivity.this, "Oi, Nome: " + nome + ".", Toast.LENGTH_LONG).show();
//
//                return false; //true mostra apenas o click longo, false mostra o longo e o normal......
//            }
//        });

        FloatingActionButton  botao = (FloatingActionButton) findViewById(R.id.novoAluno);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(ListaAlunosActivity.this, "Clicou", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ListaAlunosActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.carregaAlunos();
    }

    private void carregaAlunos() {

        //String[] alunos = {"Anderson", "Filipe", "Guilherme", "Allan", "Diogo"};

        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> lista = dao.getLista();

//        int layout = android.R.layout.simple_list_item_1;
        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layout, alunos);
//        final ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, layout, lista);

        int layout = android.R.layout.activity_list_item;
        final ListaAlunosAdapter adapter = new ListaAlunosAdapter(lista,this);
        listaAlunos.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);

        MenuItem ligar = menu.add("Ligar");
        Intent intentLigar =  new Intent(Intent.ACTION_CALL);
        intentLigar.setData(Uri.parse("tel:" + aluno.getTelefone()));
        ligar.setIntent(intentLigar);

        MenuItem sms = menu.add("Enviar SMS");
        Intent intentSMS = new Intent(Intent.ACTION_VIEW);
        intentSMS.setData(Uri.parse("sms:" + aluno.getTelefone()));
        intentSMS.putExtra("sms_boby", "Sua nota e " + aluno.getNota());
        sms.setIntent(intentSMS);

        MenuItem mapa = menu.add("Achar no Mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo: 0,0?z=14&q=" + aluno.getEndereco()));
        mapa.setIntent(intentMapa);

        MenuItem site  = menu.add("Navegar no site");
        Intent intentSite = new Intent(Intent.ACTION_VIEW);
        String strSite = aluno.getSite();

        if (!strSite.startsWith("http://")) {
            strSite = "http://" + strSite;
        }

        intentSite.setData(Uri.parse(strSite));
        site.setIntent(intentSite);

        MenuItem deletar = menu.add("Deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                new AlertDialog.Builder(ListaAlunosActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Deletar")
                        .setMessage("Deseja mesmo deletar?")
                        .setPositiveButton("Quero",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                                        dao.deletar(aluno);
                                        dao.close();
                                        carregaAlunos();
                                    }
                                }).setNegativeButton("Nao", null).show();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_enviar_notas:
            AlunoDAO dao = new AlunoDAO(this);
            List<Aluno> alunos = dao.getLista();

            String json = new AlunoConverter().toJson(alunos);

            String resposta = new WebClient().post(json);

            Toast.makeText(this, resposta, Toast.LENGTH_LONG).show();
        }

        return false;
    }
}
