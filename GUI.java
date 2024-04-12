import javax.swing.*;
import java.awt.*;

public class GUI implements Runnable
{
    private Dimension scaledSize;
    private String title;
    private Controller controller;

    public GUI(Dimension scaledSize, String title, Controller controller)
    {
        this.scaledSize = scaledSize;
        this.title = title;
        this.controller = controller;
    }
    /**
     * Runs this operation.
     */
    @Override
    public void run()
    {
        new BorderLayoutDemo(scaledSize, title, controller);
    }
}
