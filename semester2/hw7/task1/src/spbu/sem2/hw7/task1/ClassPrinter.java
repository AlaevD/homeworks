package spbu.sem2.hw7.task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/** represents class information printer(fields, methods, etc) */
class ClassPrinter {
    /**
     * prints information about given class into console
     * @param clazz class to be printed
     */
    void printClazz(Class clazz) {
        printPackage(clazz);
        printClass(clazz, 0);
    }

    /**
     * prints class' package
     * @param clazz class
     */
    private void printPackage(Class clazz) {
        if (clazz.getPackage() != null) {
            System.out.println(clazz.getPackage() + ";" + "\n");
        }
    }

    /**
     * prints information about given class
     * @param clazz given class
     * @param numberOfTabs required number of indentations
     */
    private void printClass(Class clazz, int numberOfTabs) {
        printClassModifiers(clazz, numberOfTabs);
        printParents(clazz);
        System.out.println(" {");
        printConstructors(clazz, numberOfTabs + 1);
        printFields(clazz, numberOfTabs + 1);
        printMethods(clazz, numberOfTabs + 1);
        printInnerClasses(clazz, numberOfTabs + 1);
        printTabs(numberOfTabs);
        System.out.println("}");

    }

    /**
     * prints all inner classes of given class
     * @param clazz given class
     * @param numberOfTabs required number of indentations
     */
    private void printInnerClasses(Class clazz, int numberOfTabs) {
        Class[] classes = clazz.getDeclaredClasses();
        for (Class aClass : classes) {
            printClass(aClass, numberOfTabs);
        }
    }

    /**
     * prints all methods(prototypes) of given class
     * @param clazz given class
     * @param numberOfTabs required number of indentations
     */
    private void printMethods(Class clazz, int numberOfTabs) {
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method: methods) {
            printAnnotations(method.getDeclaredAnnotations(), numberOfTabs);
            printTabs(numberOfTabs);
            printModifiers(method.getModifiers());
            System.out.print(method.getReturnType().getSimpleName() + " "
                    + method.getName() + "(");
            printParameters(method.getParameters());
            System.out.print(")");
            printExceptions(method.getExceptionTypes());
            System.out.println(";\n");
        }
    }

    /**
     * prints all fields of given class
     * @param clazz given class
     * @param numberOfTabs required number of indentations
     */
    private void printFields(Class clazz, int numberOfTabs) {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.getName().contains("this$")) {
                continue;
            }
            printAnnotations(field.getDeclaredAnnotations(), numberOfTabs);
            printTabs(numberOfTabs);
            printModifiers(field.getModifiers());
            System.out.println(field.getType().getSimpleName() + " "
                    + field.getName() + ";\n");
        }
    }

    /**
     * prints all constructors(prototypes) of given class
     * @param clazz given class
     * @param numberOfTabs required number of indentations
     */
    private void printConstructors(Class clazz, int numberOfTabs) {
        Constructor[] constructors = clazz.getDeclaredConstructors();
        if (constructors.length == 0) {
            return;
        }

        for (Constructor constructor : constructors) {
            printConstructor(clazz, constructor, numberOfTabs);
        }
    }

    /**
     * prints one constructor of a class
     * @param clazz given class
     * @param constructor its constructor to be printed
     * @param numberOfTabs required number of indentations
     */
    private void printConstructor(Class clazz, Constructor constructor, int numberOfTabs) {
        printAnnotations(constructor.getDeclaredAnnotations(), numberOfTabs);

        printTabs(numberOfTabs);
        printModifiers(constructor.getModifiers());
        System.out.print(clazz.getSimpleName() + "(");

        printParameters(constructor.getParameters());
        System.out.print(")");

        printExceptions(constructor.getExceptionTypes());
        System.out.println(";\n");
    }

    /**
     * prints given modifiers
     * @param modifiers modifiers to be printed
     */
    private void printModifiers(int modifiers) {
        String mods = Modifier.toString(modifiers);
        if (mods.length() == 0) {
            return;
        }
        System.out.print(mods + " ");
    }

    /**
     * prints given exceptions
     * @param exceptions to be printed
     */
    private void printExceptions(Class[] exceptions) {
        if (exceptions.length == 0) {
            return;
        }

        System.out.print(" throws ");
        for (int i = 0; i < exceptions.length; i++) {
            System.out.print(exceptions[i].getSimpleName());
            if (i < exceptions.length - 1) {
                System.out.print(", ");
            }
        }
    }

    /**
     * prints given parameters
     * @param parameters parameters to be printed
     */
    private void printParameters(Parameter[] parameters) {
        for (int i = 0; i < parameters.length; i++) {
            System.out.print(parameters[i].getType().getSimpleName() + " " + parameters[i].getName());
            if (i < parameters.length - 1) {
                System.out.print(", ");
            }
        }
    }

    /**
     * prints given annotations
     * @param annotations annotations to be printed
     * @param numberOfTabs required number of indentations
     */
    private void printAnnotations(Annotation[] annotations, int numberOfTabs) {
        for (Annotation annotation : annotations) {
            printTabs(numberOfTabs);
            System.out.println("@" + annotation.annotationType().getSimpleName());
        }
    }

    /**
     * prints all classes and interfaces which given class extends or implements
     * @param clazz given class
     */
    private void printParents(Class clazz) {
        if (!clazz.getSuperclass().equals(Object.class)) {
            System.out.print(" extends " + clazz.getSuperclass().getSimpleName());
        }

        Class[] interfaces = clazz.getInterfaces();
        int interfacesSize = interfaces.length;
        if (interfacesSize > 0) {
            System.out.print(" implements ");
            for (int i = 0; i < interfacesSize; i++) {
                System.out.print(interfaces[i].getSimpleName());
                if (i < interfacesSize - 1) {
                    System.out.print(", ");
                }
            }
        }
    }

    /**
     * prints head of given class.
     * Prints modifiers and class' name
     * @param clazz given class
     * @param numberOfTabs required number of indentations
     */
    private void printClassModifiers(Class clazz, int numberOfTabs) {
        printTabs(numberOfTabs);
        String modifiers = Modifier.toString(clazz.getModifiers());
        if (modifiers.isEmpty()) {
            System.out.print("class " + clazz.getSimpleName());
        }
        else {
            System.out.print(modifiers + " class " + clazz.getSimpleName());
        }
    }

    /**
     * prints required number of indentations
     * @param numberOfTabs number of indentations
     */
    private void printTabs(int numberOfTabs) {
        for (int i = 0; i < numberOfTabs; i++) {
            System.out.print("  ");
        }
    }
}
