import objetcs.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //♥♦♠♣

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Card> deck = new ArrayList<>();
    static ArrayList<Card> playerHand = new ArrayList<>();
    static ArrayList<Card> computerHand = new ArrayList<>();
    static ArrayList<Card> allTakenCards = new ArrayList<>();
    static int player_counter = 0, computer_counter = 0, total_player = 0, total_computer = 0;
    static boolean victory = false;
    static boolean computer_option = true,player_pass = false,computer_pass = false;
    static int player_option = 1;


    private static int getRandomNumber() {
        return (int) Math.floor(Math.random() * (51) + 1);
    }

    private static int getRandomIA() {
        return (int) Math.floor(Math.random() * (100) + 1);
    }

    private static void fillDeck() {
        for (int x = 1; x < 5; x++) { //Fill the Type
            for (int y = 1; y < 14; y++) { //Fill the number
                deck.add(new Card(y, x));
            }
        }
    }

    private static Card takeCard() { //Recursive function to take a new card
        int cardNumber = getRandomNumber();
        Card newCard = deck.get(getRandomNumber());

        for (Card card : allTakenCards) {
            if (card.getNumber() == newCard.getNumber() && card.getType() == newCard.getType()) {
                return takeCard();
            }
        }
        allTakenCards.add(newCard);
        return newCard;

    }

    private static void showCard(Card card) {
        String type = null;
        String number;

        switch (card.getType()) {
            case 1:
                type = "♥";
                break;
            case 2:
                type = "♦";
                break;
            case 3:
                type = "♠";
                break;
            case 4:
                type = "♣";
                break;
        }

        switch (card.getNumber()) {
            case 11:
                number = "J";
                break;
            case 12:
                number = "Q";
                break;
            case 13:
                number = "K";
                break;
            case 1:
                number = "A";
                break;
            default:
                number = String.valueOf(card.getNumber());
        }
        System.out.print("|" + number + " " + type + "|");
    }

    private static void showPlayerHand() {
        for (Card card : playerHand) {
            showCard(card);
        }
    }

    private static void showComputerHand() {
        for (Card card : computerHand) {
            showCard(card);
        }
    }

    private static void takeTotalPlayer() {
        total_player = 0;
        for (Card card : playerHand) {
            total_player += card.getNumber();
        }
    }

    private static void takeTotalComputer() {
        total_computer = 0;
        for (Card card : computerHand) {
            total_computer += card.getNumber();
        }
    }

    private static void addCardPlayer() {
        playerHand.add(takeCard());
    }

    private static void addCardComputer() {
        computerHand.add(takeCard());
    }

    private static boolean computerIA() {
        int number = getRandomIA();
        if (player_counter == 21) {
            addCardComputer();
            return true;
        }

        if (total_computer < 8) {
            addCardComputer();
            return true;
        }
        else if (total_computer < 12 && getRandomIA() < 80) {
            addCardComputer();
            return true;
        }
        else if (total_computer < 15 && getRandomIA() < 60) {
            addCardComputer();
            return true;
        }
        else if (total_computer < 17 && getRandomIA() < 30) {
            addCardComputer();
            return true;
        }
        else if (total_computer < 19 && getRandomIA() < 10) {
            addCardComputer();
            return true;
        }
        else if (total_computer < 20 && getRandomIA() == 1) {
            addCardComputer();
            return true;
        }
        return false;
    }

    private static void showGame() {
        System.out.println("Your hand:");
        showPlayerHand();
        System.out.println("");
        System.out.println("total: " + total_player);
        System.out.println();
        System.out.println("Computer hand:");
        showComputerHand();
        System.out.println("");
        System.out.println("total: " + total_computer);
    }

    public static void main(String[] args) {

        fillDeck();
        addCardPlayer();
        addCardComputer();
        takeTotalPlayer();
        takeTotalComputer();
        showGame();
        while (!victory) {
            if(!player_pass) {
                System.out.println("What do you want to do?\n1. Take card \n2. Pass");
                player_option = sc.nextInt();
                if (player_option == 1) {
                    addCardPlayer();
                }
                else {
                    player_pass = true;
                }
            }
            if (!computer_pass) {
                computer_option = computerIA();
                if (!computer_option) {
                    computer_pass = true;
                }
            }


            takeTotalPlayer();
            takeTotalComputer();

            showGame();


            if (total_computer >= 21 || total_player >= 21) {
                if (total_computer > 21 && total_player <= 21) {
                    System.out.println("Congratulations player you won");
                    victory = true;
                }
                else if (total_player > 21 && total_computer <= 21) {
                    System.out.println("Sadly player you lose");
                    victory = true;
                }
                else if (total_computer == 21 && total_player == 21) {
                    System.out.println("Wow thats a draw");
                    victory = true;
                }
                else if (total_computer > 21 && total_player > 21) {
                    System.out.println("Both loses");
                }
            }

            if (player_option == 2 && !computer_option && !victory) {
                if (total_player > total_computer) {
                    System.out.println("Congratulations player you won");
                    victory = true;
                }
                else if (total_player < total_computer) {
                    System.out.println("Sadly player you lose");
                    victory = true;
                }
                else if (total_computer == total_player) {
                    System.out.println("Wow thats a draw");
                    victory = true;
                }
            }

            if(player_pass && computer_pass) {
                victory = true;
            }
        }
    }
}