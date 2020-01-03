package com.wenzeasy.ui.activities;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pixplicity.easyprefs.library.Prefs;
import com.wenzeasy.R;
import com.wenzeasy.remote.client.api.OSmsApi;
import com.wenzeasy.remote.client.api.ServiceGenerator;
import com.wenzeasy.util.Constants;

import dmax.dialog.SpotsDialog;

import static com.wenzeasy.app.Configuration.firebaseReferencce;

public class ConfirmPinActivity2 extends AppCompatActivity {

    public static String phoneNumber = "+243821741481";
    private SpotsDialog dialog;
    private EditText editTextVerificationCode;
    private Button buttonConfirmCode;
    private Button buttonReSendCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {

        }
        setContentView(R.layout.activity_confirm_pin);

        dialog = new SpotsDialog(this, "Verification en cours...");

        buttonConfirmCode = findViewById(R.id.btn_confirm_code);
        buttonReSendCode = findViewById(R.id.btn_resend_code);
        editTextVerificationCode = findViewById(R.id.editVerificationCode);

        editTextVerificationCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                String code = v.getText().toString();

                if (code.length()==2){
                    v.setText(code+" ");
                }

                validateEnteredCode(phoneNumber, v.getText().toString());
                return false;
            }
        });

        buttonConfirmCode.setEnabled(false);
        buttonConfirmCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getPresenter().confirmVerificationCode(phoneNumber, editTextVerificationCode.getText().toString());
                confirmVerificationCode(phoneNumber, editTextVerificationCode.getText().toString());
            }
        });
    }

    public void xml_resend_verification_code(View view) {

        //getPresenter().resendVerificationCode(editTextVerificationCode.getText().toString());
        dialog.show();
        resendVerificationCode(editTextVerificationCode.getText().toString());
    }



    public void xml_login_confirm_verification_code(View view) {

        confirmVerificationCode(phoneNumber, editTextVerificationCode.getText().toString());

    }

    public void validateEnteredCode(String phoneNumber, String enteredCode) {


        enteredCode = enteredCode.replace(" ","");

        if (enteredCode.length()==5){

            //getView().enabledControls(true);
            //getView().startVerificationCode();
            confirmVerificationCode(phoneNumber, enteredCode);
        }
    }

    public void resendVerificationCode(String phoneNumber){

        String message = ServiceGenerator.createNewSMS();

        OSmsApi.sendMessage(phoneNumber, message, new OSmsApi.OnSmsAuthListener() {
            @Override
            public void finish(String s) {
               // getView().onConfirmPinSuccess(s);
                Toast.makeText(ConfirmPinActivity2.this, "SMS Verification number is sent!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void failure(String message, Throwable t) {
                //getView().onError(message,t);
                Toast.makeText(ConfirmPinActivity2.this, t.getStackTrace().toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    public void confirmVerificationCode(String phoneNumber, String enteredCode){

        String codeSent = Prefs.getString(Constants.OSMS_VERIFICATION_CODE, "");
        codeSent = codeSent.replace(" ","");

        if (codeSent.equalsIgnoreCase(enteredCode)){

            firebaseReferencce.child(phoneNumber).child(enteredCode);

        }else {

        }
    }

}
