package br.com.caelum.agendaalunos.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.agendaalunos.R;
import br.com.caelum.agendaalunos.br.com.caelum.agendaalunos.modelo.Aluno;

/**
 * Created by android7543 on 15/05/18.
 */

public class ListaAlunosAdapter extends BaseAdapter {

    private List<Aluno> listaAlunos;
    private Activity activity;

    public ListaAlunosAdapter(List<Aluno> listaAlunos, Activity activity) {
        this.listaAlunos = listaAlunos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.listaAlunos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaAlunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaAlunos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View viewNew = inflater.inflate(R.layout.item_list_layout, viewGroup, false); // false = ja prender no pai.........

        TextView texto = (TextView) viewNew.findViewById(R.id.item_nome);

        Aluno aluno = listaAlunos.get(i);
        texto.setText(aluno.getNome());

        ImageView foto = (ImageView) viewNew.findViewById(R.id.item_foto);

        Bitmap bm;
        if (aluno.getCaminhofoto() != null) {
            bm = BitmapFactory.decodeFile(aluno.getCaminhofoto());
        } else {
            bm = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
        }

        bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
        foto.setImageBitmap(bm);

        return viewNew;
    }
}
