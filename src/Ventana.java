import java.awt.Color;
import java.awt.Frame;

public class Ventana extends Frame {
	public Ventana() {
		super("Barquitos");
		this.setSize(Vista.LADO*Tablero.SIZE+Vista.MARGEN*Tablero.SIZE-Vista.MARGEN, Vista.LADO*Tablero.SIZE+Vista.MARGEN*Tablero.SIZE+25);
		this.setBackground(Color.BLACK);
	}
	
}
