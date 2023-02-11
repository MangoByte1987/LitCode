public class BinarySearchTree<T> {
    TreeNode<Integer> root ;
    private static class TreeNode<T>{
        T data;
        TreeNode<T> left,right;
        TreeNode(T data){
            this.data=data;
            left=null;
            right=null;
        }
    }
    private void printInOrder(TreeNode<Integer> node){
        if(node == null)
            return;
        printInOrder(node.left);
        System.out.print(node.data + " ");
        printInOrder(node.right);
    }
    private void printPreOrder(TreeNode<Integer> node){
        if(node == null)
            return;
        System.out.print(node.data + " ");
        printInOrder(node.left);
        printInOrder(node.right);
    }
    private void printPostOrder(TreeNode<Integer> node){
        if(node == null)
            return;
        printInOrder(node.left);
        printInOrder(node.right);
        System.out.print(node.data + " ");
    }
    private boolean isBST(TreeNode<Integer> node){
        return isBSTUtil(node,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    private boolean isBSTUtil(TreeNode<Integer> node, int min, int max) {
        if (node == null)
            return true;
        if (node.data < min || node.data > max)
            return false;
        return (isBSTUtil(node.left, min, node.data - 1) &&
                isBSTUtil(node.right,node.data +1 ,max)) ;
    }
    private void insert(int key) {
        root = insertNode(root, key);
    }

    private TreeNode<Integer> insertNode(TreeNode<Integer> node , int key){
        if(node == null){
            node = new TreeNode<>(key);
           return node;
        }
        if(key < node.data)
            node.left = insertNode(node.left,key);
        else if(key > node.data)
            node.right = insertNode(node.right,key);
        return  node;
    }

    private TreeNode<Integer> inOrderSuccesser(TreeNode<Integer> node){
        if(node.right !=null){
                TreeNode<Integer> current = node.right;
                while(current.left!=null) {
                    current = current.left;
                }
                return current;
        } else {
            TreeNode<Integer> current = node;
            TreeNode<Integer> parent = null;
            while(current != null){
                if(current.data > node.data){
                    parent = current;
                    current = current.left;
                }else{
                    current = current.right;
                }
            }
            return  parent;
        }
    }
    public static void main(String [] args){

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.root = new TreeNode<>(4);
        bst.root.left = new TreeNode<>(2);
        bst.root.right = new TreeNode<>(5);
        bst.root.left.left = new TreeNode<>(1);
        bst.root.left.right = new TreeNode<>(3);
        TreeNode<Integer> temp = bst.root;
        bst.printInOrder(bst.root);
        bst.root = temp;
        System.out.println("");
        bst.printPreOrder(bst.root);
        bst.root = temp;
        System.out.println("");
        bst.printPostOrder(bst.root);
        bst.root = temp;
        System.out.println("\n" + bst.isBST(bst.root));

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        tree.printInOrder(tree.root);
    }

}
