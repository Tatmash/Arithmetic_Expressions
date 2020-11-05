import java.util.Set;

/**
 * Abstract class for Expressions with two direct subexpressions. Provides an
 * implementation for numberOfNodes() method. Instances of this class are immutable.
 *
 * @author Carsten Fuhs
 * @author Janos Nagy
 */
public abstract class BinaryExpression implements Expression {

    /** the left subexpression; non-null */
    private Expression left;

    /** the right subexpression; non-null */
    private Expression right;

    /** String representation of the operator symbol; non-null */
    private String operatorSymbol;

    /**
     * Constructs a BinaryExpression with left and right as direct
     * subexpressions and with operatorSymbol as the String representation of
     * the operator.
     *
     * @param left
     *            the left subexpression; non-null
     * @param right
     *            the right subexpression; non-null
     * @param operatorSymbol
     *            String representation of the operator symbol; non-null
     */
    public BinaryExpression(Expression left, Expression right,
            String operatorSymbol) {
        if (left == null) {
            throw new NullPointerException("Illegal null value for left!");
        }
        if (right == null) {
            throw new NullPointerException("Illegal null value for right!");
        }
        if (operatorSymbol == null) {
            throw new NullPointerException(
                    "Illegal null value for operatorSymbol!");
        }
        this.left = left;
        this.right = right;
        this.operatorSymbol = operatorSymbol;
    }

    /**
     * Getter for the left subexpression.
     *
     * @return the left subexpression
     */
    public Expression getLeft() {
        return left;
    }

    /**
     * Getter for the right subexpression.
     *
     * @return the right subexpression
     */
    public Expression getRight() {
        return right;
    }

    /**
     * Getter for the operator symbol.
     *
     * @return the operator symbol
     */
    public String getOperatorSymbol() {
        return operatorSymbol;
    }

    @Override
    public int numberOfNodes() {
        return 1 + left.numberOfNodes() + right.numberOfNodes();
    }


    @Override
    public void collectVariables(Set<Variable> vars) {
        if (vars == null){
            throw new NullPointerException("Illegal null value for vars.");
        }

        left.collectVariables(vars);
        right.collectVariables(vars);

    }

    @Override
    public boolean isVariableFree() {
       return getLeft().isVariableFree() && getRight().isVariableFree();

    }

    @Override
    public String toString() {
        return "(" + left + " " + operatorSymbol + " " + right + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BinaryExpression)) {
            return false;
        }
        BinaryExpression other = (BinaryExpression) o;
        // relies on instance variables being non-null
        return operatorSymbol.equals(other.operatorSymbol)
                && left.equals(other.left) && right.equals(other.right);
    }
}
