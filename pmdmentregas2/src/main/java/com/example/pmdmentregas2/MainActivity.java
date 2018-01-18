package com.example.pmdmentregas2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mylib.AsyncTask.HttpJsonAsyncTask;

public class MainActivity extends AppCompatActivity {
    private MainActivtyEvents mainActivityEvents;
    private Button btnLogin;
    private EditText txtUser;
    private EditText txtPass;
    private HttpJsonAsyncTask httpJsonAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mainActivityEvents = new MainActivtyEvents(this);
        this.btnLogin = (Button) this.findViewById(R.id.btnAccess);
        this.txtUser = (EditText) this.findViewById(R.id.txtUsuario);
        this.txtPass = (EditText) this.findViewById(R.id.editText);
        this.btnLogin.setText(R.string.btnLogin);
        this.btnLogin.setOnClickListener(this.mainActivityEvents);


    }
    public MainActivtyEvents getMainActivityEvents() {
        return mainActivityEvents;
    }

    public void setMainActivityEvents(MainActivtyEvents mainActivityEvents) {
        this.mainActivityEvents = mainActivityEvents;
    }

    public Button getBtnLogin() {
        return btnLogin;
    }

    public void setBtnLogin(Button btnLogin) {
        this.btnLogin = btnLogin;
    }

    public TextView getTxtUser() {
        return txtUser;
    }


    public TextView getTxtPass() {
        return txtPass;
    }

    public void setTxtUser(EditText txtUser) {
        this.txtUser = txtUser;
    }

    public void setTxtPass(EditText txtPass) {
        this.txtPass = txtPass;
    }
}
