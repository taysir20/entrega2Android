package com.example.pmdmentregas3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.InputStream;

public class SecondActivity extends AppCompatActivity {
    private  SecondActivityEvents secondActivityEvents;
    private TextView txtNombrePerfil;
    private TextView txtCumple;
    private ImageView imgPerfilFacebook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        this.setSecondActivityEvents(new SecondActivityEvents(this));
        this.txtNombrePerfil = this.findViewById(R.id.txtNombreFacebook);
        this.imgPerfilFacebook = this.findViewById(R.id.imgPerfilFacebook);
        this.txtCumple = this.findViewById(R.id.txtCumple);
        try {
            JSONObject data = DataHolder.MyDataHolder.jsonObject;
            this.txtNombrePerfil.setText(data.get("name").toString());
            this.txtCumple.setText(data.get("birthday").toString());
            if (data.has("picture")) {
                System.out.println("La url de la img de facebook es: " + data.getJSONObject("picture").getJSONObject("data").get("url").toString());
               /* Picasso.with(this)
                        .load(data.getJSONObject("picture").getJSONObject("data").get("url").toString())
                        .into(this.imgPerfilFacebook);
                        */
                Picasso.with(this)
                        .load( "https://graph.facebook.com/" + data.get("id").toString()+ "/picture?type=large")
                        .into(this.imgPerfilFacebook);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SecondActivityEvents getSecondActivityEvents() {
        return secondActivityEvents;
    }

    public void setSecondActivityEvents(SecondActivityEvents secondActivityEvents) {
        this.secondActivityEvents = secondActivityEvents;
    }

    public TextView getTxtNombrePerfil() {
        return txtNombrePerfil;
    }

    public void setTxtNombrePerfil(TextView txtNombrePerfil) {
        this.txtNombrePerfil = txtNombrePerfil;
    }

    public ImageView getImgPerfilFacebook() {
        return imgPerfilFacebook;
    }

    public void setImgPerfilFacebook(ImageView imgPerfilFacebook) {
        this.imgPerfilFacebook = imgPerfilFacebook;
    }

    public TextView getTxtCumple() {
        return txtCumple;
    }

    public void setTxtCumple(TextView txtCumple) {
        this.txtCumple = txtCumple;
    }
}
