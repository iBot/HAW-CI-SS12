/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prak3.node;

import cip.base.AbstractDescr;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Tobi
 */
public class SimpleExpressionNode extends AbstractNode{
    private static final long serialVersionUID = 1L;
    private final TermNode firstTerm;
    private final List<Character> operators;
    private final List<TermNode> terms;
    private final boolean firstIsNegative;
    
    public SimpleExpressionNode(String name, int line, int column, boolean firstIsNegative, TermNode firstTerm, List<Character> operators, List<TermNode> terms){
        super(name, line, column);
        this.firstIsNegative=firstIsNegative;
        this.firstTerm=firstTerm;
        this.operators=operators;
        this.terms=terms;       
    }
    
    @Override
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void print() {
        System.out.println(String.format("SimpleExpressinNode (l:%d c:%d)",line,column));
        System.out.println((firstIsNegative ? "-" : "+"));
        firstTerm.print();
        for (int i = 0; i<operators.size();i++) {
            System.out.println(operators.get(i));
            terms.get(i).print();
        }
    } 
}
