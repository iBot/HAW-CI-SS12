package prak3.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import prak3.descr.AbstractDescr;

public class IdentListNode extends AbstractNode implements Node  {
	
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
		indent();
		System.out.format("IdentList: %n, Zeile: %s, Spalte: %s", name, line, column);
		for(IdentNode n : identList){
			n.print();
		}
		unindent();

	}

}
