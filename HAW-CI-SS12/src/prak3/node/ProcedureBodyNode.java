package prak3.node;

import java.util.HashMap;
import prak3.descr.AbstractDescr;

public class ProcedureBodyNode extends AbstractNode  implements Node {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DeclarationsNode declarations;
	private StatementSequenceNode statementSequence;

	public ProcedureBodyNode(String name, int line, int column, DeclarationsNode d, StatementSequenceNode ss) {
		super(name, line, column);
		this.declarations = d;
		this.statementSequence = ss;
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		indent();
		System.out.format("ProcedureBody: %n, Zeile: %s, Spalte: %s", name, line, column);
		declarations.print();
		statementSequence.print();
		unindent();
	}

}
