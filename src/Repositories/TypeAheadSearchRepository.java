package Repositories;

import DTOs.RepositoryDTO;
import Models.InsertOperation;
import Models.SearchOperation;
import Models.TypeAheadSearch;

import java.util.HashMap;

public class TypeAheadSearchRepository {
    private HashMap<String, TypeAheadSearch> typeAheadSearchRepo = new HashMap<>();
    public RepositoryDTO insertTypeAheadSearchSystem(String id, TypeAheadSearch typeAheadSearch){
        RepositoryDTO repositoryDTO = new RepositoryDTO();
        if (typeAheadSearchRepo.containsKey(id)){
            repositoryDTO.insertOperation = InsertOperation.ALREADY_EXISTS;
        }
        else{
            typeAheadSearchRepo.put(id, typeAheadSearch);
            repositoryDTO.insertOperation = InsertOperation.SUCCESSFUL;
        }
        return repositoryDTO;
    }
    public RepositoryDTO searchTypeAheadSearchSystem(String id){
        RepositoryDTO repositoryDTO = new RepositoryDTO();
        if (!typeAheadSearchRepo.containsKey(id))
            repositoryDTO.searchOperation = SearchOperation.NOT_FOUND;
        else{
            repositoryDTO.searchOperation = SearchOperation.FOUND;
            repositoryDTO.typeAheadSearch = typeAheadSearchRepo.get(id);
        }
        return repositoryDTO;
    }
}
