import java.util.*;

class Tree {
    private Node root = null;
    private int m;

    private class Node {
        List<Integer> values;
        List<Node> children;
        Node parent;

        Node() {
            this.values = new ArrayList<>();
            this.children = new ArrayList<>();
            this.parent = null;
        }

        Node getChild(int key) {
            for (int i = 0; i < this.values.size(); i++) {
                if (key < this.values.get(i)) {
                    return (i < this.children.size()) ? this.children.get(i) : null;
                }
            }
            return (this.values.size() < this.children.size()) ? this.children.get(this.values.size()) : null;
        }

        void insert(int val) {
            if (!this.values.contains(val)) {
                this.values.add(val);
                Collections.sort(this.values);
            }
        }
    }

    Tree(int m) {
        this.m = m;
        if(m % 2 != 0)
            this.m += 1;
    }

    void insertNode(int val) {
        if (this.root == null) {
            this.root = new Node();
            this.root.insert(val);
            return;
        }

        Node currentNode = this.root;
        while (!currentNode.children.isEmpty()) {
            currentNode = currentNode.getChild(val);
        }

        currentNode.insert(val);

        while (currentNode.values.size() > this.m) {
            currentNode = balance(currentNode);
        }
    }

    private Node balance(Node currentNode) {
        Node firstChild = new Node();
        Node secondChild = new Node();
        int midIndex = this.m / 2;
        int midValue = currentNode.values.get(midIndex);

        for (int i = 0; i < currentNode.values.size(); i++) {
            if (i < midIndex) {
                firstChild.insert(currentNode.values.get(i));
            } else if (i > midIndex) {
                secondChild.insert(currentNode.values.get(i));
            }
        }

        if (!currentNode.children.isEmpty()) {
            for (int i = 0; i <= midIndex; i++) {
                Node grandChild = currentNode.children.get(i);
                firstChild.children.add(grandChild);
                if (grandChild != null) {
                    grandChild.parent = firstChild;
                }
            }
            for (int i = midIndex + 1; i < currentNode.children.size(); i++) {
                Node grandChild = currentNode.children.get(i);
                secondChild.children.add(grandChild);
                if (grandChild != null) {
                    grandChild.parent = secondChild;
                }
            }
        }

        if (currentNode.parent == null) {
            Node newNode = new Node();
            newNode.insert(midValue);
            newNode.children.add(firstChild);
            newNode.children.add(secondChild);
            firstChild.parent = newNode;
            secondChild.parent = newNode;
            this.root = newNode;
            return newNode;
        }

        Node parent = currentNode.parent;
        int insertIndex = 0;
        while (insertIndex < parent.values.size() && midValue > parent.values.get(insertIndex)) {
            insertIndex++;
        }

        parent.values.add(insertIndex, midValue);
        int childIndex = parent.children.indexOf(currentNode);
        if (childIndex != -1) {
            parent.children.remove(childIndex);
        }
        parent.children.add(insertIndex, firstChild);
        parent.children.add(insertIndex + 1, secondChild);
        firstChild.parent = parent;
        secondChild.parent = parent;

        return parent;
    }

    void searchValue(int val) {
        Node temp = this.root;
        int lvl = 0;
        while (temp != null) {
            if (temp.values.contains(val)) {
                System.out.println("Number: " + val + ", Found at Node level: " + lvl);
                return;
            }
            if (temp.children.isEmpty())
                break;
            temp = temp.getChild(val);
            lvl += 1;
        }
        System.out.println("Number: " + val + ", Not Found.");
    }

    void printTree() {
        traverse(this.root, 0);
    }

    private void traverse(Node node, int level) {
        if (node == null)
            return;
        System.out.println("Level " + level + ": " + node.values);
        for (Node child : node.children)
            traverse(child, level + 1);
    }
}


public class BTree{

    private static List<Integer> generate(int n, int h){
        List<Integer> numbers = new ArrayList<>();
        for(int i=0; i<n; i++) {
            int num = (int)(Math.random() * h);
            if(!numbers.contains(num))
                numbers.add(num);
        }
        System.out.println(numbers.size());
        return numbers;
    }

    public static void main(String[] args) {
        Tree btree4 = new Tree(6);
        List<Integer> numbers = generate(5000, 20000);
        for(int i=0; i<numbers.size(); i++)
            btree4.insertNode(numbers.get(i));
        btree4.printTree();
        //for(int i : numbers)
            //btree4.searchValue(i);
    }
}
