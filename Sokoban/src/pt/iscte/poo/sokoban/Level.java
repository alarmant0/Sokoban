package pt.iscte.poo.sokoban;

import java.io.File;
import java.io.FileNotFoundException;
	import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import pt.iscte.poo.utils.Point2D;

public class Level {

	public static final String ROOM_DIR = "levels/";
	public static final int MAX_LEVEL = 7;
	
	private int level;
	private List<GameElement> gameElements;
	private List<Alvo> alvos = new ArrayList<>();
	
	public Level(int level) {
		if (level >= MAX_LEVEL) throw new IllegalArgumentException("Level excedido!");
		this.gameElements = new ArrayList<GameElement>();
		this.level = level;
	}
	
	//GETTERS AND SETTERS
	
	public int getLevel() {
		return this.level;
	}
	
	public void getAlvos() {
		for(GameElement e : this.gameElements) 
			if (e instanceof Alvo) this.alvos.add((Alvo) e);
		
	}
	
	public List<GameElement> getGameElements() {
		return this.gameElements;
	}
	
	public void addElement(GameElement e) {
		this.gameElements.add(e);
	}
	
	public void remove(GameElement g) {
		this.gameElements.remove(g);
	}
	
	//--------------------------------------
	
	public boolean checkLevelComplete(Alvo a) {
		int alvosChecked = 0;
		getAlvos();
		if (alvos.size() == 1) return true;
		this.alvos.remove(a); // Vamos  verificar os outros alvos, nao o que foi ativado 
		
		for(Alvo alvo : this.alvos) {
			for(GameElement e : this.gameElements) {
				if(e instanceof Caixote && alvo.getPosition().equals(e.getPosition())) {
					alvosChecked++;			
				}
			}
		}
		
		int numOfAlvos = this.alvos.size();
		this.alvos = new ArrayList<>(); // Reset
		return alvosChecked==numOfAlvos;
	}
	
	public static Level createLevel(int level) {
		if (level == MAX_LEVEL) {
			Level.win();
			return null;
		}
		Level l = new Level(level);
		try {
			Scanner scanner = new Scanner(new File(ROOM_DIR + "level" + level + ".txt"));
			int x = 0;
			int y = 0;
			while(scanner.hasNextLine()) {
				String [] array = scanner.nextLine().split("");
				for (String s : array) {
					l.addElement(GameElement.createGameElement(s,new Point2D(x,y)));
					x++;
				}
				x=0;
				y++;
			}
		} catch (FileNotFoundException e) {
			System.err.println("Erro a criar o level!");
		}
		l.getGameElements().sort((e1,e2)-> e2.getLayer() - e1.getLayer()); // ou new ComparadorDeGES());
		return l;
	}
	
	//------------------------------------------------
	
	public static double createPontuacao() {
		return Engine.getInstance().getBobCat().getPower() * Engine.getInstance().getLevelInt();
	}
	
	public static void reniciarLevel() {
		Engine en = Engine.getInstance();
		en.getLevels().set(en.getLevelInt(), Level.createLevel(en.getLevelInt()));
		en.getLevels().remove(en.getLevelInt());
		en.setLevel(en.getLevelInt(), Level.createLevel(en.getLevelInt()));
	}
	
	public static void win() {
		Engine en = Engine.getInstance();
		en.getGUI().setMessage("YOU WON!");
		en.getGUI().setMessage("POINTS: " + Level.createPontuacao());
		saveScore();
		en.getGUI().dispose();
		return;
	}
	
	public static void lose() {
		Engine en = Engine.getInstance();
		en.getBobCat().lost();
		en.getGUI().setMessage("YOU LOST!");
		en.getGUI().setMessage("POINTS: " + Level.createPontuacao());
		saveScore();
		String again = en.getGUI().askUser("Wanna play again?");
		if (again.toLowerCase().equals("y") || again.toLowerCase().equals("yes") || again.toLowerCase().equals("s") ||
			again.toLowerCase().equals("sim")) {
			reniciarLevel();
		} else {
			en.getGUI().dispose();
			return;
		}
	}
	
	private static List<Score> getScoresOnFile(String file, Score score) {
		List<Score> scores = new ArrayList<>();
		scores.add(score);
		try {
			Scanner scanner = new Scanner(new File(file));
			while(scanner.hasNextLine()) {
				scores.add(new Score(scanner.nextLine()));
			}
			scanner.close();
		} catch(FileNotFoundException e) {
			System.err.println("Erro a retirar os scores!");
		}
		scores.sort(new ComparadorDeScores());
		return scores;
	}

	public static void saveScore() {
		try {
			Engine en = Engine.getInstance();
			String file = "stats" + en.getLevelInt() + ".txt";
			int power = en.getBobCat().getPower();
			String name = en.getPlayerName();
			Score score = new Score(name,power);
			List<Score> scores = getScoresOnFile(file, score);
			PrintWriter pw = new PrintWriter(new File(file));
			int aux = 0;
			for(Score s : scores) {
				if(aux>=3) break;
				pw.println(s);
				aux++;
			}
			pw.close();
		} catch (FileNotFoundException e) {
			System.err.println("Erro a salvar pontuacao!");
		}		
	}

	@Override
	public String toString() {
		return "LEVEL: " + this.level + System.lineSeparator() + this.gameElements + System.lineSeparator();
	}
	
}
