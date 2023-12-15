package pt.iscte.poo.sokoban;

import pt.iscte.poo.utils.Point2D;

public class Chao extends GameElement {

	public Chao(Point2D point2D){
		super(point2D, 0);
	}
	
	//GETTER
	
	@Override
	public String getName() {
		return "Chao";
	}
	
	@Override
	public String toString() {
		return this.getName() + " : " + super.getPosition();
	}

}
