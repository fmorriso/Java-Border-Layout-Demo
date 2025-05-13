import java.awt.*;

public class GUI implements Runnable
{
    private Dimension scaledSize;
    private final String title;
    private final GameController controller;

    public GUI(Dimension scaledSize, String title, GameController controller)
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
