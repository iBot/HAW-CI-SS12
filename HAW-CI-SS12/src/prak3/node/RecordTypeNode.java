package prak3.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import prak3.descr.AbstractDescr;

public class RecordTypeNode extends AbstractNode implements Node  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<FieldListNode> fieldList = new ArrayList<FieldListNode>();

	public RecordTypeNode(String name, int line, int column) {
		super(name, line, column);
	}
	
	public void addFieldList(FieldListNode fl){
		this.fieldList.add(fl);
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		indent();
		System.out.format("RecordType: %n, Zeile: %s, Spalte: %s", name, line, column);
			for(FieldListNode f : fieldList){
				f.print();
			}
		unindent();

	}
	
	

}
