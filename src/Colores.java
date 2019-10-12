import java.awt.Color;

public enum Colores {
	AGUA(0),
	BORDE(1),
	CUBIERTO(2),
	HUNDIDO(3),
	TOCADO(4);
	
	private int valor;
	
	private Colores(int valor) {
		this.valor = valor;
	}
	
	public Color getColor() {
		switch(this.valor) {
			case 0:
				return Color.CYAN;
			case 1:
				return Color.BLACK;
			case 2:
				return Color.GRAY;
			case 3:
				return Color.RED;
			default:
				return Color.YELLOW;
		}
	}
	
	public static Color getColor(int valor) {
		switch(valor) {
			case 0:
				return Color.CYAN;
			case 1:
				return Color.BLACK;
			case 2:
				return Color.GRAY;
			case 3:
				return Color.RED;
			default:
				return Color.YELLOW;
		}
	}
}
