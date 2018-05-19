package br.com.caelum.agendaalunos.helper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.caelum.agendaalunos.CadastroActivity;
import br.com.caelum.agendaalunos.R;
import br.com.caelum.agendaalunos.br.com.caelum.agendaalunos.modelo.Aluno;

/**
 * Created by android7543 on 10/05/18.
 */

public class CadastroHelper {

    private Aluno aluno;
    private EditText nome;
    private EditText telefone;
    private EditText endereco;
    private EditText site;
    private RatingBar nota;
    private FloatingActionButton botao;
    private ImageView foto;

    public CadastroHelper(CadastroActivity activity) {
        this.aluno = new Aluno();
        this.nome = (EditText) activity.findViewById(R.id.nome);
        this.telefone = (EditText) activity.findViewById(R.id.telefone);
        this.endereco = (EditText) activity.findViewById(R.id.endereco);
        this.site = (EditText) activity.findViewById(R.id.site);
        this.nota = (RatingBar) activity.findViewById(R.id.nota);
        this.botao = (FloatingActionButton) activity.findViewById(R.id.formBtnFoto);
        this.foto = (ImageView) activity.findViewById(R.id.formFoto);
    }

    public Aluno getAluno() {
        aluno.setNome(this.nome.getText().toString());
        aluno.setTelefone(this.telefone.getText().toString());
        aluno.setEndereco(this.endereco.getText().toString());
        aluno.setSite(this.site.getText().toString());
        aluno.setNota(Double.valueOf(this.nota.getProgress()));
        aluno.setCaminhofoto(this.foto.getTag().toString());

        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.nome.setText(aluno.getNome());
        this.telefone.setText(aluno.getTelefone());
        this.endereco.setText(aluno.getEndereco());
        this.site.setText(aluno.getSite());
        this.nota.setProgress(aluno.getNota().intValue());

        if (aluno.getCaminhofoto() != null && !aluno.getCaminhofoto().isEmpty())
            colocaImagemNaTela(aluno.getCaminhofoto());

        this.aluno = aluno;
    }

    public boolean validaCampos() {
        if (this.nome.getText().toString().isEmpty()) {
            this.nome.setError("Campo nao pode ser vazio.");
            return false;
        }

        return true;
    }

    public FloatingActionButton getBotao() {
        return botao;
    }

    public void colocaImagemNaTela(String caminho) {
        Bitmap bmp = BitmapFactory.decodeFile(caminho);
        Bitmap bmpReduzido = Bitmap.createScaledBitmap(bmp, 300, 300, true); // altera o tamanho da imagem..........

        foto.setImageBitmap(bmpReduzido);
        foto.setScaleType(ImageView.ScaleType.FIT_XY); // configura como a foto ira paracer na tela
        foto.setTag(caminho);
    }
}


