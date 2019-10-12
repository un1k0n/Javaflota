import java.beans.PropertyChangeSupport;

public class Tablero implements ITablero {
	
	private int[][] tablero;
	private int tamanio;
	private PropertyChangeSupport supportTablero;
	
	public Tablero(int tamanio) {
		tablero = new int[tamanio][tamanio];
		this.tamanio = tamanio;
		borra();
	}
	
	@Override
	public void borra() {
		for(int i = 0; i < tamanio; i++) {
			for(int j = 0; j < tamanio; j++) {
				tablero[i][j] = AGUA;
			}
		}
	}

	@Override
	public int getPosicion(int x, int y) {
		return tablero[x][y];
	}

	@Override
	public int[][] getTablero() {
		return this.tablero;
	}

	@Override
	public int getTamanio() {
		return this.tamanio;
	}

	@Override
	public int ocupacion() {
		int cont = 0;
		for(int i = 0; i < tamanio; i++) {
			for(int j = 0; j < tamanio; j++) {
				if(tablero[i][j] != AGUA && tablero[i][j] != CUBIERTO) {
					cont++;
				}
			}
		}
		return cont;
	}

	@Override
	public void setPosicion(int x, int y, int valor) {
		this.tablero[x][y] = valor;
	}

	@Override
	public void setTablero(int[][] tablero) {
		for(int i = 0; i < tamanio; i++) {
			for(int j = 0; j < tamanio; j++) {
				this.tablero[i][j] = tablero[i][j];
			}
		}
	}
	
	public void actualizarVista() {
		supportTablero.firePropertyChange("Tablero", null, this);
	}
	
	public void addPropertyChangeSupport(PropertyChangeSupport supportTablero){
		this.supportTablero = supportTablero;
	}
	
	public boolean estaDentro(int x, int y) {
		return (x >= 0 && x < getTamanio()) && (y >= 0 && y < getTamanio());
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < tamanio; i++) {
			for(int j = 0; j < tamanio; j++) {
				sb.append(String.valueOf(this.tablero[i][j])).append("\t");
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}
