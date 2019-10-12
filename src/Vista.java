import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Vista extends Canvas implements IVista, PropertyChangeListener {
	
	private Tablero tablero;
	private Graphics2D g2d;
	
	public Vista(Tablero tablero) {
		this.tablero = tablero;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g2d = (Graphics2D)g;
		for(int i = 0; i < tablero.getTamanio(); i++) {
			for(int j = 0; j < tablero.getTamanio(); j++) {
				if(tablero.getPosicion(i, j) == 0) {
					g2d.setColor(Colores.AGUA.getColor());
				} else if(tablero.getPosicion(i, j) <= 100 && tablero.getPosicion(i, j) > 0) {
					g2d.setColor(Colores.CUBIERTO.getColor());
				} else if(tablero.getPosicion(i, j) < 0) {
					if(tablero.getPosicion(i, j) == -100) {
						g2d.setColor(Colores.HUNDIDO.getColor());
					} else {
						g2d.setColor(Colores.TOCADO.getColor());
					}
				}
				g2d.fillRect(j*LADO, i*LADO, LADO-MARGEN, LADO-MARGEN);
			}
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.tablero = (Tablero)evt.getNewValue();
		repaint();
	}

}
