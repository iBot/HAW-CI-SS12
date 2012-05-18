package prak3.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cip.base.AbstractDescr;

public class DeclarationsNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<AbstractNode> consts = new ArrayList<AbstractNode>();;
	private List<AbstractNode> typs = new ArrayList<AbstractNode>();;
	private List<AbstractNode> vars = new ArrayList<AbstractNode>();;
	private List<ProcedureDeclarationNode> procedureDeaclaration = new ArrayList<ProcedureDeclarationNode>();
	
	public DeclarationsNode(String name, int line, int column) {
		super(name, line, column);
	}
	
	public void addProcedureDeclaration(ProcedureDeclarationNode pd){
		this.procedureDeaclaration.add(pd);
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addTyp(AbstractNode typ){
		this.typs.add(typ);
	}
	
	public void addConst(AbstractNode c){
		this.consts.add(c);
	}
	
	public void addVar(AbstractNode v){
		this.vars.add(v);
	}

	@Override
	public void print() {
		indent();
		System.out.format("Declarations: %n, Zeile: %s, Spalte: %s", name, line, column);
		if(!(consts.isEmpty())){
			for(AbstractNode a : consts){
				a.print();
			}
		} else if(!(typs.isEmpty())){
			for(AbstractNode a: typs){
				a.print();
			}
			
		} else if(!(vars.isEmpty())){
			for(AbstractNode a: vars){
				a.print();
			}
			
		}
		for(ProcedureDeclarationNode p : procedureDeaclaration){
			p.print();
		}
		unindent();
		
	}

}
