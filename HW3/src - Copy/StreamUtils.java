import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * Each function implementation must be done using a single method chain (as shown in example 1 above),
 * and all the functions must be implemented in a file named StreamUtils.java.
 */

public class StreamUtils {
    /*
    public static class Person implements Comparable {
        String name;
        int index;

        public Person(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public int compareTo(Object o) {
            Person p1 = (Person) o;
            return name.compareTo(p1.getName());
        }


        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", index=" + index +
                    '}';
        }
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>(Arrays.asList("red", "orange", "yellow", "green", "blue", "purple"));

        System.out.println(longest(strings, false));


        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9, 7, 5, 3, 1));

        System.out.println(least(integers, false));


        //"red, orange, yellow, green, blue, purple"

        HashMap<Integer, String> map = new HashMap<>();//Creating HashMap
        map.put(1, "hea");  //Put elements in Map
        map.put(2, "hehare");
        map.put(3, "dashdah");
        map.put(4, "vsdav");
        System.out.println(flatten(map));

        ArrayList<Person> newlist = new ArrayList<>(Arrays.asList(new Person("Test", 1), new Person("Test", 2)));
        System.out.println(least(newlist, false));

        System.out.println((least(new ArrayList<String>(), true) == null) ? "Null" : "Not Null");
    }

     */



    /**
     * @param strings: the input collection of <code>String</code>s.
     * @return a collection of those <code>String</code>s in the input collection
     * <p>
     * that start with a capital letter.
     */
    public static Collection<String> capitalized(Collection<String> strings) {
        return strings
                .stream()
                .filter(string -> Character.isUpperCase(string.charAt(0)))
                .collect(Collectors.toList()); //Might want to use Collectors.toCollections if I have time later but it throws errors for now.
    }

    /**
     * Find and return the longest <code>String</code> in a given collection of <code>String</code>s.
     *
     * @param strings:    the given collection of <code>String</code>s.
     * @param from_start: a <code>boolean</code> flag that decides how ties are broken.
     *                    If <code>true</code>, then the element encountered earlier in
     *                    the iteration is returned, otherwise the later element is returned.
     * @return the longest <code>String</code> in the given collection,
     * where ties are broken based on <code>from_start</code>.
     */
    public static String longest(Collection<String> strings, boolean from_start) {
        return strings
                .stream()
                .reduce("", (from_start) ?
                        ((str1, str2) -> (str1.length() < str2.length()) ? (str2) : (str1)) :
                        ((str1, str2) -> (str1.length() <= str2.length()) ? (str2) : (str1)));//"", (str1, str2) -> (str1.length() > str2.length()) ? (str1) : ((from_start) ? (str1) : ()))//(from_start) ? (BinaryOperator.maxBy()) : (Binary)) //max(Comparator.comparing(string -> string.length())).get();
    }


    /**
     * The least element. In this function, the single method chain can return a java.util.Optional<T>.
     * So you must write something extra (your final code should still be a single method chain) to convert it
     * to an object of type T (handling any potential exceptions).
     */

    /**
     * Find and return the least element from a collection of given elements that are comparable.
     *
     * @param items:      the given collection of elements
     * @param from_start: a <code>boolean</code> flag that decides how ties are broken.
     *                    If <code>true</code>, the element encountered earlier in the
     *                    iteration is returned, otherwise the later element is returned.
     * @param <T>:        the type parameter of the collection (i.e., the items are all of type T).
     * @return the least element in <code>items</code>, where ties are
     * broken based on <code>from_start</code>.
     */
    public static <T extends Comparable<T>> T least(Collection<T> items, boolean from_start) {
        return (T) items
                .stream()
                .reduce((from_start) ?
                        (((o1, o2) -> (o1.compareTo(o2) > 0) ? (o2) : (o1))) :
                        ((o1, o2) -> (o1.compareTo(o2) >= 0) ? (o2) : (o1)))
                .orElse(null); /*reduce(null, (from_start) ?
                       (((o1, o2) -> (o1.compareTo(o2) > 0) ? (o2) : (o1))) :
                       ((o1, o2) -> (o1.compareTo(o2) >= 0) ? (o2) : (o1)));
                       */
    }

    /**
     * Flattens a map to a stream of <code>String</code>s, where each element in the list
     * is formatted as "key -> value" (i.e., each key-value pair is converted to a string
     * with this format).
     *
     * @param aMap the specified input map.
     * @param <K>  the type parameter of keys in <code>aMap</code>.
     * @param <V>  the type parameter of values in <code>aMap</code>.
     * @return the flattened list representation of <code>aMap</code>.
     */
    public static <K, V> List<String> flatten(Map<K, V> aMap) {
        return aMap
                .entrySet()
                .stream()
                .map((element) -> (element.getKey() + " -> " + element.getValue()))
                .collect(Collectors.toList()); //replaceAll((k, v) -> (String) (k.toString() + " -> " + v.toString())).collect;
    }
}
