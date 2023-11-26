package Controllers;

import Client.Client;
import DTOs.ClientRequestDTO;
import DTOs.ClientResponseDTO;
import DTOs.TypeAheadSearchRequestDTO;
import DTOs.TypeAheadSearchResponseDTO;
import Exceptions.CreateNewTypeAheadSearchException;
import Models.OperationStatus;
import Models.OperationType;
import Models.SearchTerm;

import java.util.Scanner;

public class ClientController {

    private TypeAheadSearchController searchController;
    public ClientController(){
        searchController = new TypeAheadSearchController();
    }

    public ClientResponseDTO performAnOperation(ClientRequestDTO requestDTO){
        ClientResponseDTO responseDTO = new ClientResponseDTO();
        OperationType choice = requestDTO.operationType;
        switch (choice){
            case CREATE: {
                responseDTO = createNewTypeAheadSystem();
                break;
            }
            case SEARCH : {
                responseDTO = searchForATerm();
                break;
            }
            case GET_SUGGESTIONS : {
                responseDTO = getSuggestions();
                break;
            }
            case DECAY : {
                responseDTO = performDecayOperation();
            }
            case EXIT : {
                responseDTO.operationStatus = OperationStatus.EXIT;
                break;
            }
        }
        return responseDTO;
    }
    private ClientResponseDTO createNewTypeAheadSystem(){
        TypeAheadSearchRequestDTO requestDTO = new TypeAheadSearchRequestDTO();
        ClientResponseDTO responseDTO = new ClientResponseDTO();
        try {
            searchController.createNewTypeAheadSystem(requestDTO);
            responseDTO.operationStatus = OperationStatus.SUCCESSFUL;
        }catch (Exception exp){
            System.out.println(exp.getMessage());
            responseDTO.operationStatus = OperationStatus.FAILED;
        }
        return  responseDTO;
    }

    private ClientResponseDTO searchForATerm(){
        TypeAheadSearchRequestDTO requestDTO = new TypeAheadSearchRequestDTO();
        ClientResponseDTO responseDTO = new ClientResponseDTO();
        System.out.println("Please enter the term you want to search for - ");
        Scanner inp = new Scanner(System.in);
        String input = inp.nextLine();
        requestDTO.searchTerm = input;
        try{
            TypeAheadSearchResponseDTO typeAheadSearchResponseDTO = searchController.searchForATerm(requestDTO);
            System.out.println("The count For the term has been incremented in the system to - " + typeAheadSearchResponseDTO.currentFrequency);
            responseDTO.operationStatus = OperationStatus.SUCCESSFUL;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            responseDTO.operationStatus = OperationStatus.FAILED;
        }
        return responseDTO;
    }
    private ClientResponseDTO getSuggestions(){
        TypeAheadSearchRequestDTO requestDTO = new TypeAheadSearchRequestDTO();
        ClientResponseDTO responseDTO = new ClientResponseDTO();
        System.out.println("Please enter the term you want to get suggestions for - ");
        Scanner inp = new Scanner(System.in);
        String input = inp.nextLine();
        requestDTO.searchTerm = input;
        try{
            TypeAheadSearchResponseDTO typeAheadSearchResponseDTO = searchController.getSuggestions(requestDTO);
            System.out.println("The top suggestions for this query are :");
            for (String suggestion : typeAheadSearchResponseDTO.suggestions){
                System.out.println(suggestion);
            }
            responseDTO.operationStatus = OperationStatus.SUCCESSFUL;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            responseDTO.operationStatus = OperationStatus.FAILED;
        }
        return responseDTO;
    }
    private ClientResponseDTO performDecayOperation(){
        TypeAheadSearchRequestDTO requestDTO = new TypeAheadSearchRequestDTO();
        ClientResponseDTO responseDTO = new ClientResponseDTO();
        System.out.println("**** Performing decay operation on the terms in the system ****");
        try{
            TypeAheadSearchResponseDTO typeAheadSearchResponseDTO = searchController.performDecayOperation(requestDTO);
            System.out.println("Decay Operation Successful");
            responseDTO.operationStatus = OperationStatus.SUCCESSFUL;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            responseDTO.operationStatus = OperationStatus.FAILED;
        }
        return responseDTO;
    }

}
