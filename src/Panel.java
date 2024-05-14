import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Panel extends JPanel {
    //NOT bu img path suan benim localimde o yuzden calistirdiginiz zaman herhangi bir fotograf cikmaz
    ImageIcon img1 = new ImageIcon("/Users/aliemreaydin/Downloads/emre-2.png");

    final int IMG_WIDTH = img1.getIconWidth();
    final int IMG_HEIGHT = img1.getIconHeight();

    Point imgCorner; // suanki fotografin koordinatini gosteren pointer
    Point prevPoint; // mouse click eventinden hemen sonra koordinatini gosteren pointer
    Panel(){
        imgCorner = new Point(0,0);

        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);

        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        img1.paintIcon(this,g,(int)imgCorner.getX(),(int)imgCorner.getY());

        // not diger imagelar icinde yukaridaki satirin aynisi lazim
    }
    private class ClickListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent event){
            prevPoint = event.getPoint();

        }

    }
    private class DragListener extends MouseMotionAdapter{
        @Override
        public void mouseDragged(MouseEvent event){
            Point currentPoint = event.getPoint();
            imgCorner.translate((int)(currentPoint.getX()-prevPoint.getX())
                    ,(int)(currentPoint.getY()-prevPoint.getY()));
            prevPoint = currentPoint;
            repaint();
        }
    }
}
