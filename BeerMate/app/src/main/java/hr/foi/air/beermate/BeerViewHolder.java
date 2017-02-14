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

/**
 * Klasa koja sluzi za prikaz odredenog modela odnosno prikaz cardview u recyclerView-u
 */
public  class BeerViewHolder extends  RecyclerView.ViewHolder {
    View mView;
    ContextBeer contextBeer;

    /**
     * Konstruktor koji prima parametar
     * @param itemView
     */

    public BeerViewHolder(View itemView) {
        super(itemView);

        mView=itemView;
    }

    /**
     * Metoda koja na osnovu parametara
     * @param typeOfRating
     * @param id
     * @param name
     * @param averageRate
     * @param totalVotes
     * @param numberOfVotes
     * vrši odabir strategije koja će se izvršiti za ocjenjivanje piva. Ukoliko parametar typeOfRating
     * je jednak 1 tada se radi o strategiji ocjenjivanja pomocu zvjezdica, ukoliko je parametar typeOfRating
     * jednak 2 tada se radi o strategiji ocjenjivanja pomocu tapkanja
     */

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

    /**
     * Metoda koja prima parametar
     * @param id
     *kako bi identificirala id pive te na osnovu toga odredila dostupne lokacije za odredeni model
     */


    public void setShowButton(final String id){
        final Button showAv =  (Button) mView.findViewById(R.id.buttonShowPubs);
        showAv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BeerAvailability beerAvailability = new BeerAvailability(mView);
                beerAvailability.showAvailability(id);
            }
        });
    }

    /**
     * Metoda za postavljanje naziva modela u cardView-u
     * @param title
     */

    public void setTitle(String title){

        TextView post_title = (TextView)mView.findViewById(R.id.post_title);
        post_title.setText(title);
    }

    /**
     * Metoda za postavljanje prikaza tipa modela u cardView-u
     * @param desc
     */

    public void setDesc(String desc){
        TextView post_desc = (TextView)mView.findViewById(R.id.post_text);
        post_desc.setText(desc);
    }

    /**
     * Metoda za postavljanje pivovare modela u CardView-u
     * @param brewery
     */

    public void setBrewery(String brewery){
        TextView post_brewery= (TextView)mView.findViewById(R.id.post_brewery);
        post_brewery.setText(brewery);
    }

    /**
     * Metoda za postavljanje zemlje porijekla modela u cardView-u
     * @param country
     */
    public void setCountry(String country){
        TextView post_country = (TextView)mView.findViewById(R.id.post_country);
        post_country.setText(country);
    }

    /**
     * Metoda za postavljanje jakosti modela u cardView-u
     * @param percentage
     */
    public void setPercentage(Float percentage){
        TextView post_percentage = (TextView)mView.findViewById(R.id.post_percentage);
        post_percentage.setText(String.valueOf(percentage));
    }

    /**
     * Metoda za postavljanje slike modela u cardViewu
     * korišten picasso kako bi se mogla slika prikazati
     * @param ctx
     * @param image
     */

    public void setImage (Context ctx, String image){
        ImageView post_image = (ImageView)mView.findViewById(R.id.post_image);
        Picasso.with(ctx).load(image).into(post_image);
    }

    /**
     * Metoda za postavljanje prosjeka modela u cardView-u
     * @param averageRate
     */

    public void setAverageRate(String averageRate){
        TextView post_averageRate = (TextView)mView.findViewById(R.id.post_averageRate);
        post_averageRate.setText(String.valueOf(averageRate));
    }

    /**
     * Metoda za postavljanje broj glasova u cardView-u
     * @param votes
     */

    public void setVotes(int votes){
        TextView post_votes = (TextView)mView.findViewById(R.id.post_votes);
        post_votes.setText(String.valueOf(votes));
    }
}
