import java.io.IOException;
import java.math.BigInteger;

public class Karatsuba_Demo {
    // Change the two numbers for multiplication
    static BigInteger num1 = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
    static BigInteger num2 = new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");

    public static void main (String[] args) throws IOException {
        System.out.println("Multiplication: ");
        BigInteger result = karatsuba(num1, num2);
        System.out.println("Multiplication of " + num1 + " and " + num2 + " is\n" + result.toString());
        System.out.println("Using Karatsuba Multiplication Method");
    }

    public static BigInteger karatsuba(BigInteger num1, BigInteger num2) {
        int length;
        int split;

        if (num1.compareTo(BigInteger.TEN) <= -1 || num2.compareTo(BigInteger.TEN) <= -1) {
            return num1.multiply(num2);
        }

        length = Math.min(num1.bitLength(), num2.bitLength());
        split = (int) Math.floor(length / 2);

        BigInteger high1 = num1.shiftRight(split);
        BigInteger low1 = num1.subtract(high1.shiftLeft(split));

        BigInteger high2 = num2.shiftRight(split);
        BigInteger low2 = num2.subtract(high2.shiftLeft(split));

        BigInteger z0 = karatsuba(low1, low2);
        BigInteger z1 = karatsuba((low1.add(high1)), (low2.add(high2)));
        BigInteger z2 = karatsuba(high1, high2);

        return (z0.add(z2.shiftLeft(split * 2).add(z1.subtract(z2).subtract(z0).shiftLeft(split))));
    }
}
