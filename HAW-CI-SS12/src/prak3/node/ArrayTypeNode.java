package prak3.node;

import java.util.HashMap;

import cip.base.AbstractDescr;

public class ArrayTypeNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IndexExpresssionNode indexExpression;
	private TypeNode type;

	public ArrayTypeNode(String name, int line, int column, IndexExpresssionNode e, TypeNode t) {
		super(name, line, column);
		this.indexExpression = e;
		this.type = t;
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
