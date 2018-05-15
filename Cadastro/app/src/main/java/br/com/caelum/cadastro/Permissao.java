package br.com.caelum.cadastro;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;


/**
 * Created by android7543 on 14/05/18.
 */

public class Permissao {
    private static final int CODE = 123;
    private static ArrayList<String> listaPermissoes = new ArrayList<>();


    public static void fazPermissao(Activity activity){
        String[] permissoes = {Manifest.permission.CALL_PHONE, Manifest.permission.RECEIVE_SMS, Manifest.permission.INTERNET};

        for (String permissao : permissoes ) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if( activity.checkSelfPermission(permissao) != PackageManager.PERMISSION_GRANTED){
                    listaPermissoes.add(permissao);
                }
            }

        }
        request(activity);
    }


    private static void request(Activity activity){
        String[] array = listaPermissoes.toArray(new String[]{});

        if(listaPermissoes.size() > 0){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(array, CODE);
            }
        }
    }
}
