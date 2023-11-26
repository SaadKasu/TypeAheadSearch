package Models;

import DecayOperationStratergies.DecayOperation;

public class TypeAheadSearch extends BaseClass{
    private Trie wordRepository;
    private int noOfWordsRecommended;
    public TypeAheadSearch(){
        wordRepository = new Trie();
    }
    public String getId(){
        return this.id;
    }
    public int getNoOfWordsRecommended() {
        return noOfWordsRecommended;
    }

    public void setNoOfWordsRecommended(int noOfWordsRecommended) {
        this.noOfWordsRecommended = noOfWordsRecommended;
    }

    public Trie getWordRepository() {
        return wordRepository;
    }
}
