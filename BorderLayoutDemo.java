// Java program to illustrate the BorderLayout

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

// class extends JFrame
public class BorderLayoutDemo extends JFrame
{
    private Dimension scaledSize;
    private String title;
    private GameController controller;

    private ArrayList<CardButton> cards;

    private JPanel mainPanel;
    private JPanel centralPanel;
    private JPanel gameControlButtonsPanel;

    private JButton checkForMatchButton;
    private JButton resetButton;
    private JButton startRoundButton;
    private JButton exitButton;

    public BorderLayoutDemo(Dimension scaledSize, String title, GameController controller)
    {
        this.scaledSize = scaledSize;
        this.title = title;
        this.controller = controller;

        cards = new ArrayList<CardButton>();

        this.setTitle(title);
        this.setSize(scaledSize);
        this.setLocationRelativeTo(null);

        // Creating Object of Jpanel class
        mainPanel = new JPanel();

        // set the layout
        mainPanel.setLayout(new BorderLayout(10, 10));

        // put match buttons in the center buttons panel
        this.centralPanel = createMatchButtons(mainPanel);
        mainPanel.add(centralPanel, BorderLayout.CENTER);

        // create a penel to hold all the game control buttons
        this.gameControlButtonsPanel = generateControlButtonsPanel();
        mainPanel.add(gameControlButtonsPanel, BorderLayout.SOUTH);

        // add the pa object which refer to the Jpanel
        add(mainPanel);

        // Function to close the operation of JFrame.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Function to set visible status of JFrame.
        setVisible(true);
    }

    private JPanel generateControlButtonsPanel()
    {
        JPanel pnl = new JPanel();
        GridLayout layout = new GridLayout(1, 0);
        layout.setVgap(10);
        layout.setHgap(10);
        pnl.setLayout(layout);

        // add a new JButton with name "Start Round"
        startRoundButton = new JButton("Start Round");
        startRoundButton.addActionListener(e -> startRoundButtonClicked(e));
        pnl.add(startRoundButton);

        // add a new JButton with name "Check for Match"
        checkForMatchButton = new JButton("Check For Match");
        checkForMatchButton.addActionListener(e -> checkForMatchButtonClicked(e));
        pnl.add(checkForMatchButton);

        // add a new JButton that will reset the game
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetButtonClicked(e));
        pnl.add(resetButton);

        // exit button goes on bottom
        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> exitProgramButtonClicked(e));
        pnl.add(exitButton);

        return pnl;
    }

    private void resetButtonClicked(ActionEvent e)
    {
        System.out.println("DEBUG: resetButtonClicked");
        clearSelections();
    }

    private void startRoundButtonClicked(ActionEvent e)
    {
        System.out.println("DEBUG: startRoundButtonClicked");
        this.controller.startNewRound();

        //this.controller.enableMatchButtons(this.cards);
        for(Component component: this.centralPanel.getComponents())
        {
            if (component instanceof CardButton)
            {
                CardButton button = (CardButton) component;
                button.setEnabled(true);
                button.setRevealed(false);
                button.setText("");
            }
        }
    }

    private JPanel createMatchButtons(JPanel pa)
    {
        JPanel pnl = new JPanel();

        GridLayout layout = new GridLayout(0, 4);
        layout.setVgap(10);
        layout.setHgap(10);

        pnl.setLayout(layout);

        for (int i = 0; i < controller.getMaxCards(); i++)
        {
            CardButton b = new CardButton("", new CardPayload(""), i);
            // disable all match buttons until "start new round" has happened
            b.setEnabled(false);
            cards.add(b);
            b.addActionListener(e -> cardButtonClicked(e));
            pnl.add(b);
        }

        return pnl;
    }

    private void cardButtonClicked(ActionEvent e)
    {
        System.out.println("DEBUG: cardButtonClicked");
        if (e.getSource() instanceof CardButton)
        {
            CardButton b = (CardButton) e.getSource();
            //String msg = String.format("Card button %s was clicked", b.getText());
            //OutputUtils.displayMessage(msg, title);
            String text = controller.getAssociatedText(b);
            b.setText(text);
            b.setRevealed(true);

            controller.incrementNumberOfCardsRevealed();
            if(controller.getNumberOfCardsRevealed() >= controller.getMaxCardsToReveal()  )
            {
                controller.disableUnrevealedButtons(this.cards);
            }

            b.setEnabled(false); // each button can only reveal itself once per round
        }
    }

    private void checkForMatchButtonClicked(ActionEvent e)
    {
        OutputUtils.displayMessage("Add logic to check for match", "Check for match button Clicked");
    }

    private void exitProgramButtonClicked(ActionEvent e)
    {
        System.exit(0);
    }

    private void clearSelections()
    {
        System.out.println("DEBUG: clearSelections");
        this.controller.clearSelections(centralPanel);
    }

}


