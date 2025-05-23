package domain;

import util.Utility;

public class BTree implements  Tree {
    private BTreeNode root; //se refiere a la raiz del arbol

    @Override
    public int size() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        return size(root);
    }

    private int size(BTreeNode node){
        if(node==null) return 0;
        else return 1 + size(node.left) + size(node.right);
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public boolean contains(Object element) throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        return binarySearch(root, element);
    }

    private boolean binarySearch(BTreeNode node, Object element) {
        if (node==null) return false;
        else if (Utility.compare(node.data,element)==0) return true;
        return binarySearch(node.left,element) || binarySearch(node.right,element);
    }

    @Override
    public void add(Object element) {
       //this.root = add(root, element);
        this.root = add(root, element, "root");
    }

    private BTreeNode add(BTreeNode node, Object element){
        if(node==null)
            node = new BTreeNode(element);
        else{
            int value = Utility.random(100);
            if(value%2==0)
                node.left = add(node.left, element);
            else node.right = add(node.right, element);
        }
        return node;
    }

    private BTreeNode add(BTreeNode node, Object element, String path){
        if(node==null)
            node = new BTreeNode(element, path);
        else{
            int value = Utility.random(100);
            if(value%2==0)
                node.left = add(node.left, element, path+"/left");
            else node.right = add(node.right, element, path+"/right");
        }
        return node;
    }

    @Override
    public void remove(Object element) throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        boolean[] flag = new boolean[]{false};
        root = remove(root, element, flag);
    }

    private BTreeNode remove(BTreeNode node, Object element, boolean[] flag) {
        if (node == null || flag[0]) {
            return node;
        }

        if (util.Utility.compare(node.data, element) == 0) {
            flag[0] = true; // cambia a true porque lo elimino

            // Caso 1: Nodo hoja
            if (node.left == null && node.right == null) {
                return null;
            }
            // Caso 2: Un solo hijo
            else if (node.left != null && node.right == null) {
                return node.left;
            } else if (node.left == null && node.right != null) {
                return node.right;
            }
            // Caso 3: Dos hijos
            else {
                /**
                 * El algoritmo de supresion dice que cuando el nodo a suprimir
                 * tiene 2 hijos, entonces busque una hoja del subarbol derecho
                 * y sustituya la data del nodo a suprimir por la data de esa hoja
                 * luego elimine esa hoja
                 */
                Object leafValue = getLeaf(node.right);
                node.data = leafValue;
                boolean[] leafFlag = new boolean[]{false};
                node.right = removeLeaf(node.right, leafValue, leafFlag);
                return node;
            }
        }
        if (!flag[0]) {
            node.left = remove(node.left, element, flag);
        }
        if (!flag[0]) {
            node.right = remove(node.right, element, flag);
        }

        return node;
    }

    private BTreeNode removeLeaf(BTreeNode node, Object value, boolean[] flag) {
        if (node == null || flag[0]) {
            return node;
        }
        if (node.left == null && node.right == null) {
            if (util.Utility.compare(node.data, value) == 0) {
                flag[0] = true;
                return null;
            }
            return node;
        }
        node.left = removeLeaf(node.left, value, flag);
        if (!flag[0]) {
            node.right = removeLeaf(node.right, value, flag);
        }
        return node;
    }

    private Object getLeaf(BTreeNode node){
        Object aux;
        if (node==null) return null;
        else if (node.left==null&&node.right==null) return node.data;//hoja
        else{
            aux = getLeaf(node.left);//siga bajando por la izquierda
            if (aux==null) aux= getLeaf(node.right);
        }
            return aux;
    }


    @Override
    public int height(Object element) throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        return height(root,element,0);
    }
    //devuelve la altura de un nodo(numero de ancestros)
    private int height(BTreeNode node, Object element, int count){
        if(node==null) return 0;
        else if (util.Utility.compare(node.data, element)==0) return count;
        else  return Math.max(height(node.left, element, ++count),
                    height(node.right,element,count));


    }

    @Override
    public int height() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        return height(root);
    }

//devuelve la altura del arbol
    private int height(BTreeNode node, int lvl){
        if (node==null) return lvl-1;//se le resta para que no cuente el nodo nulo

        else return Math.max(height(node.left, ++lvl),
                height(node.right,lvl));
    }

    //opcion 2
    private int height(BTreeNode node){
        if (node==null)return -1;//se le resta uno para no contar el nulo
        return Math.max(height(node.left), height(node.right)) +1;
    }



    @Override
    public Object min() throws TreeException {
        return null;
    }

    @Override
    public Object max() throws TreeException {
        return null;
    }

    @Override
    public String preOrder() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        return preOrder(root);
    }

    //recorre el árbol de la forma: nodo-hijo izq-hijo der
    private String preOrder(BTreeNode node){
        String result="";
        if(node!=null){
            //result = node.data+" ";
            result  = node.data+"("+node.path+")"+" ";
            result += preOrder(node.left);
            result += preOrder(node.right);
        }
        return  result;
    }

    public String preOrderHeight() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        return preOrderHeight(root);
    }

    //recorre el árbol de la forma: nodo-hijo izq-hijo der
    private String preOrderHeight(BTreeNode node) throws TreeException {
        String result="";
        if(node!=null){
            //result = node.data+" ";
            result  = node.data+"Height("+height(node.data)+")"+" ";
            result += preOrderHeight(node.left);
            result += preOrderHeight(node.right);
        }
        return  result;
    }


    @Override
    public String inOrder() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        return inOrder(root);
    }

    //recorre el árbol de la forma: hijo izq-nodo-hijo der
    private String inOrder(BTreeNode node){
        String result="";
        if(node!=null){
            result  = inOrder(node.left);
            result += node.data+" ";
            result += inOrder(node.right);
        }
        return  result;
    }

    //para mostrar todos los elementos existentes
    @Override
    public String postOrder() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        return postOrder(root);
    }

    //recorre el árbol de la forma: hijo izq-hijo der-nodo,
    private String postOrder(BTreeNode node){
        String result="";
        if(node!=null){
            result  = postOrder(node.left);
            result += postOrder(node.right);
            result += node.data+" ";
        }
        return result;
    }

    @Override
    public String toString() {
        String result="Binary Tree Content:";
        try {
            result = "PreOrder: "+preOrder();
            result+= "\nInOrder: "+inOrder();
            result+= "\nPostOrder: "+postOrder();

        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public String printLeaves() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        return printLeaves(root);
    }
    private String printLeaves(BTreeNode node){
       String result = "";

        if(node==null) return "";
        else{
            if (node.left==null&&node.right==null){
                result+= node.data+",";
            }else {
                result=printLeaves(node.left);
                result+=printLeaves(node.right);
            }
        }
        return result;
    }

    public String printNodes1Child() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        return printNodes1Child(root);
    }
    private String printNodes1Child(BTreeNode node) {
        String result = "";

        if (node == null) return "";

        if (node.left != null && node.right == null) {
            result += "Node: " + node.data + ", left son: " + node.left.data + "\n";
        }
        else if (node.left == null && node.right != null) {
            result += "Node: " + node.data + ", right son: " + node.right.data + "\n";
        }

        result += printNodes1Child(node.left);
        result += printNodes1Child(node.right);

        return result;
    }

    public String printNodes2Children() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        return printNodes2Children(root);
    }
    private String printNodes2Children(BTreeNode node) {
        String result = "";

        if (node == null) return "";

        // Si tiene exactamente dos hijos
        if (node.left != null && node.right != null) {
            result += "Node: " + node.data +
                    ", left son: " + node.left.data +
                    ", right son: " + node.right.data + "\n";
        }

        result += printNodes2Children(node.left);
        result += printNodes2Children(node.right);

        return result;
}
    public String printNodesWithChildren() throws TreeException {
        if (isEmpty())
            throw new TreeException("Binary Tree is empty");
        String result = "Binary tree - nodes with children\n";
        return result + printNodesWithChildren(root);
    }

    private String printNodesWithChildren(BTreeNode node) {
        String result = "";

        if (node == null) return "";

        boolean hasLeft = node.left != null;
        boolean hasRight = node.right != null;

        if (hasLeft || hasRight) {
            result += "Node: " + node.data;

            if (hasLeft) {
                result += ", left son: " + node.left.data;
            }

            if (hasRight) {
                result += ", right son: " + node.right.data;
            }

            result += "\n";
        }

        result += printNodesWithChildren(node.left);
        result += printNodesWithChildren(node.right);

        return result;
    }
    public int totalLeaves() throws TreeException {
        if (isEmpty())
            throw new TreeException("Binary Tree is empty");
        return totalLeaves(root);
    }

    private int totalLeaves(BTreeNode node) {
        if (node == null)
            return 0;

        if (node.left == null && node.right == null)
            return 1;

        return totalLeaves(node.left) + totalLeaves(node.right);
    }

}
