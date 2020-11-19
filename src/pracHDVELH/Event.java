/**
 * File: NodeMultipleEvents.java
 * Creation: 7 nov. 2020, Jean-Philippe.Prost@univ-amu.fr
 * Template étudiants
 */
package pracHDVELH;

import java.*;
import java.util.Scanner;

import myUtils.ErrorNaiveHandler;

/**
 * @author prost
 *
 */
public class Event extends NodeMultiple {
	public static final String ERROR_MSG_UNEXPECTED_END = "Sorry, for some unexpected reason the story ends here...";
	public static final String PROMPT_ANSWER = "Answer: ";
	public static final String WARNING_MSG_INTEGER_EXPECTED = "Please input a integer within range!";
	private Scanner S;
	private String answer;
	private int chosenPath = 0;
	private GUIManager gui;
	private int id; //

	
	

	/**
	 * @return the playerAnswer
	 */
	public String getPlayerAnswer() {
		return this.answer;
	}

	/**
	 * @param playerAnswer the playerAnswer to set
	 */
	public void setPlayerAnswer(String playerAnswer) {
		this.answer = playerAnswer;
	}

	/**
	 * @return the reader
	 */
	public Scanner getReader() {
		return this.S;
	}

	/**
	 * @param reader the reader to set
	 */
	public void setReader(Scanner reader) {
		this.S = reader;
	}

	/**
	 * @return the chosenPath
	 */
	public int getChosenPath() {
		return this.chosenPath;
		
	}

	/**
	 * @param chosenPath the chosenPath to set
	 */
	public void setChosenPath(int chosenPath) {
		this.chosenPath = chosenPath;
	}

	/* Methods */
	/**
	 * @see pracHDVELH.NodeMultiple#getData()
	 */
	public String getData() {
		if (super.getData() != null && super.getData().getClass().equals(String.class))
			return (String) super.getData();
		return null;
	}

	/**
	 * @see pracHDVELH.NodeMultiple#setData(Object)
	 * @param data
	 */
	public void setData(String data) {
		super.setData(data);
	}

	/**
	 * @see pracHDVELH.NodeMultiple#getDaughter(int)
	 */
	@Override
	public Event getDaughter(int i) {
		if (super.getDaughters() != null && super.getData().getClass().equals(String.class))
			return (Event) super.getDaughter(i);
		return null;
	}

	/**
	 * @see pracHDVELH.NodeMultiple#setDaughter(NodeMultiple, int)
	 * @param daughter
	 * @param i
	 */
	public void setDaughter(Event daughter, int i) {
		super.setDaughter(daughter, i);
	}

	/**
	 * @return the gui
	 */
	public GUIManager getGui() {
		return this.gui;
	}

	/**
	 * @param gui the gui to set
	 */
	public void setGui(GUIManager gui) {
		this.gui = gui;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.getId();
	}

	public Event (GUIManager gui, String data) {
		this.gui = gui;
		this.id = nextId++;
		
	
	}
	
	public Event()
	{
		this.gui = new GUIManager();
		this.id= nextId++;
	}
	
	public void run() {
		if (isFinal())
		{
			return; // si il n'y a pas de fils on arrête
		}
		
		gui.outputln(getData());
		gui.outputln(PROMPT_ANSWER);
		setPlayerAnswer(gui.read()); //gui.read permet de saisir quelque chose au clavier
		setChosenPath(interpretAnswer()); //en fonction de la réponse on choisi le chemin suivant
		while(chosenPath < 0) //si chosenPath est infèrieur à 0 alors erreur on recommence les étapes du dessus
		{
			setPlayerAnswer(gui.read()); 
			setChosenPath(interpretAnswer());
		}
		
		getDaughter(chosenPath).run();
		
	}
	
	public boolean isInRange(int index)
	{
		if (index < 0)
		{
			return false; // gestion erreur
		}
		int cpt = 0;
		while( cpt < NODE_MAX_ARITY && getDaughter(max) != null)
		{
			cpt += 1;
		}
		
		if (index >= cpt)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public String toString()
	{
		return "Event #"+getId()+"("+getClass().getName()+")"+": "+(String) getData();
	}
	public boolean isFinal()
	{
		return !hasDaughters();
	}
	public void test()
	{
		return test;
	}
}

// eof
