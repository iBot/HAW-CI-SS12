package prak3.node;

import java.util.HashMap;
import prak3.descr.AbstractDescr;

public class IdentNode extends AbstractNode implements Node  {

	public IdentNode(String name, int line, int column) {
		super(name, line, column);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
                AbstractDescr ad = symbolTable.get(name);
                
		return null;
	}

	@Override
	public void print() {
		indent();
			System.out.println(String.format("Ident: %s, Zeile: %s, Spalte: %s",name,line,column));
		unindent();
	}

}
