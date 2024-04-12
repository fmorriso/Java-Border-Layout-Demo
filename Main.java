import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        String title = String.format("Java Border Layout Demo using java version %s", getJavaVersion());
        System.out.println(title);
        Dimension scaledSize = SwingScreenUtilities.getScaledSize(.5, 10);
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        Controller controller = new Controller();
        GUI gui = new GUI(scaledSize, title, controller);
        javax.swing.SwingUtilities.invokeLater(gui);
    }

    private static String getJavaVersion()
    {
        Runtime.Version runTimeVersion = Runtime.version();
        return String.format("%s.%s.%s.%s", runTimeVersion.feature(), runTimeVersion.interim(), runTimeVersion.update(), runTimeVersion.patch());
    }
}
