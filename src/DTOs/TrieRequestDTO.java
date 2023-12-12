package DTOs;

import Models.Trie;
import Repositories.SearchTermRepository;

public class TrieRequestDTO {
    public String term;
    public Trie wordRepository;
    public SearchTermRepository searchTermRepository;
}
