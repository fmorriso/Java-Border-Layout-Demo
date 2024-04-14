import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameController
{
    private static final int MAX_CARDS = 16;
    private static final int MAX_TIMES_REUSE = 2;
    private static final int MAX_CARDS_TO_REVEAL = 2;

    private Round round;

    public GameController()
    {
        this.round = new Round(MAX_CARDS , MAX_TIMES_REUSE);
    }

    public void startNewRound()
    {
        System.out.println("DEBUG: GameController.startNewRound");
        this.round.startNewRound();
    }

    public int getMaxCards()
    {
        return MAX_CARDS;
    }

    public ArrayList<CardPayload> getCurrentCards()
    {
        return this.round.getSelections();
    }

    public String getAssociatedText(CardButton b)
    {
        System.out.format("%n%s%n",getMethodName(1));
        int idx = b.getIndex();
        CardPayload c = this.getCurrentCards().get(idx);
        return c.getText();
    }

    public void enableButton(CardButton b)
    {
        System.out.format("%n%s%n",getMethodName(1));
        b.setEnabled(true);
        CardPayload c = this.getCurrentCards().get(b.getIndex());
    }

    public void enableMatchButtons(ArrayList<CardButton> buttons)
    {
        System.out.format("%n%s%n",getMethodName(1));
        for(CardButton button : buttons)
        {
            button.setEnabled(true);
        }
    }

    public static int getMaxCardsToReveal()
    {
        System.out.format("%n%s%n",getMethodName(1));
        return MAX_CARDS_TO_REVEAL;
    }

    public void incrementNumberOfCardsRevealed()
    {
        this.round.incrementRevealedCount();
    }

    public int getNumberOfCardsRevealed()
    {
        System.out.format("%n%s%n",getMethodName(1));
        return this.round.getNumberOfCardsRevealed();
    }

    public void disableUnrevealedButtons(ArrayList<CardButton> buttons)
    {
        System.out.format("%n%s%n",getMethodName(1));
        this.round.disableUnrevealedButtons(buttons);
    }

    public void clearSelections(JPanel buttonsPanel)
    {
        System.out.format("%n%s%n",getMethodName(1));
        // clear the selections and set the button text to blank
        for(Component c : buttonsPanel.getComponents())
        {
            if(c instanceof CardButton)
            {
                CardButton b = (CardButton) c;
                b.setText("");
            }
        }
    }

    public void createAndShowGUI(Dimension scaledSize, String title)
    {
        GUI gui = new GUI(scaledSize, title, this);
        javax.swing.SwingUtilities.invokeLater(gui);
    }

    public static String getMethodName(final int depth)
    {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

        //System. out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
        // return ste[ste.length - depth].getMethodName();  //Wrong, fails for depth = 0
        return ste[ste.length - 1 - depth].getMethodName(); //Thank you Tom Tresansky
    }


}
