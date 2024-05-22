import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Panel1 extends JPanel {
    private List<Symbol> symbols = new ArrayList<>();
    private Panel2 panel2;
    private final int IMG_WIDTH = 100;
    private final int IMG_HEIGHT = 100;

    public Panel1() {
        ImageIcon startIcon = new ImageIcon("1.png");
        ImageIcon processIcon = new ImageIcon("2.png");
        ImageIcon decisionIcon = new ImageIcon("3.png");
        ImageIcon ioIcon = new ImageIcon("4.png");

        symbols.add(new Symbol(startIcon, new Point(50, 50), "Start", Symbol.SymbolType.START, false));
        symbols.add(new Symbol(processIcon, new Point(50, 170), "Process", Symbol.SymbolType.PROCESS, true));
        symbols.add(new Symbol(decisionIcon, new Point(50, 290), "Decision", Symbol.SymbolType.DECISION, true));
        symbols.add(new Symbol(ioIcon, new Point(50, 410), "I/O", Symbol.SymbolType.INPUT_OUTPUT, true));

        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);

        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);

        this.setTransferHandler(new TransferHandler() {
            @Override
            public int getSourceActions(JComponent c) {
                return DnDConstants.ACTION_COPY;
            }

            @Override
            protected Transferable createTransferable(JComponent c) {
                Point mousePosition = clickListener.getPressedPoint();
                for (Symbol symbol : symbols) {
                    if (isWithinImage(mousePosition, symbol.getPosition())) {
                        return new SymbolTransferable(symbol);
                    }
                }
                return null;
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Symbol symbol : symbols) {
            symbol.draw(g);
        }
    }

    private class ClickListener extends MouseAdapter {
        private Point pressedPoint;

        @Override
        public void mousePressed(MouseEvent e) {
            pressedPoint = e.getPoint();
            transferImageToPanel2(pressedPoint);
        }

        public Point getPressedPoint() {
            return pressedPoint;
        }
    }

    private class DragListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
    }

    private void transferImageToPanel2(Point mousePosition) {
        if (panel2 != null) {
            for (Symbol symbol : symbols) {
                if (isWithinImage(mousePosition, symbol.getPosition())) {
                    Point pointInPanel2 = SwingUtilities.convertPoint(Panel1.this, mousePosition, panel2);
                    panel2.addSymbol(new Symbol(symbol.getImage(), pointInPanel2, symbol.getText(), symbol.getType(), symbol.isEditable()));
                    break;
                }
            }
        }
    }

    private boolean isWithinImage(Point point, Point imgCorner) {
        return point.getX() >= imgCorner.getX() && point.getX() <= imgCorner.getX() + IMG_WIDTH &&
                point.getY() >= imgCorner.getY() && point.getY() <= imgCorner.getY() + IMG_HEIGHT;
    }

    public void setPanel2(Panel2 panel2) {
        this.panel2 = panel2;
    }
}

class SymbolTransferable implements Transferable {
    private final Symbol symbol;

    public SymbolTransferable(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] { new DataFlavor(Symbol.class, "Symbol") };
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(new DataFlavor(Symbol.class, "Symbol"));
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(new DataFlavor(Symbol.class, "Symbol"))) {
            return symbol;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
