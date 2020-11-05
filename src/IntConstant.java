import java.util.Objects;
import java.util.Set;

/**
 * Expression that represents an int value.
 *
 * @author Carsten Fuhs
 * @author Janos Nagy
 */
public class IntConstant implements Expression {

    /** Stores the encapsulated value. */
    private int value;

    /**
     * Constructs a new IntConstant encapsulating value.
     *
     * @param value
     *            to be encapsulated in this IntConstant
     */
    public IntConstant(int value) {
        this.value = value;
    }

    /**
     * @return the int value this IntConstant stands for
     */

    /**public int getValue() {
        return value;
    }*/

    @Override
    public int numberOfNodes() {
        return 1;
    }

    @Override
    public int computeValue() {
        return value;
    }

    @Override
    public Expression applySubstitution(Substitution s) {

        return this;
    }

    @Override
    public boolean isVariableFree() {

        return true;
    }

    @Override
    public void collectVariables(Set<Variable> vars) {

        return;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof IntConstant)) {
            return false;
        }
        IntConstant other = (IntConstant) o;
        return value == other.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
