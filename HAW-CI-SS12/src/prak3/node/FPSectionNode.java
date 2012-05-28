package prak3.node;
import prak3.descr.AbstractDescr;
import java.util.HashMap;

public class FPSectionNode extends AbstractNode implements Node  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IdentListNode identList;
	private TypeNode type;

	public FPSectionNode(String name, int line, int column, IdentListNode il, TypeNode t) {
		super(name, line, column);
		this.identList = il;
		this.type = t;
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		indent();
		System.out.format("FPSection: %n, Zeile: %s, Spalte: %s", name, line, column);
		identList.print();
		type.print();
		unindent();

	}

}
