package spbu.sem2.hw6.task1;

/** represents tree leafs, i.e. numbers */
public class Number implements Node {
    /** node value */
    private int value;

    public Number(int value) {
        this.value = value;
    }

    @Override
    public int calculate() {
        return value;
    }

    @Override
    public void print() {
        System.out.print(value);
    }
}
