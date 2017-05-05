package com.example.joyli.picassolibrarysample;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import org.json.JSONException;
import org.json.JSONObject;


import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import static java.lang.System.load;

public class MainActivity extends AppCompatActivity {

    //private static final String = "https://joyyliu.files.wordpress.com/";

    public static Button pressMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickMeClick (View view){

        ImageView img = (ImageView) findViewById(R.id.catPhoto);
        img.getLayoutParams().width=550;
        Picasso.with(this)
                .load("https://joyyliu.files.wordpress.com/2017/05/img_1620.jpg?w=640")
                .into(img);

        ImageView img2 = (ImageView) findViewById(R.id.catPhoto2);
        Picasso.with(this)
                .load("https://joyyliu.files.wordpress.com/2016/07/img_1609.jpg")
                .into(img2);

        Ion.with(this)
                .load("http://thecatapi.com/api/images/get?format=xml&size=med&results_per_page=6")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        //data has arrived

                        try{
                            JSONObject json = new JSONObject(result);
                            JSONObject value = json.getJSONObject("value");
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                });


    }

}
