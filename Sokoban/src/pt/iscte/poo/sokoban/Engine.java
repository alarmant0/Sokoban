package pt.iscte.poo.sokoban;

import java.util.ArrayList;
import java.util.List;
import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Engine implements Observer {

	public static final int GRID_HEIGHT = 10;
	public static final int GRID_WIDTH = 10;
	
	private static Engine INSTANCE; 
	private ImageMatrixGUI gui;  		 
	private List<GameElement> tileList;	
	private Empilhadora bobcat;	        
	private List<Level> levels = new ArrayList<>();
	private int level = 0;
	private String playerName = "Default";

	private Engine() {
		tileList = new ArrayList<>();   
	}
	
	// ------------------------ GETTERS
	
	public List<Level> getLevels() {
		return this.levels;
	}
	
	public int getLevelInt() {
		return this.level;
	}
	
	public Level getLevel() {
		return this.levels.get(level);
	}
	
	public Empilhadora getBobCat() {
		return this.bobcat;
	}
	
	public ImageMatrixGUI getGUI() {
		return this.gui;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public static Engine getInstance() {
		if (INSTANCE==null)
			return INSTANCE = new Engine();
		return INSTANCE;
	}
	
	// ------------------------ SETTERS
		
	public void setLevel(int level , Level l) {
		if (l == null) return;
		this.level = level;
		this.levels.add(l);
		addToTileList();	
		paintFloor();
	}
	
	public void add(GameElement g) {
		tileList.add(g);
		gui.addImage(g);
	}
	
	public void remove(GameElement g) {
		this.levels.get(level).remove(g);
		gui.removeImage(g);
	}		
	
	// --------------------------

	//PAINTING
	
	public void paintFloor() {
		for(int i = 0;i<10;i++) {
			for(int j = 0;j<10;j++) {
				this.add(new Chao(new Point2D(i,j)));			
			}
		}
	}
	
	public void addToTileList() {
		if (tileList.size() != 0) {
			for(GameElement e : this.tileList) {
				this.remove(e);
			}
			this.tileList = new ArrayList<>();
		}
		for(GameElement e : this.levels.get(level).getGameElements()) {
			if (e instanceof Empilhadora) this.bobcat = (Empilhadora) e;
			this.add(e);
		}
	}
	
	//----------------------------------
	
	public void start() {
		gui = ImageMatrixGUI.getInstance();    
		gui.setSize(GRID_HEIGHT, GRID_WIDTH);  
		gui.registerObserver(this);            
		gui.go();                              
		gui.setName("Sokoban");
		this.playerName = gui.askUser("Qual o seu nome?");
		setLevel(this.level,Level.createLevel(this.level));
		gui.update();
		gui.setStatusMessage("Sokoban     ENERGY: " + this.bobcat.getPower());
	}
	
	@Override
	public void update(Observed source) {
		int key = gui.keyPressed(); 
//		if(key == 10) this.setLevel(level + 1, Level.createLevel(level + 1)); //So para debugging
		if (key>=37 && key<=40) bobcat.move(Direction.directionFor(key));
		gui.update();   
		gui.setStatusMessage("Sokoban     ENERGY: " + this.bobcat.getPower() + "    LEVEL: " + this.level);	                               
	}
		
}