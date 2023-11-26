package DTOs;

import Models.TypeAheadSearch;
import Repositories.SearchTermRepository;
import Repositories.TypeAheadSearchRepository;
import Services.TrieService;
import Services.TypeAheadSearchService;

public class TypeAheadSearchRequestDTO {
    public String searchTerm;
    public TypeAheadSearch typeAheadSearch;

    public TypeAheadSearchService typeAheadSearchService;
    public SearchTermRepository searchTermRepository;
    public TypeAheadSearchRepository typeAheadSearchRepository;
    public TrieService trieService;
}
