import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class Panel1 extends JPanel {
    List<ImageIcon> images = new ArrayList<>();
    List<Point> imagePositions = new ArrayList<>();

    final int IMG_WIDTH;
    final int IMG_HEIGHT;

    Point prevPoint;
    int selectedImage = -1; // -1: None, 0: img1, 1: img2, etc.

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
        // Add images to the list
        images.add(new ImageIcon("C:\\Users\\Emir Başak Sunar\\Documents\\GitHub\\interactive-flowcharts\\1.png"));
        images.add(new ImageIcon("C:\\Users\\Emir Başak Sunar\\Documents\\GitHub\\interactive-flowcharts\\2.png"));
        images.add(new ImageIcon("C:\\Users\\Emir Başak Sunar\\Documents\\GitHub\\interactive-flowcharts\\3.png"));
        images.add(new ImageIcon("C:\\Users\\Emir Başak Sunar\\Documents\\GitHub\\interactive-flowcharts\\4.png"));
        images.add(new ImageIcon("C:\\Users\\Emir Başak Sunar\\Documents\\GitHub\\interactive-flowcharts\\5.png"));
        images.add(new ImageIcon("C:\\Users\\Emir Başak Sunar\\Documents\\GitHub\\interactive-flowcharts\\6.png"));
        images.add(new ImageIcon("C:\\Users\\Emir Başak Sunar\\Documents\\GitHub\\interactive-flowcharts\\7.png"));

        // Add initial positions for the images
        imagePositions.add(new Point(50, 50));
        imagePositions.add(new Point(150, 50));
        imagePositions.add(new Point(50, 150));
        imagePositions.add(new Point(150, 150));
        imagePositions.add(new Point(50, 250));
        imagePositions.add(new Point(150, 250));
        imagePositions.add(new Point(150, 350));

        // Initialize width and height based on the first image
        IMG_WIDTH = images.get(0).getIconWidth();
        IMG_HEIGHT = images.get(0).getIconHeight();

        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);

        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < images.size(); i++) {
            if (images.get(i) != null) {
                images.get(i).paintIcon(this, g, (int) imagePositions.get(i).getX(), (int) imagePositions.get(i).getY());
            }
        }
        for (int i = 0; i < clones.size(); i++) {
            clones.get(i).paintIcon(this, g, (int) clonePositions.get(i).getX(), (int) clonePositions.get(i).getY());
        }
    }

    private class ClickListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            Point clickPoint = event.getPoint();
            selectedImage = -1;

            for (int i = 0; i < imagePositions.size(); i++) {
                if (isWithinImage(clickPoint, imagePositions.get(i))) {
                    selectedImage = i;
                    cloneImage(images.get(i), imagePositions.get(i));
                    break;
                }
            }
            prevPoint = event.getPoint();
        }

        private boolean isWithinImage(Point clickPoint, Point imgCorner) {
            return clickPoint.getX() >= imgCorner.getX() && clickPoint.getX() <= imgCorner.getX() + IMG_WIDTH &&
                    clickPoint.getY() >= imgCorner.getY() && clickPoint.getY() <= imgCorner.getY() + IMG_HEIGHT;
        }

        private void cloneImage(ImageIcon image, Point originalPosition) {
            clones.add(new ImageIcon(image.getImage()));
            clonePositions.add(new Point(originalPosition));
        }
    }

    private class DragListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent event) {
            if (selectedImage != -1) {
                Point currentPoint = event.getPoint();
                Point imgCorner = imagePositions.get(selectedImage);
                imgCorner.translate((int) (currentPoint.getX() - prevPoint.getX()),
                        (int) (currentPoint.getY() - prevPoint.getY()));
                prevPoint = currentPoint;
                repaint();

                if (isOutOfPanelBounds(imgCorner)) {
                    transferImageToPanel2(imgCorner);
                    updatePanel3WithImageName();
                    removeSelectedImage();
                    repaint();
                }
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

        private void updatePanel3WithImageName() {
            if (panel3 != null) {
                String imageName = getSelectedImageName();
                panel3.addImageName(imageName);
            }
        }
    }

    public ImageIcon cloneSelectedImage() {
        if (selectedImage >= 0 && selectedImage < images.size()) {
            return new ImageIcon(images.get(selectedImage).getImage());
        }
        return null;
    }

    public void removeSelectedImage() {
        if (selectedImage >= 0 && selectedImage < images.size()) {
            images.set(selectedImage, null);
            selectedImage = -1;
        }
    }

    private String getSelectedImageName() {
        switch (selectedImage) {
            case 0: return "public class Main {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        \n";
            case 1: return "Image 2";
            case 2: return "Image 3";
            case 3: return "Image 4";
            case 4: return "Image 5";
            case 5: return "Image 6";
            case 6: return "    }\n" +
                    "}\n";
            default: return "Unknown Image";
        }
    }
}
