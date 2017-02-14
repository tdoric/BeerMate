package hr.foi.air.beermate.StrategyRateBeer;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import hr.foi.air.beermate.R;
import hr.foi.air.beermate.RatingUtil;

/**
 * Created by Tomislav on 2/8/2017.
 */

/**
 * Klasa za ocjenjivanje modela zvjezdicama
 * Implementira sucelje StrategyBeer
 */

public class StarRating extends RecyclerView.ViewHolder implements StrategyBeer {
    View mView;
    Dialog rankDialog;
    RatingBar ratingBar;
    RatingUtil ratingUtil = new RatingUtil();

    public StarRating(View itemView) {
        super(itemView);
        mView=itemView;
    }

    /**
     * Metoda koja na osnovu sljedecih parametara
     * @param id
     * @param name
     * @param averageRate
     * @param totalVotes
     * @param numberOfVotes
     *
     * izvrsava ocjenjivanje zvjezdicama te prije toga poziva dijalog na kojem je prikazana funkcionalnost ocjenjivanja
     */
    @Override
    public void rateBeer(final String id, String name, float averageRate,final int totalVotes, final int numberOfVotes) {
        rankDialog = new Dialog(mView.getContext(), R.style.FullHeightDialog);
        rankDialog.setContentView(R.layout.star_rank_dialog);
        rankDialog.setCancelable(true);
        ratingBar = (RatingBar)rankDialog.findViewById(R.id.star_rank_dialog_ratingbar);
        ratingBar.setRating(averageRate);

        TextView text = (TextView) rankDialog.findViewById(R.id.star_rank_dialog_text);
        text.setText(name);

        Button rateBtn = (Button) rankDialog.findViewById(R.id.star_rank_dialog_rateBtn);
        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float newRating = ratingUtil.calculateNewRating(Math.round(ratingBar.getRating()), totalVotes, numberOfVotes);
                ratingUtil.updateVotes(id, totalVotes+Math.round(ratingBar.getRating()), numberOfVotes+1);
                rankDialog.dismiss();
                Toast.makeText(mView.getContext(), R.string.msgRating, Toast.LENGTH_SHORT).show();
            }
        });
        Button cancelBtn = (Button) rankDialog.findViewById(R.id.star_rank_dialog_cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rankDialog.dismiss();
            }
        });
        rankDialog.show();

    }
}
