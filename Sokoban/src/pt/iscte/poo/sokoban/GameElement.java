package pt.iscte.poo.sokoban;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;


public abstract class GameElement implements ImageTile {

	private Point2D position;
	private int layer;
	
	public GameElement(Point2D position, int layer) {
		this.position = position;
		this.layer = layer;
	}
	
	//GETTERS AND SETTERS
	
	public Point2D getPosition() {
		return this.position;
	}
	
	public int getLayer() {
		return this.layer;
	}
	
	public void setPosition(Point2D p) {
		this.position = p;
	}
	
	//-----------------
	
	//METODO FABRICA
	
	public static GameElement createGameElement(String c, Point2D point) {
		switch(c) {
		case "=":
			return new Vazio(point);
		case "#":
			return new Parede(point);
		case "C":
			return new Caixote(point);
		case "X":
			return new Alvo(point);
		case " ":
			return new Chao(point);
		case "E":
			return new Empilhadora(point);
		case "B":
			return new Bateria(point);
		case "O":
			return new Buraco(point);
		case "P":
			return new Palete(point);
		case "M":
			return new Martelo(point);
		case "%":
			return new ParedeRachada(point);
		case "T":
			return new Teleporte(point);
		default:
			return null;
		}
	}	
	
}
