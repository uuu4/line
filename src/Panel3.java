import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.List;
import javax.tools.*;

public class Panel3 extends JPanel implements Metod{
    private List<String> imageNames = new ArrayList<>();
    private List<JTextField> inputs = new ArrayList<>();
    private List<List<Integer>> connections = new ArrayList<>();
    private JTextArea codeArea;
    private JTextArea outputArea;

    Panel3() {
        this.setLayout(new GridLayout(1, 2));

        // Code area setup
        codeArea = new JTextArea(20, 50);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setEditable(false);
        JScrollPane codeScrollPane = new JScrollPane(codeArea);
        codeScrollPane.setBorder(BorderFactory.createTitledBorder("Generated Code"));
        this.add(codeScrollPane);

        // Output area setup
        outputArea = new JTextArea(20, 50);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        outputScrollPane.setBorder(BorderFactory.createTitledBorder("Output"));
        this.add(outputScrollPane);
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

    @Override
    public void addImage(ImageIcon image, Point point, boolean addTextField) {

    }

    public void clearPanel() {
        imageNames.clear();
        inputs.clear();
        connections.clear();
        codeArea.setText("");
        outputArea.setText("");
    }

    public void runCode() {
        String code = generateJavaCode();
        String result = compileAndRun(code);
        outputArea.setText(result);
    }

    private String compileAndRun(String code) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            return "No Java compiler available. Make sure you are using a JDK, not a JRE.";
        }

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        File sourceFile = null;
        StringWriter compilationErrors = new StringWriter();

        try {
            // Ensure the file name matches the class name
            sourceFile = new File(System.getProperty("java.io.tmpdir"), "GeneratedCode.java");
            try (FileWriter writer = new FileWriter(sourceFile)) {
                writer.write(code);
            }

            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(sourceFile);
            JavaCompiler.CompilationTask task = compiler.getTask(compilationErrors, fileManager, null, null, null, compilationUnits);

            if (!task.call()) {
                return "Compilation failed:\n" + compilationErrors.toString();
            }

            // Load and run the compiled class
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{sourceFile.getParentFile().toURI().toURL()});
            Class<?> cls = Class.forName("GeneratedCode", true, classLoader);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream oldOut = System.out;
            System.setOut(new PrintStream(baos));
            try {
                cls.getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[]{});
            } finally {
                System.setOut(oldOut);
            }
            return baos.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        } finally {
            if (sourceFile != null && sourceFile.exists()) {
                sourceFile.delete();
            }
        }
    }
}
