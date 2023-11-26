package Services;

import CustomMetadatas.CustomMetadata;
import DTOs.RepositoryDTO;
import DTOs.TrieRequestDTO;
import DTOs.TrieResponseDTO;
import Exceptions.SearchTermException;
import Helpers.SortSuggestions;
import Models.*;
import Repositories.SearchTermRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TrieService {
    public TrieResponseDTO insertOrIncrementSearchTerm(TrieRequestDTO requestDTO) throws SearchTermException{
        Trie wordRepositoryHead = requestDTO.wordRepository;
        String term = requestDTO.searchTerm;
        TrieResponseDTO responseDTO = new TrieResponseDTO();
        responseDTO.searchTerm = traverseTheTrie(requestDTO,0,wordRepositoryHead);
        responseDTO.operationStatus = OperationStatus.SUCCESSFUL;
        return responseDTO;
    }
    private SearchTerm traverseTheTrie(TrieRequestDTO requestDTO, int index, Trie node) throws SearchTermException{
        if (index == requestDTO.searchTerm.length()){
            SearchTerm searchTerm;
            if (node.getStateOfTrie() == TrieState.WORD_ENDS){
                RepositoryDTO repositoryDTO = requestDTO.searchTermRepository.getSearchTermByName(requestDTO.searchTerm);
                if (repositoryDTO.searchOperation == SearchOperation.NOT_FOUND)
                    throw new SearchTermException();
                searchTerm = repositoryDTO.searchTerm;
                searchTerm.setFrequency(searchTerm.getFrequency() + CustomMetadata.increment);
            }
            else{
                node.setStateOfTrie(TrieState.WORD_ENDS);
                searchTerm = new SearchTerm();
                searchTerm.setFrequency(CustomMetadata.increment);
                RepositoryDTO repositoryDTO = requestDTO.searchTermRepository.insertIntoRepo(requestDTO.searchTerm, searchTerm);
                if (repositoryDTO.insertOperation == InsertOperation.ALREADY_EXISTS)
                    throw new SearchTermException();
            }
            adjustTopSuggestionsAtNode(searchTerm, node.getTopSuggestions());
            return searchTerm;
        }
        char ch = requestDTO.searchTerm.charAt(index);
        if (node.getNextChars()[ch] == null)
            node.getNextChars()[ch] = new Trie();
        SearchTerm searchTerm = traverseTheTrie(requestDTO,index + 1,node.getNextChars()[ch]);
        adjustTopSuggestionsAtNode(searchTerm, node.getTopSuggestions());
        return searchTerm;
    }

    private void adjustTopSuggestionsAtNode(SearchTerm searchTerm, ArrayList<SearchTerm> topSuggestions){
        if (searchTermIsPresent(searchTerm,topSuggestions)){
            adjustSuggestions(topSuggestions);
        }
        else if (topSuggestions.size() < CustomMetadata.noOfSuggestions){
            topSuggestions.add(searchTerm);
            adjustSuggestions(topSuggestions);
        }
        else{
            SearchTerm leastFrequentSearchTerm = topSuggestions.get(CustomMetadata.noOfSuggestions - 1);
            if (leastFrequentSearchTerm.getFrequency() < searchTerm.getFrequency() || (leastFrequentSearchTerm.getFrequency() == searchTerm.getFrequency() && searchTerm.getWord().compareTo(leastFrequentSearchTerm.getWord()) > 0 )){
                topSuggestions.set(CustomMetadata.noOfSuggestions - 1, searchTerm);
                adjustSuggestions(topSuggestions);
            }
        }
    }

    private boolean searchTermIsPresent(SearchTerm searchTerm, ArrayList<SearchTerm> topSuggestions){
        for (SearchTerm currentSuggestion : topSuggestions){
            if (currentSuggestion.getWord().equals(searchTerm.getWord()))
                return true;
        }
        return false;
    }

    private void adjustSuggestions(ArrayList<SearchTerm> topSuggestions){
        Collections.sort(topSuggestions, new SortSuggestions());
    }

    public TrieResponseDTO getTopSuggestions(TrieRequestDTO requestDTO){
        Trie wordRepositoryHead = requestDTO.wordRepository;
        String term = requestDTO.searchTerm;
        TrieResponseDTO responseDTO = new TrieResponseDTO();
        responseDTO.topSuggestions = traverseTrieForTopSuggestions(term,0,wordRepositoryHead);
        return responseDTO;
    }

    private ArrayList<String> traverseTrieForTopSuggestions(String term, int index, Trie node){
        if (index == term.length()){
            ArrayList<String> topSuggestions = new ArrayList<>();
            for (int i = 0 ; i<node.getTopSuggestions().size(); i++){
                topSuggestions.add(node.getTopSuggestions().get(i).getWord());
            }
            return topSuggestions;
        }
        char ch = term.charAt(index);
        if (node.getNextChars()[ch] == null){
            ArrayList<String> topSuggestions = new ArrayList<>();
            return topSuggestions;
        }
        return traverseTrieForTopSuggestions(term,index + 1,node.getNextChars()[ch]);
    }
    
}
