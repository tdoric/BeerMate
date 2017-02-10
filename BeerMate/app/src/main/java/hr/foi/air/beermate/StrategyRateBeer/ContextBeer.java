package hr.foi.air.beermate.StrategyRateBeer;

/**
 * Created by Tomislav on 2/10/2017.
 */

public class ContextBeer {

    private StrategyBeer strategyBeer;

    public ContextBeer (StrategyBeer strategyBeer){
        this.strategyBeer = strategyBeer;
    }

    public void  executeStrategyBeer (final String id, String name, float averageRate,final int totalVotes, final int numberOfVotes){
        strategyBeer.rateBeer(id,name,averageRate,totalVotes,numberOfVotes);
    }

}
