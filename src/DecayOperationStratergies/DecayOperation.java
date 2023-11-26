package DecayOperationStratergies;

import Controllers.TypeAheadSearchController;
import DTOs.TypeAheadSearchRequestDTO;
import Models.TypeAheadSearch;

public interface DecayOperation {
    public void performDecay(TypeAheadSearchRequestDTO requestDTO);
}
