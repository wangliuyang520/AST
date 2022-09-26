package org.example.test;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.printer.YamlPrinter;

public class Generate {
    public static void main(String[] args) {
        CompilationUnit cu = new CompilationUnit();
        ClassOrInterfaceDeclaration people = cu.addClass("People").setPublic(true);
        people.addField(String.class, "name", Modifier.Keyword.PRIVATE);
        people.addField(int.class, "age", Modifier.Keyword.PRIVATE);

        YamlPrinter printer = new YamlPrinter(true);
        System.out.println(printer.output(cu));

        System.out.println("====================================");

        System.out.println(cu.toString());
    }
}
