package spbu.sem2.hw4.task2;

/** represents "rotate by 13 places" hash function */
public class Rot13Hash implements HashFunction {
    @Override
    public int getHash(String value, int mod) {
        int hash = 0;
        int n = value.length();
        for (int i = 0; i < n; i++) {
            hash += value.charAt(i);
            hash -= (hash << 13) | (hash >> 13);
        }
        return (hash % mod + mod) % mod;
    }
}
