package spbu.sem2.hw6.task1;

/** represents multiplication operation */
public class Multiplication extends Operator {
    @Override
    public int calculate() {
        return leftChild.calculate() * rightChild.calculate();
    }

    @Override
    public String getOperation() {
        return "*";
    }
}
