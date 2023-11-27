package Services;

import CustomMetadatas.CustomMetadata;
import DTOs.*;
import DecayOperationStratergies.DecayByFactorOperation;
import DecayOperationStratergies.DecayOperation;
import DecayOperationStratergies.NoDecayOperation;
import Exceptions.CreateNewTypeAheadSearchException;
import Exceptions.SearchTermException;
import Models.InsertOperation;
import Models.OperationStatus;
import Models.TypeAheadSearch;
import Models.TypeOfDecayOperation;
import Repositories.SearchTermRepository;
import Repositories.TypeAheadSearchRepository;

public class TypeAheadSearchService {

    public TypeAheadSearchResponseDTO createNewTypeAheadSystem(TypeAheadSearchRequestDTO requestDTO) throws CreateNewTypeAheadSearchException{
        TypeAheadSearch typeAheadSearch = new TypeAheadSearch();
        typeAheadSearch.setNoOfWordsRecommended(CustomMetadata.noOfSuggestions);
        requestDTO.typeAheadSearch = typeAheadSearch;
        insertToRepository(requestDTO);
        TypeAheadSearchResponseDTO responseDTO = new TypeAheadSearchResponseDTO();
        responseDTO.typeAheadSearch = typeAheadSearch;

        return responseDTO;
    }
    public TypeAheadSearchResponseDTO searchForATerm(TypeAheadSearchRequestDTO requestDTO) throws SearchTermException {
        TypeAheadSearchResponseDTO responseDTO = new TypeAheadSearchResponseDTO();
        TypeAheadSearch typeAheadSearch = requestDTO.typeAheadSearch;

        TrieRequestDTO trieRequestDTO = new TrieRequestDTO();
        trieRequestDTO.wordRepository = typeAheadSearch.getWordRepository();
        trieRequestDTO.term = requestDTO.term;
        trieRequestDTO.searchTermRepository = requestDTO.searchTermRepository;

        TrieResponseDTO trieResponseDTO = requestDTO.trieService.insertOrIncrementSearchTerm(trieRequestDTO);
        responseDTO.operationStatus = OperationStatus.SUCCESSFUL;
        responseDTO.currentFrequency = trieResponseDTO.searchTerm.getFrequency();
        return responseDTO;
    }
    public TypeAheadSearchResponseDTO getSuggestions(TypeAheadSearchRequestDTO requestDTO){
        TypeAheadSearchResponseDTO responseDTO = new TypeAheadSearchResponseDTO();
        TypeAheadSearch typeAheadSearch = requestDTO.typeAheadSearch;

        TrieRequestDTO trieRequestDTO = new TrieRequestDTO();
        trieRequestDTO.wordRepository = typeAheadSearch.getWordRepository();
        trieRequestDTO.term = requestDTO.term;
        trieRequestDTO.searchTermRepository = requestDTO.searchTermRepository;

        TrieResponseDTO trieResponseDTO = requestDTO.trieService.getTopSuggestions(trieRequestDTO);
        responseDTO.suggestions = trieResponseDTO.topSuggestions;
        responseDTO.operationStatus = OperationStatus.SUCCESSFUL;
        return responseDTO;
    }
    public TypeAheadSearchResponseDTO performDecayOperation(TypeAheadSearchRequestDTO requestDTO){
        TypeAheadSearchResponseDTO responseDTO = new TypeAheadSearchResponseDTO();
        TypeOfDecayOperation decayOperation = CustomMetadata.decayOperation;

        DecayOperation operationInstance;
        if (decayOperation.equals(TypeOfDecayOperation.DECAY_BY_FACTOR)){
             operationInstance = DecayByFactorOperation.getInstance();
        }
        else{
            operationInstance = NoDecayOperation.getInstance();

        }
        operationInstance.performDecay(requestDTO);
        responseDTO.operationStatus = OperationStatus.SUCCESSFUL;
        return responseDTO;
    }

    private void insertToRepository(TypeAheadSearchRequestDTO requestDTO) throws CreateNewTypeAheadSearchException {
        RepositoryDTO repositoryDTO = requestDTO.typeAheadSearchRepository.insertTypeAheadSearchSystem(requestDTO.typeAheadSearch.getId(), requestDTO.typeAheadSearch);
        if (repositoryDTO.insertOperation != InsertOperation.SUCCESSFUL){
            throw new CreateNewTypeAheadSearchException();
        }
    }

}
