{% include navigation.html %}

## Data Structures Work

### TT3 Data Structures (4/4/2022) (Sorts)

[Runtime Link](https://replit.com/@PaulBokelman/Data-Structures-Challenge-4?v=1)

**Bubble Sort (n^2)**

```java
import java.util.ArrayList;

public class BubbleSort {
    public static void bubbleSort(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.size() - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }
}
```

**Insertion Sort (n^2)**

```java
import java.util.ArrayList;

public class InsertionSort {
    public static void insertionSort(ArrayList<Integer> arr) {
        int n = arr.size();
        for (int i = 1; i < n; ++i) {
            int key = arr.get(i);
            int j = i - 1;
            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j));
                j = j - 1;
            }
            arr.set(j + 1, key);
        }

    }
}
```

**Merge Sort (n \* log(n))**

```java
import java.util.ArrayList;

public class MergeSort {
    public static void mergeSort(ArrayList<Integer> arr) {
        int n = arr.size();
        if (n < 2)
            return;
        ArrayList<Integer> left = new ArrayList<Integer>();
        ArrayList<Integer> right = new ArrayList<Integer>();
        for (int i = 0; i < n / 2; i++) {
            left.add(arr.get(i));
        }
        for (int i = n / 2; i < n; i++) {
            right.add(arr.get(i));
        }
        mergeSort(left);
        mergeSort(right);
        merge(left, right, arr);
    }

    private static void merge(ArrayList<Integer> left, ArrayList<Integer> right, ArrayList<Integer> arr) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i) < right.get(j)) {
                arr.set(k, left.get(i));
                i++;
            } else {
                arr.set(k, right.get(j));
                j++;
            }
            k++;
        }
        while (i < left.size()) {
            arr.set(k, left.get(i));
            i++;
            k++;
        }
        while (j < right.size()) {
            arr.set(k, right.get(j));
            j++;
            k++;
        }
    }
}
```

### TT2 Data Structures (3/29/2022) (Calculator)

```java
package tt2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import tt2.Stack;

import java.util.*;

public class Calculator {
    // Key instance variables
    private final String expression;
    private ArrayList<String> tokens;
    private ArrayList<String> reverse_polish;
    private Double result = 0.0;

    // Helper definition for supported operators
    private final Map<String, Integer> OPERATORS = new HashMap<>();
    {
        // Map<"token", precedence>
        OPERATORS.put("sqrt", 2);
        OPERATORS.put("^", 2);
        OPERATORS.put("*", 3);
        OPERATORS.put("/", 3);
        OPERATORS.put("%", 3);
        OPERATORS.put("+", 4);
        OPERATORS.put("-", 4);
    }

    // Helper definition for supported operators
    private final Map<String, Integer> SEPARATORS = new HashMap<>();
    {
        // Map<"separator", not_used>
        SEPARATORS.put(" ", 0);
        SEPARATORS.put("(", 0);
        SEPARATORS.put(")", 0);
    }

    // Create a 1 argument constructor expecting a mathematical expression
    public Calculator(String expression) {
        // original input
        this.expression = expression;

        // parse expression into terms
        this.termTokenizer();

        // place terms into reverse polish notation
        this.tokensToReversePolishNotation();

        // calculate reverse polish notation
        this.rpnToResult();
    }

    // Test if token is an operator
    private boolean isOperator(String token) {
        // find the token in the hash map
        return OPERATORS.containsKey(token);
    }

    // Test if token is an seperator
    private boolean isSeperator(String token) {
        // find the token in the hash map
        return SEPARATORS.containsKey(token);
    }

    // Compare precedence of operators.
    private Boolean isPrecedent(String token1, String token2) {
        // token 1 is precedent if it is greater than token 2
        return (OPERATORS.get(token1) - OPERATORS.get(token2) >= 0);
    }

    // Term Tokenizer takes original expression and converts it to ArrayList of
    // tokens
    private void termTokenizer() {
        // contains final list of tokens
        this.tokens = new ArrayList<>();

        int start = 0; // term split starting index
        StringBuilder multiCharTerm = new StringBuilder(); // term holder
        for (int i = 0; i < this.expression.length(); i++) {
            Character c = this.expression.charAt(i);
            if (isOperator(c.toString()) || isSeperator(c.toString())) {
                // 1st check for working term and add if it exists
                if (multiCharTerm.length() > 0) {
                    tokens.add(this.expression.substring(start, i));
                }
                // Add operator or parenthesis term to list
                if (c != ' ') {
                    tokens.add(c.toString());
                }
                // Get ready for next term
                start = i + 1;
                multiCharTerm = new StringBuilder();
            } else {
                // multi character terms: numbers, functions, perhaps non-supported elements
                // Add next character to working term
                multiCharTerm.append(c);
            }

        }
        // Add last term
        if (multiCharTerm.length() > 0) {
            tokens.add(this.expression.substring(start));
        }
    }

    // Takes tokens and converts to Reverse Polish Notation (RPN), this is one where
    // the operator follows its operands.
    private void tokensToReversePolishNotation() {
        // contains final list of tokens in RPN
        this.reverse_polish = new ArrayList<>();

        // stack is used to reorder for appropriate grouping and precedence
        Stack tokenStack = new Stack();
        for (String token : tokens) {
            switch (token) {
                // If left bracket push token on to stack
                case "(":
                    tokenStack.push(token);
                    break;
                case ")":
                    while (tokenStack.peek() != null && !tokenStack.peek().equals("(")) {
                        reverse_polish.add((String) tokenStack.pop());
                    }
                    tokenStack.pop();
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                case "^":
                case "sqrt":
                    // While stack
                    // not empty AND stack top element
                    // and is an operator
                    while (tokenStack.peek() != null && isOperator((String) tokenStack.peek())) {
                        if (isPrecedent(token, (String) tokenStack.peek())) {
                            reverse_polish.add((String) tokenStack.pop());
                            continue;
                        }
                        break;
                    }
                    // Push the new operator on the stack
                    tokenStack.push(token);
                    break;
                default: // Default should be a number, there could be test here
                    this.reverse_polish.add(token);
            }
        }
        // Empty remaining tokens
        while (tokenStack.peek() != null) {
            reverse_polish.add((String) tokenStack.pop());
        }

    }

    // Takes RPN and produces a final result
    private void rpnToResult() {
        // Stack used to hold calculation while process RPN
        Stack stack = new Stack();

        // for loop to process RPN
        for (String token : this.reverse_polish) {
            // If the token is a number
            if (!isOperator(token)) {
                // Push number to stack
                stack.push(token);
            }
            // else
            else {
                // Pop the two top entries
                Double x1 = Double.valueOf((String) stack.pop());
                Double x0 = Double.valueOf((String) stack.pop());

                // Based off of Token operator calculate result
                Double result;
                switch (token) {
                    case "+":
                        result = x0 + x1;
                        break;
                    case "-":
                        result = x0 - x1;
                        break;
                    case "*":
                        result = x0 * x1;
                        break;
                    case "/":
                        result = x0 / x1;
                        break;
                    case "%":
                        result = x0 % x1;
                        break;
                    case "^":
                        result = Math.pow(x0, x1);
                        break;
                    case "sqrt":
                        result = Math.sqrt(x0);
                        break;
                    default:
                        result = 0.0;
                }

                // Push result back onto the stack
                stack.push(String.valueOf(result));
            }
        }
        // Pop final result and set as final result for expression
        this.result = Double.valueOf((String) stack.pop());
    }

    // Print the expression, terms, and result
    public String toString() {
        return ("Original expression: " + this.expression + "\n" +
                "Tokenized expression: " + this.tokens.toString() + "\n" +
                "Reverse Polish Notation: " + this.reverse_polish.toString() + "\n" +
                "Final result: " + String.format("%.2f", this.result));
    }

    // Tester method
    public static void main(String[] args) {
        // Random set of test cases
        Calculator simpleMath = new Calculator("100 + 200  * 3");
        System.out.println("Simple Math\n" + simpleMath);

        System.out.println();

        Calculator parenthesisMath = new Calculator("(100 + 200)  * 3");
        System.out.println("Parenthesis Math\n" + parenthesisMath);

        System.out.println();

        Calculator fractionMath = new Calculator("100.2 - 99.3");
        System.out.println("Fraction Math\n" + fractionMath);

        System.out.println();

        Calculator moduloMath = new Calculator("300 % 200");
        System.out.println("Modulo Math\n" + moduloMath);

        System.out.println();

        Calculator divisionMath = new Calculator("300/200");
        System.out.println("Division Math\n" + divisionMath);

        System.out.println();

        Calculator multiplicationMath = new Calculator("300 * 200");
        System.out.println("Multiplication Math\n" + multiplicationMath);

        System.out.println();

        Calculator allMath = new Calculator("200 % 300 + 5 + 300 / 200 + 1 * 100");
        System.out.println("All Math\n" + allMath);

        System.out.println();

        Calculator allMath2 = new Calculator("200 % (300 + 5 + 300) / 200 + 1 * 100");
        System.out.println("All Math2\n" + allMath2);

        System.out.println();

        Calculator allMath3 = new Calculator("200%(300+5+300)/200+1*100");
        System.out.println("All Math3\n" + allMath3);

        System.out.println();

        Calculator expMath = new Calculator("8 ^ 4");
        System.out.println("Exponential Math\n" + expMath);

        System.out.println();

        String userInput;
        Scanner input = new Scanner(System.in);

        System.out.println("What equation would you like to calculate?");

        userInput = input.next();
        Calculator test = new Calculator(userInput);
        System.out.print("Result:\n" + test);

        /**
         * working on bug
         * Calculator sqrtMath = new Calculator("sqrt9");
         * System.out.println("Square Root Math\n" + sqrtMath);
         * System.out.println();
         */
    }
}
```

### TT1 Data Structures (3/21/2022)

[Repl for challenge](https://replit.com/@PaulBokelman/Challenge-TT1#LinkedLists.java)

**Linked Lists**

- a Linked list is a type of dynamic array. The list increases if we add/remove items. Elements are not stored continuously, there is no need to increase the size. LinkedList is implemented using the doubly linked list data structure. Between a normal linked list and a doubly LinkedList is that a doubly linked list contains an extra pointer, typically called the previous pointer, together with the next pointer and data which are there in the singly linked list.

**Java Generics**

- Java Generics are a mechanism that allows us to write code that can work with multiple types of objects without having to write separate code for each type.

**Queue Add and Delete**

- The Queue interface in the java.util package extends the interface o Collection is used to hold elements processed in FIFO(First In First Out) order. It is an ordered list of objects with its use limited to insert elements at the end of the list and deleting elements from the start of the list, (i.e.), it follows the FIFO or the First-In-First-Out principle.

- `remove()` of Interface (queue) returns and removes the element in the container at the front. It deletes the head of the container. The method throws an NoSuchElementException when the Queue is empty.

**Merge Queues**

- In order to merge 2 queues there is a list of steps you must go through:

1. Make pointers for both of the queues.
1. Make another queue, add the node having least value from the other two queues(the pointer's node having the smallest value) to it and move that pointer to the next node.
1. Again compare the node's values and add the node having the smallest value to the queue and move that pointer to the next node.
1. Keep doing this until rear of both the queues are reached. bear

**Reversing a Queue**

- Reversing the queue is simply reinserting the data in an inverse order. So now our task is to choose such data-structure which can serve the purpose. According to the approach, the data-structure should have the property of ‘LIFO’ as the last element to be inserted in the data structure should actually be the first element of the reversed queue. The stack could help in approaching this problem. This will be a two-step process:

1. Pop the elements from the queue and insert into the stack. (Topmost element of the stack is the last element of the queue)
1. Pop the elements of the stack to insert back into the queue. (The last element is the first one to be inserted into the queue) duck
   today

**Build Stack**

- There is a multitude of different functions one can perform with a stack, and with these are pretty much endless possibilities as to what you can do with these functions:

1. push (to add a new item to the top)
1. pop (to remove the most top item)
1. peek (to get the most top item)
1. isEmpty (to check whether the stack is empty)
1. size (to get the size of the stack)
1. search (to search for objects)
1. Here's an example of some functions above at work: deer
