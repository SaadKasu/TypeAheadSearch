package Models;

import java.util.ArrayList;

public class Trie {
    private TrieState stateOfTrie;
    private Trie[] nextChars;
    private ArrayList<SearchTerm> topSuggestions;
}
