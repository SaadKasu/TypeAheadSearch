package Client;

import Controllers.ClientController;
import DTOs.ClientRequestDTO;
import DTOs.ClientResponseDTO;
import Models.OperationStatus;
import Models.OperationType;

import java.util.Scanner;

public class Client {
    private static ClientController clientController;
    public static void main(String[] args) {
        System.out.println("Hello, welcome to the Type Ahead Search Implementation\n\n");
        ClientResponseDTO responseDTO = createTypeAheadSystem();
        if (responseDTO.operationStatus == OperationStatus.FAILED){
            System.out.println("Unfortunately we have encountered some issue. Please try again later");
            return;
        }
        while(true){
            responseDTO = performOperation();
            if (responseDTO.operationStatus == OperationStatus.EXIT){
                break;
            }
        }
    }

     static ClientResponseDTO createTypeAheadSystem(){
        clientController = new ClientController();

        ClientRequestDTO requestDTO = new ClientRequestDTO();
        requestDTO.operationType = OperationType.CREATE;
        ClientResponseDTO responseDTO = clientController.performAnOperation(requestDTO);

        return responseDTO;
    }

    static ClientResponseDTO performOperation(){
        Scanner inp = new Scanner(System.in);
        ClientResponseDTO responseDTO = new ClientResponseDTO();
        System.out.println("Please Select The Next Operation You Would Like To Perform : \n1.)Search For A Term\n2.)Get Suggestions For A Prefix \n3.)Perform Decay Operation \n4.) Exit \nYour Input - ");
        String input = inp.nextLine();
        try{
            Integer.parseInt(input);
        }
        catch (Exception e){
            System.out.println("Please select a valid input");
            responseDTO.operationStatus = OperationStatus.FAILED;
            return responseDTO;
        }
        int choice = Integer.parseInt(input);
        ClientRequestDTO requestDTO = new ClientRequestDTO();
        switch (choice){
            case 1 : {
                requestDTO.operationType = OperationType.SEARCH;
            }
            case 2 : {
                requestDTO.operationType = OperationType.GET_SUGGESTIONS;
            }
            case 3 :{
                requestDTO.operationType = OperationType.DECAY;
            }
            default:{
                requestDTO.operationType = OperationType.EXIT;
            }
        }
        responseDTO = clientController.performAnOperation(requestDTO);
        return responseDTO;
    }
}
