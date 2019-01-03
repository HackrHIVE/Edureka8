package com.chirag.edureka8;

import android.os.Handler;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    String response;
    RecyclerView recyclerView;
    List<SampleData.MovieInfoBean> movieInfo;
    Adapter recyclerAdapter;
    private ProgressBar progressOne,progressTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById( R.id.recyclerView );
        recyclerView.setLayoutManager( new GridLayoutManager(this ,2 ) );
        progressOne = findViewById( R.id.progressBarOne );
        progressTwo = findViewById( R.id.progressBarTwo );

        progressOne.setVisibility( View.VISIBLE );
        progressTwo.setVisibility( View.VISIBLE );

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    response = fetchData();
                    runOnUiThread( new Runnable() {
                        @Override
                        public void run() {
                            Log.i( "Response from Server : ",response );
                            final SampleData data = new Gson().fromJson( response,SampleData.class );

                            movieInfo = data.getMovieInfo();

                            progressOne.setVisibility( View.INVISIBLE );
                            progressTwo.setVisibility( View.INVISIBLE );
                            Toast.makeText( MainActivity.this, "Made by Chirag Rawal using GSON library, Palette API & RecyclerView", Toast.LENGTH_SHORT ).show();
                            setAdapter();
                        }
                    } );
                } catch (IOException e) {
                    Log.i( "Response from Server : ","Exception Occured" );
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private void setAdapter(){
        recyclerAdapter = new Adapter( MainActivity.this,R.layout.sample_item,movieInfo );
        recyclerView.setAdapter( recyclerAdapter );
    }

    private String fetchData() throws IOException {
        Log.i( "Edureka8Result","In the FetchData() method!" );
        //https://api.myjson.com/bins/yjylo
        //http://www.json-generator.com/api/json/get/cqgPcEhKSq?indent=2
        URL url = new URL("http://www.json-generator.com/api/json/get/cqgPcEhKSq?indent=2");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        try{
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode==httpURLConnection.HTTP_OK){
                Log.i("Edureka8Result","Success!");
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader( inputStream );
                BufferedReader bufferedReader = new BufferedReader( inputStreamReader );

                String temp;
                StringBuffer buffer = new StringBuffer(  );
                while((temp=bufferedReader.readLine())!=null){
                    buffer.append( temp+"\n" );
                }
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                return buffer.toString();
            }
            else{
                Log.i("Edureka8Result","Failure!");
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            Log.i( "Edureka8Result","Exception Occured" );
            return null;
        }

    }
}
