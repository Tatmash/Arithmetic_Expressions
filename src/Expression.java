import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Basic interface for arithmetic expressions. Implementations are expected to
 * be immutable, i.e., after object creation, the object's state cannot change,
 * and there are no mutator methods in any class that implements this interface.
 * See also:
 *
 * https://wiki.c2.com/?ImmutableValue
 *
 * @author Carsten Fuhs
 */
public interface Expression {

    /**
     * Computes the number of sub-expressions of this Expression (its "size").
     *
     * @return the number of nodes of this Expression.
     */
    int numberOfNodes();

    /**
     * Computes the int value represented by this Expression object. This
     * Expression object must not contain variables.
     *
     * @return the int value represented by this Expression
     * @throws UnsupportedOperationException
     *             if the Expression contains variables
     */
    int computeValue();

    /**
     * Computes the int value represented by this Expression.
     *
     * @param subst
     *            to be used to assign values to this expression; must not be
     *            null
     * @return the int value represented by this Expression
     * @throws UnsupportedOperationException
     *             if the expression with subst applied to it still has
     *             variables
     * @throws NullPointerException
     *             if s is null
     */
    default int computeValue(Substitution subst) {
        Expression specialised = applySubstitution(subst);
        return specialised.computeValue();
    }

    /**
     * Returns whether this Expression is variable-free, i.e., none of its
     * direct or indirect sub-expressions is a Variable object.
     *
     * @return whether this Expression is variable-free, i.e., none of its
     *         direct or indirect sub-expressions is a Variable object.
     */
    boolean isVariableFree();

    /**
     * Returns the Set of Variables of this Expression. The returned Set may be
     * modified.
     *
     * @return the Set of Variables of this Expression
     */

    default Set<Variable> getVariables() {
        Set<Variable> result = new LinkedHashSet<>();
        collectVariables(result);
        return result;
    }

    /**
     * Adds all variables in this Expression to vars
     *
     * @param vars
     *            Variables will be added here; parameter must not be null
     * @throws NullPointerException
     *             if vars is null
     */
    void collectVariables(Set<Variable> vars);

    /**
     * Applies a substitution to this Expression and returns the result.
     *
     * @param s
     *            a substitution to be applied to this Expression; must not be
     *            null
     * @return a version of this Expression where all variables have been
     *         replaced by the values stored in s for the variables
     * @throws NullPointerException
     *             if s is null
     */
    Expression applySubstitution(Substitution s);
}
