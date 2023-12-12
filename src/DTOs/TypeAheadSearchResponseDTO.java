package DTOs;

import Models.OperationStatus;
import Models.SearchTerm;
import Models.TypeAheadSearch;

import java.util.ArrayList;

public class TypeAheadSearchResponseDTO {
    public double currentFrequency;
    public ArrayList<String> suggestions;
    public OperationStatus operationStatus;
    public TypeAheadSearch typeAheadSearch;
}
