// Java program to illustrate the BorderLayout

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// class extends JFrame
public class BorderLayoutDemo extends JFrame
{
    private Dimension scaledSize;
    private String title;

    public BorderLayoutDemo(Dimension scaledSize, String title)
    {
        this.scaledSize = scaledSize;
        this.title = title;

        this.setTitle(title);
        this.setSize(scaledSize);
        this.setLocationRelativeTo(null);

        // Creating Object of Jpanel class
        JPanel pa = new JPanel();


        // set the layout
        pa.setLayout(new BorderLayout());

        // add a new JButton with name "welcome" to the top (North) part of the border
        JButton welcomeButton = new JButton("Welcome");
        welcomeButton.addActionListener(e -> sayWelcome());
        pa.add(welcomeButton, BorderLayout.NORTH);

        // Geeks button goes on bottom
        JButton geeksButton = new JButton("Geeks");
        welcomeButton.addActionListener(e -> geeksClicked(e));
        pa.add(geeksButton, BorderLayout.SOUTH);

        // add a new JButton with name "Layout" and it is
        // lie left of the container
        pa.add(new JButton("Layout"), BorderLayout.EAST);

        // add a new JButton with name "Border" and it is
        // lie right of the container
        pa.add(new JButton("Border"), BorderLayout.WEST);

        // add a new JButton with name "hello everybody" and it is
        // lie center of the container
        pa.add(new JButton("GeeksforGeeks"), BorderLayout.CENTER);

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

    private void geeksClicked(ActionEvent e)
    {
        OutputUtils.displayMessage("Geeks are cool!", "Geeks button Clicked");
    }

    private void sayWelcome()
    {
        OutputUtils.displayMessage("Welcome!", "Welcome button Clicked");
    }
}

/*class MainFrame {

    // Driver code
    public static void main(String[] args)
    {

        // calling the constructor
        new BoderLayoutDemo();
    }
}*/

