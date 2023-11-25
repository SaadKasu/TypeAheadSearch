package Controllers;

import Client.Client;
import DTOs.ClientRequestDTO;
import DTOs.ClientResponseDTO;
import DTOs.TypeAheadSearchResponseDTO;
import Exceptions.CreateNewTypeAheadSearchException;
import Models.OperationStatus;

public class ClientController {

    private TypeAheadSearchController searchController;
    public ClientController(){
        searchController = new TypeAheadSearchController();
    }
    private ClientResponseDTO createNewTypeAheadSystem(){
        ClientResponseDTO responseDTO = new ClientResponseDTO();
        try {
            searchController.createNewTypeAheadSystem();
            responseDTO.operationStatus = OperationStatus.SUCCESSFUL;
        }catch (CreateNewTypeAheadSearchException exp){
            System.out.println("Creation of a new type ahead system has failed");
            responseDTO.operationStatus = OperationStatus.FAILED;
        }
        catch (Exception exp){
            System.out.println("Unknown error has occured");
            responseDTO.operationStatus = OperationStatus.FAILED;
        }
        return  responseDTO;
    }

    private ClientResponseDTO searchForATerm(ClientRequestDTO requestDTO){

    }
    private ClientResponseDTO getSuggestions(ClientRequestDTO requestDTO){

    }
    private ClientResponseDTO performDecayOperation(ClientResponseDTO responseDTO){

    }

}
