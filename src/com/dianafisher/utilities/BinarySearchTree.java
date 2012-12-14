package com.dianafisher.utilities;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: dianafisher
 * Date: 12/13/12
 * Time: 12:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class BinarySearchTree {

    class Node {
        private String key;
        private Node left;
        private Node right;

        public Node(String k)
        {
            this.key = k;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public BinarySearchTree()
    {
        root = null;
    }

    public void put(String key) {
        root = put(root, key);
    }

    private Node put(Node node, String key) {
        if (node == null) return new Node(key);

        if (key.compareTo(node.key) < 0) {
            // put key on left side
            node.left = put(node.left, key);
        }
        else if (key.compareTo(node.key) > 0) {
            // put key on right side of tree
            node.right = put(node.right, key);
        }
        else node.key = key;
        return node;
    }

    public void printLevelOrder() {
        LinkedList<Node> openList = new LinkedList<Node>();
        // put the root on the open list
        openList.add(root);
        while (openList.size() > 0) {
            Node current = openList.remove();

            if (current != null) {
                System.out.print(current.key);
                if (current.left != null) System.out.print(", left: " + current.left.key);
                if (current.right != null) System.out.print(", right: " + current.right.key);
                System.out.println();
                openList.add(current.left);
                openList.add(current.right);
            }
        }
    }

    public void printPostOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        // left, right, current node
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.key);
    }

    public void printPreOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        // current node, left side, right side
        if (node == null) return;
        System.out.println(node.key);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void printInOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        // left side, current node, right side
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.key);
        inOrder(node.right);
    }


    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.put("F");
        bst.put("B");
        bst.put("G");
        bst.put("A");
        bst.put("D");
        bst.put("C");
        bst.put("E");
        bst.put("I");
        bst.put("H");

        System.out.println("level order;");
        bst.printLevelOrder();

        System.out.println("\nin order:");
        bst.printInOrder();

        System.out.println("\npre order:");
        bst.printPreOrder();

        System.out.println("\npost order:");
        bst.printPostOrder();

//        BinarySearchTree bstWorst = new BinarySearchTree();
//        bstWorst.put("A");
//        bstWorst.put("B");
//        bstWorst.put("C");
//        bstWorst.put("D");
//        bstWorst.put("E");
//        bstWorst.put("F");
//        bstWorst.put("G");
//        bstWorst.put("H");
//        bstWorst.put("I");
//
//        bstWorst.printLevelOrder();

        BinarySearchTree desc = new BinarySearchTree();
        desc.put("I");
        desc.put("H");
        desc.put("G");
        desc.put("F");
        desc.put("E");
        desc.put("D");
        desc.put("C");
        desc.put("B");
        desc.put("A");
        System.out.println("\ntree constructed in descending order:");
        desc.printLevelOrder();


    }
}
