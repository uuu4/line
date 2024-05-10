import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Step 1: Create a JFrame
        JFrame frame = new JFrame("Grid Inside Grid Example");
        frame.setSize(1200,1200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel disPanel = new JPanel(new GridLayout(2, 1));
        disPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel icPanel = new JPanel(new GridLayout(1, 2));
        JPanel icicPanel = new JPanel(new GridLayout(3,3));
        JLabel label1 = new JLabel("(diyagram cizme yeri)");
        JLabel label2 = new JLabel("(islem gecmisi)");
        JLabel label12 = new JLabel("(kare)");
        JLabel label4 = new JLabel("(ucgen)");
        JLabel label5 = new JLabel("(dikdortgen)");
        JLabel label6 = new JLabel("(daire)");
        JLabel label7 = new JLabel("(osuruk)");
        JLabel label8 = new JLabel("(ucgen)");
        JLabel label9 = new JLabel("(daire)");
        JLabel label10 = new JLabel("(osuruk)");
        JLabel label11 = new JLabel("(ucgen)");

        icPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        icicPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        icPanel.add(label1);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        icPanel.add(label2);
        disPanel.add(icPanel);
        JLabel label3 = new JLabel("terminal");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        disPanel.add(label3);
        icPanel.add(icicPanel);
        icicPanel.add(label12);
        icicPanel.add(label4);
        icicPanel.add(label5);
        icicPanel.add(label6);
        icicPanel.add(label7);
        icicPanel.add(label8);
        icicPanel.add(label9);
        icicPanel.add(label10);
        icicPanel.add(label11);




        frame.getContentPane().add(disPanel);


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
