import java.util.Objects;
import java.util.Set;

/**
 * A variable is a symbolic expression that stands for a value that has not yet
 * been fixed. A variable has a name of the format
 * 
 * letter (letter | digit)^*
 *
 * (where '(letter | digit)^*' stands for 'a string of length 0 or more that
 * contains only letters and digits').
 *
 * Here the class methods Character.isLetter(char) and
 * Character.isLetterOrDigit(char) determine whether a character is
 * a letter/a letter or a digit, respectively.
 *
 * For example, the following names are legal:<br>
 * - "AbC"<br>
 * - "e2e4"<br>
 * - "BBK"<br>
 * - "x"
 *
 * The following names are not legal:<br>
 * - "" (no start letter)<br>
 * - "2e4e" (no start letter)<br>
 * - "a/b" (illegal character '/')<br>
 * - "a_b" (illegal character '_')
 *
 * Instances of this class are immutable.
 *
 * @author Carsten Fuhs
 * @author Janos Nagy
 */
public class Variable implements Expression {

    /**
     * Name of this Variable. Non-null, of the format
     *
     * letter (letter | digit)*
     */
    private String name;

    /**
     * Constructs a new Variable with the specified name.
     *
     * @param name
     *            must not be null; must be a String of the format letter
     *            (letter | digit)^*
     */
    public Variable(String name) {

        if (checkFormat(name)){
            this.name = name;
        }
    }

    /**
     * This method checks if the variable has a name of the format: letter (letter | digit)^*
     *
     * @param   name must not be null; must be a String of the format: letter (letter | digit)^*
     * @return  true if the format is correct, throws an exception otherwise
     * */
    public boolean checkFormat(String name){
        if (name == null) {
            throw new NullPointerException("Illegal null value for name.");
        }
        if (Character.isDigit(name.charAt(0))) {
            throw new IllegalArgumentException("The first character of the name must be a letter.");
        }
        if(name.length()>1) {
            char[] arr= name.toCharArray();
            for(char c : arr){
                if (!Character.isLetterOrDigit(c)){
                    throw new IllegalArgumentException("The name must consist of letter(s) and digit(s).");
                }
            }
        }
        return true;
    }

    public String getName(){

        return name;
    }

    @Override
    public int numberOfNodes() {
        return 1;
    }

    @Override
    public int computeValue() {
        throw new UnsupportedOperationException("Cannot compute the value of a variable without a substitution!");
    }

    @Override
    public Expression applySubstitution(Substitution s) {
        if (s == null) {
            throw new NullPointerException("Illegal null value for s.");
        }

        return s.get(this);
    }


    @Override
    public void collectVariables(Set<Variable> vars) {
        if (vars == null) {
            throw new NullPointerException("Illegal null value for vars.");
        }
        vars.add(this);
    }

    @Override
    public boolean isVariableFree() {

        return false;
    }

    @Override
    public String toString() {

        return getName();
    }

    /**
     * The method returns true if o is an instance of class Variable
     * whose name is equal to the name of this Variable; otherwise it
     * returns false.
     * 
     * @return whether this Variable and Object o are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(name, variable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
