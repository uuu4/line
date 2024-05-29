import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimatedBackgroundPanel extends JPanel {
    private int x = 0;  // x-coordinate of the circle
    private int y = 100;  // y-coordinate of the circle
    private int diameter = 50;  // diameter of the circle
    private int deltaX = 2;  // horizontal speed of the circle

    public AnimatedBackgroundPanel() {
        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x += deltaX;
                if (x + diameter > getWidth() || x < 0) {
                    deltaX *= -1;  // reverse direction if it hits the edge
                }
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval(x, y, diameter, diameter);
    }
}