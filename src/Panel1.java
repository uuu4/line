import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

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
    Point img2Corner = new Point(img1Corner.x + 100, 50);
    Point img3Corner = new Point(50, img1Corner.y + 100);
    Point img4Corner = new Point(img1Corner.x + 100, img2Corner.y + 100);
    Point img5Corner = new Point(50, img3Corner.y + 100);
    Point img6Corner = new Point(img1Corner.x + 100, img4Corner.y + 100);
    Point img7Corner = new Point(100, img5Corner.y + 100);

    Point prevPoint;
    int selectedImage = 0;

    private Panel2 panel2;
    private Panel3 panel3;

    List<ImageIcon> clones = new ArrayList<>();
    List<Point> clonePositions = new ArrayList<>();

    public void setPanel2(Panel2 panel2) {
        this.panel2 = panel2;
    }

    public void setPanel3(Panel3 panel3) {
        this.panel3 = panel3;
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
        if (img1 != null) img1.paintIcon(this, g, img1Corner.x, img1Corner.y);
        if (img2 != null) img2.paintIcon(this, g, img2Corner.x, img2Corner.y);
        if (img3 != null) img3.paintIcon(this, g, img3Corner.x, img3Corner.y);
        if (img4 != null) img4.paintIcon(this, g, img4Corner.x, img4Corner.y);
        if (img5 != null) img5.paintIcon(this, g, img5Corner.x, img5Corner.y);
        if (img6 != null) img6.paintIcon(this, g, img6Corner.x, img6Corner.y);
        if (img7 != null) img7.paintIcon(this, g, img7Corner.x, img7Corner.y);

        for (int i = 0; i < clones.size(); i++) {
            clones.get(i).paintIcon(this, g, clonePositions.get(i).x, clonePositions.get(i).y);
        }
    }

    private class ClickListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            Point clickPoint = event.getPoint();

            if (isWithinImage(clickPoint, img1Corner)) {
                selectedImage = 1;
                cloneImage(img1, img1Corner);
            } else if (isWithinImage(clickPoint, img2Corner)) {
                selectedImage = 2;
                cloneImage(img2, img2Corner);
            } else if (isWithinImage(clickPoint, img3Corner)) {
                selectedImage = 3;
                cloneImage(img3, img3Corner);
            } else if (isWithinImage(clickPoint, img4Corner)) {
                selectedImage = 4;
                cloneImage(img4, img4Corner);
            } else if (isWithinImage(clickPoint, img5Corner)) {
                selectedImage = 5;
                cloneImage(img5, img5Corner);
            } else if (isWithinImage(clickPoint, img6Corner)) {
                selectedImage = 6;
                cloneImage(img6, img6Corner);
            } else if (isWithinImage(clickPoint, img7Corner)) {
                selectedImage = 7;
                cloneImage(img7, img7Corner);
            } else {
                selectedImage = 0;
            }

            prevPoint = event.getPoint();
        }

        private boolean isWithinImage(Point clickPoint, Point imgCorner) {
            return clickPoint.x >= imgCorner.x && clickPoint.x <= imgCorner.x + IMG_WIDTH &&
                    clickPoint.y >= imgCorner.y && clickPoint.y <= imgCorner.y + IMG_HEIGHT;
        }

        private void cloneImage(ImageIcon image, Point originalPosition) {
            clones.add(new ImageIcon(image.getImage()));
            clonePositions.add(new Point(originalPosition));
        }
    }

    private class DragListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent event) {
            if (selectedImage != 0 && !clones.isEmpty()) {
                Point currentPoint = event.getPoint();
                int lastCloneIndex = clones.size() - 1;
                Point cloneCorner = clonePositions.get(lastCloneIndex);
                cloneCorner.translate(currentPoint.x - prevPoint.x, currentPoint.y - prevPoint.y);
                prevPoint = currentPoint;
                repaint();

                if (isOutOfPanelBounds(cloneCorner)) {
                    transferImageToPanel2(cloneCorner);
                    updatePanel3WithImageName();
                    clones.remove(lastCloneIndex);
                    clonePositions.remove(lastCloneIndex);
                    repaint();
                }
            }
        }

        private boolean isOutOfPanelBounds(Point imgCorner) {
            return imgCorner.x < 0 || imgCorner.x > getWidth() || imgCorner.y < 0 || imgCorner.y > getHeight();
        }

        private void transferImageToPanel2(Point point) {
            if (panel2 != null) {
                ImageIcon imageToTransfer = getSelectedImageIcon();
                Point adjustedPoint = new Point(point.x - IMG_WIDTH, point.y - IMG_HEIGHT); // Adjust point if necessary
                panel2.addImage(imageToTransfer, adjustedPoint);
            }
        }

        private ImageIcon getSelectedImageIcon() {
            switch (selectedImage) {
                case 1: return img1;
                case 2: return img2;
                case 3: return img3;
                case 4: return img4;
                case 5: return img5;
                case 6: return img6;
                case 7: return img7;
                default: return null;
            }
        }

        private void updatePanel3WithImageName() {
            if (panel3 != null) {
                String imageName = getSelectedImageName();
                panel3.addImageName(imageName);
            }
        }
    }

    private String getSelectedImageName() {
        switch (selectedImage) {
            case 1: return "Image 1";
            case 2: return "Image 2";
            case 3: return "Image 3";
            case 4: return "Image 4";
            case 5: return "Image 5";
            case 6: return "Image 6";
            case 7: return "Image 7";
            default: return "";
        }
    }
}
