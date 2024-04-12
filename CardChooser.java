import java.util.ArrayList;
import java.util.Arrays;

public class CardChooser
{
    private final static String[] images = {"wolf", "turtle", "rabbit", "dolphin", "parrot", "penguin", "cheetah", "frog"};

    private static ArrayList<String> cards = new ArrayList<String>(Arrays.asList(images));

    public static String getRandomCard()
    {
        final int MIN = 0;
        final int MAX = cards.size() - 1;
        final int RANGE = MAX - MIN + 1;
        int n = (int)(Math.random() * RANGE) + MIN;
        return cards.get(n);
    }

    public static ArrayList<String> getUniqueCardNames()
    {
        return cards;
    }
}
