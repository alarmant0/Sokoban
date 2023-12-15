package pt.iscte.poo.sokoban;

import pt.iscte.poo.utils.Point2D;

public class Teleporte extends GameElement {

	private Teleporte twin = null;
	
	public Teleporte(Point2D position) {
		super(position, 2);
	}

	public Teleporte getTwin() {
		return this.twin;
	}

	
	public boolean isBlocked() {
		Engine en = Engine.getInstance();
		for(GameElement e : en.getLevel().getGameElements()) 
			if (twin.getPosition().equals(e.getPosition()) && !(e instanceof Teleporte)) return true;
		return false;
	}
	
	public void findTwin() {
		Engine en = Engine.getInstance();
		Level l = en.getLevel();
		for(GameElement e : l.getGameElements()) {
			if (e instanceof Teleporte && !e.equals(this)) {
				this.twin = (Teleporte) e;
			}
		}
	}
	
	public TypeOfAction teleport(GameElement e) {
		findTwin();
		if (!isBlocked()) {
			e.setPosition(twin.getPosition());
			if (e instanceof Movable) return TypeOfAction.EXCEPTION;
		}
		return TypeOfAction.STOP;
	}

	@Override
	public String getName() {
		return "Teleporte";
	}

}
