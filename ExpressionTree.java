/**ExpressionTree.java
 * An Expression Tree to return its eval, prefix, postfix and infix Strings
 */
public class ExpressionTree<AnyType extends Comparable<? super AnyType>> {

    //create root instance variable
    private ExpressionNode root;


    public ExpressionTree(String post){
        root = buildExpress(post);
    }

    public int eval(){
        return eval(root);
    }

    //t is root of tree
    private int eval(ExpressionNode t){
        //initialize x as something noticeable for debugging
        int x = -123;
        //postorder recursion, return the sum of right and left subtree
        if (t == null){
            return 0;
        }
        if (t.left != null && t.right != null){
            if(t.element.equals("+")){
                x = eval(t.left) + eval(t.right);
            }
            else if (t.element.equals("-")){
                x = eval(t.left) - eval(t.right);
            }
            else if (t.element.equals("*")){
                x = eval(t.left)*eval(t.right);
            }
            else if (t.element.equals("/")){
                x = eval(t.left)/eval(t.right);
            }
        }
        //if the left reference is null then return the int for that element
        if (t.left == null){
            return Integer.parseInt(t.element);
        }
        return x;
    }

    public String postfix(){
        return postfix(root);
    }

    //t is root of tree
    private String postfix(ExpressionNode t){
        String str = "";
        if(t == null){
            return null;
        }
        if(t.left != null && t.right != null){
            str = postfix(t.left)+" "+postfix(t.right)+" "+t.element;
        }
        if (t.left == null){
            return t.element;
        }
        return str;
    }

    public String prefix(){
        return prefix(root);
    }

    //t is root
    private String prefix(ExpressionNode t){
        String str = "";
        if (t == null){
            return null;
        }
        if (t.left != null && t.right != null){
            str = t.element+" "+prefix(t.left)+" "+prefix(t.right);
        }
        if (t.left == null){
            return t.element;
        }
        return str;
    }

    public String infix(){
        return infix(root);
    }

    private String infix(ExpressionNode t){
        String str = "";
        if (t == null){
            return null;
        }
        if (t.left != null && t.right != null){
            str="("+infix(t.left)+" "+t.element+" "+infix(t.right)+")";
        }
        if (t.left == null){
            return t.element;
        }
        return str;
    }

    private ExpressionNode buildExpress(String p){
        //split string based on white space into operators and operands
        String[] postfix = p.split("\\s");
        int c = 0;
        boolean valid = true;
        while(valid && c < postfix[0].length()){
            if (postfix[0].charAt(c) == '+')
                valid = false;
            if (postfix[0].charAt(c) == '-')
                valid = false;
            if (postfix[0].charAt(c) == '*')
                valid = false;
            if (postfix[0].charAt(c) == '/')
                valid = false;
            else
                c++;
        }
        //if the String has characters other than ints then its an error
        if (!valid){
            throw new RuntimeException();
        }
        MyStack<ExpressionNode> stack = new MyStack<ExpressionNode>();
        for(int i=0; i<postfix.length; i++){
            if(postfix[i].equals("+")){
                //create Expression Node for each insert
                ExpressionNode n = new ExpressionNode(postfix[i]);
                //set first pop to reference right child
                n.right = stack.pop();
                //second pop left child
                n.left = stack.pop();
                //push it back onto stack when done
                stack.push(n);
            }
            else if(postfix[i].equals("-")){
                ExpressionNode n = new ExpressionNode(postfix[i]);
                n.right = stack.pop();
                n.left = stack.pop();
                stack.push(n);
            }
            else if(postfix[i].equals("*")){
                ExpressionNode n = new ExpressionNode(postfix[i]);
                n.right = stack.pop();
                n.left = stack.pop();
                stack.push(n);
            }
            else if(postfix[i].equals("/")){
                ExpressionNode n = new ExpressionNode(postfix[i]);
                n.right = stack.pop();
                n.left = stack.pop();
                stack.push(n);
            }
            else {
                ExpressionNode n = new ExpressionNode(postfix[i]);
                stack.push(n);
            }
        }
        //return the node reference left in the stack when finished for loop
        ExpressionNode d = stack.pop();
        //if stack isn't empty after creating expression tree then throw
        //exception
        if(!stack.isEmpty()){
            throw new RuntimeException();
        }
        return d;
    }

    //Referenced Weiss Code for this inner class
    private static class ExpressionNode{
        ExpressionNode(String theEle){
            this(theEle, null, null);
        }
        ExpressionNode(String theEle, ExpressionNode lt,
                       ExpressionNode rt){
            element = theEle;
            left = lt;
            right = rt;
        }
        String element;
        ExpressionNode left;
        ExpressionNode right;
    }

}
