import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controlador extends MouseAdapter {
	
	private Barquitos barquitos;
	
	public Controlador(Barquitos barquitos) {
		this.barquitos = barquitos;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		int fila = e.getY()/(Vista.LADO);
		int columna = e.getX()/(Vista.LADO);
		barquitos.disparo(fila, columna);
	}
}
