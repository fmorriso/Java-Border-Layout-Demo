import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        String title = String.format("Java Border Layout Demo using java version %s", getJavaVersion());
        System.out.println(title);
        Dimension scaledSize = SwingScreenUtilities.getScaledSize(0.4, 10);

        GameController controller = new GameController();
        controller.createAndShowGUI(scaledSize, title);
    }

    private static String getJavaVersion()
    {
        Runtime.Version runTimeVersion = Runtime.version();
        return String.format("%s.%s.%s.%s", runTimeVersion.feature(), runTimeVersion.interim(), runTimeVersion.update(), runTimeVersion.patch());
    }
}
