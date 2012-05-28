package prak3.node;
import prak3.descr.AbstractDescr;
import java.util.HashMap;

public class ProcedureHeadingNode extends AbstractNode implements Node  {

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
		indent();
		System.out.format("ProcedureHeading: %n, Zeile: %s, Spalte: %s", name, line, column);
		ident.print();
		if(formalParameters!=null){
			formalParameters.print();
		}
		unindent();

	}

}
