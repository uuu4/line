import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Panel2 extends JPanel {
<<<<<<< Updated upstream
    ImageIcon img1, img2, img3, img4, img5, img6, img7;
=======
    List<ImageIcon> images = new ArrayList<>();
    List<Point> imageCorners = new ArrayList<>();
    List<JTextField> inputs = new ArrayList<>();
    List<String> imageNames = new ArrayList<>();
    List<List<Integer>> connections = new ArrayList<>();
>>>>>>> Stashed changes

    final int IMG_WIDTH = 100; // Assuming fixed width for simplicity
    final int IMG_HEIGHT = 100; // Assuming fixed height for simplicity

    Point img1Corner, img2Corner, img3Corner, img4Corner, img5Corner, img6Corner, img7Corner;

    ImageIcon img1 = new ImageIcon("C:\\Users\\Emir Başak Sunar\\Documents\\GitHub\\interactive-flowcharts\\1.png"); // replace with actual path
    ImageIcon img2 = new ImageIcon("C:\\Users\\Emir Başak Sunar\\Documents\\GitHub\\interactive-flowcharts\\2.png"); // replace with actual path
    ImageIcon img3 = new ImageIcon("C:\\Users\\Emir Başak Sunar\\Documents\\GitHub\\interactive-flowcharts\\3.png"); // replace with actual path
    ImageIcon img4 = new ImageIcon("C:\\Users\\Emir Başak Sunar\\Documents\\GitHub\\interactive-flowcharts\\4.png"); // replace with actual path
    ImageIcon img5 = new ImageIcon("C:\\Users\\Emir Başak Sunar\\Documents\\GitHub\\interactive-flowcharts\\5.png"); // replace with actual path
    ImageIcon img6 = new ImageIcon("C:\\Users\\Emir Başak Sunar\\Documents\\GitHub\\interactive-flowcharts\\6.png"); // replace with actual path
    ImageIcon img7 = new ImageIcon("C:\\Users\\Emir Başak Sunar\\Documents\\GitHub\\interactive-flowcharts\\7.png"); // replace with actual path

    Point prevPoint;
<<<<<<< Updated upstream

    int selectedImage = 0; // 0: None, 1: img1, 2: img2, etc.
=======
    int selectedImageIndex = -1;
    int lastSelectedIndex = -1;
>>>>>>> Stashed changes

    Panel2() {
        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);
     //   setLayout(null);
        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
<<<<<<< Updated upstream
        if (img1 != null) img1.paintIcon(this, g, (int) img1Corner.getX(), (int) img1Corner.getY());
        if (img2 != null) img2.paintIcon(this, g, (int) img2Corner.getX(), (int) img2Corner.getY());
        if (img3 != null) img3.paintIcon(this, g, (int) img3Corner.getX(), (int) img3Corner.getY());
        if (img4 != null) img4.paintIcon(this, g, (int) img4Corner.getX(), (int) img4Corner.getY());
        if (img5 != null) img5.paintIcon(this, g, (int) img5Corner.getX(), (int) img5Corner.getY());
        if (img6 != null) img6.paintIcon(this, g, (int) img6Corner.getX(), (int) img6Corner.getY());
        if (img7 != null) img7.paintIcon(this, g, (int) img7Corner.getX(), (int) img7Corner.getY());
=======
        // Draw images
        for (int i = 0; i < images.size(); i++) {
            ImageIcon image = images.get(i);
            Point corner = imageCorners.get(i);
            image.paintIcon(this, g, corner.x, corner.y);
            JTextField input = inputs.get(i);
            if (input != null) {
                input.setLocation(corner.x + IMG_WIDTH / 2 - input.getWidth() / 2, corner.y + IMG_HEIGHT + 5);
            }
        }
        // Draw lines between images
        g.setColor(Color.BLACK);
        for (List<Integer> connection : connections) {
            Point p1 = imageCorners.get(connection.get(0));
            Point p2 = imageCorners.get(connection.get(1));
            g.drawLine(p1.x + IMG_WIDTH / 2, p1.y + IMG_HEIGHT / 2, p2.x + IMG_WIDTH / 2, p2.y + IMG_HEIGHT / 2);
        }
>>>>>>> Stashed changes
    }

    private class ClickListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            Point clickPoint = event.getPoint();
<<<<<<< Updated upstream

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

=======
            selectedImageIndex = getSelectedImageIndex(clickPoint);
            if (selectedImageIndex != -1 && lastSelectedIndex != -1 && lastSelectedIndex != selectedImageIndex) {
                connections.add(List.of(lastSelectedIndex, selectedImageIndex));
                repaint();
            }
            lastSelectedIndex = selectedImageIndex;
>>>>>>> Stashed changes
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
    private int nextY = 0;
    private int nextX = 0;
    public void addImage(ImageIcon image, Point location) {
        JLabel label = new JLabel(image);
        label.setBounds(location.x, location.y, image.getIconWidth(), image.getIconHeight());
        add(label);
        revalidate();
        repaint();
    }

<<<<<<< Updated upstream
    }


=======
    public List<JTextField> getInputs() {
        return inputs;
    }

    public List<String> getImageNames() {
        return imageNames;
    }

    public List<List<Integer>> getConnections() {
        return connections;
    }
}
>>>>>>> Stashed changes
