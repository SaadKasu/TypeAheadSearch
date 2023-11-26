package Models;

import java.util.ArrayList;

public class Trie extends BaseClass{
    private TrieState stateOfTrie;
    private Trie[] nextChars;
    private ArrayList<SearchTerm> topSuggestions;
    public Trie(){
        stateOfTrie = TrieState.WORD_DOES_NOT_END;
        nextChars = new Trie[256];
        topSuggestions = new ArrayList<>();
    }

    public void setStateOfTrie(TrieState stateOfTrie){
        this.stateOfTrie = stateOfTrie;
    }

    public TrieState getStateOfTrie() {
        return stateOfTrie;
    }

    public ArrayList<SearchTerm> getTopSuggestions() {
        return topSuggestions;
    }

    public Trie[] getNextChars() {
        return nextChars;
    }
}
