package prak3.node;

import java.util.HashMap;

import cip.base.AbstractDescr;

public class ProcedureDeclarationNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProcedureHeadingNode procedureHeading;
	private ProcedureBodyNode procedureBody;
	private IdentNode ident;

	public ProcedureDeclarationNode(String name, int line, int column,ProcedureHeadingNode ph, ProcedureBodyNode pb, IdentNode i) {
		super(name, line, column);
		this.procedureBody = pb;
		this.procedureHeading = ph;
		this.ident = i;
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
