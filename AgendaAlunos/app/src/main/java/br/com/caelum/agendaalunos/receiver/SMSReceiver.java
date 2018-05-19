package br.com.caelum.agendaalunos.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.caelum.agendaalunos.R;
import br.com.caelum.agendaalunos.dao.AlunoDAO;

/**
 * Created by android7543 on 16/05/18.
 */

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];

        SmsMessage sms;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            sms = SmsMessage.createFromPdu(pdu); // menor que a versao 6.0......
        } else {
            String formato = intent.getStringExtra("format");
            sms = SmsMessage.createFromPdu(pdu, formato); // >= versao 6.0.......
        }

        String telefone = sms.getOriginatingAddress();
        if (new AlunoDAO(context).exitsAluno(telefone)) {
            MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
            mp.start();

            Toast.makeText(context, "Chegou SMS: " + telefone, Toast.LENGTH_LONG).show();
        }
    }
}
