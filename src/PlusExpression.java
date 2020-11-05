import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represents an Expression of the form e1 + e2.
 * Instances of this class are immutable.
 * 
 * @author Carsten Fuhs
 * @author TODO
 */
public class PlusExpression extends BinaryExpression {

    /**
     * Constructs a PlusExpression with left and right as direct subexpressions.
     *
     * @param left
     *            the left subexpression; non-null
     * @param right
     *            the right subexpression; non-null
     */
    public PlusExpression(Expression left, Expression right) {
        super(left, right, "+");
    }


    @Override
    public Expression applySubstitution(Substitution s){
        if (s == null) {
            throw new NullPointerException("Illegal null value for s.");
        }

        Expression leftX = getLeft().applySubstitution(s);
        Expression rightX = getRight().applySubstitution(s);

        Expression result = new PlusExpression(leftX,rightX);
        return result;
    }

    @Override
    public int computeValue() {
        return getLeft().computeValue() + getRight().computeValue();
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PlusExpression)) {
            return false;
        }
        return super.equals(o);
    }
}
