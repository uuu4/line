import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnterenceScreenFrame extends JFrame {

    private static EnterenceScreenFrame enterenceScreenFrame;

    private EnterenceScreenFrame() {
        setTitle("github.com/uuu4 for Source Code");
        setSize(1300, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // "Welcome To Graph Plotter" label
        JLabel label = new JLabel("INTERACTIVE FLOWCHARTS");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBorder(new EmptyBorder(90, 0, 0, 0));
        Font font = label.getFont();
        label.setFont(font.deriveFont(font.getSize() + 35f));

        // "How to use" label
        JLabel label2 = new JLabel("How to use");
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setBorder(new EmptyBorder(0, 0, 70, 0));
        font = label2.getFont();
        label2.setFont(font.deriveFont(font.getSize() + 7f));

        label2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane optionPane = new JOptionPane();
                optionPane.setMessage(
                        "1. Click the 'Start' button to begin using the application.\n\n" +
                                "2. You will be presented with a set of buttons representing different actions and blocks.\n" +
                                "   - Start: Adds a starting flowchart image to the canvas.\n" +
                                "   - Process: Adds a process block where you can input code to execute.\n" +
                                "   - If Block: Adds an if block to the canvas where you can input a condition.\n" +
                                "   - Output: Adds an output block to display results.\n" +
                                "   - Else Block: Adds an else block for conditional branching.\n" +
                                "   - Loop: Adds a loop block for repeated actions.\n" +
                                "   - End: Adds an end point to the flowchart canvas.\n\n" +
                                "3. Click on any button to add the corresponding block to the canvas area.\n" +
                                "4. Drag the blocks to position them as desired.\n" +
                                "5. Click on the blocks to connect them logically (e.g., if block to process block)(P.S Changes are only visually, not changing compiler's output).\n" +
                                "6. Use the 'Run' button in the canvas area to generate and execute the code based on your flowcharts.\n" +
                                "7. Use the 'Clear' button to reset the canvas and start over.\n\n" +
                                "Note: Ensure that your code snippets and conditions are correctly formatted Java code."
                );
                optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);

                String title = "How to use";

                JDialog dialog = optionPane.createDialog(null, title);

                dialog.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label2.setForeground(Color.BLUE); // Change text color to blue on mouse hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label2.setForeground(Color.BLACK); // Change text color back to black on mouse exit
            }
        });

        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        JPanel innerPanel = new JPanel(new GridLayout(2, 1));
        JPanel innerPanel2 = new JPanel(new GridLayout(2, 3));

        innerPanel.add(label);
        innerPanel.add(label2);

        mainPanel.add(innerPanel);
        mainPanel.add(innerPanel2);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowFrame frame = new WindowFrame();
                frame.setVisible(true);
                EnterenceScreenFrame.this.setVisible(false); // Hide the entrance screen
            }
        });

        startButton.setPreferredSize(new Dimension(200, 60));
        startButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.setBorder(new EmptyBorder(130, 0, 0, 0));

        innerPanel2.add(buttonPanel);

        JLabel label3 = new JLabel("Contributors");
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setBorder(new EmptyBorder(130, 0, 0, 0));
        label3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane optionPane = new JOptionPane();
                optionPane.setMessage("Ali Emre Aydın\n\nEmir Başak Sunar\n\nSaruhan Görkem Türköz\n\nYunus Emre Boztepe\n\nAhmet Semih Turan");
                optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);

                String title = "Contributors";

                JDialog dialog = optionPane.createDialog(null, title);

                dialog.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label3.setForeground(Color.BLUE); // Change text color to blue on mouse hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label3.setForeground(Color.BLACK); // Change text color back to black on mouse exit
            }
        });

        innerPanel2.add(label3);

        getContentPane().add(mainPanel);
    }

    public static EnterenceScreenFrame getInstance() {
        if (enterenceScreenFrame == null) {
            synchronized (EnterenceScreenFrame.class) {
                if (enterenceScreenFrame == null) {
                    enterenceScreenFrame = new EnterenceScreenFrame();
                }
            }
        }
        return enterenceScreenFrame;
    }
}
