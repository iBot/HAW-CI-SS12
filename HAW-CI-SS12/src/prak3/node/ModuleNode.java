package prak3.node;

import java.util.HashMap;

import cip.base.AbstractDescr;

public class ModuleNode extends AbstractNode {

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
		// TODO Auto-generated method stub

	}

}
