package DecayOperationStratergies;

import CustomMetadatas.CustomMetadata;
import DTOs.TypeAheadSearchRequestDTO;
import Models.SearchTerm;
import Models.Trie;
import Models.TrieState;
import Models.TypeAheadSearch;
import Repositories.SearchTermRepository;

import java.util.ArrayList;
import java.util.Collection;

public class DecayByFactorOperation implements DecayOperation{
    private double decayFactor = CustomMetadata.decayFactor;
    private static DecayByFactorOperation decayByFactorOperation;
    private DecayByFactorOperation(){
    }

    public static DecayByFactorOperation getInstance(){
        if (decayByFactorOperation != null)
            return decayByFactorOperation;
        return decayByFactorOperation = new DecayByFactorOperation();
    }
    public void performDecay(TypeAheadSearchRequestDTO requestDTO) {
        Collection<SearchTerm> allSearchTerms = requestDTO.searchTermRepository.getAllSearchTerms();
        for (SearchTerm searchTerm : allSearchTerms){
            searchTerm.setFrequency(searchTerm.getFrequency()/decayFactor);
        }
    }

}
