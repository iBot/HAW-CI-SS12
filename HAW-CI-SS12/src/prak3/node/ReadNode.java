/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prak3.node;

import cip.base.AbstractDescr;
import java.util.HashMap;

/**
 *
 * @author Tobi
 */
public class ReadNode extends AbstractNode{
    
	private static final long serialVersionUID = 1L;
    private final PromptNode prompt;
    
    public ReadNode(String name, int line, int column, PromptNode prompt){
        super(name, line, column);
        this.prompt = prompt;
    }
            
    @Override
    public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void print() {
        indent();
        System.out.println(String.format("ReadNode (l:%d c:%d)",line,column));
        if (prompt!=null) prompt.print();
        unindent();
    }
    
}
