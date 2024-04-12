import javax.swing.*;
import java.awt.*;

public class GUI implements Runnable
{
    private Dimension scaledSize;
    private String title;

    public GUI(Dimension scaledSize, String title)
    {
        this.scaledSize = scaledSize;
        this.title = title;
    }
    /**
     * Runs this operation.
     */
    @Override
    public void run()
    {
        new BorderLayoutDemo(scaledSize, title);
    }
}
