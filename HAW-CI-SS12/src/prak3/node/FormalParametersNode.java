package prak3.node;

import cip.base.AbstractDescr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FormalParametersNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<FPSectionNode> fpsectionList = new ArrayList<FPSectionNode>();

	public FormalParametersNode(String name, int line, int column) {
		super(name, line, column);
		// TODO Auto-generated constructor stub
	}
	
	public void addFPSection(FPSectionNode fp){
		this.fpsectionList.add(fp);
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		indent();
		System.out.format("FormalParameters: %n, Zeile: %s, Spalte: %s", name, line, column);
		for(FPSectionNode f : fpsectionList){
			f.print();
		}
		unindent();

	}

}
