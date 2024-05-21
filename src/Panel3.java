import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Panel3 extends JPanel {
    private List<String> imageNames = new ArrayList<>();

    Panel3() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addImageName(String imageName) {
        JLabel imageNameLabel = new JLabel(imageName);
        this.add(imageNameLabel);
        imageNames.add(imageName);
        revalidate();
        repaint();
    }
}
