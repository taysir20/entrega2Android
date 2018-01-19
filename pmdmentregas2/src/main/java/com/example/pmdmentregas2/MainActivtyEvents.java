package com.example.pmdmentregas2;

import android.content.Intent;
import android.view.View;

import com.example.mylib.AsyncTask.HttpJsonAsyncTask;
import com.example.mylib.AsyncTask.HttpJsonAsyncTaskListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by tay on 16/1/18.
 */

public class MainActivtyEvents implements HttpJsonAsyncTaskListener, View.OnClickListener{
    private MainActivity mainActivity;

    public MainActivtyEvents (MainActivity mainActivity) {
        this.mainActivity = mainActivity;

    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }



    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnAccess){
            String url ="http://10.0.2.2/datos/datosAlumno.php";
            HttpJsonAsyncTask httpJsonAsyncTask = new HttpJsonAsyncTask();
           System.out.println("----->>>>>" + this.getMainActivity().getTxtUser().getText().toString() + " " +
                   this.getMainActivity().getTxtPass().getText().toString());

           httpJsonAsyncTask.devolverCuenta(this.getMainActivity().getTxtUser().getText().toString(),
                   this.convertPassMd5(this.getMainActivity().getTxtPass().getText().toString()));
            httpJsonAsyncTask.setHttpJsonAsyncTaskListener(this);
            httpJsonAsyncTask.execute(url);
        }
    }

    public static String convertPassMd5(String pass) {
        String password = null;
        MessageDigest mdEnc;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(pass.getBytes(), 0, pass.length());
            pass = new BigInteger(1, mdEnc.digest()).toString(16);
            while (pass.length() < 32) {
                pass = "0" + pass;
            }
            password = pass;
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        return password;
    }



    @Override
    public void FinalTasknotification(Object object) {
        System.out.println("-------------->>>>>>>>>>JSON---------------->>>>>>>>>>>>>>" + String.valueOf(object));

        try{
            JSONObject jsonObj = new JSONObject(String.valueOf(object));
            if(jsonObj.get("estado").equals("ok")){
                DataHolder.MyDataHolder.jsonObject = jsonObj;
                Intent intent = new Intent(mainActivity, SecondActivity.class);
                mainActivity.startActivity(intent);
                mainActivity.finish();
            }else{
                System.out.println("<_____-------------------ERROR EN JSON-----------------_____>");
            }
        }catch (JSONException e){
            e.getStackTrace();
        }

    }
}
