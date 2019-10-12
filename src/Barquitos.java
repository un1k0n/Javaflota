import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;

public class Barquitos extends Ventana implements IBarquitos {
	
	private Tablero tablero;
	private Controlador controlador;
	private Vista vista;
	private int [] numBarcosLong;
	
	public static void main(String[] args) {
		int [] barcos = {2,3,3,1};
		new Barquitos(barcos, 10);
	}
	
	public Barquitos(int [] numBarcosLong, int tamanioTablero) {
		super();
		this.numBarcosLong = numBarcosLong;
		tablero = new Tablero(tamanioTablero);
		colocarBarcos();
		PropertyChangeSupport supportTablero = new PropertyChangeSupport(tablero);
		vista = new Vista(this.tablero);
		supportTablero.addPropertyChangeListener(vista);
		controlador = new Controlador(this);
		vista.addMouseListener(controlador);
		super.add(vista);
		tablero.addPropertyChangeSupport(supportTablero);
		setVisible(true);
	}
	
	@Override
	public void colocarBarcos() {
		for(int i = 0; i < tablero.getTamanio(); i++) {
			for(int j = 0; j < tablero.getTamanio(); j++) {
				tablero.setPosicion(i, j, Tablero.CUBIERTO);
			}
		}
		Random randomFila = new Random();
		Random randomColumna = new Random();
		Random randomOrientacion = new Random();
		for(int i = numBarcosLong.length-1; i >= 0; i--) {
			for(int j = 1; j <= numBarcosLong[i]; j++) {
				int fila = randomFila.nextInt(tablero.getTamanio());
				int columna = randomColumna.nextInt(tablero.getTamanio());
				int orientacion = randomOrientacion.nextInt(2);
				int k = 0;
				ArrayList<int[]> puntos = new ArrayList<>();
				int incrementoFila = (orientacion == 0)?0:1;
				int incrementoColumna = (orientacion == 0)?1:0;
				while(k < i+1) {
					if(tablero.estaDentro(fila+(k*incrementoFila), columna+(k*incrementoColumna)) && tablero.getPosicion(fila+(k*incrementoFila), columna+(k*incrementoColumna)) == 100) {
						int [] aux = new int[2];
						aux[0] = fila+(k*incrementoFila);
						aux[1] = columna+(k*incrementoColumna);
						puntos.add(aux);
						k++;
					} else {
						k = 0;
						puntos.clear();
						fila = randomFila.nextInt(tablero.getTamanio());
						columna = randomColumna.nextInt(tablero.getTamanio());
						orientacion = randomOrientacion.nextInt(2);
					}
				}
				for(int[] array: puntos) {
					tablero.setPosicion(array[0], array[1], j*10+(i+1));
				}
			}
		}
	}

	@Override
	public void disparo(int x, int y) {
		int posicion = tablero.getPosicion(x, y);
		if(posicion == Tablero.CUBIERTO) {
			tablero.setPosicion(x, y, Tablero.AGUA);
		} else if(posicion > 0 && posicion < 100) {
			tablero.setPosicion(x, y, posicion*-1);
			int barco = posicion*-1;
			int cont = 0;
			for(int i = 0; i < tablero.getTamanio(); i++) {
				for(int j = 0; j < tablero.getTamanio(); j++) {
					if(tablero.getPosicion(i, j) == barco) {
						cont++;
					}
				}
			}
			if(cont == posicion%10) {
				for(int i = 0; i < tablero.getTamanio(); i++) {
					for(int j = 0; j < tablero.getTamanio(); j++) {
						if(tablero.getPosicion(i, j) == barco) {
							tablero.setPosicion(i, j, Tablero.HUNDIDO);
						}
					}
				}
			}
		}
		if(finPartida()) {
			mostrarFinPartida();
		}
		tablero.actualizarVista();
	}

	@Override
	public boolean finPartida() {
		boolean fin = true;
		int i = 0;
		while(fin && i < tablero.getTamanio()) {
			int j = 0;
			while(fin && j < tablero.getTamanio()) {
				if(tablero.getPosicion(i, j) > 0 && tablero.getPosicion(i, j) < 100) {
					fin = false;
				}
				j++;
			}
			i++;
		}
		return fin;
	}

	@Override
	public void mostrarFinPartida() {
		int i = 0;
		while(i < tablero.getTamanio()) {
			int j = 0;
			while(j < tablero.getTamanio()) {
				if(tablero.getPosicion(i, j) < 0) {
					tablero.setPosicion(i, j, Tablero.HUNDIDO);
				}
				if(tablero.getPosicion(i, j) == Tablero.CUBIERTO) {
					tablero.setPosicion(i, j, Tablero.AGUA);
				}
				j++;
			}
			i++;
		}
	}

}
