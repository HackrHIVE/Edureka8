package com.chirag.edureka8;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ImageViewer extends AppCompatActivity {

    RelativeLayout parentLayout;
    String mainLead;
    String releaseYear;
    String imgURL;
    String movie_name;
    TextView ReleaseYear,MovieName,LeadActors;
    ImageView imageToShow;
    ProgressBar progressBarViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_image_viewer );

        ReleaseYear = findViewById( R.id.release );
        MovieName = findViewById( R.id.movie );
        LeadActors = findViewById( R.id.leadActors );
        imageToShow = findViewById( R.id.imageViewer );
        parentLayout = findViewById( R.id.ParentLayout );
        progressBarViewer = findViewById( R.id.progress_bar_imageViewer );



        imgURL = getIntent().getStringExtra( "imgURL" );
        movie_name = getIntent().getStringExtra( "movieName" );
        releaseYear = getIntent().getStringExtra( "movieYear" );
        mainLead = getIntent().getStringExtra( "movieLead" );

        Picasso.get()
                .load(imgURL)
                .into(imageToShow);

        ReleaseYear.setText( releaseYear);
        MovieName.setText( movie_name );
        LeadActors.setText( mainLead );

        Bitmap bitmap = ((BitmapDrawable)imageToShow.getDrawable()).getBitmap();

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette p) {
                displayPalette(p);
            }
        });

    }

    private void displayPalette(Palette palette) {

        if(palette.getVibrantSwatch()!=null){
            parentLayout.setBackgroundColor( palette.getVibrantSwatch().getRgb() );
        }
        else
            if(palette.getDarkMutedSwatch()!=null){
                MovieName.setTextColor( Color.WHITE );
                LeadActors.setTextColor( Color.WHITE );
                ReleaseYear.setTextColor( Color.WHITE );
                parentLayout.setBackgroundColor( palette.getDarkMutedSwatch().getRgb() );
            }

    }
}
