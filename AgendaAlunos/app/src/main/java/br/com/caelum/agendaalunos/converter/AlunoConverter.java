package br.com.caelum.agendaalunos.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.com.caelum.agendaalunos.br.com.caelum.agendaalunos.modelo.Aluno;

/**
 * Created by android7543 on 16/05/18.
 */

public class AlunoConverter {

    public String toJson(List<Aluno> lista) {
        JSONStringer jStringer = new JSONStringer();

        try {
            jStringer.object().key("list").array().object().key("aluno").array();

            for (Aluno a : lista) {
                jStringer.object().key("id").value(a.getId())
                        .key("nome").value(a.getNome())
                        .key("telefone").value(a.getTelefone())
                        .key("endereco").value(a.getEndereco())
                        .key("site").value(a.getSite())
                        .key("nota").value(a.getNota())
                        .key("caminhofoto").value(a.getCaminhofoto())
                        .endObject();
            }

            jStringer.endArray().endObject().endArray().endObject();

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jStringer.toString();
    }
}
