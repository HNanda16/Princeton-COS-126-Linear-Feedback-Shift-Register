/* *****************************************************************************
 * Name: Hasit Nanda
 * NetID:
 * Precept:
 *
 * Description: This program produces pseudo-random bits by simulating a linear-feedback shift register.
 * The register shifts the bits one position to the left and replaces the vacated bit by the exclusive
 * or(xor) of the bit shifted off and the bit previously at a given tap position in the register.
 * The parameters of LFSR are the initial seed and the tap position.
 **************************************************************************** */

public class LFSR {

    String lfsr1; // LFSR String instance variable
    int currentIndex; // Tap value instance variable

    // creates an LFSR in form of a String with the specified seed and tap
    public LFSR(String seed, int tap) {
        lfsr1 = seed + 'z'; // 'z' to signal end of bits
        currentIndex = lfsr1.length() - tap - 1;
    }


    // returns the number of bits in this LFSR
    public int length() {
        int counter = 0;
        while (bitAt(counter) == '1' || bitAt(counter) == '0') {
            counter = counter + 1;
        }
        return counter;
    }

    // returns the ith bit of this LFSR (as 0 or 1)
    public int bitAt(int i) {
        return Integer.parseInt(String.valueOf(lfsr1.charAt(i)));
    }

    // returns a string representation of this LFSR
    public String toString() {
        return lfsr1.substring(0, lfsr1.length() - 1);
    }

    // simulates one step of this LFSR and returns the new bit (as 0 or 1)
    public int step() {
        int lastBit = bitAt(0) ^ bitAt(currentIndex);
        char[] ch = lfsr1.toCharArray();
        for (int i = 0; i < lfsr1.length() - 2; i++) {
            ch[i] = ch[i + 1];
        }

        ch[lfsr1.length() - 2] = (char) (lastBit + '0');
        int temporary = lfsr1.length();
        lfsr1 = "";
        for (int i = 0; i < temporary; i++) {
            lfsr1 = lfsr1 + ch[i];
        }

        return lastBit;
    }

    // simulates k steps of this LFSR and returns the k bits as a k-bit integer
    public int generate(int k) {
        int result = 0;

        for (int i = 0; i < k; i++) {
            result = 2 * result + step();
        }
        return result;
    }

    // tests this class by directly calling all instance methods
    public static void main(String[] args) {
        LFSR lfsr1 = new LFSR("01101000010", 9);
        StdOut.println(lfsr1);
        for (int i = 0; i < 10; i++) {
            int bit = lfsr1.step();
            StdOut.println(lfsr1 + " " + bit);
        }

        StdOut.println("");
        LFSR lfsr2 = new LFSR("01101000010", 9);
        StdOut.println(lfsr2);
        for (int i = 0; i < 10; i++) {
            int r = lfsr2.generate(5);
            StdOut.println(lfsr2 + " " + r);
        }
    }

}
