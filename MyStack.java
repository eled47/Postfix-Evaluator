/**Ed Ellis
 * ele2117
 * MyStack.java
 * to implement a stack
 */
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyStack<AnyType> {

    //create linkedlist instance variable
    private LinkedList<AnyType> lt = new LinkedList<AnyType>();

    //set top equal to the top element then remove the top element and return
    // top
    public AnyType pop(){
        try {
            AnyType x = lt.removeLast();
            return x;
        }
        catch(NoSuchElementException e){
            System.out.println("Not a valid postfix expression");
        }
        return null;
    }

    //when you push add it to the end of the LinkedList
    public void push(AnyType cha){
            lt.add(cha);
        }

    //return the element at the top of the LinkedList
    public AnyType top(){
            return lt.getLast();
        }

    //return if the size of the LinkedList is 0
    public boolean isEmpty(){
            return lt.size() == 0;
        }

}

