import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class Panel2 extends JPanel {
    List<ImageIcon> images = new ArrayList<>();
    List<Point> imageCorners = new ArrayList<>();

    final int IMG_WIDTH = 100; // Assuming fixed width for simplicity
    final int IMG_HEIGHT = 100; // Assuming fixed height for simplicity

    Point prevPoint;

    int selectedImageIndex = -1; // -1: None

    Panel2() {
        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);

        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < images.size(); i++) {
            ImageIcon image = images.get(i);
            Point corner = imageCorners.get(i);
            image.paintIcon(this, g, (int) corner.getX(), (int) corner.getY());
        }
    }

    private class ClickListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            Point clickPoint = event.getPoint();
            selectedImageIndex = getSelectedImageIndex(clickPoint);
            prevPoint = event.getPoint();
        }

        private int getSelectedImageIndex(Point clickPoint) {
            for (int i = images.size() - 1; i >= 0; i--) {
                Point imgCorner = imageCorners.get(i);
                if (isWithinImage(clickPoint, imgCorner)) {
                    return i;
                }
            }
            return -1; // None selected
        }

        private boolean isWithinImage(Point clickPoint, Point imgCorner) {
            return clickPoint.getX() >= imgCorner.getX() && clickPoint.getX() <= imgCorner.getX() + IMG_WIDTH &&
                    clickPoint.getY() >= imgCorner.getY() && clickPoint.getY() <= imgCorner.getY() + IMG_HEIGHT;
        }
    }

    private class DragListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent event) {
            if (selectedImageIndex != -1) {
                Point currentPoint = event.getPoint();
                Point imgCorner = imageCorners.get(selectedImageIndex);
                imgCorner.translate((int) (currentPoint.getX() - prevPoint.getX()),
                        (int) (currentPoint.getY() - prevPoint.getY()));
                prevPoint = currentPoint;
                repaint();
            }
        }
    }

    public void addImage(ImageIcon image, Point point) {
        images.add(image);
        imageCorners.add(point);
        repaint();
    }
}
