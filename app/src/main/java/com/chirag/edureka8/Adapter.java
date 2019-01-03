package com.chirag.edureka8;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    Context context;
    int resource;
    static List<SampleData.MovieInfoBean> objects;

    Adapter(Context context, int resource, List<SampleData.MovieInfoBean> objects){
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( context ).inflate( resource,viewGroup,false );
        ViewHolder viewHolder = new ViewHolder( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        final SampleData.MovieInfoBean obj = objects.get( position );

        Picasso.get()
                .load(obj.getImgURL())
                .into(viewHolder.imageView);

        viewHolder.movieName.setText( objects.get( position ).getMovie_name() );
        viewHolder.parentCard.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context,ImageViewer.class );
                intent.putExtra( "imgURL", objects.get( position ).getImgURL());
                intent.putExtra( "movieName", objects.get( position ).getMovie_name());
                intent.putExtra( "movieYear",objects.get( position ).getReleaseYear() );
                intent.putExtra( "movieLead",objects.get( position ).getMainLead() );
                context.startActivity(intent);
            }
        } );

        viewHolder.progressBar.setVisibility( View.INVISIBLE );
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView movieName;
        ProgressBar progressBar;
        CardView parentCard;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            imageView = itemView.findViewById( R.id.imageView );
            movieName = itemView.findViewById( R.id.movieName );
            progressBar = itemView.findViewById( R.id.progress_bar );
            parentCard = itemView.findViewById( R.id.parent );
        }
    }
}
