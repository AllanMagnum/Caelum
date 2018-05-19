package br.com.caelum.agendaalunos;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android7543 on 14/05/18.
 */

public class Permissao {

    public static void verificaPermissoes(Activity activity) {
        //Verificar permissoes.......
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissoes = {Manifest.permission.CALL_PHONE,
                    Manifest.permission.INTERNET,
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA};
            List<String> listaPermissoes = new ArrayList<>();

            for (String p : permissoes) {
                if (activity.checkSelfPermission(p) != PackageManager.PERMISSION_GRANTED) {
                    listaPermissoes.add(p);
                }
            }

            if (!listaPermissoes.isEmpty()) {
                String[] array = listaPermissoes.toArray(new String[]{});

                activity.requestPermissions(array, 123); //segundo paramentro e o id do pedido que podera ser tratado de forma diferente em outro metodo.......
            }
        }
        //...........................................................................
    }
}
