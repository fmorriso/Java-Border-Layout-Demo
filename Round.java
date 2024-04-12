import java.util.ArrayList;

public class Round
{
    private int totalCards;
    private int maxTimesUsed;

    private ArrayList<Card> selections;
    private ArrayList<Card> uniqueCards;

    private Round()
    {/* prevent uninitialized instances*/ }

    public Round(int totalCards, int maxTimesUsed)
    {
        this.totalCards = totalCards;
        this.maxTimesUsed = maxTimesUsed;

        selections = new ArrayList<Card>(this.totalCards);

        uniqueCards = new ArrayList<Card>();
        ArrayList<String> uniqueCardNames = CardChooser.getUniqueCardNames();
        for(String text: uniqueCardNames)
            uniqueCards.add(new Card(text));

    }

    public ArrayList<Card> getSelections() { return this.selections; }

    public void startNewRound()
    {
        selections.clear();
        Card card = null;
        for (int i = 0; i < totalCards; ) {
            // while waiting to populate ...
            boolean foundExisting = false;
            boolean searchingList = true;
            String text = "";

            boolean continueSearching = true;
            while (continueSearching)
            {
                // grab a random card
                text = CardChooser.getRandomCard();
                int idx = lookForExistingSelection(text);
                int idxUnique = findUniqueCard(text);
                if (idx == -1) {
                    card = new Card(text);
                    selections.add(card);
                    uniqueCards.get(idxUnique).incrementUsage();
                    continueSearching = false;
                } else {
                    // already know about this card, so just make sure we haven't used it too many times
                    if (uniqueCards.get(idxUnique).getTimesUsed() < maxTimesUsed)
                    {
                        uniqueCards.get(idxUnique).incrementUsage();
                        card = new Card(text);
                        selections.add(card);
                        continueSearching = false;
                    }
                }

            }
            System.out.format("DEBUG: Finished creating selection %d%n", i);
            //displaySelections();
            i++;
        }
        //System.out.println(selections);
        //displaySelections();
        //displayUniqueCards();

    }


    public void displaySelections()
    {
        int i = 0;
        for(Card card: selections) {
            System.out.format("index=%d, %s%n", i++, card);
        }
    }

    private void displayUniqueCards()
    {
        int i = 0;
        for(Card card: uniqueCards) {
            System.out.format("index=%d, %s%n", i++, card);
        }
    }

    private int findUniqueCard(String text)
    {
        int idx = -1;
        for (int i = 0; i < uniqueCards.size(); i++)
        {
            if (uniqueCards.get(i).getText().equals(text))
            {
                idx = i;
                break;
            }
        }
        return idx;
    }

    private int lookForExistingSelection(String text)
    {
        for (int i = 0; i < selections.size(); i++) {
            Card card = selections.get(i);
            if (card.getText().equals(text))
                return i;
        }
        return -1;
    }


}
