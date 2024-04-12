import javax.swing.*;

public class CardButton extends JButton
{
    private Card associatedCard;
    private int index;
    private boolean revealed;

    public CardButton(String text, Card associatedCard, int index)
    {
        super(text);
        this.associatedCard = associatedCard;
        this.index = index;
        this.revealed = false;
    }

    public Card getAssociatedCard()
    {
        return this.associatedCard;
    }

    public void setAssociatedCard(Card associatedCard)
    {
        this.associatedCard = associatedCard;
    }

    public int getIndex() { return this.index;}

    public void setRevealed(boolean yesNo)
    {
        this.revealed = yesNo;
    }

    public boolean getRevealed()
    {
        return this.revealed;
    }
}
