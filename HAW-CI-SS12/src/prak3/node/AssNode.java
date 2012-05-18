package prak3.node;

import java.util.HashMap;

import cip.base.AbstractDescr;

public class AssNode extends AbstractNode {
	
	private AbstractNode left;
	private AbstractNode right;

	public AssNode(String name, int line, int column, AbstractNode left, AbstractNode right) {
		super(name, line, column);
		this.left = left;
		this.right = right;
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		indent();
		System.out.format("Zuweisung: %n, Zeile: %s, Spalte: %s", name, line, column);
		left.print();
		right.print();
		unindent();

	}

}
