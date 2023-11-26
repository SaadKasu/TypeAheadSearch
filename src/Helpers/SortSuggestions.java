package Helpers;

import Models.SearchTerm;

import java.util.Comparator;

public class SortSuggestions implements Comparator<SearchTerm> {
    public int compare(SearchTerm a, SearchTerm b){
        if (a.getFrequency() == b.getFrequency())
            return b.getWord().compareTo(a.getWord());
        else if (b.getFrequency() > a.getFrequency())
            return 1;
        else
            return -1;
    }
}
