#Arithmetic_Expressions

## What is this project about?
This repository contains a course work assignment that I completed in 2019. The project is about creating a data structure that represents variables and their mapping to arithmetic expressions.

## What was given?
The following classes and interfaces were given and had to remain unmodified. That is, the code that I wrote in the other classes had to work with the original version of these classes:

#### public interface Expression:
The interface Expression describes the methods that each concrete class in the inheritance hierarchy had to to provide (by implementing them or by inheriting them) along with Javadoc comments. It provides some default implementations as well.

#### public class Coursework3Main:
This class makes use of some of the desired functionalities of our data structure in its main method. I had the opportunity of testing my implementation by running main.

## What was I required to do?
I was required to create a class ExpressionTest as a test suite, in the format of JUnit version 4.
I was also required to extend a given inheritance hierarchy further: to introduce an additional class "Variable" to represent variables with a String to identify them. Moreover, I was required to add a class "Substitution" whose objects represent finite mappings from Variables to Expressions.

For example, a Substitution can map variable "x" to an IntExpression "2" and variable "y" to a PlusExpression "a+b". The idea is that a Substitution describes which Expressions are supposed to replace Variables in an Expression.

Using the class Substitution, we add further methods to the interface Expression. We can apply a Substitution to an Expression to obtain a version of the Expression where all occurrences of the Variables have been replaced by the corresponding Expressions (if any) as indicated by the Substitution. So, applying a Substitution that maps x to 2 and y to (a + b) to an Expression ((x ∗ y) + (x ∗ z)) will result in ((2 ∗ (a + b)) + (2 ∗ z)). In Java, we can write this as follows:

![arithmetics_code](https://user-images.githubusercontent.com/38473664/122651780-086f9600-d133-11eb-8aa1-57ada11c3533.png)

![arithmetic_diagram](https://user-images.githubusercontent.com/38473664/122651838-56849980-d133-11eb-9717-84668c1c50fe.png)

Further specification of the required functionality is stated in JavaDoc.
