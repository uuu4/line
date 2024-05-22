import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Panel2 extends JPanel {
    private List<Symbol> symbols = new ArrayList<>();
    private List<Connection> connections = new ArrayList<>();
    private Symbol selectedSymbol = null;
    private Point initialClickPoint;
    private boolean isDraggingConnection = false;
    private Symbol connectionStartSymbol;
    private Panel3 panel3;
    private Point connectionEndPoint;
    public Panel2(Panel3 panel3) {
        this.panel3 = panel3;
        this.addMouseListener(new ClickListener());
        this.addMouseMotionListener(new DragListener());
        this.setDropTarget(new DropTarget(this, DnDConstants.ACTION_COPY, new DropTargetListener() {
            @Override
            public void dragEnter(DropTargetDragEvent dropTargetDragEvent) {

            }

            @Override
            public void dragOver(DropTargetDragEvent dropTargetDragEvent) {

            }

            @Override
            public void dropActionChanged(DropTargetDragEvent dropTargetDragEvent) {

            }

            @Override
            public void dragExit(DropTargetEvent dropTargetEvent) {

            }

            public void drop(DropTargetDropEvent dtde) {
                try {
                    Transferable tr = dtde.getTransferable();
                    if (tr.isDataFlavorSupported(new DataFlavor(Symbol.class, "Symbol"))) {
                        Symbol symbol = (Symbol) tr.getTransferData(new DataFlavor(Symbol.class, "Symbol"));
                        Point dropPoint = dtde.getLocation();
                        symbol.setPosition(dropPoint);
                        addSymbol(symbol);
                        dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    } else {
                        dtde.rejectDrop();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // ... (Other DropTargetListener methods - empty for now)
        }));
        JButton runButton = new JButton("Run");
        runButton.setBounds(10, 10, 80, 30);
        this.add(runButton);
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String generatedCode = generateJavaCode();
                panel3.displaySymbolActions(symbols);
            }
        });
        this.setLayout(null);
    }
    private Point getSymbolCenter(Symbol symbol) {
        return new Point(symbol.getPosition().x + symbol.getImage().getIconWidth() / 2,
                symbol.getPosition().y + symbol.getImage().getIconHeight() / 2);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Connection connection : connections) {
            connection.draw(g);
        }
        for (Symbol symbol : symbols) {
            symbol.draw(g);
        }
        if (isDraggingConnection && connectionEndPoint != null) {
            Point startPoint = getSymbolCenter(connectionStartSymbol);
            g.drawLine(startPoint.x, startPoint.y, connectionEndPoint.x, connectionEndPoint.y);
        }
    }

    public void addSymbol(Symbol symbol) {
        symbols.add(symbol);
        repaint();
    }

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            initialClickPoint = e.getPoint();
            isDraggingConnection = false;
            connectionStartSymbol = null;
            for (Symbol symbol : symbols) {
                if (isWithinImage(initialClickPoint, symbol.getPosition())) {
                    selectedSymbol = symbol;
                    if (e.getClickCount() == 2) {
                        SymbolPropertiesDialog dialog = new SymbolPropertiesDialog((Frame) SwingUtilities.getWindowAncestor(Panel2.this), selectedSymbol);
                        dialog.setVisible(true);
                    }
                    return;
                }
            }
            isDraggingConnection = true;
            connectionStartSymbol = getSymbolAt(initialClickPoint);
        }

        public void mouseReleased(MouseEvent e) {
            if (isDraggingConnection) {
                Symbol endSymbol = getSymbolAt(e.getPoint());
                if (endSymbol != null && endSymbol != connectionStartSymbol) {
                    connections.add(new Connection(connectionStartSymbol, endSymbol));
                    connectionStartSymbol.addConnection(endSymbol);
                }
                isDraggingConnection = false;
                repaint();
            } else {
                selectedSymbol = null;
            }
        }

        private Symbol getSymbolAt(Point point) {
            for (Symbol s : symbols) {
                if (isWithinImage(point, s.getPosition())) {
                    return s;
                }
            }
            return null;
        }
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            if (selectedSymbol != null) {
                // ... (Sembol sürükleme mantığı)
            } else if (isDraggingConnection) {
                connectionEndPoint = e.getPoint();
                for (Symbol symbol : symbols) {
                    if (symbol != connectionStartSymbol && isWithinImage(connectionEndPoint, symbol.getPosition())) {
                        connectionEndPoint = getSnapPoint(symbol, connectionEndPoint);
                        break;
                    }
                }
                repaint();
            }
        }
    }

    private String generateJavaCode() {
        StringBuilder code = new StringBuilder();

        // Start sembolünü bul
        Symbol startSymbol = symbols.stream()
                .filter(s -> s.getType() == Symbol.SymbolType.START)
                .findFirst()
                .orElse(null);

        if (startSymbol == null) {
            return "Error: Start sembolü bulunamadı!";
        }

        // Kod oluşturma işlemini başlat
        generateCodeFromSymbol(startSymbol, code, new HashSet<>());

        return code.toString();
    }

    private void generateCodeFromSymbol(Symbol symbol, StringBuilder code, Set<Symbol> visited) {
        if (visited.contains(symbol)) {
            return; // Sonsuz döngüleri önle
        }
        visited.add(symbol);

        switch (symbol.getType()) {
            case START:
                code.append("public static void main(String[] args) {\n");
                break;
            case END:
                code.append("}\n");
                break;
            case PROCESS:
                code.append("\t// İşlem: ").append(symbol.getText()).append("\n");
                // TODO: İşlem sembolü için özel kod oluşturma mantığı buraya eklenecek.
                break;
            case DECISION:
                code.append("\tif (").append(symbol.getText()).append(") {\n");
                if (!symbol.getConnections().isEmpty()) {
                    generateCodeFromSymbol(symbol.getConnections().get(0), code, visited); // True dalı
                }
                code.append("\t} else {\n");
                if (symbol.getConnections().size() > 1) {
                    generateCodeFromSymbol(symbol.getConnections().get(1), code, visited); // False dalı
                }
                code.append("\t}\n");
                break;
            case INPUT_OUTPUT:
                code.append("\t// Giriş/Çıkış: ").append(symbol.getText()).append("\n");
                // TODO: Giriş/çıkış sembolü için özel kod oluşturma mantığı buraya eklenecek.
                break;
        }

        // Bağlı semboller için recursive çağrı
        for (Symbol nextSymbol : symbol.getConnections()) {
            generateCodeFromSymbol(nextSymbol, code, visited);
        }
    }


    private Panel3 getPanel3() {
        Container parent = this.getParent();
        if (parent instanceof JLayeredPane) {
            parent = parent.getParent();
        }
        if (parent instanceof WindowFrame) {
            return ((WindowFrame) parent).getPanel3();
        }
        return null;
    }
    private Point getSnapPoint(Symbol symbol, Point mousePosition) {
        Rectangle bounds = new Rectangle(symbol.getPosition(),
                new Dimension(symbol.getImage().getIconWidth(), symbol.getImage().getIconHeight()));
        double topDist = mousePosition.y - bounds.y;
        double bottomDist = bounds.y + bounds.height - mousePosition.y;
        double leftDist = mousePosition.x - bounds.x;
        double rightDist = bounds.x + bounds.width - mousePosition.x;
        double minDist = Math.min(Math.min(topDist, bottomDist), Math.min(leftDist, rightDist));
        if (minDist == topDist) {
            return new Point(mousePosition.x, bounds.y);
        } else if (minDist == bottomDist) {
            return new Point(mousePosition.x, bounds.y + bounds.height);
        } else if (minDist == leftDist) {
            return new Point(bounds.x, mousePosition.y);
        } else {
            return new Point(bounds.x + bounds.width, mousePosition.y);
        }
    }

    private boolean isWithinImage(Point point, Point imgCorner) {
        ImageIcon image = selectedSymbol.getImage();
        return point.getX() >= imgCorner.getX() && point.getX() <= imgCorner.getX() + image.getIconWidth() &&
                point.getY() >= imgCorner.getY() && point.getY() <= imgCorner.getY() + image.getIconHeight();
    }

}
