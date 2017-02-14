package hr.foi.air.beermate.StrategyRateBeer;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import hr.foi.air.beermate.R;
import hr.foi.air.beermate.RatingUtil;

/**
 * Created by Tomislav on 2/8/2017.
 */

/**
 * Klasa za ocjenjivanje modela tapkanjem
 * Implementira sucelje StrategyBeer
 */
public class TapRating extends RecyclerView.ViewHolder implements StrategyBeer {
    View mView;
    Dialog rankDialog;
    int vote;
    RatingUtil ratingUtil = new RatingUtil();

    public TapRating(View itemView) {
        super(itemView);
        mView=itemView;
    }

    /**Metoda koja na osnovu parametara
     *
     * @param id
     * @param name
     * @param averageRate
     * @param totalVotes
     * @param numberOfVotes
     *
     * izvrsava ocjenjivanje modela. Prije ocjenjivanja poziva dijalog na kojem se prikazuje funkcionalnost ocjenjivanja
     */
    @Override
    public void rateBeer( final String id, String name, float averageRate, final int totalVotes, final int numberOfVotes) {

        vote = Math.round(averageRate);

        rankDialog = new Dialog(mView.getContext(), R.style.FullHeightDialog);
        rankDialog.setContentView(R.layout.tap_rank_dialog);
        rankDialog.setCancelable(true);

        TextView text = (TextView) rankDialog.findViewById(R.id.tap_rank_dialog_text);
        text.setText(name);

        final TextView textRate = (TextView) rankDialog.findViewById(R.id.tap_rank_dialog_textRate);
        textRate.setText(Integer.toString(vote));
        textRate.setTextColor(Color.GREEN);

        textRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vote < 5) {
                    vote++;
                    textRate.setTextColor(Color.GREEN);
                } else {
                    vote = 0;
                    textRate.setTextColor(Color.RED);
                }
                textRate.setText(Integer.toString(vote));
            }
        });

        Button rateBtn = (Button) rankDialog.findViewById(R.id.tap_rank_dialog_rateBtn);
        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float newRating = ratingUtil.calculateNewRating(vote, totalVotes, numberOfVotes);
                ratingUtil.updateVotes(id, totalVotes+vote, numberOfVotes+1);
                rankDialog.dismiss();
                Toast.makeText(mView.getContext(),R.string.msgRating, Toast.LENGTH_SHORT).show();
                rankDialog.dismiss();
            }
        });
        Button cancelBtn = (Button) rankDialog.findViewById(R.id.tap_rank_dialog_cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rankDialog.dismiss();
            }
        });
        rankDialog.show();

    }
}
