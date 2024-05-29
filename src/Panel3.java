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

        appendNestedCode(sb, -1, "    ");

        sb.append("    }\n");
        sb.append("}\n");
        return sb.toString();
    }

    private void appendNestedCode(StringBuilder sb, int parentIndex, String indent) {
        for (int i = 0; i < imageNames.size(); i++) {
            if (parentIndex == -1 || isConnected(parentIndex, i)) {
                String imageName = imageNames.get(i);
                String code = getCodeForShape(imageName, i, indent);
                sb.append(code);
                if (imageName.equals("IfBlock") || imageName.equals("ElseBlock") || imageName.equals("Loop")) {
                    int nestedIndex = getFirstConnectedIndex(i);
                    if (nestedIndex != -1) {
                        String nestedCode = getCodeForShape(imageNames.get(nestedIndex), nestedIndex, indent + "    ");
                        sb.append(nestedCode);
                    }
                    sb.append(indent).append("}\n");  // Close the block
                }
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

    private int getFirstConnectedIndex(int parentIndex) {
        for (List<Integer> connection : connections) {
            if (connection.get(0) == parentIndex) {
                return connection.get(1);
            }
        }
        return -1;
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
                code.append(indent).append("if (").append(inputs.get(index).getText()).append(") {\n");
                break;
            case "ElseBlock":
                code.append(indent).append("// ElseBlock\n");
                code.append(indent).append("else {\n");
                break;
            case "Output":
                code.append(indent).append("// Output\n");
                code.append(indent).append("System.out.println(").append(inputs.get(index).getText()).append(");\n");
                break;
            case "Loop":
                code.append(indent).append("// Loop\n");
                code.append(indent).append("while (").append(inputs.get(index).getText()).append(") {\n");
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
}
