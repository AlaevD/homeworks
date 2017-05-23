package spbu.sem2.hw4.task2;

/** represents hash function interface */
public interface HashFunction {
    /**
     * calculates value hash.
     * final hash is the remainder after division of calculated hash by modulus
     * @param value
     * @param mod modulus
     * @return calculated hash
     */
    int getHash(String value, int mod);
}
