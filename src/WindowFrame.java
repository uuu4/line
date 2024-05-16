import javax.swing.*;
import java.awt.*;

public class WindowFrame extends JFrame {
    public WindowFrame(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,800);
        this.setTitle("github.com/uuu4");
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        Panel1 panel1 = new Panel1();
        panel1.setBounds(0,0,300,500);
        panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE,20,true));
        this.add(panel1);

        Panel2 panel2 = new Panel2();
        panel2.setBounds(300, 0, 900, 500); // Set size of panel2
        panel2.setBorder(BorderFactory.createLineBorder(Color.BLUE,20,true));
        this.add(panel2); // Add panel2 to the right side



        Panel3 panel3 = new Panel3();
        panel3.setBounds(0,500,1200,300);
        panel3.setBorder(BorderFactory.createLineBorder(Color.BLUE,20,true));
        this.add(panel3);



        this.setVisible(true);

    }
}
