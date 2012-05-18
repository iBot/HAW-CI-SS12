package prak3.node;

import java.util.HashMap;

import cip.base.AbstractDescr;
import java.util.List;

public class StatementSequenceNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private final StatementNode statement;
    private final List<StatementNode> additionalStatements;

	public StatementSequenceNode(String name, int line, int column, StatementNode statement, List<StatementNode> additionalStatements) {
		super(name, line, column);
		this.statement=statement;
                this.additionalStatements=additionalStatements;
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
            System.out.println(String.format("StatementSequenzeNode (l:%d c:%d)",line,column));
            statement.print();
            for (StatementNode statementNode : additionalStatements) {
                statementNode.print();
            }

	}

}
