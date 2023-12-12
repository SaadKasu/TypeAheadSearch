package DTOs;

import Models.OperationStatus;
import Models.SearchTerm;

import java.util.ArrayList;

public class TrieResponseDTO {
    public OperationStatus operationStatus;
    public SearchTerm searchTerm;
    public ArrayList<String> topSuggestions;
}
