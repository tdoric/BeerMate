package hr.foi.air.beermate;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Tomislav on 2/10/2017.
 */

public class RatingUtil {

    private DatabaseReference mReference;

    public float calculateNewRating(int vote, int totalVotes, int numberOfVotes) {
        return (float) (vote + totalVotes) / (float) (numberOfVotes + 1);
    }

    public void updateVotes(String id, int totalVotes, int numberOfVotes) {

        mReference = FirebaseDatabase.getInstance().getReference().child("beers").child(id);
        mReference.child("totalVotes").setValue(totalVotes);
        mReference.child("numberOfVotes").setValue(numberOfVotes);
    }


}
