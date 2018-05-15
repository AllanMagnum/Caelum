package br.com.caelum.cadastro;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;

import android.widget.Toast;



import br.com.caelum.cadastro.activity.ListaAlunoActivity;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.model.Aluno;

/**
 * Created by android7543 on 11/05/18.
 */

public class ContextActionBar implements ActionMode.Callback {
    private Aluno alunoSelecionado;
    private ListaAlunoActivity activity;

    public ContextActionBar(Aluno alunoSelecionado, ListaAlunoActivity activity) {
        this.alunoSelecionado = alunoSelecionado;
        this.activity = activity;
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        final MenuItem ligar = menu.add("Ligar");
        final MenuItem sms = menu.add("Enviar SMS");
        final MenuItem mapa = menu.add("Achar no Mapa");
        final MenuItem site = menu.add("Navegar no site");

        MenuItem deletar = menu.add("Deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                new AlertDialog.Builder(activity).
                        setIcon(android.R.drawable.ic_dialog_alert).
                        setTitle("Deletar").
                        setMessage("Deseja mesmo deletar?").
                        setPositiveButton("Quero", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int whih){
                                AlunoDAO alunoDAO = new AlunoDAO(activity);

                                if(alunoDAO.deletar(alunoSelecionado) > 0){
                                    Toast.makeText(activity, "Aluno deletado", Toast.LENGTH_SHORT);
                                    activity.carregaLista();
                                }else {
                                    Toast.makeText(activity, "Aluno nao deletado", Toast.LENGTH_SHORT);
                                }
                            }
                        }).setNegativeButton("Nao", null).show();
                return true;
            }
        });

        ligar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intentLigar = new Intent(Intent.ACTION_CALL);
                intentLigar.setData(Uri.parse("tel:"+ alunoSelecionado.getTelefone()));
                ligar.setIntent(intentLigar);
                return true;
            }
        });

        sms.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intentSms = new Intent(Intent.ACTION_VIEW);
                intentSms.setData(Uri.parse("sms:"+ alunoSelecionado.getTelefone()));
                intentSms.putExtra("sms_body","Sua nota e: " + alunoSelecionado.getNota());
                sms.setIntent(intentSms);
                return true;
            }
        });

        mapa.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intentMapa = new Intent(Intent.ACTION_VIEW);
                intentMapa.setData(Uri.parse("geo:0,0?z=14&q=" + alunoSelecionado.getEndereco()));
                mapa.setIntent(intentMapa);
                return true;
            }
        });

        site.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intentSite = new Intent(Intent.ACTION_VIEW);
                String siteStr = alunoSelecionado.getSite();

                if( !siteStr.startsWith("http://") ){
                    siteStr= "http://" + siteStr;
                }
                intentSite.setData(Uri.parse(siteStr));

                site.setIntent(intentSite);

                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {

    }
}
