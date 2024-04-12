import java.util.ArrayList;

public class Controller
{
    private static final int MAX_CARDS = 16;
    private static final int MAX_TIMES_REUSE = 2;

    private Round round;

    public Controller()
    {
        this.round = new Round(MAX_CARDS , MAX_TIMES_REUSE);
    }


    public void startNewRound()
    {
        this.round.startNewRound();
    }

    public int getMaxCards()
    {
        return MAX_CARDS;
    }

    public ArrayList<Card> getCurrentCards()
    {
        return this.round.getSelections();
    }
}
