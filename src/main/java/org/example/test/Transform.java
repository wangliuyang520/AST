package org.example.test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

public class Transform {
    public static void main(String[] args) {
        CompilationUnit compilationUnit = StaticJavaParser.parse("abstract class People {\n" +
                "    private String name;\n" +
                "    private int age;\n" +
                "}\n");
        compilationUnit.findAll(ClassOrInterfaceDeclaration.class).stream()
                .filter(c -> !c.isInnerClass()
                        && c.isAbstract()
                        && !c.getNameAsString().startsWith("Abstract"))
                .forEach(c -> {
                    String name = c.getNameAsString();
                    String targetName = "Abstract" + name;
                    System.out.println("rename " + name + " to " + targetName);
                    c.setName(targetName);
                });
    }
}

