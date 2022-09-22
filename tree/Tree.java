package tree;

import java.util.List;
import java.util.ArrayList;
 
public class Tree {

    public static void main(String[] params) {

        Tree2 solveTree = new Tree2(10);
        solveTree.getpath("");
        solveTree.toSimpleArray();

        System.out.println("\n\n");
        for (String path : solveTree.all_paths) {
            System.out.println(path);
        }
    }

    static class Tree2 {
        int value;
        Tree2 add;
        Tree2 parent;
        Tree2 mul;
        static int findValue;
        static String[] all_paths;
        static List<String> paths = new ArrayList<String>();

        // Констуктор корня
        public Tree2(int findValue) {
            this.findValue = findValue;
            this.value = 1;
            this.parent = null;

            int nextValue = this.value + 1;
            if (nextValue <= findValue) {
                this.add = new Tree2(nextValue, this);
            } else
                this.add = null;

            nextValue = this.value * 2;
            if (nextValue <= findValue) {
                this.mul = new Tree2(nextValue, this);
            } else
                this.mul = null;
        }

        // листья
        public Tree2(int currentValue, Tree2 parent) {

            this.value = currentValue;
            this.parent = parent;

            int nextValue = this.value + 1;
            if (nextValue <= findValue) {
                //System.out.println("next == " + nextValue);
                this.add = new Tree2(nextValue, this);
            } else
                this.add = null;

            nextValue = this.value * 2;
            if (nextValue <= findValue) {
                this.mul = new Tree2(nextValue, this);
            } else
                this.mul = null;

        }

        public void toSimpleArray() {
            System.out.println("paths.size() " + paths.size());

            String[] simpleArray = new String[paths.size()];
            paths.toArray(simpleArray);
            this.all_paths = simpleArray;
        }

        //
        public void getpath(String path) {

            // Если дошли до решения
            if (this.value == findValue) {
                System.out.println("solution path " + path + " this.value= " + this.value);
                this.paths.add(path);
            } else {
                if (this.add != null) {
                    System.out.println("add path " + path + " this.value= " + this.value);
                    String currentPath = path + "add ";
                    
                    this.add.getpath(currentPath);
                }

                if (this.mul != null) {
                    
                    System.out.println("mul path " + path + " this.value= " + this.value);
                    String currentPath = path + "mull ";
                    this.mul.getpath(currentPath);
                }
            }
        }

    }

} 


/* >>>
add path  this.value= 1
add path add  this.value= 2
add path add add  this.value= 3
add path add add add  this.value= 4
add path add add add add  this.value= 5
add path add add add add add  this.value= 6
add path add add add add add add  this.value= 7
add path add add add add add add add  this.value= 8
add path add add add add add add add add  this.value= 9
solution path add add add add add add add add add  this.value= 10
mul path add add add add  this.value= 5
solution path add add add add mull  this.value= 10
mul path add add add  this.value= 4
add path add add add mull  this.value= 8
add path add add add mull add  this.value= 9
solution path add add add mull add add  this.value= 10
mul path add add  this.value= 3
add path add add mull  this.value= 6
add path add add mull add  this.value= 7
add path add add mull add add  this.value= 8
add path add add mull add add add  this.value= 9
solution path add add mull add add add add  this.value= 10
mul path add  this.value= 2
add path add mull  this.value= 4
add path add mull add  this.value= 5
add path add mull add add  this.value= 6
add path add mull add add add  this.value= 7
add path add mull add add add add  this.value= 8
add path add mull add add add add add  this.value= 9
solution path add mull add add add add add add  this.value= 10
mul path add mull add  this.value= 5
solution path add mull add mull  this.value= 10
mul path add mull  this.value= 4
add path add mull mull  this.value= 8
add path add mull mull add  this.value= 9
solution path add mull mull add add  this.value= 10
mul path  this.value= 1
add path mull  this.value= 2
add path mull add  this.value= 3
add path mull add add  this.value= 4
add path mull add add add  this.value= 5
add path mull add add add add  this.value= 6
add path mull add add add add add  this.value= 7
add path mull add add add add add add  this.value= 8
add path mull add add add add add add add  this.value= 9
solution path mull add add add add add add add add  this.value= 10
mul path mull add add add  this.value= 5
solution path mull add add add mull  this.value= 10
mul path mull add add  this.value= 4
add path mull add add mull  this.value= 8
add path mull add add mull add  this.value= 9
solution path mull add add mull add add  this.value= 10
mul path mull add  this.value= 3
add path mull add mull  this.value= 6
add path mull add mull add  this.value= 7
add path mull add mull add add  this.value= 8
add path mull add mull add add add  this.value= 9
solution path mull add mull add add add add  this.value= 10
mul path mull  this.value= 2
add path mull mull  this.value= 4
add path mull mull add  this.value= 5
add path mull mull add add  this.value= 6
add path mull mull add add add  this.value= 7
add path mull mull add add add add  this.value= 8
add path mull mull add add add add add  this.value= 9
solution path mull mull add add add add add add  this.value= 10
mul path mull mull add  this.value= 5
solution path mull mull add mull  this.value= 10
mul path mull mull  this.value= 4
add path mull mull mull  this.value= 8
add path mull mull mull add  this.value= 9
solution path mull mull mull add add  this.value= 10

paths.size() 14

 
add add add add add add add add add
add add add add mull
add add add mull add add
add add mull add add add add
add mull add add add add add add
add mull add mull
add mull mull add add
mull add add add add add add add add
mull add add add mull
mull add add mull add add
mull add mull add add add add
mull mull add add add add add add
mull mull add mull
mull mull mull add add

*/