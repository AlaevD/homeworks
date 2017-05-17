package spbu.sem2.hw4.task2;

/** represents simple hash function */
public class StupidHashFunction implements HashFunction {
    @Override
    public int getHash(String value, int mod) {
        int hash = 3;
        int n = value.length();
        for (int i = 0; i < n; i++) {
            hash = 3 * hash + value.charAt(i);
        }
        return (hash % mod + mod) % mod;
    }
}
