package prak3.node;

import java.util.HashMap;

import cip.base.AbstractDescr;

public class ProcedureBodyNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DeclarationsNode declarations;
	private StatementSequenceNode statementSequence;

	public ProcedureBodyNode(String name, int line, int column, DeclarationsNode d, StatementSequenceNode ss) {
		super(name, line, column);
		this.declarations = d;
		this.statementSequence = ss;
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
