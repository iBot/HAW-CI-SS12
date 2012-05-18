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
public class TermNode extends AbstractNode{
     private static final long serialVersionUID = 1L;
    private final FactorNode firstFactor;
    private final List<Character> operators;
    private final List<FactorNode> factors;


    public TermNode(String name, int line, int column, FactorNode firstFactor, List<Character> operators, List<FactorNode> factors){
        super(name, line, column);
        this.firstFactor=firstFactor;
        this.operators=operators;
        this.factors=factors;
    }
    
    @Override
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void print() {
        indent();
        System.out.println(String.format("TermNode (l:%d c:%d)",line,column));
        firstFactor.print();
        for (int i = 0; i<operators.size();i++) {
            System.out.println(operators.get(i));
            factors.get(i).print();
        }
        unindent();
    }
}
