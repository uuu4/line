import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Panel3 extends JPanel {
    private List<String> imageNames = new ArrayList<>();
    private List<JTextField> inputs = new ArrayList<>();
    private List<List<Integer>> connections = new ArrayList<>();
    private JTextArea codeArea;

    Panel3() {
        this.setLayout(new BorderLayout());
        codeArea = new JTextArea(20, 50);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(codeArea);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void addImageName(String imageName) {
        imageNames.add(imageName);
        revalidate();
        repaint();
    }

    public void addInputs(List<JTextField> inputs) {
        this.inputs.addAll(inputs);
    }

    public void addConnections(List<List<Integer>> connections) {
        this.connections.addAll(connections);
    }

    public void updateCode() {
        codeArea.setText(generateJavaCode());
    }

    public String generateJavaCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("public class GeneratedCode {\n");
        sb.append("    public static void main(String[] args) {\n");

        appendNestedCode(sb, -1, "    ", new ArrayList<>());

        sb.append("    }\n");
        sb.append("}\n");
        return sb.toString();
    }

    private void appendNestedCode(StringBuilder sb, int parentIndex, String indent, List<Integer> visited) {
        for (int i = 0; i < imageNames.size(); i++) {
            if ((parentIndex == -1 || isConnected(parentIndex, i)) && !visited.contains(i)) {
                visited.add(i);
                String imageName = imageNames.get(i);
                String code = getCodeForShape(imageName, i, indent);
                sb.append(code);
                if (imageName.equals("IfBlock") || imageName.equals("ElseBlock") || imageName.equals("Loop")) {
                    sb.append(indent).append("{\n");
                    appendNestedCode(sb, i, indent + "    ", visited);
                    sb.append(indent).append("}\n");
                }
                appendNestedCode(sb, i, indent, visited);
            }
        }
    }

    private boolean isConnected(int parentIndex, int childIndex) {
        for (List<Integer> connection : connections) {
            if (connection.get(0) == parentIndex && connection.get(1) == childIndex) {
                return true;
            }
        }
        return false;
    }

    private String getCodeForShape(String shapeName, int index, String indent) {
        StringBuilder code = new StringBuilder();
        switch (shapeName) {
            case "Start":
                code.append(indent).append("// Start\n");
                code.append(indent).append("System.out.println(\"Start\");\n");
                break;
            case "Process":
                code.append(indent).append("// Process\n");
                code.append(indent).append(inputs.get(index).getText()).append(";\n");
                break;
            case "IfBlock":
                code.append(indent).append("// IfBlock\n");
                code.append(indent).append("if (").append(inputs.get(index).getText()).append(") ");
                break;
            case "ElseBlock":
                code.append(indent).append("// ElseBlock\n");
                code.append(indent).append("else ");
                break;
            case "Output":
                code.append(indent).append("// Output\n");
                code.append(indent).append("System.out.println(").append(inputs.get(index).getText()).append(");\n");
                break;
            case "Loop":
                code.append(indent).append("// Loop\n");
                code.append(indent).append("while (").append(inputs.get(index).getText()).append(") ");
                break;
            case "End":
                code.append(indent).append("// End\n");
                code.append(indent).append("System.out.println(\"End\");\n");
                break;
            default:
                code.append(indent).append("// Unknown shape\n");
                break;
        }
        return code.toString();
    }

    public void clearPanel() {
        imageNames.clear();
        inputs.clear();
        connections.clear();
        codeArea.setText("");
    }
}
