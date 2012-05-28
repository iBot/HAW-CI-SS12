package prak3.node;

import java.util.HashMap;
import prak3.descr.AbstractDescr;

public class ModuleNode extends AbstractNode  implements Node {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IdentNode ident1;
	private DeclarationsNode declarations;
	private StatementSequenceNode statementSequence;
	private IdentNode ident2;
	
	public ModuleNode(String name, int line, int column, IdentNode ident1, DeclarationsNode dn, StatementSequenceNode ss, IdentNode ident2) {
		super(name, line, column);
		this.ident1 = ident1;
		this.declarations = dn;
		this.statementSequence = ss;
		this.ident2 = ident2;
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		indent();
			System.out.format("Module: %n, Zeile: %s, Spalte: %s", name, line, column);
			ident1.print();
			declarations.print();
			statementSequence.print();
			ident2.print();
		unindent();

	}

}
