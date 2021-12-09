package InterfaceAdapter.Search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {

    /**
     * a node of Trie
     */
    public static class TrieNode {
        Map<Character, TrieNode> children;
        char c;
        boolean isEndWord;

        /**
         * Creates a node with a char
         */
        public TrieNode(char c) {
            this.c = c;
            children = new HashMap<>();
        }

        /**
         * Creates an empty node
         */
        public TrieNode() {
            children = new HashMap<>();
        }

        /**
         * Insert a node into the Trie
         */
        public void insert(String word) {
            if (word == null || word.isEmpty())
                return;
            char firstChar = word.charAt(0);
            TrieNode child = children.get(firstChar);
            if (child == null) {
                child = new TrieNode(firstChar);
                children.put(firstChar, child);
            }

            if (word.length() > 1)
                child.insert(word.substring(1));
            else
                child.isEndWord = true;
        }

    }

    static TrieNode root;

    public Trie(List<String> words) {
        root = new TrieNode();
        for (String word : words)
            root.insert(word);

    }

    /**
     * helper method of suggest
     */
    public static void suggestHelper(TrieNode root, List<String> list, StringBuffer curr) {
        if (root.isEndWord) {
            list.add(curr.toString());
        }

        if (root.children == null || root.children.isEmpty())
            return;

        for (TrieNode child : root.children.values()) {
            suggestHelper(child, list, curr.append(child.c));
            curr.setLength(curr.length() - 1);
        }
    }
    /**
     * return all recommendations to auto-complete the client's input word based on the strings stored in the Trie，
     * return an empty List if nothing is found
     * @param prefix the String that client enter
     * @return return a list of suggested movieNames
     */
    public static List<String> suggest(String prefix) {
        List<String> list = new ArrayList<>();
        TrieNode lastNode = root;
        StringBuffer curr = new StringBuffer();
        for (char c : prefix.toCharArray()) {
            if (lastNode.children.containsKey(Character.toLowerCase(c))) {
                lastNode = lastNode.children.get(Character.toLowerCase(c));
            }else if (lastNode.children.containsKey(Character.toUpperCase(c))) {
                lastNode = lastNode.children.get(Character.toUpperCase(c));
            } else lastNode = lastNode.children.get(c);
            if (lastNode == null)
                return list;
            curr.append(lastNode.c);
        }
        suggestHelper(lastNode, list, curr);
        return list;
    }



}
