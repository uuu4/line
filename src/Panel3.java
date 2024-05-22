import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Panel3 extends JPanel {
    private JTextArea codeTextArea;

    public Panel3() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        codeTextArea = new JTextArea();
        codeTextArea.setEditable(false);
        codeTextArea.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(codeTextArea);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void displaySymbolActions(List<Symbol> symbols) {
        StringBuilder output = new StringBuilder();
        for (Symbol symbol : symbols) {
            output.append(getSymbolDescription(symbol)).append("\n");
        }
        codeTextArea.setText(output.toString());
        revalidate();
        repaint();
    }

    private String getSymbolDescription(Symbol symbol) {
        switch (symbol.getType()) {
            case START:
                return "Start";
            case END:
                return "End";
            case PROCESS:
                return symbol.getText() + " (Process)";
            case DECISION:
                return symbol.getText() + " (Decision)";
            case INPUT_OUTPUT:
                return symbol.getText() + " (Input/Output)";
            default:
                return "Unknown Symbol";
        }
    }
}
