package prak3.descr;

import java.io.Serializable;

//import cip.debug.Debug;

/**
 * 
 * @author nilo VLL
 * 
 */
public abstract class AbstractDescr
implements
		Serializable {

	private static final long serialVersionUID = 1L;

	protected int size;
	protected int level;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public AbstractDescr() {
		this.size = 0;
                this.level = 0;
//		this.level = CodeGen.level;
	}

	public void setSize(int fs) {
		size = fs;
	}

	public int getSize() {
		return size;
	}

	abstract public void print();

	/**
	 * Diese Variable steuert die Einr�ckung bei der Ausgabe
	 */
	private static String spaces = "";

	/**
	 * Diese Methode ist eine Darstellungshilfe f�r den abstrakten Syntaxbaum.
	 * Die Einr�ckung wird aufgehoben.
	 */
	public void unindent() {
		spaces = spaces.substring(2);
	}

	private void indent() {
		spaces = spaces + "  ";
	}

	/**
	 * Einr�ckungstiefe um zwei Leerzeichen erh�hen und den String ausgeben.
	 * 
	 * @param fieldName
	 */
	public void trace(String s) {
		this.indent();
//		if (Debug.debug > 0) System.out.println(spaces + s);
	}

}