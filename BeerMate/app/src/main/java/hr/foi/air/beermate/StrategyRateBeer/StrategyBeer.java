package hr.foi.air.beermate.StrategyRateBeer;

/**
 * Created by Tomislav on 2/10/2017.
 */


/**
 * Sucelje koja sadrzi metodu rateBeer
 */

public interface StrategyBeer {

    public void  rateBeer(final String id, String name, float averageRate, final int totalVotes, final int numberOfVotes);
}
