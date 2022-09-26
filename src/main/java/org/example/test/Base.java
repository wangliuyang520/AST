package org.example.test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.YamlPrinter;

public class Base {
    public static void main(String[] args) {
        // Parse the code you want to inspect:
        CompilationUnit cu = StaticJavaParser.parse("class X { int x; }");
        // Now comes the inspection code:
        System.out.println(cu);

        // Now comes the inspection code:
        YamlPrinter printer = new YamlPrinter(true);
        System.out.println(printer.output(cu));
    }
}
