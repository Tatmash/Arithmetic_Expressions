import static org.junit.Assert.*;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;
/**
 * A test suite with the tests listed below,
 * in the format of JUnit.
 *
 * @author Janos Nagy
 *
 * */

public class ExpressionTest {
    private static final int TIMEOUT = 3000;

    /**
     * Test that the expression (x + 1) has 3 nodes.
     */
    @Test(timeout = TIMEOUT)
    public void test_1() {

        Expression y = new IntConstant(1);
        Expression x = new Variable("x");
        Expression t1 = new PlusExpression(x, y);

        int expected = 3;
        int result = t1.numberOfNodes();
        assertEquals("", expected, result);
    }

    /**
     * Test that after construction of an empty substitution and putting the mapping from x to
     * 2 into the substitution, getting the expression for the variable x returns the expression 2.
     */
    @Test(timeout = TIMEOUT)
    public void test_2() {
        Substitution s = new Substitution();
        Variable x = new Variable("x");

        s.put(x, new IntConstant(2));

        Expression expected = new IntConstant(2);
        Expression result = s.get(x);
        assertEquals("", expected, result);

    }

    /**
     * Test that after construction of an empty substitution and putting the mapping from x to
     * 2 into the substitution, getting the expression for the variable y returns the expression y.
     */
    @Test(timeout = TIMEOUT)
    public void test_3() {
        Substitution s = new Substitution();
        Variable x = new Variable("x");
        s.put(x, new IntConstant(2));

        Variable y = new Variable("y");

        String expected = ("y");
        String result = y.getName();

        assertEquals("", expected, result);
    }


    /**
     * Test that after construction of an empty substitution, then putting the mapping from x
     * to 2 into the substitution, and then putting the mapping from x to 3 into the substitution,
     * getting the expression for the variable x returns the expression 3.
     */
    @Test(timeout = TIMEOUT)
    public void test_4() {
        Substitution s = new Substitution();
        Variable x = new Variable("x");
        s.put(x, new IntConstant(2));
        s.put(x, new IntConstant(3));

        Expression expected = new IntConstant(3);
        Expression result = s.get(x);
        assertEquals("", expected, result);
    }

    /**
     * Test that after construction of an empty substitution, then putting the mapping from x
     * to 2 into the substitution, and then forgetting the mapping for x, getting the expression
     * for the variable x returns the expression x.
     */
    @Test(timeout = TIMEOUT)
    public void test_5() {
        Substitution s = new Substitution();
        Variable x = new Variable("x");
        s.put(x, new IntConstant(2));
        s.forget(x);

        String x2 = "x";
        Expression expected = new Variable(x2);
        Expression result = s.get(x);
        assertEquals("", expected, result);

    }

    /**
     *Test that applying the substitution that maps x to 2 to the expression (x + 1) yields an
     * expression (2 + 1).
     */
    @Test(timeout = TIMEOUT)
    public void test_6() {
        Substitution s = new Substitution();
        Variable x = new Variable("x");
        s.put(x, new IntConstant(2));

        Expression e = new IntConstant(1);
        Expression e2 = new PlusExpression(x, e);

        Expression expected  = new PlusExpression(new IntConstant(2),new IntConstant(1));
        Expression result = e2.applySubstitution(s);
        assertEquals("", expected, result);
    }

    /**
     * Test that the set of variables of the expression (1 + 1) is the empty set.
     */
    @Test(timeout = TIMEOUT)
    public void test_7(){
        Expression e1 = new IntConstant(1);
        Expression e2 = new IntConstant(1);
        Expression e3 = new PlusExpression(e1,e2);

        boolean expected = true ;
        boolean result = e3.isVariableFree();
        assertEquals("", expected, result);
    }

    /**
     * Test that the set of variables of the expression ((x + y) * (x + 2)) is {x, y}.
     */
    @Test(timeout = TIMEOUT)
    public void test_8(){
        Variable x = new Variable("x");
        Variable y = new Variable("y");

        Expression e1 = new IntConstant(2);
        Expression e2 = new PlusExpression(x,y);
        Expression e3 = new PlusExpression(x,e1);
        Expression e4 = new TimesExpression(e2,e3);

        Set<Variable> expected = new LinkedHashSet<>();
        expected.add(x);
        expected.add(y);
        Set<Variable> result = new LinkedHashSet<>();
        e4.collectVariables(result);

        assertEquals("", expected, result);
    }

    /**
     *
     * Test that the expression ((1 + 2) ∗ (3 + 4)) is “variable-free” according to the corresponding
     * method (with boolean result).
     */
    @Test(timeout = TIMEOUT)
    public void test_9(){
        Expression e1 = new PlusExpression(new IntConstant(1),new IntConstant(2));
        Expression e2 = new PlusExpression(new IntConstant(3),new IntConstant(4));
        Expression e3 = new TimesExpression(e1,e2);

        Set<Variable> expected = new LinkedHashSet<>();
        Set<Variable> result = new LinkedHashSet<>();
        e3.collectVariables(result);

        assertEquals("", expected, result);
    }

    /**
     *
     * Test that the expression ((x + y) ∗ (x + 2)) is not “variable-free”
     * according to the corresponding method (with boolean result).
     */
    @Test(timeout = TIMEOUT)
    public void test_10(){
        Variable x = new Variable("x");
        Variable y = new Variable("y");

        Expression e1 = new IntConstant(2);
        Expression e2 = new PlusExpression(x,y);
        Expression e3 = new PlusExpression(x,e1);
        Expression e4 = new TimesExpression(e2,e3);

        boolean expected = false;
        boolean result = e4.isVariableFree();

        assertEquals("", expected, result);
    }

    /**
     *
     * Test that for the expression (x + 1), computing the value with the substitution that maps
     * x to 2 returns 3.
     */
    @Test(timeout = TIMEOUT)
    public void test_11(){
        Variable x = new Variable("x");
        Substitution s = new Substitution();
        s.put(x, new IntConstant(2));

        Expression e1 = new IntConstant(1);
        Expression e2 = new PlusExpression(x,e1);

        int expected = 3;
        int result = e2.computeValue();

        assertEquals("", expected, result);

    }

    /**
     * Test that creating a variable of the name 1234 throws an IllegalArgumentException.
     * */
    @Test(expected = IllegalArgumentException.class)
    public void test_13(){
        Variable x = new Variable("1234");

    }

    /**
     * Test that creating a variable of the name h_i throws an IllegalArgumentException.
     *
     * */
    @Test(expected = IllegalArgumentException.class)
    public void test_14(){
        Variable x = new Variable("h_i");

    }
}