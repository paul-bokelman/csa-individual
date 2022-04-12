import java.util.*;

public class Calculator {
    private final String expression;
    private ArrayList<String> tokens;
    private ArrayList<String> reverse_polish;
    private Double result;

    private final Map<String, Integer> OPERATORS = new HashMap<>();
    // all operators
    // takes into account the order of operations
    {
        OPERATORS.put("*", 3);
        OPERATORS.put("/", 3);
        OPERATORS.put("%", 3);
        OPERATORS.put("+", 4);
        OPERATORS.put("-", 4);
        OPERATORS.put("^", 2);
        OPERATORS.put("sqrt", 2);
        OPERATORS.put("pythagorean", 2);
    }

    private final Map<String, Integer> SEPARATORS = new HashMap<>();
    {
        SEPARATORS.put(" ", 0);
        SEPARATORS.put("(", 0);
        SEPARATORS.put(")", 0);
    }

    public String toString() {
        return ("Original expression: " + this.expression + "\n" +
                "Tokenized expression: " + this.tokens.toString() + "\n" +
                "Reverse Polish Notation: " + this.reverse_polish.toString() + "\n" +
                "Final result: " + String.format("%.2f", this.result));
    }

    public static boolean isNumeric(String n) {
        try {
            double d = Double.parseDouble(n);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private void rpnToResult() {
        // uses reverse polish notation to calculate the result: if parsed token is a
        // number it is added to the stack, if it is an operator it is popped two
        // numbers from the stack and the result is calculated and added to the stack,
        // obeys the order of operations from previously set order.
        Stack calculation = new Stack();
        for (int i = 0; i < this.reverse_polish.size(); i++) {
            if (isNumeric(this.reverse_polish.get(i))) {
                calculation.push(this.reverse_polish.get(i));
            } else {
                if (Objects.equals(this.reverse_polish.get(i), "sqrt")) {
                    Double num = Double.parseDouble(calculation.pop().toString());
                    calculation.push(Math.pow(num, 0.5));
                } else {
                    Double num2 = Double.parseDouble(calculation.pop().toString());
                    Double num1 = Double.parseDouble(calculation.pop().toString());
                    switch (this.reverse_polish.get(i)) {
                        case "+":
                            calculation.push(num1 + num2);
                            break;
                        case "-":
                            calculation.push(num1 - num2);
                            break;
                        case "*":
                            calculation.push(num1 * num2);
                            break;
                        case "/":
                            calculation.push(num1 / num2);
                            break;
                        case "%":
                            calculation.push(num1 % num2);
                            break;
                        case "^":
                            calculation.push(Math.pow(num1, num2));
                        case "pythagorean":
                            calculation.push(Math.pow((Math.pow(num1, 2) + Math.pow(num2, 2)), 0.5));
                    }
                }
            }
        }
        result = (Double) calculation.pop();
    }

    public Calculator(String expression) {
        this.expression = expression;
        this.termTokenizer();
        this.tokensToReversePolishNotation();
        this.rpnToResult();
    }

    private void termTokenizer() {
        this.tokens = new ArrayList<>();
        int start = 0;
        StringBuilder multiCharTerm = new StringBuilder();
        for (int i = 0; i < this.expression.length(); i++) {
            Character c = this.expression.charAt(i);
            if (isOperator(c.toString()) || isSeperator(c.toString())) {
                if (multiCharTerm.length() > 0) {
                    tokens.add(this.expression.substring(start, i));
                }
                if (c != ' ') {
                    tokens.add(c.toString());
                }
                start = i + 1;
                multiCharTerm = new StringBuilder();
            } else {
                multiCharTerm.append(c);
            }

        }
        if (multiCharTerm.length() > 0) {
            tokens.add(this.expression.substring(start));
        }
    }

    private void tokensToReversePolishNotation() {
        this.reverse_polish = new ArrayList<>();
        Stack tokenStack = new Stack();
        for (String token : tokens) {
            switch (token) {
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
                case "pythagorean":
                    while (!tokenStack.empty() && isOperator((String) tokenStack.peek())) {
                        if (isPrecedent(token, (String) tokenStack.peek())) {
                            reverse_polish.add((String) tokenStack.pop());
                            continue;
                        }
                        break;
                    }
                    tokenStack.push(token);
                    break;
                default:
                    this.reverse_polish.add(token);
            }
        }
        while (!tokenStack.empty()) {
            reverse_polish.add((String) tokenStack.pop());
        }

    }

    private boolean isOperator(String token) {
        return OPERATORS.containsKey(token);
    }

    private boolean isSeperator(String token) {
        return SEPARATORS.containsKey(token);
    }

    private Boolean isPrecedent(String token1, String token2) {
        return (OPERATORS.get(token1) - OPERATORS.get(token2) >= 0);
    }

    public static void main(String[] args) {
        Calculator simpleMath = new Calculator("100 + 200 * 3");
        System.out.println("Simple Math\n" + simpleMath);
        System.out.println("\n-------------------------\n");

        Calculator parenthesisMath = new Calculator("(100 + 200) * 3");
        System.out.println("Parenthesis Math\n" + parenthesisMath);
        System.out.println("\n-------------------------\n");

        Calculator allMath = new Calculator("200 % 300 + 5 + 300 / 200 + 1 * 100");
        System.out.println("All Math\n" + allMath);
        System.out.println("\n-------------------------\n");

        Calculator allMath2 = new Calculator("200 % (300 + 5 + 300) / 200 + 1 * 100");
        System.out.println("All Math2\n" + allMath2);
        System.out.println("\n-------------------------\n");

        Calculator allMath3 = new Calculator("200 % (300 + 5 + 300) / 200 + 1 * 100 + 10 ^ 3");
        System.out.println("All Math3\n" + allMath3);
        System.out.println("\n-------------------------\n");

        Calculator allMath4 = new Calculator("200 % (300 + 5 + 300) / 200 + 1 * 100 + 10 ^ 3 + sqrt 4");
        System.out.println("All Math4\n" + allMath4);
        System.out.println("\n-------------------------\n");

        Calculator pythagorean = new Calculator("5 pythagorean 12");
        System.out.println("pythagorean theorem\n" + pythagorean);
        System.out.println("\n-------------------------\n");
    }
}