package Java;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter the server name
        System.out.print("Enter the name of the central server: ");
        String serverName = scanner.nextLine();

        // Create a star network with the provided server name
        Star starNetwork = new Star(serverName);
        System.out.println("Central server named '" + serverName + "' has been successfully added.");

        // Prompt user to enter the number of clients to add
        System.out.print("Enter the number of clients to add: ");
        int numClients = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Add clients to the network
        for (int i = 1; i <= numClients; i++) {
            System.out.print("Enter the name of client " + i + ": ");
            String clientName = scanner.nextLine();
            starNetwork.insertNode(clientName);
            System.out.println("Client '" + clientName + "' has been successfully added.");
        }

// Send messages from one client to another and print out the received messages
        for (int i = 1; i <= numClients; i++) {
            String senderName = "Client" + i;
            String receiverName = (i == numClients) ? "Client1" : "Client" + (i + 1);

            ClientNode sender = starNetwork.getConnectedClients().get(senderName);
            ClientNode receiver = starNetwork.getConnectedClients().get(receiverName);

            if (sender != null && receiver != null) {
                // Prompt user to enter the message
                System.out.print("Enter the message to send from " + senderName + " to " + receiverName + ": ");
                String message = scanner.nextLine();
                sender.send(receiverName, message); // Send the user-entered message

                // Receive message
                String receivedMessage = receiver.receive();
                System.out.println("Message received by " + receiverName + ": " + receivedMessage);
            } else {
                System.out.println("Error: Sender or receiver not found.");
            }
        }


        scanner.close();
    }
}
