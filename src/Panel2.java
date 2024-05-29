import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Panel2 extends JPanel implements Metod{
    List<ImageIcon> images = new ArrayList<>();
    List<Point> imageCorners = new ArrayList<>();
    List<JTextField> inputs = new ArrayList<>();
    List<String> imageNames = new ArrayList<>();
    List<List<Integer>> connections = new ArrayList<>();

    private Panel3 panel3;

    final int IMG_WIDTH = 100;
    final int IMG_HEIGHT = 100;

    ImageIcon img1 = new ImageIcon(getClass().getResource("/images/1.png")); // Use getResource to load image from classpath
    ImageIcon img2 = new ImageIcon(getClass().getResource("/images/2.png"));
    ImageIcon img3 = new ImageIcon(getClass().getResource("/images/3.png"));
    ImageIcon img4 = new ImageIcon(getClass().getResource("/images/4.png"));
    ImageIcon img5 = new ImageIcon(getClass().getResource("/images/5.png"));
    ImageIcon img6 = new ImageIcon(getClass().getResource("/images/6.png"));
    ImageIcon img7 = new ImageIcon(getClass().getResource("/images/7.png"));

    Point prevPoint;
    int selectedImageIndex = -1;
    int lastSelectedIndex = -1;

    Panel2() {
        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);

        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);

        JButton runButton = new JButton("Run");
        runButton.setBounds(10, 10, 80, 30);
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (panel3 != null) {
                    panel3.addInputs(getInputs());
                    panel3.addConnections(getConnections());
                    panel3.updateCode();
                    panel3.runCode();
                }
            }
        });
        this.setLayout(null);
        this.add(runButton);

        // Clear button
        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(100, 10, 80, 30);  // Adjust the position and size as needed
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearPanel();
                if (panel3 != null) {
                    panel3.clearPanel();
                }
            }
        });
        this.add(clearButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
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
    }



    private class ClickListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            Point clickPoint = event.getPoint();
            selectedImageIndex = getSelectedImageIndex(clickPoint);
            if (selectedImageIndex != -1 && lastSelectedIndex != -1 && lastSelectedIndex != selectedImageIndex) {
                connections.add(List.of(lastSelectedIndex, selectedImageIndex));
                repaint();
            }
            lastSelectedIndex = selectedImageIndex;
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



    @Override
    public void addImage(ImageIcon image, Point point, boolean addTextField) {
        images.add(image);
        imageCorners.add(point);

        if (addTextField) {
            JTextField input = new JTextField(10);
            input.setSize(IMG_WIDTH, 20);
            inputs.add(input);
            add(input);
        } else {
            inputs.add(null);
        }

        imageNames.add(image.getDescription()); // Add the image name to the list
        repaint();
    }

    public List<JTextField> getInputs() {
        return inputs;
    }

    public List<String> getImageNames() {
        return imageNames;
    }

    public List<List<Integer>> getConnections() {
        return connections;
    }

    public void setPanel3(Panel3 panel3) {
        this.panel3 = panel3;
    }

    public void clearPanel() {
        images.clear();
        imageCorners.clear();
        for (JTextField input : inputs) {
            if (input != null) {
                this.remove(input);
            }
        }
        inputs.clear();
        imageNames.clear();
        connections.clear();
        selectedImageIndex = -1;
        lastSelectedIndex = -1;
        repaint();
    }

    @Override
    public void runCode() {

    }
}
