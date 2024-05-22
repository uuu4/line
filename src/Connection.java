import java.awt.*;

public class Connection {
    private Symbol startSymbol;
    private Symbol endSymbol;

    public Connection(Symbol startSymbol, Symbol endSymbol) {
        this.startSymbol = startSymbol;
        this.endSymbol = endSymbol;
    }

    // Getter metotları
    public Symbol getStartSymbol() {
        return startSymbol;
    }

    public Symbol getEndSymbol() {
        return endSymbol;
    }

    // Çizim metodu (Panel2 içerisinde kullanılacak)
    public void draw(Graphics g) {
        Point startPoint = getSymbolCenter(startSymbol);
        Point endPoint = getSymbolCenter(endSymbol);
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }

    // Sembol merkezini hesaplama metodu
    private Point getSymbolCenter(Symbol symbol) {
        return new Point(symbol.getPosition().x + symbol.getImage().getIconWidth() / 2,
                symbol.getPosition().y + symbol.getImage().getIconHeight() / 2);
    }
}
