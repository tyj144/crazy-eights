import java.util.Scanner;

/**
 * Plays a game of Crazy Eights against the computer. (using Hand object)
 * 
 * @author Tyler Jiang
 * @version 
 * @date 6/8/2015
 */
public class CrazyEights
{
    //Variables and Objects
    static Deck deck = new Deck();  //Create and instantiate the deck being played with

    //Create an array of cards for each hand
    static final int HAND_MAX = 52;
    static Hand cpuHand = new Hand();    //computer's hand
    static Hand userHand = new Hand();   //user's hand

    //Top card
    static Card topCard;

    //Choices
    static int menuChoice;     //Allows user to choose to start the game or 
    static int cardChoice;     //Allows choice for which card to play
    static int suitChoice;     //Allows choice for a suit change (if card is 8)

    //Set to true when there is a winner
    static boolean winnerU = false, winnerCPU = false, tie = false;
    static boolean hasCardU, hasCardCPU;

    //Create and instantiate Scanner
    static Scanner scan = new Scanner(System.in);

    //-----End of Variables-----

    public static void main(String[] args)
    {
        //Welcome menu
        do
        {
            do
            {
                System.out.print("\033[H\033[2J");
System.out.flush();
                System.out.println("------------------------------------- ♥ -------------------------------------");
                System.out.println("--------------------------- ♣ - Crazy Eights - ♠ ---------------------------");
                System.out.println("------------------------------------- ♦ -------------------------------------\n");
                System.out.println("                                   --------");
                System.out.println("-----------------------------------  Menu  -----------------------------------");
                System.out.println("                                   --------");
                System.out.println("                    Please choose an option from the menu.                    \n");
                System.out.println("                                 [1] Play                                   ");
                System.out.println("                                 [2] Rules                                   ");
                System.out.println("                                 [3] About                                   ");
                System.out.println("                                 [4] Exit");
                menuChoice = scan.nextInt();
                scan.nextLine();    //eat return character
            }
            while ((menuChoice < 1) || (menuChoice > 4));

            switch (menuChoice)
            {
                case 1:
                {
                    break;
                }
                case 2:
                {
                    rules();
                    break;
                }
                case 3:
                {
                    about();
                    break;
                }
                case 4:
                {
                    System.exit(0);
                    break;
                }
            }
        }
        while (!(menuChoice == 1));

        //         menu(menuChoice);
        //Shuffle the deck before the game starts
        deck.shuffle();

        //Deal out 7 cards to the starting hands
        for (int i = 0; i < 7; i++)
        {
            //Deal a card to each hand
            userHand.addCard(deck.deal());
            cpuHand.addCard(deck.deal());
        }

        //Flip over the first card
        topCard = deck.deal();

        //Runs the game while there is no winner
        do
        {
            userHand.sortBySuit();
            clear();

            //Play the user's turn
            userTurn();

            //User wins if he has no cards left
            if (userHand.getCardCount() == 0)
            {
                winnerU = true;
                System.out.print("\033[H\033[2J");
System.out.flush();
                break;
            }
            else    
            {
                //Computer's turn
                System.out.print("\033[H\033[2J");
System.out.flush();
                cpuTurn();

                //Computer wins if it has no cards left
                if (cpuHand.getCardCount() == 0)
                {
                    winnerCPU = true;
                    System.out.print("\033[H\033[2J");
System.out.flush();
                    break;
                }
            }

            //If both don't have cards and there are no more cards to draw
            if (!hasCardCPU && !hasCardU && !deck.hasMoreCards())
            {
                System.out.print("\033[H\033[2J");
System.out.flush();
                System.out.println("There are no more cards left.\n");

                if (cpuHand.getCardCount() == 1)
                    System.out.println("The computer finished with " + cpuHand.getCardCount() + " card left.");
                else
                    System.out.println("The computer finished with " + cpuHand.getCardCount() + " cards left.");

                if (userHand.getCardCount() == 1)
                    System.out.println("You finished with " + userHand.getCardCount() + " card left.\n");
                else
                    System.out.println("You finished with " + userHand.getCardCount() + " cards left.\n");

                //User won if the computer has more cards
                if (cpuHand.getCardCount() > userHand.getCardCount())
                    winnerU = true;
                else if (userHand.getCardCount() > cpuHand.getCardCount())
                    winnerCPU = true;
                else
                    tie = true;
            }
        }
        while (!winnerU && !winnerCPU && !tie);

        //Prints a message depending on who wins
        if (winnerU)
            System.out.println("Congratulations! You won!");
        else if (winnerCPU)
            System.out.println("The computer won.");
        else
            System.out.println("You tied the computer.");
    }

    /**
     * Prints the rules.
     */
    public static void rules()
    {
        System.out.print("\033[H\033[2J");
System.out.flush();
        //         System.out.println("------------------------------------------------------------------------------");
        System.out.println("------------------------------------- ♥ -------------------------------------");
        System.out.println("--------------------------- ♣ - Crazy Eights - ♠ ---------------------------");
        System.out.println("------------------------------------- ♦ -------------------------------------\n");
        System.out.println("                                   -------");
        System.out.println("----------------------------------  Rules  ----------------------------------");
        System.out.println("                                   -------");
        System.out.println("       Crazy Eights is a card game where the goal is to get rid of all       ");
        System.out.println("  of your of your cards by playing a number that either matches the number   ");
        System.out.println("                           or suit of the top card.                           \n");
        System.out.println("    1. The player and the computer are each dealt seven cards to begin.");
        System.out.println("    2. A top card is then flipped, and the players then have to play  ");
        System.out.println("       another card that matches the top card in either rank (number) ");
        System.out.println("       or suit.");
        System.out.println("    3. Eights are \"wild\" and can be played on any cards. The player ");
        System.out.println("       that plays the eight has to choose a suit to change the top card ");
        System.out.println("       to. Once an eight is played, the next player has to play either ");
        System.out.println("       another eight or a card of the suit chosen.");
        System.out.println("    4. If a player has no cards they can play, they have to draw a card ");
        System.out.println("       from the stock pile. They have to draw cards until either they ");
        System.out.println("       draw a playable card, or they drew 5 cards without getting a ");
        System.out.println("       playable card.");
        System.out.println("    5. The player that gets rid of all their cards first wins.");
        System.out.println("    6. If there are no more cards to draw, the game is played until no ");
        System.out.println("       more cards can be played. Then, the person with the least amount  ");
        System.out.println("       of cards wins.");
        scan.nextLine();
    }

    /**
     * Prints the about section.
     */
    public static void about()
    {
        System.out.print("\033[H\033[2J");
System.out.flush();
        System.out.println("------------------------------------- ♥ -------------------------------------");
        System.out.println("--------------------------- ♣ - Crazy Eights - ♠ ---------------------------");
        System.out.println("------------------------------------- ♦ -------------------------------------\n");
        System.out.println("                                   -------");
        System.out.println("----------------------------------  About  ----------------------------------");
        System.out.println("                                   -------");
        System.out.println("                                 Tyler Jiang");
        System.out.println("                                  Block III");
        System.out.println("                                June 5th, 2015");
        scan.nextLine();
    }

    /**
     * Runs the user's turn and returns how many cards they has left.
     */
    public static void userTurn()
    {
        //Reset hasCardU
        hasCardU = false;

        //Checks to see if the user has a card they can play
        for (int i = 0; i < userHand.getCardCount(); i++)
        {
            if (userHand.getCard(i).getFaceName().equals(topCard.getFaceName()) || userHand.getCard(i).getFaceName().equals("8") || userHand.getCard(i).getSuitName().equals(topCard.getSuitName()))
            {
                hasCardU = true;
                break;
            }
        }

        //Forces user to play a card if they have one
        if (hasCardU)
        {
            //Have the user pick a card in his hand
            System.out.println("Please select the number of the card you want to play.");
            cardChoice = (scan.nextInt() - 1);
            scan.nextLine();    //eat return character

            //If the card does not match the top card in suit or number
            while (cardChoice > userHand.getCardCount() - 1 || cardChoice < 0)
            {
                clear();

                System.out.println("You do not have that card in your hand.");
                //Have the user pick a card in his hand
                System.out.println("Please select the number of the card you want to play.");
                cardChoice = (scan.nextInt() - 1);
                scan.nextLine();    //eat return character
            }

            //Keeps looping if user has a card that can be played, but does not play it
            while (hasCardU && !userHand.getCard(cardChoice).getFaceName().equals(topCard.getFaceName()) && !userHand.getCard(cardChoice).getFaceName().equals("8") && !userHand.getCard(cardChoice).getSuitName().equals(topCard.getSuitName()))
            {
                clear();

                //Print if both suit and face don't match
                System.out.println("That card does not match neither the face, nor the suit of the top card.");

                //Have the user pick a card in his hand
                System.out.println("Please select the number of the card you want to play.");
                cardChoice = (scan.nextInt() - 1);
                scan.nextLine();    //eat return character

                //If the card does not match the top card in suit or number
                while (cardChoice > userHand.getCardCount() - 1 || cardChoice < 0)
                {
                    clear();

                    System.out.println("You do not have that card in your hand.");
                    //Have the user pick a card in his hand
                    System.out.println("Please select the number of the card you want to play.");
                    cardChoice = (scan.nextInt() - 1);
                    scan.nextLine();    //eat return character
                }
            }

            //Print the card the user played

            System.out.println("You played: ");
            System.out.println("\t" + userHand.getCard(cardChoice));

            scan.nextLine();

            //Prompt menu to choose suit if the user plays an 8
            if (userHand.getCard(cardChoice).getFaceName().equals("8"))
                eight(0);

            //Otherwise play the top card
            else
                topCard = userHand.getCard(cardChoice);

            userHand.removeCard(userHand.getCard(cardChoice));
        }

        //Keep dealing cards to the player if they don't have a card
        if (!hasCardU && deck.hasMoreCards())
        {
            for (int i = 0; i < 5; i++)
            {
                System.out.println("You do not have a card you can play.");

                //Deal a card
                Card newCard = deck.deal();
                userHand.addCard(newCard);

                System.out.println("You were dealt a: ");
                System.out.println("\t" + newCard);

                //If the user gets a card that's playable, play it
                if (newCard.getFaceName().equals(topCard.getFaceName()) || newCard.getFaceName().equals("8") || newCard.getSuitName().equals(topCard.getSuitName()))
                {
                    scan.nextLine();
                    hasCardU = true;

                    //Print the top card
                    System.out.println("You played: ");
                    System.out.println("\t" + newCard);

                    scan.nextLine();

                    if (newCard.getFaceName().equals("8"))
                        eight(0);
                    else

                        topCard = newCard;

                    userHand.removeCard(newCard);
                    break;
                }
                else 
                {
                    scan.nextLine();

                    if (i == 4)
                    {
                        System.out.println("You have drawn five cards without drawing a playable card.");
                        System.out.println("It is now the computer's turn.");
                        scan.nextLine();
                        System.out.print("\033[H\033[2J");
System.out.flush();
                    }
                }
            }
        }
        else if (!hasCardU)
        {
            System.out.println("You do not have a card you can play.");
            System.out.println("There are no more cards in the deck.");
            scan.nextLine();
            System.out.print("\033[H\033[2J");
System.out.flush();
        }
    }

    /**
     * Runs the computer's turn and returns how many cards it has left.
     */
    public static void cpuTurn()
    {
        //Reset hasCardCPU
        hasCardCPU = false;

        //Print the top card
        System.out.println("Top Card: ");
        if (topCard.getFaceName().equals("8"))
            System.out.println("\t8 (Suit: " + topCard.getSuitName() + ")");
        else
            System.out.println("\t" + topCard);

        //         //Debug (print the computer's hand)
        //                 cpuHand.display();

        //Checks to see if the computer has a card
        for (int i = 0; i < cpuHand.getCardCount() - 1; i++)
        {
            //Play the first card that's elligible in the hand
            if (cpuHand.getCard(i).getFaceName().equals(topCard.getFaceName()) || cpuHand.getCard(i).getFaceName().equals("8") || cpuHand.getCard(i).getSuitName().equals(topCard.getSuitName()))
            {
                hasCardCPU = true;

                //Card is played
                topCard = cpuHand.getCard(i);

                System.out.println("The computer played: ");
                System.out.println("\t" + topCard);

                if (cpuHand.getCard(i).getFaceName().equals("8"))
                    eight(1);

                cpuHand.removeCard(i);
                break;
            }
        }

        //If there's no card that the computer can play, keep taking cards until it can play one
        if (!hasCardCPU && deck.hasMoreCards())
        {
            for (int i = 0; i < 5; i++)
            {
                //Deal a card
                Card newCard = deck.deal();
                cpuHand.addCard(newCard);
                System.out.print("The computer was dealt a card.\n");
                scan.nextLine();

                //Computer was dealt an elligible card, play it
                if (newCard.getFaceName().equals(topCard.getFaceName()) || newCard.getFaceName().equals("8")|| newCard.getSuitName().equals(topCard.getSuitName()))
                {
                    hasCardCPU = true;

                    //Play the card
                    topCard = newCard;

                    System.out.println("The computer played: ");
                    System.out.println("\t" + topCard);

                    if (newCard.getFaceName().equals("8"))
                        eight(1);

                    cpuHand.removeCard(newCard);
                    break;
                }
                else if (i == 4)
                {
                    System.out.println("After five draws, the computer did not draw a playable card.");
                    System.out.println("It is now your turn.");
                    scan.nextLine();
                    System.out.print("\033[H\033[2J");
System.out.flush();
                }
            }
        }

        //Check to see if the computer won
        if (userHand.getCardCount() == 0)
            winnerCPU = true;
        else if (!hasCardCPU)
        {
            System.out.println("There are no more cards left.");
            System.out.println("The computer has no more cards to play.");
            scan.nextLine();
            System.out.print("\033[H\033[2J");
System.out.flush();
        }
        else 
        {
            if (userHand.getCardCount() == 1)
                System.out.println("The computer has " + userHand.getCardCount() + " card remaining.");
            else
                System.out.println("The computer has " + userHand.getCardCount() + " cards remaining.");
            scan.nextLine();
            System.out.print("\033[H\033[2J");
System.out.flush();
        }
    }

    /**
     * Allows a change of suit if an ace is played.
     * 
     * @param   userOrCpu    0 if user, 1 if cpu
     */
    public static void eight(int userOrCPU)
    {
        //Variables
        int choice;     //choice of suit change

        //Let user choose
        if (userOrCPU == 0)
        {
            do
            {
                clear();
                System.out.println("Please select the number for the suit you'd like to change it to.");
                System.out.println("\t1. Clubs (♣)");
                System.out.println("\t2. Diamonds (♦)");
                System.out.println("\t3. Hearts (♥)");
                System.out.println("\t4. Spades (♠)");
                choice = scan.nextInt();
                scan.nextLine();    //eat return character
            }
            while (!(choice > 1) && !(choice < 4));
        }
        //Computer randomly chooses a suit
        else
        {
            choice = (int)(Math.random() * 4);
        }

        //The top card is now that new suit
        topCard = new Card(8, choice);
        System.out.println("The suit was set to " + topCard.getSuitName() + ".");
    }

    /**
     * Clears the screen and re-prints the necessary information.
     */
    public static void clear()
    {
        //Clears the screen
        System.out.print("\033[H\033[2J");
System.out.flush();

        //Prints the top card
        System.out.println("Top Card: ");
        if (topCard.getFaceName().equals("8"))
            System.out.println("\t8 (Suit: " + topCard.getSuitName() + ")");
        else
            System.out.println("\t" + topCard);

        //Prints the user's hand
        System.out.println("Your hand:");
        userHand.display();
    }
}
