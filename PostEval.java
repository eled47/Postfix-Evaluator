/**Problem1.java
 * Hardcode an expression tree to test expression tree class
 */
public class Problem1 {

    public static void main (String[] args){
        ExpressionTree tree =
                new ExpressionTree("1 2 + 3 4 - +");
        System.out.println(tree.eval());
        System.out.println(tree.postfix());
        System.out.println(tree.prefix());
        System.out.println(tree.infix());
    }
}
