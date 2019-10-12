
public interface ITablero {
	public static final int AGUA = 0;
	public static final int CUBIERTO = 100;
	public static final int HUNDIDO = -100;
	public static final int SIZE = 10;
	public void borra();
	public int getPosicion(int x, int y);
	public int [][] getTablero();
	public int getTamanio();
	public int ocupacion();
	public void setPosicion(int x, int y, int valor);
	public void setTablero(int[][] tablero);
}
