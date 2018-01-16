package com.example.dintentregas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnShow_Hide;
    private Button btnShowHide2;
    private TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnShow_Hide = (Button) this.findViewById(R.id.btnShowHide);
        this.btnShowHide2 = (Button) this.findViewById(R.id.btnShowHide2);
        this.txtMessage = (TextView) this.findViewById(R.id.txtMessage);
        this.btnShow_Hide.setText(R.string.btnShowHide);
        this.btnShowHide2.setText(R.string.btnShowHide2);


    }

    public Button getBtnShow_Hide() {
        return btnShow_Hide;
    }

    public void setBtnShow_Hide(Button btnShow_Hide) {
        this.btnShow_Hide = btnShow_Hide;
    }

    public Button getBtnShowHide2() {
        return btnShowHide2;
    }

    public void setBtnShowHide2(Button btnShowHide2) {
        this.btnShowHide2 = btnShowHide2;
    }

    public TextView getTxtMessage() {
        return txtMessage;
    }

    public void setTxtMessage(TextView txtMessage) {
        this.txtMessage = txtMessage;
    }


    //Este método será llamado desde el propio XML evitando tener que implementar el onClick listener.
    //Por así decirlo, desde el xmlya se adjudica el onclick listener
    public void onClick(View v) {
        if (v.getId() == R.id.btnShowHide) {
            if (this.txtMessage.getText().equals("¡¡¡Hola!!!")) {
                this.txtMessage.setText("");
            }else{
                    this.txtMessage.setText("¡¡¡Hola!!!");
                }

        }else if(v.getId() == R.id.btnShowHide2){
            if (this.txtMessage.getText().equals("¡¡¡Adiós!!!")) {
                this.txtMessage.setText("");
            }else{
                this.txtMessage.setText("¡¡¡Adiós!!!");
            }
        }
    }
}
