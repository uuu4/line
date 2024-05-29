import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel1 extends JPanel{
    Panel2 panel2;
    Panel3 panel3;

    Panel1() {
        setLayout(new GridLayout(7, 1));

        JButton btn1 = new JButton("Start");
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel2.addImage(panel2.img1, new Point(50, 50), false);
                panel3.addImageName("Start");
            }
        });
        add(btn1);

        JButton btn2 = new JButton("Process");
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel2.addImage(panel2.img2, new Point(200, 50), true);
                panel3.addImageName("Process");
            }
        });
        add(btn2);

        JButton btn3 = new JButton("If Block");
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel2.addImage(panel2.img3, new Point(350, 50), true);
                panel3.addImageName("IfBlock");
            }
        });
        add(btn3);

        JButton btn4 = new JButton("Output");
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel2.addImage(panel2.img4, new Point(500, 50), true);
                panel3.addImageName("Output");
            }
        });
        add(btn4);

        JButton btn5 = new JButton("Else Block");
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel2.addImage(panel2.img5, new Point(650, 50), true);
                panel3.addImageName("ElseBlock");
            }
        });
        add(btn5);

        JButton btn6 = new JButton("Loop");
        btn6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel2.addImage(panel2.img6, new Point(800, 50), true);
                panel3.addImageName("Loop");
            }
        });
        add(btn6);

        JButton btn7 = new JButton("End");
        btn7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel2.addImage(panel2.img7, new Point(50, 250), false);
                panel3.addImageName("End");
            }
        });
        add(btn7);
    }

    public void setPanel2(Panel2 panel2) {
        this.panel2 = panel2;
    }

    public void setPanel3(Panel3 panel3) {
        this.panel3 = panel3;
    }



}
