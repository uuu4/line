import javax.swing.*;
import java.awt.*;

public class WindowFrame extends JFrame {
    public WindowFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setTitle("github.com/uuu4");
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setLayout(new BorderLayout());

        // Panel1
        Panel1 panel1 = new Panel1();
        panel1.setPreferredSize(new Dimension(300, 500));
        panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, false));
        this.add(panel1, BorderLayout.WEST);

        // Panel2
        Panel2 panel2 = new Panel2();
        panel2.setPreferredSize(new Dimension(900, 500)); // Set size of panel2
        panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, false));
        this.add(panel2, BorderLayout.CENTER); // Add panel2 to the right side

        // Panel3 with JScrollPane
        Panel3 panel3 = new Panel3();
        panel3.setPreferredSize(new Dimension(1180, 300)); // Set preferred size larger to allow scrolling
        panel3.setBackground(Color.darkGray);

        JScrollPane scrollPane = new JScrollPane(panel3);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.darkGray, 5, false)); // Test için sınır çizin
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Adding scrollPane to the bottom of the frame
        this.add(scrollPane, BorderLayout.SOUTH);

        this.setVisible(true);
    }}