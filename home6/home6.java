package home6;

/* хорошее описание логики https://medium.com/@dimko1/алгоритмы-обход-дерева-ed54848c2d47
 * 1(3(31,511(202,nil)),2(4,5))
 */
public class home6 {

    public static void main(String[] params) {

        tree root = new tree(1,
                new tree(3,
                        new tree(31),
                        new tree(511,
                                new tree(202),
                                null)),
                new tree(2,
                        new tree(4),
                        new tree(5)));

        System.out.println("deepPreOrder");
        root.deepPreOrder(root);
        System.out.println("\ndeepInOrder");
        root.deepInOrder(root);
        System.out.println("\ndeepPostOrder");
        root.deepPostOrder(root);
        System.out.println("\nbreadthOrder");
        root.breadthOrder(root);

    }

    /* ////////////////////////////////////////////////////////////////////// */

    // элемент c сылкой
    private static class Node<T> {
        private T obj;
        private Node<T> next;

        public Node(T obj) {
            this.obj = obj;
        }
    }

    private static class myStack<T> {

        private Node<T> first = null;

        public void push(T data) {
            Node<T> nFirst = new Node<T>(data);
            nFirst.next = first;
            first = nFirst;
        }

        public T pop() {
            if (first != null) {
                Node<T> popFirst = first;
                first = first.next;
                return popFirst.obj;
            } else
                return null;
        }

        public boolean isEmpty() {
            return first == null;
        }

    }

    private static class myQueue<T> {

        private Node<T> first = null;
        private Node<T> last = null;

        public void add(T data) {
            Node<T> nextNode = new Node<T>(data);
            if (first == null) {
                first = nextNode;
            } else {
                last.next = nextNode;
            }
            last = nextNode;
        }

        public T poll() {
            if (first != null) {
                Node<T> pollFirst = first;
                first = first.next;
                return pollFirst.obj;
            } else
                return null;
        }

        public boolean isEmpty() {
            return first == null;
        }

    }

    /* ////////////////////////////////////////////////////////////////////// */
    static class tree {
        int value;
        tree left;
        tree right;

        public tree(int value, tree left, tree right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public tree(int value) {
            this.value = value;
        }

        // Pre-order сначала корни потом листья
        public void deepPreOrder(tree node) {
            if (node == null)
                return;

            System.out.print(node.value);
            if (node.left != null || node.right != null)
                System.out.print("(");
            if (node.left != null) {
                deepPreOrder(node.left);
                System.out.print(",");
            } else if (node.right != null)
                System.out.print("null,");

            if (node.right != null)
                deepPreOrder(node.right);
            else if (node.left != null)
                System.out.print(",null");

            if (node.left != null || node.right != null)
                System.out.print(")");
        }

        // дети подом родители
        public void deepInOrder(tree node) {
            if (node == null)
                return;

            if (node.left != null || node.right != null)
                System.out.print("(");
            if (node.left != null) {
                deepInOrder(node.left);
                System.out.print(",");
            } else if (node.right != null)
                System.out.print("null,");

            if (node.left != null || node.right != null)
                System.out.print(node.value + ",");
            else
                System.out.print(node.value);

            if (node.right != null)
                deepInOrder(node.right);

            else if (node.left != null)
                System.out.print("null");

            if (node.left != null || node.right != null)
                System.out.print(")");
        }

        // сначала листья
        public void deepPostOrder(tree node) {
            if (node == null)
                return;

            if (node.left != null || node.right != null)
                System.out.print("(");
            if (node.left != null) {
                deepPostOrder(node.left);
                System.out.print(",");
            } else if (node.right != null)
                System.out.print("null,");

            if (node.right != null)
                deepPostOrder(node.right);
            else if (node.left != null)
                System.out.print("null");

            if (node.left != null || node.right != null)
                System.out.print(")");
            System.out.print(node.value);

        }

        // обход в ширину
        public void breadthOrder(tree node) {
            myQueue<tree> mQueue = new myQueue<tree>();
            StringBuilder ValuesBuilder = new StringBuilder();
            mQueue.add(node);

            while (!mQueue.isEmpty()) {
                tree tmpNode = mQueue.poll();
                ValuesBuilder.append(tmpNode.value).append(",");

                if (tmpNode.left != null) {
                    mQueue.add(tmpNode.left);
                }
                if (tmpNode.right != null) {
                    mQueue.add(tmpNode.right);
                }
            }
            System.out.print(ValuesBuilder.toString());

        }

    }
}

/* >>>
 * deepPreOrder
 * 1(3(31,511(202,,null)),2(4,5))
 * deepInOrder
 * ((31,3,(202,511,null)),1,(4,2,5))
 * deepPostOrder
 * ((31,(202,null)511)3,(4,5)2)1
 * breadthOrder
 * 1,3,2,31,511,4,5,202,
 */