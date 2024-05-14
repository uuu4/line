import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public GUI(){
        // bilesen ekleme
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel foto1 = new JLabel("Image1");
        JLabel foto2 = new JLabel("Image2");
        JLabel foto3 = new JLabel("Image3");
        JLabel foto4 = new JLabel("Image4");
        JLabel foto5 = new JLabel("Image5");
        JLabel foto6 = new JLabel("Image6");
        JLabel foto7 = new JLabel("Image7");
        JLabel foto8 = new JLabel("Image8");
        JButton nasilKullanirim = new JButton("Nasıl Kullanılır?");

        //sabit frame ayarlari
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1200, 1200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //panel ayarlari
        panel.setLayout(null);
        frame.add(panel);

        //label ayarlari
        foto1.setBounds(25, 25, 150, 150);
        panel.add(foto1);

        foto2.setBounds(25+foto1.getWidth(),25,150,150);
        panel.add(foto2);

        foto3.setBounds(25,25+foto1.getHeight(),150,150);
        panel.add(foto3);

        foto4.setBounds(25+foto1.getWidth(),25+foto1.getHeight(),150,150);
        panel.add(foto4);

        foto5.setBounds(25,25+2*foto1.getHeight(),150,150);
        panel.add(foto5);

        foto6.setBounds(25+foto1.getWidth(),25+2*foto1.getHeight(),150,150);
        panel.add(foto6);

        foto7.setBounds(25,25+3*foto1.getHeight(),150,150);
        panel.add(foto7);

        foto8.setBounds(25+foto1.getWidth(),25+3*foto1.getHeight(),150,150);
        panel.add(foto8);

        //button ayarlari
        nasilKullanirim.setBounds(frame.getWidth()-200,0,150,20);
        panel.add(nasilKullanirim);

    }
}
