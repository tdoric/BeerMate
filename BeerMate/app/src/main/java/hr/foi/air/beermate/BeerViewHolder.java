package hr.foi.air.beermate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Tomislav on 11/9/2016.
 */

public  class BeerViewHolder extends  RecyclerView.ViewHolder {
    View mView;
    public BeerViewHolder(View itemView) {
        super(itemView);

        mView=itemView;
    }

    public void setTitle(String title){

        TextView post_title = (TextView)mView.findViewById(R.id.post_title);
        post_title.setText(title);
    }

    public void setDesc(String desc){
        TextView post_desc = (TextView)mView.findViewById(R.id.post_text);
        post_desc.setText(desc);
    }

    public void setBrewery(String brewery){
        TextView post_brewery= (TextView)mView.findViewById(R.id.post_brewery);
        post_brewery.setText(brewery);
    }
    public void setCountry(String country){
        TextView post_country = (TextView)mView.findViewById(R.id.post_country);
        post_country.setText(country);
    }
    public void setPercentage(Float percentage){
        TextView post_percentage = (TextView)mView.findViewById(R.id.post_percentage);
        post_percentage.setText(String.valueOf(percentage));
    }

    public void setImage (Context ctx, String image){
        ImageView post_image = (ImageView)mView.findViewById(R.id.post_image);
        Picasso.with(ctx).load(image).into(post_image);
    }
}
