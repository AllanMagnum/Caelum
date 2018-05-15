package br.com.caelum.cadastro.helper;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.activity.FormularioAlunoActivity;
import br.com.caelum.cadastro.model.Aluno;

/**
 * Created by android7543 on 10/05/18.
 */

public class FormularioAlunoHelper {

    private EditText nome;
    private EditText telefone;
    private EditText endereco;
    private EditText site;
    private RatingBar nota;

    private Aluno aluno;

    public FormularioAlunoHelper(FormularioAlunoActivity activity) {
        this.nome = (EditText) activity.findViewById(R.id.nome);
        this.telefone = (EditText) activity.findViewById(R.id.telefone);
        this.endereco = (EditText) activity.findViewById(R.id.endereco);
        this.site = (EditText) activity.findViewById(R.id.site);
        this.nota = (RatingBar) activity.findViewById(R.id.nota);

        this.aluno = new Aluno();
    }

    public Aluno getAlunoFormulario(){
        this.aluno.setNome(this.nome.getText().toString());
        this.aluno.setTelefone(this.telefone.getText().toString());
        this.aluno.setEndereco(this.endereco.getText().toString());
        this.aluno.setSite(this.site.getText().toString());
        this.aluno.setNota((double) this.nota.getRating());

        return this.aluno;
    }

    public void setAlunoFormulario(Aluno aluno){
        this.nome.setText(aluno.getNome());
        this.telefone.setText(aluno.getTelefone());
        this.endereco.setText(aluno.getEndereco());
        this.site.setText(aluno.getSite());
        this.nota.setProgress(  aluno.getNota().intValue() );

        this.aluno = aluno;
    }

    public boolean temNome(){
        return !nome.getText().toString().isEmpty();
    }

    public void mostraErro(){
        this.nome.setError("Campo nome nao pode ser vazio");
    }
}
