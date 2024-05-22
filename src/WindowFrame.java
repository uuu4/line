import javax.swing.*;
import java.awt.*;

public class WindowFrame extends JFrame {
    private Panel3 panel3;

    public WindowFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setTitle("Flowchart App");
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setLayout(null);

        Panel1 panel1 = new Panel1();
        panel1.setBounds(0, 0, 300, 500);
        panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, false));
        this.add(panel1);

        panel3 = new Panel3();
        panel3.setBounds(0, 500, 1200, 300);
        panel3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, false));
        panel3.setBackground(Color.darkGray);
        this.add(panel3);

        Panel2 panel2 = new Panel2(panel3);
        panel2.setBounds(300, 0, 900, 500);
        panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, false));
        this.add(panel2);

        panel1.setPanel2(panel2);

        this.setVisible(true);
    }

    public Panel3 getPanel3() {
        return panel3;
    }

    public static void main(String[] args) {
        new WindowFrame();
    }
}
