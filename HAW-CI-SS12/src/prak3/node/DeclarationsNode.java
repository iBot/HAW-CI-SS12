package prak3.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cip.base.AbstractDescr;

public class DeclarationsNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<IdentNode,ExpressionNode> consts = null;
	private Map<IdentNode,TypeNode> typs = null;
	private Map<IdentListNode,TypeNode> vars = null;
	private List<ProcedureDeclarationNode> procedureDeaclaration = new ArrayList<ProcedureDeclarationNode>();
	public DeclarationsNode(String name, int line, int column, Map<IdentNode,ExpressionNode> consts, Map<IdentNode,TypeNode> typs, Map<IdentListNode,TypeNode> vars) {
		super(name, line, column);
		this.consts = consts;
		this.typs = typs;
		this.vars = vars;
	}
	
	public void addProcedureDeclaration(ProcedureDeclarationNode pd){
		this.procedureDeaclaration.add(pd);
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
