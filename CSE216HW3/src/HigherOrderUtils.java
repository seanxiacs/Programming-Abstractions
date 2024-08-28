import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Code for the following questions must be written in a file named HigherOrderUtils.java. You may
 * also have to consult some of the official Java documentation and/or the reference text on Functional
 * Programming in Java. The remaining answers need NOT be written as a single method chain.
 * Note: be very careful with the parameterized types in this section, and pay attention to the warnings
 * issued by your IDE regarding the raw type.
 */

public class HigherOrderUtils {
    /**
     * First, write a static nested interface in HigherOrderUtils called NamedBiFunction. This interface must
     * extend the interface java.util.Function.BiFunction. The interface should just have one method
     * declaration: String name();, i.e., a class implementing this interface must provide a “name” for every
     * instance of that class.
     */
    interface NamedBiFunction<T, U, R> extends java.util.function.BiFunction<T, U, R> {
        String name();
    }

    /**
     * Next, create public static NamedBiFunction instances as follows:
     * (a) add, with the name “add”, to perform addition of two Doubles.
     * (b) subtract, with the name “diff”, to perform subtraction of one Double from another.
     * (c) multiply, with the name “mult”, to perform multiplication of two Doubles.
     * (d) divide, with the name “div”, to divide one Double by another. This operation should throw a
     * java.lang.ArithmeticException if there is a division by zero being attempted.
     */
    public static NamedBiFunction<Double, Double, Double> add = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "add";
        }

        @Override
        public Double apply(Double aDouble, Double aDouble2) {
            return aDouble + aDouble2;
        }
    };

    public static NamedBiFunction<Double, Double, Double> subtract = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "diff";
        }

        @Override
        public Double apply(Double aDouble, Double aDouble2) {
            return aDouble - aDouble2;
        } //Should it be aDouble2 - aDouble? The wording is weird. (b) subtract, with the name “diff”, to perform subtraction of one Double from another.
    };

    public static NamedBiFunction<Double, Double, Double> multiply = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "mult";
        }

        @Override
        public Double apply(Double aDouble, Double aDouble2) {
            return aDouble * aDouble2;
        }
    };

    public static NamedBiFunction<Double, Double, Double> divide = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "div";
        }

        @Override
        public Double apply(Double aDouble, Double aDouble2) {
            if (aDouble2 == 0) {
                throw new java.lang.ArithmeticException();
            }
            return aDouble / aDouble2;
        }
    };


    /**
     * Applies a given list of bifunctions -- functions that take two arguments of a certain type
     * and produce a single instance of that type -- to a list of arguments of that type. The
     * functions are applied in an iterative manner, and the result of each function is stored in
     * the list in an iterative manner as well, to be used by the next bifunction in the next
     * iteration. For example, given
     * List<Double> args = Arrays.asList(1d, 1d, 3d, 0d, 4d), and
     * List<NamedBiFunction<Double, Double, Double>> bfs = [add, multiply, add, divide],
     * <code>zip(args, bfs)</code> will proceed iteratively as follows:
     * - index 0: the result of add(1,1) is stored in args[1] to yield args = [1,2,3,0,4]
     * - index 1: the result of multiply(2,3) is stored in args[2] to yield args = [1,2,6,0,4]
     * - index 2: the result of add(6,0) is stored in args[3] to yield args = [1,2,6,6,4]
     * - index 3: the result of divide(6,4) is stored in args[4] to yield args = [1,2,6,6,1.5]
     *
     * @param args:        the arguments over which <code>bifunctions</code> will be applied.
     * @param bifunctions: the list of bifunctions that will be applied on <code>args</code>.
     * @param <T>:         the type parameter of the arguments (e.g., Integer, Double)
     * @return the item in the last index of <code>args</code>, which has the final
     * result of all the bifunctions being applied in sequence.
     */
    public static <T> T zip(List<T> args, List<NamedBiFunction<T, T, T>> bifunctions) {
        if ((args.size() < bifunctions.size() + 1) || (bifunctions.size() == 0))
            throw new IllegalArgumentException(); //Throws an error if the arguments are not valid.
        for (int i = 0; i < bifunctions.size(); i++) {
            T result = bifunctions.get(i).apply(args.get(i), args.get(i + 1)); //We do not catch any errors, just let it through.

            args.set(i + 1, result);

            if (i == bifunctions.size() - 1) {
                return result;
            }
        }
        return null;
    }


    /**
     * Based on the above zip function, think about what a function composition would look like. Write a (12)
     * static inner class called FunctionComposition that is parameterized by three type parameters. This
     * class should have no methods, and no constructor. It should only have a single BiFunction called
     * composition, which takes in two functions and provides their composition as the output function.
     * Function composition should be consistent with the types – if there is a function f: char -> String,
     * and another function g: String -> int, the output of composition should be a function h: char ->
     * int. For example, if f concatenates a char some number of times (say, ‘b’ yields “bb”, ‘c’ yields “ccc”,
     * ‘d’ yields “dddd”, etc.), and g converts a string to its length, then composition(f, g) should output a
     * function that maps ‘z’ to 26.
     */
    static class FunctionComposition<T, U, R> {
        public BiFunction<Function<T, U>, Function<U, R>, Function> composition = (f, g) -> f.andThen(g);
    }

    static Function<Double, Double> incrementByOneFunction = num -> num + 1;

    static Function<Double, String> printNum = num -> "The number is: " + num;

    /*
    public static void main(String[] args) {
        Function<Double, String> comp = FunctionComposition.composition.apply(incrementByOneFunction, printNum);
        System.out.println(comp.apply(4.0));

        List<Double> argument = Arrays.asList(1d, 1d, 3d, 0d, 4d);
        List<NamedBiFunction<Double, Double, Double>> bfs = Arrays.asList(add, multiply, add, divide);
        System.out.println(zip(argument, bfs));
    }

     */

    /*

    public static void main(String[] args){
        List<Double> a = new ArrayList<>();
        Double[] b = {2d,3d,0d, 6d};
        for(int i = 0; i < b.length; i++){
            a.add(b[i]);
        }
        List<NamedBiFunction<Double,Double,Double>> d = new ArrayList<>();
        d.add(add);
        d.add(subtract);
        d.add(multiply);
        System.out.println(zip(a,d));

    }

     */



}
