import java.util.*;

class Calculatrice{
    public static int calculate(String[] tab){
        Deque<Integer> stack = new ArrayDeque<>();
        int value1, value2, resultat;
        for(String item : tab){
            switch(item){
                case "+":
                    value1 = stack.pop();
                    value2 = stack.pop();
                    resultat = value1 + value2;
                    stack.push(resultat);
                    break;
                case "-":
                    value1 = stack.pop();
                    value2 = stack.pop();
                    resultat = value2 - value1;
                    stack.push(resultat);
                    break;
                case "/":
                    value1 = stack.pop();
                    value2 = stack.pop();
                    resultat = value2 / value1;
                    stack.push(resultat);
                    break;
                case "x":
                    value1 = stack.pop();
                    value2 = stack.pop();
                    resultat = value2 * value1;
                    stack.push(resultat);
                    break;
                case "~":
                    value1 = stack.pop();
                    resultat = - value1;
                    stack.push(resultat);
                    break;
                default:
                    stack.push(Integer.parseInt(item));
            }
        }
        return stack.pop();
    }





}