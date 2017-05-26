package spbu.sem2.hw7.task1;

import javafx.fxml.FXML;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;


public class ClassPrinterTest {
    private ClassPrinter cp = new ClassPrinter();

    @Test
    public void emptyClassTest() {
        String expected = "package spbu.sem2.hw7.task1;\n\nprivate class EmptyClass {\n   private EmptyClass(ClassPrinterTest arg0);\n\n}\n\n";
        String actual = cp.printClazz(EmptyClass.class);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void annotationsTest() {
        String expected = "package spbu.sem2.hw7.task1;\n\nprivate class ClassWithAnnotations {\n" +
                "   private ClassWithAnnotations(ClassPrinterTest arg0);\n\n" + "   @FXML\n   int a;\n\n" +
                "   @Deprecated\n   int b;\n\n   @ClassRule\n" +
                "   String c;\n\n}\n\n";
        String actual = cp.printClazz(ClassWithAnnotations.class);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void randomClassTest() {
        String expected = "package spbu.sem2.hw7.task1;\n\nprivate class RandomClass {\n" +
                "   private RandomClass(ClassPrinterTest arg0, int arg1, double arg2);\n\n" +
                "   private int field1;\n\n" + "   private double field2;\n\n" + "   private boolean m1();\n\n" +
                "   private String m2(Integer arg0);\n\n" + "}\n\n";
        String actual = cp.printClazz(RandomClass.class);
        Assert.assertEquals(expected, actual);
    }

    private class EmptyClass {

    }

    private class ClassWithAnnotations {
        @FXML
        int a;

        @Deprecated
        int b;

        @ClassRule
        String c;
    }

    private class RandomClass {
        private RandomClass(int arg1, double arg2) {
            field1 = arg1;
            field2 = arg2;
        }

        private boolean m1() {
            return false;
        }

        private String m2(Integer arg0) {
            return "asd";
        }

        private int field1;
        private double field2;
    }
}