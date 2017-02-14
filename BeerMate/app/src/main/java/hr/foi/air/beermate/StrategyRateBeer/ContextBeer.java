package hr.foi.air.beermate.StrategyRateBeer;

/**
 * Created by Tomislav on 2/10/2017.
 */

/**
 * Klasa u kojoj se definira izvodenje strategije
 */

public class ContextBeer {

    private StrategyBeer strategyBeer;

    public ContextBeer (StrategyBeer strategyBeer){
        this.strategyBeer = strategyBeer;
    }

    /**
     * Metoda koja parametre
     * @param id
     * @param name
     * @param averageRate
     * @param totalVotes
     * @param numberOfVotes
     *
     * te na osnovu tih parametara izvrsava metodu sucelja
     */
    public void  executeStrategyBeer (final String id, String name, float averageRate,final int totalVotes, final int numberOfVotes){
        strategyBeer.rateBeer(id,name,averageRate,totalVotes,numberOfVotes);
    }

}
