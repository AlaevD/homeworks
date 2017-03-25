public class Main {
    public static void main(String[] args) {
	    List <Integer> list = new List<>();

	    list.add(1);
	    list.add(2);
	    list.print();

	    list.remove((Integer)2);
	    list.print();

	    list.remove((Integer)1);
	    list.print();
    }
}
