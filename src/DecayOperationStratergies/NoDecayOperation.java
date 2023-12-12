package DecayOperationStratergies;

import DTOs.TypeAheadSearchRequestDTO;
import Models.TypeAheadSearch;

public class NoDecayOperation implements DecayOperation{

    public static NoDecayOperation decayOperation;

    private NoDecayOperation(){
    }

    public static NoDecayOperation getInstance(){
        if (decayOperation != null)
            return decayOperation;
        return decayOperation = new NoDecayOperation();
    }
    @Override
    public void performDecay(TypeAheadSearchRequestDTO requestDTO) {
        System.out.println("No decay is needed");
    }
}
