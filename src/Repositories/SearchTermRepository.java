package Repositories;

import DTOs.RepositoryDTO;
import Models.InsertOperation;
import Models.SearchOperation;
import Models.SearchTerm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class SearchTermRepository {
    private HashMap<String, SearchTerm> stringTermPair = new HashMap<>();
    public RepositoryDTO getSearchTermByName(String term){
        RepositoryDTO repositoryDTO = new RepositoryDTO();
        if (!stringTermPair.containsKey(term)){
            repositoryDTO.searchOperation = SearchOperation.NOT_FOUND;
        }
        else{
            repositoryDTO.searchOperation = SearchOperation.FOUND;
            repositoryDTO.searchTerm = stringTermPair.get(term);
        }
        return repositoryDTO;
    }

    public RepositoryDTO insertIntoRepo(String term, SearchTerm searchTerm){
        RepositoryDTO repositoryDTO = new RepositoryDTO();
        if (stringTermPair.containsKey(term)){
            repositoryDTO.insertOperation = InsertOperation.ALREADY_EXISTS;
        }
        else{
            stringTermPair.put(term,searchTerm);
            repositoryDTO.insertOperation = InsertOperation.SUCCESSFUL;
        }
        return repositoryDTO;
    }
    public Collection<SearchTerm> getAllSearchTerms(){
        return stringTermPair.values();
    }
}
