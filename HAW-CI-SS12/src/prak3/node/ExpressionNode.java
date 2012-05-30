package prak3.node;
import prak3.descr.AbstractDescr;
import java.util.HashMap;
import static prak2.CodeGenerator.*;

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
		firstSE.compile(symbolTable);
                if (secondSE!=null){
                    secondSE.compile(symbolTable);
                    if (operator.equals("=")){
                        writeln("EQ");
                    } else if (operator.equals("#")){
                        writeln("NEQ");
                    } else if (operator.equals("<")){
                        writeln("LT");
                    } else if (operator.equals("<=")){
                        writeln("LE");
                    } else if (operator.equals(">")){
                        writeln("GT");
                    } else if (operator.equals(">=")){
                        writeln("GE");
                    } else {
                        throw new Error(String.format("Illegal Operator: %s is not allowed",operator));
                    }
                }
                
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
