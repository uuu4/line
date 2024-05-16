import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Panel extends JPanel {
    ImageIcon img1 = new ImageIcon("/Users/aliemreaydin/Downloads/emre-2-4.png");
    ImageIcon img2 = new ImageIcon("/Users/aliemreaydin/Downloads/emre-2-4.png");
    ImageIcon img3 = new ImageIcon("/Users/aliemreaydin/Downloads/emre-2-4.png");
    ImageIcon img4 = new ImageIcon("/Users/aliemreaydin/Downloads/emre-2-4.png");
    ImageIcon img5 = new ImageIcon("/Users/aliemreaydin/Downloads/emre-2-4.png");

    ImageIcon img6 = new ImageIcon("/Users/aliemreaydin/Downloads/emre-2-4.png");
    ImageIcon img7 = new ImageIcon("/Users/aliemreaydin/Downloads/emre-2-4.png");

    final int IMG_WIDTH = img1.getIconWidth();
    final int IMG_HEIGHT = img1.getIconHeight();

    Point img1Corner = new Point(50, 50);
    Point img2Corner = new Point((int)img1Corner.getX()+100, 50);
    Point img3Corner = new Point(50, (int)img1Corner.getY()+100);
    Point img4Corner = new Point((int)img1Corner.getX()+100, (int)img2Corner.getY()+100);
    Point img5Corner = new Point(50, (int)img3Corner.getY()+100);

    Point img6Corner = new Point((int)img1Corner.getX()+100, (int)img4Corner.getY()+100);
    Point img7Corner = new Point(100, (int)img5Corner.getY()+100);

    Point prevPoint1;
    Point prevPoint2;
    Point prevPoint3;
    Point prevPoint4;
    Point prevPoint5;
    Point prevPoint6;
    Point prevPoint7;

    int selectedImage = 0; // 0: Hiçbiri, 1: img1, 2: img2 diye devam ediyor... :3

    Panel() {
        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);

        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        img1.paintIcon(this, g, (int) img1Corner.getX(), (int) img1Corner.getY());
        img2.paintIcon(this, g, (int) img2Corner.getX(), (int) img2Corner.getY());
        img3.paintIcon(this, g, (int) img3Corner.getX(), (int) img3Corner.getY());
        img4.paintIcon(this, g, (int) img4Corner.getX(), (int) img4Corner.getY());
        img5.paintIcon(this, g, (int) img5Corner.getX(), (int) img5Corner.getY());
        img6.paintIcon(this, g, (int) img6Corner.getX(), (int) img6Corner.getY());
        img7.paintIcon(this, g, (int) img7Corner.getX(), (int) img7Corner.getY());
    }

    private class ClickListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            Point clickPoint = event.getPoint();

            if (clickPoint.getX() >= img1Corner.getX() && clickPoint.getX() <= img1Corner.getX() + IMG_WIDTH &&
                    clickPoint.getY() >= img1Corner.getY() && clickPoint.getY() <= img1Corner.getY() + IMG_HEIGHT) {
                selectedImage = 1;
            }
            else if (clickPoint.getX() >= img2Corner.getX() && clickPoint.getX() <= img2Corner.getX() + IMG_WIDTH &&
                    clickPoint.getY() >= img2Corner.getY() && clickPoint.getY() <= img2Corner.getY() + IMG_HEIGHT) {
                selectedImage = 2;
            }
            else if (clickPoint.getX() >= img3Corner.getX() && clickPoint.getX() <= img3Corner.getX() + IMG_WIDTH &&
                    clickPoint.getY() >= img3Corner.getY() && clickPoint.getY() <= img3Corner.getY() + IMG_HEIGHT) {
                selectedImage = 3;
            }
            else if (clickPoint.getX() >= img4Corner.getX() && clickPoint.getX() <= img4Corner.getX() + IMG_WIDTH &&
                    clickPoint.getY() >= img4Corner.getY() && clickPoint.getY() <= img4Corner.getY() + IMG_HEIGHT) {
                selectedImage = 4;
            }
            else if (clickPoint.getX() >= img5Corner.getX() && clickPoint.getX() <= img5Corner.getX() + IMG_WIDTH &&
                    clickPoint.getY() >= img5Corner.getY() && clickPoint.getY() <= img5Corner.getY() + IMG_HEIGHT) {
                selectedImage = 5;
            }
            else if (clickPoint.getX() >= img6Corner.getX() && clickPoint.getX() <= img6Corner.getX() + IMG_WIDTH &&
                    clickPoint.getY() >= img6Corner.getY() && clickPoint.getY() <= img6Corner.getY() + IMG_HEIGHT) {
                selectedImage = 6;
            }
            else if (clickPoint.getX() >= img7Corner.getX() && clickPoint.getX() <= img7Corner.getX() + IMG_WIDTH &&
                    clickPoint.getY() >= img7Corner.getY() && clickPoint.getY() <= img7Corner.getY() + IMG_HEIGHT) {
                selectedImage = 7;
            }
            else {
                selectedImage = 0;
            }

            prevPoint1 = event.getPoint();
            prevPoint2 = event.getPoint();
            prevPoint3 = event.getPoint();
            prevPoint4 = event.getPoint();
            prevPoint5 = event.getPoint();
            prevPoint6 = event.getPoint();
            prevPoint7 = event.getPoint();


        }
    }

    private class DragListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent event) {
            if (selectedImage == 1) {
                Point currentPoint = event.getPoint();
                img1Corner.translate((int) (currentPoint.getX() - prevPoint1.getX()),
                        (int) (currentPoint.getY() - prevPoint1.getY()));
                prevPoint1 = currentPoint;
            }
            else if (selectedImage == 2) {
                Point currentPoint = event.getPoint();
                img2Corner.translate((int) (currentPoint.getX() - prevPoint2.getX()),
                        (int) (currentPoint.getY() - prevPoint2.getY()));
                prevPoint2 = currentPoint;
            }
            else if (selectedImage == 3) {
                Point currentPoint = event.getPoint();
                img3Corner.translate((int) (currentPoint.getX() - prevPoint3.getX()),
                        (int) (currentPoint.getY() - prevPoint3.getY()));
                prevPoint3 = currentPoint;
            }
            else if (selectedImage == 4) {
                Point currentPoint = event.getPoint();
                img4Corner.translate((int) (currentPoint.getX() - prevPoint4.getX()),
                        (int) (currentPoint.getY() - prevPoint4.getY()));
                prevPoint4 = currentPoint;
            }
            else if (selectedImage == 5) {
                Point currentPoint = event.getPoint();
                img5Corner.translate((int) (currentPoint.getX() - prevPoint5.getX()),
                        (int) (currentPoint.getY() - prevPoint5.getY()));
                prevPoint5 = currentPoint;
            }
            else if (selectedImage == 6) {
                Point currentPoint = event.getPoint();
                img6Corner.translate((int) (currentPoint.getX() - prevPoint6.getX()),
                        (int) (currentPoint.getY() - prevPoint6.getY()));
                prevPoint6 = currentPoint;
            }
            else if (selectedImage == 7) {
                Point currentPoint = event.getPoint();
                img7Corner.translate((int) (currentPoint.getX() - prevPoint7.getX()),
                        (int) (currentPoint.getY() - prevPoint7.getY()));
                prevPoint7 = currentPoint;
            }

            repaint();
        }
    }


}
