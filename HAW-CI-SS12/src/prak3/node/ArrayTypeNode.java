package prak3.node;

import java.util.HashMap;

import cip.base.AbstractDescr;

public class ArrayTypeNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IndexExpressionNode indexExpression;
	private TypeNode type;

	public ArrayTypeNode(String name, int line, int column, IndexExpressionNode e, TypeNode t) {
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
		indent();
		System.out.format("Array: %n, Zeile: %s, Spalte: %s", name, line, column);
		if(indexExpression!=null){
				indexExpression.print();
		}
		type.print();
		unindent();

	}

}
