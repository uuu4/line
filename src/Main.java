import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI example = new GUI();
            example.setVisible(true);
            
        });
    }
}
