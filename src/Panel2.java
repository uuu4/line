import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Panel2 extends JPanel {
    ImageIcon img1, img2, img3, img4, img5, img6, img7;

    final int IMG_WIDTH = 100; // Assuming fixed width for simplicity
    final int IMG_HEIGHT = 100; // Assuming fixed height for simplicity

    Point img1Corner, img2Corner, img3Corner, img4Corner, img5Corner, img6Corner, img7Corner;

    Point prevPoint;

    int selectedImage = 0; // 0: None, 1: img1, 2: img2, etc.

    Panel2() {
        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);

        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img1 != null) img1.paintIcon(this, g, (int) img1Corner.getX(), (int) img1Corner.getY());
        if (img2 != null) img2.paintIcon(this, g, (int) img2Corner.getX(), (int) img2Corner.getY());
        if (img3 != null) img3.paintIcon(this, g, (int) img3Corner.getX(), (int) img3Corner.getY());
        if (img4 != null) img4.paintIcon(this, g, (int) img4Corner.getX(), (int) img4Corner.getY());
        if (img5 != null) img5.paintIcon(this, g, (int) img5Corner.getX(), (int) img5Corner.getY());
        if (img6 != null) img6.paintIcon(this, g, (int) img6Corner.getX(), (int) img6Corner.getY());
        if (img7 != null) img7.paintIcon(this, g, (int) img7Corner.getX(), (int) img7Corner.getY());
    }

    private class ClickListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            Point clickPoint = event.getPoint();

            if (isWithinImage(clickPoint, img1Corner)) {
                selectedImage = 1;
            } else if (isWithinImage(clickPoint, img2Corner)) {
                selectedImage = 2;
            } else if (isWithinImage(clickPoint, img3Corner)) {
                selectedImage = 3;
            } else if (isWithinImage(clickPoint, img4Corner)) {
                selectedImage = 4;
            } else if (isWithinImage(clickPoint, img5Corner)) {
                selectedImage = 5;
            } else if (isWithinImage(clickPoint, img6Corner)) {
                selectedImage = 6;
            } else if (isWithinImage(clickPoint, img7Corner)) {
                selectedImage = 7;
            } else {
                selectedImage = 0;
            }

            prevPoint = event.getPoint();
        }

        private boolean isWithinImage(Point clickPoint, Point imgCorner) {
            return clickPoint.getX() >= imgCorner.getX() && clickPoint.getX() <= imgCorner.getX() + IMG_WIDTH &&
                    clickPoint.getY() >= imgCorner.getY() && clickPoint.getY() <= imgCorner.getY() + IMG_HEIGHT;
        }
    }

    private class DragListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent event) {
            if (selectedImage != 0) {
                Point currentPoint = event.getPoint();
                Point imgCorner = getSelectedImageCorner();
                imgCorner.translate((int) (currentPoint.getX() - prevPoint.getX()),
                        (int) (currentPoint.getY() - prevPoint.getY()));
                prevPoint = currentPoint;
                repaint();
            }
        }

        private Point getSelectedImageCorner() {
            switch (selectedImage) {
                case 1: return img1Corner;
                case 2: return img2Corner;
                case 3: return img3Corner;
                case 4: return img4Corner;
                case 5: return img5Corner;
                case 6: return img6Corner;
                case 7: return img7Corner;
                default: return null;
            }
        }
    }

    public void addImage(ImageIcon image, Point point) {
        if (img1 == null) {
            img1 = image;
            img1Corner = point;
        } else if (img2 == null) {
            img2 = image;
            img2Corner = point;
        } else if (img3 == null) {
            img3 = image;
            img3Corner = point;
        } else if (img4 == null) {
            img4 = image;
            img4Corner = point;
        } else if (img5 == null) {
            img5 = image;
            img5Corner = point;
        } else if (img6 == null) {
            img6 = image;
            img6Corner = point;
        } else if (img7 == null) {
            img7 = image;
            img7Corner = point;
        }
        repaint();
    }
}
