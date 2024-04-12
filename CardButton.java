import javax.swing.*;

public class CardButton extends JButton
{
    private Card associatedCard;

    public CardButton(String text, Card associatedCard)
    {
        super(text);
        this.associatedCard = associatedCard;
    }

    public Card getAssociatedCard()
    {
        return this.associatedCard;
    }
}
