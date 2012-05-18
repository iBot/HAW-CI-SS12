package prak3.node;

import java.util.HashMap;

import cip.base.AbstractDescr;

public class IndexExpressionNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

        private final AbstractNode innerNode;

        public IndexExpressionNode(String name, int line, int column, AbstractNode innerNode){
            super(name, line, column);
            if (!(innerNode instanceof ConstIdentNode) && !(innerNode instanceof IntegerNode)){
                throw new IllegalArgumentException(String.format("InnerNodeParameter has to be instanceof ConstIdentNode or IntegerNode, but it is %s",innerNode.getClass().toString()));
            }
            this.innerNode = innerNode;

        }
        
        @Override
        public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void print() {
            indent();
            System.out.println(String.format("IndexExpressionIdentNode (l:%d c:%d)",line,column));
            innerNode.print();
            unindent();
        }

}
