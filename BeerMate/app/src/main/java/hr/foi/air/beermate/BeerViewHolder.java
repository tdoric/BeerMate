package hr.foi.air.beermate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import hr.foi.air.beermate.StrategyRateBeer.ContextBeer;
import hr.foi.air.beermate.StrategyRateBeer.StarRating;
import hr.foi.air.beermate.StrategyRateBeer.TapRating;

/**
 * Created by Tomislav on 11/9/2016.
 */

public  class BeerViewHolder extends  RecyclerView.ViewHolder {
    View mView;
    ContextBeer contextBeer;


    public BeerViewHolder(View itemView) {
        super(itemView);

        mView=itemView;
    }

    public void setRateButton(final String typeOfRating, final String id, final String name, final float averageRate, final int totalVotes, final int numberOfVotes) {
        final Button rankBtn = (Button) mView.findViewById(R.id.buttonRate);
        rankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (typeOfRating.equals("1")) { // ovaj tip je za star rating
                    contextBeer  = new ContextBeer(new StarRating(mView));
                    contextBeer.executeStrategyBeer(id, name, averageRate, totalVotes, numberOfVotes);
                } else if (typeOfRating.equals("2")) { // ovaj tip je za tap rating
                    contextBeer = new ContextBeer(new TapRating(mView));
                    contextBeer.executeStrategyBeer(id, name, averageRate, totalVotes, numberOfVotes);
                }
            }
        });
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

    public void setAverageRate(String averageRate){
        TextView post_averageRate = (TextView)mView.findViewById(R.id.post_averageRate);
        post_averageRate.setText(String.valueOf(averageRate));
    }

    public void setVotes(int votes){
        TextView post_votes = (TextView)mView.findViewById(R.id.post_votes);
        post_votes.setText(String.valueOf(votes));
    }
}
