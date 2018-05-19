package br.com.caelum.agendaalunos.servico;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by android7543 on 16/05/18.
 */

public class WebClient {

    public String post(String json) {
        String jsonResposta = null;

        try {
            URL url = new URL("https://wwww.caelum.com.br/mobile");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoInput(true); //Tem envio.......
            conn.setDoOutput(true);// Tem retorno.....

            PrintStream ps = new PrintStream(conn.getOutputStream());
            ps.println(json);

            conn.connect(); //faz reuqisiçao  e aguarda retorno.....

            Scanner sc = new Scanner(conn.getInputStream());

            jsonResposta = sc.next();

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResposta;
    }
}
