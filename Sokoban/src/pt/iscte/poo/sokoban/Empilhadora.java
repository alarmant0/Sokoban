package pt.iscte.poo.sokoban;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Empilhadora extends GameElement implements Movable {

	public static int MAX_POWER = 100;
	
	private String imageName;
	private int power = MAX_POWER;
	private boolean martelo = false;
	
	public Empilhadora(Point2D initialPosition){
		super(initialPosition, 6);
		imageName = "Empilhadora_D";
	}
	
	// SETTERS AND GETTERS
	
	@Override
	public String getName() {
		return imageName;
	}
	
	public int getPower() {
		return this.power;
	}
	
	public boolean hasMartelo() {
		return this.martelo;
	}
	
	public void setMartelo() {
		this.martelo = true;
	}
	
	public void setName(String name) {
		this.imageName = name;
	}
	
	public void lost() {
		this.power = 0;
	}
	
	public void morePower() {
		if (this.power + 50 >= 100) {
			this.power = 100;
		} else {
			this.power += 50;
		}
	}
	
	public void losePower() {
		this.power--;
	}
	
	//---------------------------------
	
	public void changeNameBasedOnKey(Direction d) {
		switch(d){
			case DOWN:
				setName("Empilhadora_D");
				break;
			case UP:
				setName("Empilhadora_U");
				break;
			case LEFT:
				setName("Empilhadora_L");
				break;
			case RIGHT:
				setName("Empilhadora_R");
				break;
			default:
				throw new IllegalArgumentException("Direcao inválida!");
		}
	}
	
	@Override
	public TypeOfAction doSomethingWithIt(GameElement e, Direction d) {
		if (e instanceof Movable && e instanceof Blockable) { // Caixas, paletes e mais objetos 
			if (((Blockable) e).block().ordinal() == 0) {	  // com propriedades de Movable e Blockable	
				TypeOfAction t = ((Movable) e).move(d);
				if (t == TypeOfAction.MOVE || t == TypeOfAction.EXCEPTION) {
					this.losePower();
					return TypeOfAction.MOVE;
				} else {
					return TypeOfAction.STOP;
				}
			}
		}
		if (e instanceof Blockable) { 
			if (e instanceof Buraco && ((Buraco) e).block() == TypeOfAction.MOVE) Level.lose(); // Cair
			return TypeOfAction.opposite(((Blockable) e).block());
		}
		if (e instanceof Obtainable) return ((Obtainable) e).use();
		if (e instanceof Teleporte) return ((Teleporte) e).teleport(this);
		return TypeOfAction.MOVE;
	}
	
	@Override
	public TypeOfAction move(Direction d) {
		changeNameBasedOnKey(d);
		Point2D newPosition = super.getPosition().plus(d.asVector());
		if (canMove(d).ordinal() == 0) {
			this.losePower(); 
			super.setPosition(newPosition);
		}	
		return TypeOfAction.MOVE;
	}
	
	@Override
	public TypeOfAction canMove(Direction d) {
		if (this.power <= 0) Level.lose();
		Engine en = Engine.getInstance();
		Level l = en.getLevel();
		Point2D point2D = super.getPosition().plus(d.asVector());
		for (GameElement e : l.getGameElements()) {
			if (e.getPosition().equals(point2D) && e.getLayer() > 1) return doSomethingWithIt(e, d);
		}
		return TypeOfAction.MOVE;
	}

}