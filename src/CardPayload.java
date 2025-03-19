public class CardPayload
{

    private String text;
    public String getText() {return text; }

    private int timesUsed;
    public int getTimesUsed() { return timesUsed; }

    private CardPayload() {/* prevent uninitialized instances */}
    public CardPayload(String text)
    {
        this.text = text;
        this.timesUsed = 0;
    }

    public void incrementUsage()
    {
        this.timesUsed += 1;
    }

    public void resetUsage()
    {
        this.timesUsed = 0;
    }

    @Override
    public String toString()
    {
        return String.format("Card{text=%s, timesUsed=%d}", this.text, this.timesUsed);
    }

}
