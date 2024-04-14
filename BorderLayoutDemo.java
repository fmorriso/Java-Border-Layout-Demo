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
    private JPanel buttonsPanel;
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

        // add a new JButton that will reset the game
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetButtonClicked(e));
        mainPanel.add(resetButton, BorderLayout.NORTH);

        // exit button goes on bottom
        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> exitProgramButtonClicked(e));
        mainPanel.add(exitButton, BorderLayout.SOUTH);

        // add a new JButton with name "Check for Match" on EAST (left) side
        checkForMatchButton = new JButton("Check For Match");
        checkForMatchButton.addActionListener(e -> checkForMatchButtonClicked(e));
        mainPanel.add(checkForMatchButton, BorderLayout.EAST);

        // add a new JButton with name "Start Round"
        startRoundButton = new JButton("Start Round");
        startRoundButton.addActionListener(e -> startRoundButtonClicked(e));
        mainPanel.add(startRoundButton, BorderLayout.WEST);

        // put match buttons in the center buttons panel
        this.buttonsPanel = createMatchButtons(mainPanel);
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
        

        // add the pa object which refer to the Jpanel
        add(mainPanel);

        // Function to close the operation of JFrame.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Function to set size of JFrame.
        //setSize(300, 300);
        setSize(scaledSize);

        // Function to set visible status of JFrame.
        setVisible(true);
    }

    private void resetButtonClicked(ActionEvent e)
    {
        clearSelections();
    }

    private void startRoundButtonClicked(ActionEvent e)
    {
        this.controller.startNewRound();

        //this.controller.enableMatchButtons(this.cards);
        for(Component component: this.buttonsPanel.getComponents())
        {
            if (component instanceof CardButton)
            {
                CardButton button = (CardButton) component;
                button.setEnabled(true);
                button.setRevealed(false);
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

    private void changeColor(JButton btn)
    {
        String[] colorChoices = {"red", "green", "blue"};
        String choice = InputUtils.getSingleChoice("Choose Color", "Which color?", colorChoices);
        switch (choice.toLowerCase()) {
            case "red":
                btn.setBackground(Color.RED);
                btn.setForeground(Color.WHITE);
                break;
            case "green":
                btn.setBackground(Color.GREEN);
                btn.setForeground(Color.YELLOW);
                break;
            case "blue":
                btn.setBackground(Color.BLUE);
                btn.setForeground(Color.PINK);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + choice.toLowerCase());
        }
    }

    private void clearSelections()
    {
        this.controller.clearSelections(buttonsPanel);
    }
}


