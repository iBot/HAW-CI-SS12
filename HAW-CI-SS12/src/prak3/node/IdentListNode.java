package prak3.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cip.base.AbstractDescr;

public class IdentListNode extends AbstractNode {
	
	private List<IdentNode> identList = new ArrayList<IdentNode>();

	public IdentListNode(String name, int line, int column) {
		super(name, line, column);
		// TODO Auto-generated constructor stub
	}
	
	public void addNode(IdentNode n){
		identList.add(n);
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
