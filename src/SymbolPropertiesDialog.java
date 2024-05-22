import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SymbolPropertiesDialog extends JDialog {
    private JTextField textfield;
    private Symbol symbol;

    public SymbolPropertiesDialog(Frame owner, Symbol symbol) {
        super(owner, "Symbol Properties", true);
        this.symbol = symbol;

        JPanel contentPane = new JPanel(new GridLayout(0, 2));
        contentPane.add(new JLabel("Text:"));
        textfield = new JTextField(symbol.getText());
        if (!symbol.isEditable()) {
            textfield.setEditable(false);
        }
        contentPane.add(textfield);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                symbol.setText(textfield.getText());
                dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        this.getContentPane().add(contentPane, BorderLayout.CENTER);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(owner);
    }
}
