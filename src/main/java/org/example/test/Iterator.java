package org.example.test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

public class Iterator {
    public static void main(String[] args) {

        // method one
        // use iterator
        CompilationUnit cu = StaticJavaParser.parse("class X{void y(){int z;}}");
        Node.BreadthFirstIterator iterator = new Node.BreadthFirstIterator(cu);
        while (iterator.hasNext()) {
            System.out.println("* " + iterator.next());
        }
        System.out.println("====================================");

        // method two
        // use stream
        // "Walk" is a very general method that takes the pattern to walk, and the action to do for each walked node:
        cu.walk(Node.TreeTraversal.PREORDER, node -> System.out.println("* " + node));

        // And this is the familiar Java 8 stream API:
        cu.stream(Node.TreeTraversal.PREORDER).forEach(node -> System.out.println("* " + node));

        System.out.println("====================================");

        // Now let's assume pre-order traversal. Much nicer:
        cu.walk(node -> System.out.println("* " + node));
        cu.stream().forEach(node -> System.out.println("* " + node));

        System.out.println("====================================");


        // Based on "walk" we have several useful variants that take care of filtering on instance,
        // which is a bit painful using streams.
        // We can find all nodes of a specific type:
        cu.findAll(VariableDeclarationExpr.class).forEach(node -> System.out.println("* " + node));
        // We can find the first node of a specific type:
        cu.findFirst(VariableDeclarationExpr.class).ifPresent(node -> System.out.println("* " + node));
        // ... and several other variations. Use your IDE to find them, or check the Javadoc.
        System.out.println("====================================");

        // Care has been taken to prevent trouble with modifying the AST while traversing it:
        cu.findAll(MethodDeclaration.class).forEach(Node::remove);
        // Tada! The method has been removed and everything worked just fine:
        System.out.println(cu);
        System.out.println("====================================");
    }
}
