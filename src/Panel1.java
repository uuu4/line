import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Panel1 extends JPanel {
    ImageIcon img1 = new ImageIcon("1.png");
    ImageIcon img2 = new ImageIcon("2.png");
    ImageIcon img3 = new ImageIcon("3.png");
    ImageIcon img4 = new ImageIcon("4.png");
    ImageIcon img5 = new ImageIcon("5.png");
    ImageIcon img6 = new ImageIcon("6.png");
    ImageIcon img7 = new ImageIcon("7.png");

    final int IMG_WIDTH = img1.getIconWidth();
    final int IMG_HEIGHT = img1.getIconHeight();

    Point img1Corner = new Point(50, 50);
    Point img2Corner = new Point((int) img1Corner.getX() + 100, 50);
    Point img3Corner = new Point(50, (int) img1Corner.getY() + 100);
    Point img4Corner = new Point((int) img1Corner.getX() + 100, (int) img2Corner.getY() + 100);
    Point img5Corner = new Point(50, (int) img3Corner.getY() + 100);
    Point img6Corner = new Point((int) img1Corner.getX() + 100, (int) img4Corner.getY() + 100);
    Point img7Corner = new Point(100, (int) img5Corner.getY() + 100);

    Point prevPoint;

    int selectedImage = 0; // 0: None, 1: img1, 2: img2, etc.

    private Panel2 panel2;

    public void setPanel2(Panel2 panel2) {
        this.panel2 = panel2;
    }

    Panel1() {
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

                if (isOutOfPanelBounds(imgCorner)) {
                    transferImageToPanel2(imgCorner);
                    removeSelectedImage();
                    repaint();
                }
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

        private boolean isOutOfPanelBounds(Point imgCorner) {
            return imgCorner.x < 0 || imgCorner.x > getWidth() || imgCorner.y < 0 || imgCorner.y > getHeight();
        }

        private void transferImageToPanel2(Point imgCorner) {
            if (panel2 != null) {
                ImageIcon image = cloneSelectedImage();
                Point pointInPanel2 = SwingUtilities.convertPoint(Panel1.this, imgCorner, panel2);
                panel2.addImage(image, pointInPanel2);
            }
        }
    }

    public ImageIcon cloneSelectedImage() {
        switch (selectedImage) {
            case 1: return new ImageIcon(img1.getImage());
            case 2: return new ImageIcon(img2.getImage());
            case 3: return new ImageIcon(img3.getImage());
            case 4: return new ImageIcon(img4.getImage());
            case 5: return new ImageIcon(img5.getImage());
            case 6: return new ImageIcon(img6.getImage());
            case 7: return new ImageIcon(img7.getImage());
            default: return null;
        }
    }

    public void removeSelectedImage() {
        switch (selectedImage) {
            case 1: img1 = null; break;
            case 2: img2 = null; break;
            case 3: img3 = null; break;
            case 4: img4 = null; break;
            case 5: img5 = null; break;
            case 6: img6 = null; break;
            case 7: img7 = null; break;
        }
        selectedImage = 0;
    }
}