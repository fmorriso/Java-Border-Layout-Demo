import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Controller
{
    private static final int MAX_CARDS = 16;
    private static final int MAX_TIMES_REUSE = 2;
    private static final int MAX_CARDS_TO_REVEAL = 2;

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

    public String getAssociatedText(CardButton b)
    {
        int idx = b.getIndex();
        Card c = this.getCurrentCards().get(idx);
        return c.getText();
    }

    public void enableButton(CardButton b)
    {
        b.setEnabled(true);
        Card c = this.getCurrentCards().get(b.getIndex());
    }

    public void enableMatchButtons(ArrayList<CardButton> buttons)
    {
        for(CardButton button : buttons)
        {
            button.setEnabled(true);
        }
    }

    public static int getMaxCardsToReveal()
    {
        return MAX_CARDS_TO_REVEAL;
    }

    public void incrementNumberOfCardsRevealed()
    {
        this.round.incrementRevealedCount();
    }

    public int getNumberOfCardsRevealed()
    {
        return this.round.getNumberOfCardsRevealed();
    }

    public void disableUnrevealedButtons(ArrayList<CardButton> buttons)
    {
        this.round.disableUnrevealedButtons(buttons);
    }

    public void clearSelections(JPanel buttonsPanel)
    {
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
}
