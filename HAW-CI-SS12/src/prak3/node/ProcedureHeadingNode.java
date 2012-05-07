package prak3.node;

import java.util.HashMap;

import cip.base.AbstractDescr;

public class ProcedureHeadingNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IdentNode ident;
	private FormalParametersNode formalParameters = null;
	
	public ProcedureHeadingNode(String name, int line, int column, IdentNode ident) {
		super(name, line, column);
		this.ident = ident;
	}
	
	public ProcedureHeadingNode(String name, int line, int column, IdentNode ident, FormalParametersNode fp) {
		super(name, line, column);
		this.ident = ident;
		this.formalParameters = fp;
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
