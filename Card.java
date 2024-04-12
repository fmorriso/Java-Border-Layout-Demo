public class Card
{

    private String text;
    public String getText() {return text; }

    private int timesUsed;
    public int getTimesUsed() { return timesUsed; }

    private Card() {/* prevent uninitialized instances */}
    public Card(String text)
    {
        this.text = text;
        this.timesUsed = 0;
    }

    public void incrementUsage()
    {
        this.timesUsed += 1;
    }

    @Override
    public String toString()
    {
        return String.format("Card{text=%s, timesUsed=%d}", this.text, this.timesUsed);
    }

}
