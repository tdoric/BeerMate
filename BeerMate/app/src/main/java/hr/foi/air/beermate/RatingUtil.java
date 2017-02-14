package hr.foi.air.beermate;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Tomislav on 2/10/2017.
 */

/**
 * Pomoćna klasa koja se koristi za pomoć u ocjenjivanju piva
 */

public class RatingUtil {

    private DatabaseReference mReference;

    /**
     * Metoda koja izracunava novi rejting za prikaz u activityu
     * @param vote
     * @param totalVotes
     * @param numberOfVotes
     * @return
     */
    public float calculateNewRating(int vote, int totalVotes, int numberOfVotes) {
        return (float) (vote + totalVotes) / (float) (numberOfVotes + 1);
    }

    /**
     * Metoda koja  prima id, te na osnovu id vrsi azuriranje za broj glasova i ukupan zbroj u bazi podataka
     * @param id
     * @param totalVotes
     * @param numberOfVotes
     */

    public void updateVotes(String id, int totalVotes, int numberOfVotes) {

        mReference = FirebaseDatabase.getInstance().getReference().child("beers").child(id);
        mReference.child("totalVotes").setValue(totalVotes);
        mReference.child("numberOfVotes").setValue(numberOfVotes);
    }


}
