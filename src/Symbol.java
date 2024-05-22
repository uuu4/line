import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Symbol {
    public enum SymbolType {
        START, END, PROCESS, DECISION, INPUT_OUTPUT
    }

    private ImageIcon image;
    private Point position;
    private String text;
    private SymbolType type;
    private boolean isEditable;
    private List<Symbol> connections = new ArrayList<>();

    public Symbol(ImageIcon image, Point position, String text, SymbolType type, boolean isEditable) {
        this.image = image;
        this.position = position;
        this.text = text;
        this.type = type;
        this.isEditable = isEditable;
    }

    public void draw(Graphics g) {
        image.paintIcon(null, g, position.x, position.y);
        FontMetrics metrics = g.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();
        int x = position.x + (image.getIconWidth() - textWidth) / 2;
        int y = position.y + ((image.getIconHeight() - textHeight) / 2) + metrics.getAscent();
        g.drawString(text, x, y);
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SymbolType getType() {
        return type;
    }

    public void setType(SymbolType type) {
        this.type = type;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    public void addConnection(Symbol symbol) {
        connections.add(symbol);
    }

    public void removeConnection(Symbol symbol) {
        connections.remove(symbol);
    }

    public List<Symbol> getConnections() {
        return connections;
    }
}
