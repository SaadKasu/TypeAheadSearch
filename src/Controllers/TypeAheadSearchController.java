package Controllers;

import DTOs.TypeAheadSearchRequestDTO;
import DTOs.TypeAheadSearchResponseDTO;
import Models.OperationStatus;
import Models.Trie;
import Models.TypeAheadSearch;
import Repositories.SearchTermRepository;
import Repositories.TypeAheadSearchRepository;
import Services.TrieService;
import Services.TypeAheadSearchService;

public class TypeAheadSearchController {
    private TypeAheadSearch typeAheadSearch;
    private TypeAheadSearchService typeAheadSearchService;
    private SearchTermRepository searchTermRepository;
    private TypeAheadSearchRepository typeAheadSearchRepository;
    private TrieService trieService;

    public TypeAheadSearchController(){
        typeAheadSearchService = new TypeAheadSearchService();
        searchTermRepository = new SearchTermRepository();
        typeAheadSearchRepository = new TypeAheadSearchRepository();
        trieService = new TrieService();
    }
    public TypeAheadSearchResponseDTO createNewTypeAheadSystem(TypeAheadSearchRequestDTO requestDTO){
        requestDTO.searchTermRepository = this.searchTermRepository;
        requestDTO.trieService = this.trieService;
        requestDTO.typeAheadSearchRepository = this.typeAheadSearchRepository;
        TypeAheadSearchResponseDTO responseDTO = new TypeAheadSearchResponseDTO();
        try{
            responseDTO = typeAheadSearchService.createNewTypeAheadSystem(requestDTO);
            typeAheadSearch = responseDTO.typeAheadSearch;
            responseDTO.operationStatus = OperationStatus.SUCCESSFUL;
        }
        catch(Exception exp){
            System.out.println(exp.getMessage());
            responseDTO.operationStatus = OperationStatus.FAILED;
        }
        return responseDTO;
    }

    public TypeAheadSearchResponseDTO searchForATerm(TypeAheadSearchRequestDTO requestDTO){
        requestDTO.searchTermRepository = this.searchTermRepository;
        requestDTO.trieService = this.trieService;
        requestDTO.typeAheadSearchRepository = this.typeAheadSearchRepository;
        TypeAheadSearchResponseDTO responseDTO = new TypeAheadSearchResponseDTO();
        requestDTO.typeAheadSearch = typeAheadSearch;
        try{
            responseDTO = typeAheadSearchService.searchForATerm(requestDTO);
            responseDTO.operationStatus = OperationStatus.SUCCESSFUL;
        }
        catch (Exception exp){
            System.out.println(exp.getMessage());
            responseDTO.operationStatus = OperationStatus.FAILED;
        }
        return responseDTO;
    }

    public TypeAheadSearchResponseDTO getSuggestions(TypeAheadSearchRequestDTO requestDTO){
        requestDTO.searchTermRepository = this.searchTermRepository;
        requestDTO.trieService = this.trieService;
        requestDTO.typeAheadSearchRepository = this.typeAheadSearchRepository;
        TypeAheadSearchResponseDTO responseDTO = new TypeAheadSearchResponseDTO();
        requestDTO.typeAheadSearch = typeAheadSearch;
        try{
            responseDTO = typeAheadSearchService.getSuggestions(requestDTO);
            responseDTO.operationStatus = OperationStatus.SUCCESSFUL;
        }
        catch (Exception exp){
            System.out.println(exp.getMessage());
            responseDTO.operationStatus = OperationStatus.FAILED;
        }
        return responseDTO;
    }

    public TypeAheadSearchResponseDTO performDecayOperation(TypeAheadSearchRequestDTO requestDTO){
        requestDTO.searchTermRepository = this.searchTermRepository;
        requestDTO.trieService = this.trieService;
        requestDTO.typeAheadSearchRepository = this.typeAheadSearchRepository;
        TypeAheadSearchResponseDTO responseDTO = new TypeAheadSearchResponseDTO();
        requestDTO.typeAheadSearch = typeAheadSearch;
        try{
            responseDTO = typeAheadSearchService.performDecayOperation(requestDTO);
            responseDTO.operationStatus = OperationStatus.SUCCESSFUL;
        }
        catch (Exception exp){
            System.out.println(exp.getMessage());
            responseDTO.operationStatus = OperationStatus.FAILED;
        }
        return responseDTO;
    }

}
