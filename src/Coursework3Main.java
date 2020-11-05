/**
 * Class that uses some functionality of Expressions.
 *
 * @author Carsten Fuhs
 */
public class Coursework3Main {

    /**
     * @param args
     *            Ignored.
     */
    public static void main(String[] args) {
        // ((1 + 2) * (5 * x))
        Expression e1 = new IntConstant(1);
        Expression e2 = new IntConstant(2);
        Expression e5 = new IntConstant(5);
        Expression x = new Variable("x");
        Expression plus = new PlusExpression(e1, e2);
        Expression times = new TimesExpression(e5, x);
        Expression bigTimes = new TimesExpression(plus, times);

        Expression[] exps = { e1, e2, e5, x, plus, times, bigTimes };

        Substitution s = new Substitution();
        s.put(new Variable("x"), new PlusExpression(e1, e5));

        for (Expression exp : exps) {
            System.out.println(exp + " has " + exp.numberOfNodes()
                    + " nodes and after applying " + s
                    + " the value " + exp.computeValue(s));
        }
    }

    /*
     * Output:
     * 
     * 1 has 1 nodes and after applying [x:=(1 + 5)] the value 1
     * 2 has 1 nodes and after applying [x:=(1 + 5)] the value 2
     * 5 has 1 nodes and after applying [x:=(1 + 5)] the value 5
     * x has 1 nodes and after applying [x:=(1 + 5)] the value 6
     * (1 + 2) has 3 nodes and after applying [x:=(1 + 5)] the value 3
     * (5 * x) has 3 nodes and after applying [x:=(1 + 5)] the value 30
     * ((1 + 2) * (5 * x)) has 7 nodes and after applying [x:=(1 + 5)] the value 90
     */
}
