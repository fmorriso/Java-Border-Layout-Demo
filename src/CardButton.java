import javax.swing.*;

public class CardButton extends JButton
{
    private CardPayload associatedCardPayload;
    private int index;
    private boolean revealed;

    public CardButton(String text, CardPayload associatedCardPayload, int index)
    {
        super(text);
        this.associatedCardPayload = associatedCardPayload;
        this.index = index;
        this.revealed = false;
    }

    public CardPayload getAssociatedCard()
    {
        return this.associatedCardPayload;
    }

    public void setAssociatedCard(CardPayload associatedCardPayload)
    {
        this.associatedCardPayload = associatedCardPayload;
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
