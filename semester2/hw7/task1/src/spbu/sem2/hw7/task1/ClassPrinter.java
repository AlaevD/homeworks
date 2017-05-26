package spbu.sem2.hw7.task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/** represents class information printer(fields, methods, etc) */
class ClassPrinter {
    /** class description */
    private StringBuilder sb;
    /**
     * collects information about given class into string
     * @param clazz class to be printed
     * @return string with class description
     */
    String printClazz(Class clazz) {
        sb = new StringBuilder();
        getPackage(clazz);
        getClassDescription(clazz, 0);
        return sb.toString();
    }

    /**
     * adds information about given class' package to string builder
     * @param clazz given class
     */
    private void getPackage(Class clazz) {
        if (clazz.getPackage() != null) {
            sb.append(clazz.getPackage() + ";" + "\n\n");
        }
    }

    /**
     * adds full class description to string builder
     * @param clazz given class
     * @param numberOfTabs required number of indentations
     */
    private void getClassDescription(Class clazz, int numberOfTabs) {
        getClassModifiers(clazz, numberOfTabs);
        getParents(clazz);
        sb.append(" {\n");
        getConstructors(clazz, numberOfTabs + 1);
        getFields(clazz, numberOfTabs + 1);
        getMethods(clazz, numberOfTabs + 1);
        getInnerClasses(clazz, numberOfTabs + 1);
        getTabs(numberOfTabs);
        sb.append("}\n\n");
    }

    /**
     * adds information about given class' inner classes to string builder
     * @param clazz given class
     * @param numberOfTabs required number of indentations
     */
    private void getInnerClasses(Class clazz, int numberOfTabs) {
        Class[] classes = clazz.getDeclaredClasses();
        for (Class aClass : classes) {
            getClassDescription(aClass, numberOfTabs);
        }
    }

    /**
     * adds information about given class' methods to string builder
     * @param clazz given class
     * @param numberOfTabs required number of indentations
     */
    private void getMethods(Class clazz, int numberOfTabs) {
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method: methods) {
            getAnnotations(method.getDeclaredAnnotations(), numberOfTabs);
            getTabs(numberOfTabs);
            getModifiers(method.getModifiers());
            sb.append(method.getReturnType().getSimpleName() + " "
                    + method.getName() + "(");
            getParameters(method.getParameters());
            sb.append(")");
            getExceptions(method.getExceptionTypes());
            sb.append(";\n\n");
        }
    }

    /**
     * adds information about given class' fields to string builder
     * @param clazz given class
     * @param numberOfTabs required number of indentations
     */
    private void getFields(Class clazz, int numberOfTabs) {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.getName().contains("this$")) {
                continue;
            }
            getAnnotations(field.getDeclaredAnnotations(), numberOfTabs);
            getTabs(numberOfTabs);
            getModifiers(field.getModifiers());
            sb.append(field.getType().getSimpleName() + " "
                    + field.getName() + ";\n\n");
        }
    }

    /**
     * adds information about given class' constructors to string builder
     * @param clazz given class
     * @param numberOfTabs required number of indentations
     */
    private void getConstructors(Class clazz, int numberOfTabs) {
        Constructor[] constructors = clazz.getDeclaredConstructors();
        if (constructors.length == 0) {
            return;
        }

        for (Constructor constructor : constructors) {
            getConstructor(clazz, constructor, numberOfTabs);
        }
    }

    /**
     * adds information about one certain constructor of the given class to string builder
     * @param clazz given class
     * @param constructor its constructor
     * @param numberOfTabs required number of indentations
     */
    private void getConstructor(Class clazz, Constructor constructor, int numberOfTabs) {
        getAnnotations(constructor.getDeclaredAnnotations(), numberOfTabs);

        getTabs(numberOfTabs);
        getModifiers(constructor.getModifiers());
        sb.append(clazz.getSimpleName() + "(");

        getParameters(constructor.getParameters());
        sb.append(")");

        getExceptions(constructor.getExceptionTypes());
        sb.append(";\n\n");
    }

    /**
     * adds given modifiers to string builder
     * @param modifiers modifiers to be appended
     */
    private void getModifiers(int modifiers) {
        String mods = Modifier.toString(modifiers);
        if (mods.length() == 0) {
            return;
        }
        sb.append(mods + " ");
    }

    /**
     * adds given exceptions to string builder
     * @param exceptions to be appended
     */
    private void getExceptions(Class[] exceptions) {
        if (exceptions.length == 0) {
            return;
        }

        sb.append(" throws ");
        for (int i = 0; i < exceptions.length; i++) {
            sb.append(exceptions[i].getSimpleName());
            if (i < exceptions.length - 1) {
                sb.append(", ");
            }
        }
    }

    /**
     * adds given parameters to string builder
     * @param parameters parameters to be appended
     */
    private void getParameters(Parameter[] parameters) {
        for (int i = 0; i < parameters.length; i++) {
            sb.append(parameters[i].getType().getSimpleName() + " " + parameters[i].getName());
            if (i < parameters.length - 1) {
                sb.append(", ");
            }
        }
    }

    /**
     * adds given annotations to string builder
     * @param annotations annotations to be appended
     * @param numberOfTabs required number of indentations
     */
    private void getAnnotations(Annotation[] annotations, int numberOfTabs) {
        for (Annotation annotation : annotations) {
            getTabs(numberOfTabs);
            sb.append("@" + annotation.annotationType().getSimpleName() + "\n");
        }
    }

    /**
     * adds information about given class' parents to string builder
     * @param clazz given class
     */
    private void getParents(Class clazz) {
        if (!clazz.getSuperclass().equals(Object.class)) {
            sb.append(" extends " + clazz.getSuperclass().getSimpleName());
        }

        Class[] interfaces = clazz.getInterfaces();
        int interfacesSize = interfaces.length;
        if (interfacesSize > 0) {
            sb.append(" implements ");
            for (int i = 0; i < interfacesSize; i++) {
                sb.append(interfaces[i].getSimpleName());
                if (i < interfacesSize - 1) {
                    sb.append(", ");
                }
            }
        }
    }

    /**
     * adds information about given class' modifiers to string builder
     * @param clazz given class
     * @param numberOfTabs required number of indentations
     */
    private void getClassModifiers(Class clazz, int numberOfTabs) {
        getTabs(numberOfTabs);
        String modifiers = Modifier.toString(clazz.getModifiers());
        if (modifiers.isEmpty()) {
            sb.append("class " + clazz.getSimpleName());
        }
        else {
            sb.append(modifiers + " class " + clazz.getSimpleName());
        }
    }

    /**
     * adds required number of indentations("   ") to string builder
     * @param numberOfTabs required number of indentations to be appended
     */
    private void getTabs(int numberOfTabs) {
        for (int i = 0; i < numberOfTabs; i++) {
            sb.append("   ");
        }
    }
}
