import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Panel3 extends JPanel {
    private List<String> imageNames = new ArrayList<>();
    private List<JTextField> inputs = new ArrayList<>();
    private List<List<Integer>> connections = new ArrayList<>();

    Panel3() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addImageName(String imageName) {
        JLabel imageNameLabel = new JLabel(imageName);
        this.add(imageNameLabel);
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(generateJavaCode(), 10, 20);
    }

    public String generateJavaCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("public class GeneratedCode {\n");
        sb.append("    public static void main(String[] args) {\n");

        for (int i = 0; i < imageNames.size(); i++) {
            String imageName = imageNames.get(i);
            String code = getCodeForShape(imageName, i);
            sb.append(code);
        }

        sb.append("    }\n");
        sb.append("}\n");
        return sb.toString();
    }

    private String getCodeForShape(String shapeName, int index) {
        StringBuilder code = new StringBuilder();
        switch (shapeName) {
            case "Start":
                code.append("        // Start\n");
                code.append("        System.out.println(\"Start\");\n");
                break;
            case "Process":
                code.append("        // Process\n");
                code.append("        ");
                code.append(inputs.get(index).getText()).append("\n");
                break;
            case "IfBlock":
                code.append("        // IfBlock\n");
                code.append("        if (").append(inputs.get(index).getText()).append(") {\n");
                appendNestedCode(code, index, "        ");
                code.append("        }\n");
                break;
            case "ElseBlock":
                code.append("        // ElseBlock\n");
                code.append("        else {\n");
                appendNestedCode(code, index, "        ");
                code.append("        }\n");
                break;
            case "Output":
                code.append("        // Output\n");
                code.append("        System.out.println(").append(inputs.get(index).getText()).append(");\n");
                break;
            case "Loop":
                code.append("        // Loop\n");
                code.append("        while (").append(inputs.get(index).getText()).append(") {\n");
                appendNestedCode(code, index, "        ");
                code.append("        }\n");
                break;
            case "End":
                code.append("        // End\n");
                code.append("        System.out.println(\"End\");\n");
                break;
            default:
                code.append("        // Unknown shape\n");
                break;
        }
        return code.toString();
    }

    private void appendNestedCode(StringBuilder code, int parentIndex, String indent) {
        for (List<Integer> connection : connections) {
            if (connection.get(0) == parentIndex) {
                int childIndex = connection.get(1);
                String shapeName = imageNames.get(childIndex);
                String nestedCode = getCodeForShape(shapeName, childIndex).replace("        ", indent + "    ");
                code.append(nestedCode);
            }
        }
    }
}
