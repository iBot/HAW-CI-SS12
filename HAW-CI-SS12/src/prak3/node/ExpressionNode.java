package prak3.node;
import prak3.descr.AbstractDescr;
import java.util.HashMap;

public class ExpressionNode extends AbstractNode  implements Node {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private final String operator;
    private final SimpleExpressionNode firstSE;
    private final SimpleExpressionNode secondSE;

	public ExpressionNode(String name, int line, int column,SimpleExpressionNode firstSE, String operator,SimpleExpressionNode secondSE) {
		super(name, line, column);
		this.firstSE=firstSE;
                this.operator=operator;
                this.secondSE=secondSE;
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
            indent();
            System.out.println(String.format("ExpressionNode (l:%d c:%d)",line,column));
            firstSE.print();
            if (operator!=null) System.out.println(operator);
            if (secondSE!=null) secondSE.print();
            unindent();
	}

}
