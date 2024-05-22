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

    final int IMG_WIDTH = 100;
    final int IMG_HEIGHT = 100;

    Point prevPoint;
    int selectedImageIndex = -1;

    Panel2() {
        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);

        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw images
        for (int i = 0; i < images.size(); i++) {
            ImageIcon image = images.get(i);
            Point corner = imageCorners.get(i);
            image.paintIcon(this, g, corner.x, corner.y);
        }
        // Draw lines between images
        g.setColor(Color.BLACK);
        for (int i = 1; i < imageCorners.size(); i++) {
            Point prev = imageCorners.get(i - 1);
            Point current = imageCorners.get(i);
            g.drawLine(prev.x + IMG_WIDTH / 2, prev.y + IMG_HEIGHT / 2, current.x + IMG_WIDTH / 2, current.y + IMG_HEIGHT / 2);
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
            return -1;
        }

        private boolean isWithinImage(Point clickPoint, Point imgCorner) {
            return clickPoint.x >= imgCorner.x && clickPoint.x <= imgCorner.x + IMG_WIDTH &&
                    clickPoint.y >= imgCorner.y && clickPoint.y <= imgCorner.y + IMG_HEIGHT;
        }
    }

    private class DragListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent event) {
            if (selectedImageIndex != -1) {
                Point currentPoint = event.getPoint();
                Point imgCorner = imageCorners.get(selectedImageIndex);
                imgCorner.translate(currentPoint.x - prevPoint.x, currentPoint.y - prevPoint.y);
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
