package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BTreeTest {

    @Test
    void test() {
        BTree bTree = new BTree();


        for (int i = 0; i < 30; i++) {
            bTree.add(util.Utility.random(50));
        }
        System.out.println(bTree);

        try {
            System.out.println("BTree size: "+bTree.size());
            System.out.println("BTree leaves: "+bTree.printLeaves());

            for (int i = 0; i < 20; i++) {
                int value = util.Utility.random(50);
                System.out.println(
                        bTree.contains(value)
                        ?"The value ["+value+"] exists in the binary tree"
                        :"The value ["+value+"] does not in the binary tree"
                );
                if(bTree.contains(value)){
                    bTree.remove(value);
                    System.out.println("The value ["+value+"] has been removed");
                }
            }
            System.out.println(bTree);
            System.out.println("BTree Preorder Height: "+bTree.preOrderHeight());
            System.out.println("\n\n\n\n");
            System.out.println("Binary tree - leaves:\n"+bTree.printLeaves());
            System.out.println("Binary tree - nodes 1 child\n"+bTree.printNodes1Child());
            System.out.println("Binary tree - nodes 2 child\n"+bTree.printNodes2Children());
            System.out.println(bTree.printNodesWithChildren());
            System.out.println("Total leaves: " + bTree.totalLeaves());


        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testHeight(){
        BTree btree = new BTree();
        btree.add(1);
        btree.add(1);
        btree.add(1);
        btree.add(1);
        btree.add(1);
        btree.add(1);
        btree.add(1);
        try {btree.remove(1);
            System.out.println(btree);


        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
    }
}