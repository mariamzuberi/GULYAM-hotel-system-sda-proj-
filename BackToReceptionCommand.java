
package hotel.management.system;
import javax.swing.JFrame;
public class BackToReceptionCommand implements Command {
    private JFrame currentFrame;

    public BackToReceptionCommand(JFrame frame) {
        this.currentFrame = frame;
    }

    @Override
    public void execute() {
        new Reception().setVisible(true);
        currentFrame.setVisible(false);
    }
}
