import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EnterenceScreenFrame enterenceScreenFrame = EnterenceScreenFrame.getInstance();
            enterenceScreenFrame.setVisible(true);
        });
    }
}


