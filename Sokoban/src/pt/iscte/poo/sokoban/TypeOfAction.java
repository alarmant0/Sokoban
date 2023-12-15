package pt.iscte.poo.sokoban;

public enum TypeOfAction {

	MOVE(0), STOP(1), EXCEPTION(2); //EXCEPTION - Um elemento move se e outro nao (Para Movables)
												  // MOVE = Block , STOP = Nao Block para Blockables
	private int typeOfMoving;
	
	TypeOfAction(int typeOfMoving) {
		this.typeOfMoving = typeOfMoving;
	}
	
	public int typeOfMovingAsInt() {
		return this.typeOfMoving;
	}
	
	public static TypeOfAction opposite(TypeOfAction t) {
		switch(t){
			case MOVE:
				return STOP;
			case STOP:
				return MOVE;
			case EXCEPTION:
				return EXCEPTION;
		}
		throw new IllegalArgumentException("Illegal Action!");
	}
	
}