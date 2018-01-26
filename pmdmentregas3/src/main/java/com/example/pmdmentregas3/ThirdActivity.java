package com.example.pmdmentregas3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class ThirdActivity extends AppCompatActivity {
    private TextView txtEmail;
    private TextView txtNombrePerfil;
    private TextView txtNombreUsuario;
    private TextView descripcion;
    private ImageView imgPerfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        this.txtEmail = (TextView) this.findViewById(R.id.txtEmailTwitter);
        this.txtNombrePerfil = (TextView) this.findViewById(R.id.txtUserTwitter);
        this.txtNombreUsuario = (TextView) this.findViewById(R.id.txtNombreTwitter);
        this.descripcion = (TextView) this.findViewById(R.id.txtDescripcionTwitter);
        this.imgPerfil = (ImageView) this.findViewById(R.id.imgPerfilTwitter);

        this.setData();
    }

    private void setData(){

        try {
            JSONObject data = DataHolder.MyDataHolder.jsonObjectTwitter;
            this.txtNombrePerfil.setText(data.get("UserName").toString());
            this.txtNombreUsuario.setText(data.get("name").toString());
            this.descripcion.setText(data.get("description").toString());
            this.txtEmail.setText(data.get("email").toString());
            if (data.has("picture")) {
               // System.out.println("La url de la img de facebook es: " + data.getJSONObject("picture").getJSONObject("data").get("url").toString());
                Picasso.with(this)
                        .load("https://twitter.com/"+data.get("UserName").toString()+"/profile_image?size=original")
                        .into(this.imgPerfil);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
