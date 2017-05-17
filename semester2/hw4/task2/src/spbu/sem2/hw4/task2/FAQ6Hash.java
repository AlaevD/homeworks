package spbu.sem2.hw4.task2;

/** represents jenkins hash function */
public class FAQ6Hash implements HashFunction {
    @Override
    public int getHash(String value, int mod) {
        int hash = 0;
        int n = value.length();

        for (int i = 0; i < n; i++) {
            hash += value.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);

        return (hash % mod + mod) % mod;
    }
}
