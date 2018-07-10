package neu.msd.team208.Helper;

import java.util.HashSet;
import java.util.Set;

/**
 * node data structure for AST tree
 * @author rachanatondare
 *
 */
public class Node {

        Node parent;
        Set<Node> children;
        String name;

        public Node()
        {
            this.parent = null;
            this.name = null;
            this.children = new HashSet();
        }

        public void setName(String name)
        {
            this.name = name;
        }
        public void setParent(Node parent)
        {
            this.parent = parent;
        }
        public void addChild(Node child)
        {
            children.add(child);
        }


    
}
