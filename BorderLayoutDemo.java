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
    private Controller controller;

    private ArrayList<JButton> cards;

    public BorderLayoutDemo(Dimension scaledSize, String title, Controller controller)
    {
        this.scaledSize = scaledSize;
        this.title = title;
        this.controller = controller;

        cards = new ArrayList<JButton>();

        this.setTitle(title);
        this.setSize(scaledSize);
        this.setLocationRelativeTo(null);

        // Creating Object of Jpanel class
        JPanel pa = new JPanel();


        // set the layout
        pa.setLayout(new BorderLayout(10, 10));

        // add a new JButton with name "welcome" to the top (North) part of the border
        JButton welcomeButton = new JButton("Welcome");
        welcomeButton.addActionListener(e -> sayWelcome());
        pa.add(welcomeButton, BorderLayout.NORTH);

        // Geeks button goes on bottom
        JButton geeksButton = new JButton("Geeks");
        geeksButton.addActionListener(e -> geeksClicked(e));
        pa.add(geeksButton, BorderLayout.SOUTH);

        // add a new JButton with name "Layout" on EAST (left) side
        JButton layoutButton = new JButton("Layout");
        layoutButton.addActionListener(e -> layoutButtonClicked(e));
        pa.add(layoutButton, BorderLayout.EAST);

        // add a new JButton with name "Border" and it is
        // lie right of the container
        JButton startRoundButton = new JButton("Start Round");
        startRoundButton.addActionListener(e -> startRoundButtonClicked(e));
        pa.add(startRoundButton, BorderLayout.WEST);

        // put match buttons in the center
        JPanel centerPanel = createMatchButtons(pa);
        pa.add(centerPanel, BorderLayout.CENTER);
        

        // add the pa object which refer to the Jpanel
        add(pa);

        // Function to close the operation of JFrame.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Function to set size of JFrame.
        //setSize(300, 300);
        setSize(scaledSize);

        // Function to set visible status of JFrame.
        setVisible(true);
    }

    private void startRoundButtonClicked(ActionEvent e)
    {
        this.controller.startNewRound();
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
            JButton b = new JButton("");
            cards.add(b);
            b.addActionListener(e -> cardClicked(e));
            pnl.add(b);
        }

        return pnl;
    }

    private void cardClicked(ActionEvent e)
    {
        if (e.getSource() instanceof JButton)
        {
            JButton b = (JButton) e.getSource();
            String msg = String.format("Card button %s was clicked", b.getText());
            OutputUtils.displayMessage(msg, title);

        }
    }

    private void layoutButtonClicked(ActionEvent e)
    {
        OutputUtils.displayMessage("Layout button", "Layout button Clicked");
    }

    private void geeksClicked(ActionEvent e)
    {
        if (e.getSource() instanceof JButton) {
            JButton btn = (JButton) e.getSource();
            changeColor(btn);
        }
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

    private void sayWelcome()
    {
        OutputUtils.displayMessage("Welcome!", "Welcome button Clicked");
    }
}


