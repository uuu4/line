import javax.swing.*;

public class WindowFrame extends JFrame {
    public WindowFrame(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,800);
        this.setTitle("github.com/uuu4");
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        Panel panel = new Panel();
        this.add(panel);

        this.setVisible(true);


    }
}
