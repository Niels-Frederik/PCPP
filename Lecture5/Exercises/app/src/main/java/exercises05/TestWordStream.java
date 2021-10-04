// Week 3
// sestoft@itu.dk * 2015-09-09
package exercises05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class TestWordStream {
  public static void main(String[] args) {
    String filename = "src/main/resources/english-words.txt";
    //System.out.println(readWords(filename).count());
    //printFirst100Words(filename);
    //printWordsWith22OrMoreCharacters(filename);
    //printWordWith22OrMoreCharacters(filename);
    //printPalindromes(filename);
    //printPalindromesParallel(filename);
    //It is not possbile to detect any significant difference in runtime between the sequential and parallel version,
    //but one could use the benchmarking file to see this
    //streamToWordLength(filename);
    //groupWordsByLength(filename);
    //printTreeMapOfFirst100(filename);
    printAmountOfE(filename);
  }

  public static Stream<String> readWords(String filename) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      return reader.lines();
    } catch (IOException exn) {
      return Stream.<String>empty();
    }
  }

  public static void printFirst100Words(String fileName)
  {
    readWords(fileName).limit(100).forEach(x -> System.out.println(x));
  }

  public static void printWordsWith22OrMoreCharacters(String fileName)
  {
    readWords(fileName).filter(x -> x.length() >= 22).forEach(x -> System.out.println(x));
  }

  public static void printWordWith22OrMoreCharacters(String fileName)
  {
    System.out.println(readWords(fileName).filter(x -> x.length() >= 22).findAny().orElse("None"));
  }

  public static void printPalindromes(String filename)
  {
    readWords(filename).filter(x -> isPalindrome(x)).forEach(x -> System.out.println(x));
  }

  public static void printPalindromesParallel(String filename)
  {
    readWords(filename).parallel().filter(x -> isPalindrome(x)).forEach(x -> System.out.println(x));
  }

  public static void streamToWordLength(String filename)
  {
    //IntStream stream = readWords(filename).parallel().mapToInt(x -> x.length());
    Supplier<IntStream> supplier = () -> readWords(filename).parallel().mapToInt(x -> x.length());
    System.out.println("sum: " + supplier.get().sum());
    System.out.println("min: " + supplier.get().min().getAsInt());
    System.out.println("max: " + supplier.get().max().getAsInt());
  }

  public static void groupWordsByLength(String filename)
  {
    readWords(filename).collect(Collectors.groupingBy(x -> x.length())).forEach((x,y) -> System.out.println("group " + x.intValue() + " - count: " + y.stream().count()));
  }

  public static boolean isPalindrome(String s) {
    String sRev = new StringBuilder(s).reverse().toString();
    return (s.equals(sRev));
  }

  public static void printTreeMapOfFirst100(String filename)
  {
    readWords(filename).limit(100).forEach(x -> System.out.println(letters(x)));
  }

  public static void printAmountOfE(String filename)
  {
    System.out.println(readWords(filename).map(x -> letters(x)).mapToInt(x -> x.getOrDefault('e', 0).intValue()).sum());
  }

  public static Map<Character,Integer> letters(String s) {
    Map<Character,Integer> res = new TreeMap<>();
    Arrays.stream(s.toLowerCase().split("")).collect(Collectors.groupingBy(x -> x, Collectors.counting())).forEach((x,y) -> res.put(x.charAt(0), y.intValue()));
    return res;
  }
}
