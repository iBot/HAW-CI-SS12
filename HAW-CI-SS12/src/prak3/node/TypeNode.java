package prak3.node;

import java.util.HashMap;

import cip.base.AbstractDescr;

public class TypeNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayTypeNode arrayType = null;
	private RecordTypeNode recordType = null;
	private IdentNode identNode = null;
	
	public TypeNode(String name, int line, int column, ArrayTypeNode at) {
		super(name, line, column);
		this.arrayType = at;
	}
	
	public TypeNode(String name, int line, int column,RecordTypeNode rt) {
		super(name, line, column);
		this.recordType = rt;
	}
	
	public TypeNode(String name, int line, int column, IdentNode in) {
		super(name, line, column);
		this.identNode = in;
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
