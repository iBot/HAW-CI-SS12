/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prak3.node;
import prak3.descr.AbstractDescr;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Tobi
 */
public class SelectorNode extends AbstractNode implements Node  {

    private static final long serialVersionUID = 1L;
    private final List<AbstractNode> nodes;
    
    public SelectorNode(String name, int line, int column, List<AbstractNode> nodes){
        super(name, line, column);
        this.nodes=nodes;
    }
    
    @Override
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        for (Node node : nodes) {
            if (node instanceof IdentNode){
                node.compile(symbolTable);
            } else{
                node.compile(symbolTable);
            }
                
        }
        return null;
    }

    @Override
    public void print() {
        indent();
        System.out.println(String.format("SelectorNode (l:%d c:%d)",line,column));
        for (AbstractNode abstractNode : nodes) {
            abstractNode.print();
        }
        unindent();
    }
    
}
