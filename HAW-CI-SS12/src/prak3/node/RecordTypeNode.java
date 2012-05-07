package prak3.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cip.base.AbstractDescr;

public class RecordTypeNode extends AbstractNode {

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
		// TODO Auto-generated method stub

	}

}
