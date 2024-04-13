import java.util.ArrayList;

public class Round
{
    private int totalCards;
    private int maxTimesUsed;
    private int numRevealed;

    private ArrayList<CardPayload> selections;
    private ArrayList<CardPayload> uniqueCardPayloads;

    private Round()
    {/* prevent uninitialized instances*/ }

    public Round(int totalCards, int maxTimesUsed)
    {
        this.totalCards = totalCards;
        this.maxTimesUsed = maxTimesUsed;
        this.numRevealed = 0;

        selections = new ArrayList<CardPayload>(this.totalCards);
        uniqueCardPayloads = new ArrayList<CardPayload>();

        ArrayList<String> uniqueCardNames = CardChooser.getUniqueCardNames();
        for(String text: uniqueCardNames)
            uniqueCardPayloads.add(new CardPayload(text));

    }

    public ArrayList<CardPayload> getSelections() { return this.selections; }

    public void startNewRound()
    {
        this.numRevealed = 0;
        selections.clear();
        CardPayload cardPayload = null;
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
                    cardPayload = new CardPayload(text);
                    selections.add(cardPayload);
                    uniqueCardPayloads.get(idxUnique).incrementUsage();
                    continueSearching = false;
                } else {
                    // already know about this card, so just make sure we haven't used it too many times
                    if (uniqueCardPayloads.get(idxUnique).getTimesUsed() < maxTimesUsed)
                    {
                        uniqueCardPayloads.get(idxUnique).incrementUsage();
                        cardPayload = new CardPayload(text);
                        selections.add(cardPayload);
                        continueSearching = false;
                    }
                }

            }
            //System.out.format("DEBUG: Finished creating selection %d%n", i);
            //displaySelections();
            i++;
        }
        //System.out.println(selections);
        displaySelections();
        //displayUniqueCards();

    }


    public void displaySelections()
    {
        int i = 0;
        for(CardPayload cardPayload : selections) {
            System.out.format("index=%d, %s%n", i++, cardPayload);
        }
    }

    private void displayUniqueCards()
    {
        int i = 0;
        for(CardPayload cardPayload : uniqueCardPayloads) {
            System.out.format("index=%d, %s%n", i++, cardPayload);
        }
    }

    private int findUniqueCard(String text)
    {
        int idx = -1;
        for (int i = 0; i < uniqueCardPayloads.size(); i++)
        {
            if (uniqueCardPayloads.get(i).getText().equals(text))
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
            CardPayload cardPayload = selections.get(i);
            if (cardPayload.getText().equals(text))
                return i;
        }
        return -1;
    }


    public void incrementRevealedCount()
    {
        this.numRevealed++;
    }

    public int getNumberOfCardsRevealed()
    {
        return this.numRevealed;
    }

    public void disableUnrevealedButtons(ArrayList<CardButton> buttons)
    {
        for (int i = 0; i < buttons.size(); i++)
        {
            CardButton button = buttons.get(i);
            if(!button.getRevealed())
            {
                button.setEnabled(false);
            }
        }
    }

    public void clearSelections(ArrayList<CardButton> buttonList)
    {
        // clear the selections so that the buttons are also cleared
        for (int i = 0; i < buttonList.size(); i++)
        {
            CardButton button = buttonList.get(i);
            button.setRevealed(false);
            button.setEnabled(false);
            button.setText("");
        }
    }
}
