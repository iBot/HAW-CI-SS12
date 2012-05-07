package prak3.node;

import java.util.HashMap;

import cip.base.AbstractDescr;

public class StatementSequenceNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatementSequenceNode(String name, int line, int column) {
		super(name, line, column);
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
