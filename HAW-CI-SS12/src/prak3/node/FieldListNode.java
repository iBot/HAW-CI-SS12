package prak3.node;

import java.util.HashMap;

import cip.base.AbstractDescr;

public class FieldListNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IdentListNode identList;
	private TypeNode type;
	
	public FieldListNode(String name, int line, int column, IdentListNode il, TypeNode t) {
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
		// TODO Auto-generated method stub

	}

}
