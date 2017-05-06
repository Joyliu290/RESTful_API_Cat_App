package com.example.joyli.picassolibrarysample;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;
import stanford.androidlib.xml.*;
import android.view.*;
import android.widget.*;
import org.json.*;
import stanford.androidlib.*;

public class MainActivity extends SimpleActivity {

    //private static final String = "https://joyyliu.files.wordpress.com/";

    public static Button pressMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickMeClick (View view){
GridLayout gridLayout = $(R.id.grid);
        gridLayout.removeAllViews();

       ImageView img = (ImageView) findViewById(R.id.catPhoto);
        img.getLayoutParams().width=550;
        Picasso.with(this)
                .load("https://joyyliu.files.wordpress.com/2017/05/img_1620.jpg?w=640")
                .resize(300,300)
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
                        loadCatPics(result);
                    }
                });
                    }

public void loadCatPics(String result){
                        try{
                            JSONObject json = XML.toJSONObject(result);
                            log(json);
                       JSONArray a = json.getJSONObject("response")
                               .getJSONObject("data")
                               .getJSONObject("images")
                               .getJSONArray("image");

                            for (int i =0; i<a.length(); i++)
                            {
                                JSONObject img4 = a.getJSONObject(i);
                                String url = img4.getString("url");
                                log ("loading image from " + url);
                                loadImage(url);
                            }

                        } catch (JSONException jsone) {
                            Log.wtf("help", jsone);
                        }
                    }

    public void loadImage(String url) {
        ImageView imgView = new ImageView(this);
        ViewGroup.LayoutParams params= new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        imgView.setLayoutParams(params);
        GridLayout gridLayout = $(R.id.grid);
        gridLayout.addView(imgView);

        Picasso.with(this)
                .load(url)
                .resize(300,300)
                .into(imgView);
    }

}
