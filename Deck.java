
/**
 * Creates a deck object using the cards.
 * 
 * @author Tyler Jiang
 * @version 5/29/2015
 */
public class Deck
{
    //Instance variables
    private final int DECK_SIZE = 52;               //number of cards in a deck
    private Card[] deck = new Card[DECK_SIZE];      //creates a deck array that stores cards
    private int currentCard = 0, positionD = -1;    //index: currentCard is for making the deck, dealStart is for dealing
    private int numCardsInDeck;                     //stores the number of cards in the deck

    /**
     * Constructor for objects of class Deck
     */
    public Deck()
    {
        //Intialize the instance variables
        currentCard = 0;    //reset currentCard
        for (int face = Card.ACE; face <= Card.KING; face++)    //makes a card for each number
        {
            for (int suit = Card.CLUBS; suit <= Card.SPADES; suit++)    //makes a card for each suit
            {
                deck[currentCard] = new Card(face, suit);
                currentCard++;
            }
        }
    }

    //Useless stuff
    //     /**
    //      * Constructor for objects of class Deck
    //      */
    //     public Deck(Card[] pile)
    //     {
    //         //Intialize the instance variables
    //         for (int i = 0; i < pile.length; i++)    //makes a card for each number
    //         {
    //             deck[i] = pile[i];
    //         }
    //         shuffle();
    //     }

    /**
     * Displays the entire deck of cards.
     */
    public void display()
    {
        //Rest currentCard
        currentCard = 0;
        //Prints each card
        for (int face = Card.ACE; face <= Card.KING; face++)
        {
            for (int suit = Card.CLUBS; suit <= Card.SPADES; suit++)
            {
                //Formats the printing based on tabs
                if (deck[currentCard].toString().length() < 16)
                    System.out.print(deck[currentCard] + "\t\t");
                else 
                    System.out.print(deck[currentCard] + "\t");
                currentCard++;
            }
            System.out.println();
        }
    }

    /**
     * Returns the next card in the deck.
     */
    public Card deal()
    {
        if (positionD == 51)
            positionD = -1;

        //Moves the position of the card being dealt down one position
        positionD++;

        //Subrtract 1 because dealStart starts at -1 (due to arrays being less than one)
        numCardsInDeck = 52 - positionD - 1;

        //         if (numCardsInDeck == 0)
        //             System.out.println("There are no cards left in the deck.");

        //Return the card at the position
        return deck[positionD];
    }

    /**
     * Returns the number of cards remaining in the deck.
     */
    public int getNumCardsInDeck()
    {
        //Ensures something is returned if deal() hasn't been called yet
        numCardsInDeck = 52 - positionD - 1;

        if (numCardsInDeck == 0)
            System.out.println("There are no cards left in the deck.");

        return numCardsInDeck;
    }

    /**
     * Returns true if the deck still has cards left.
     */
    public boolean hasMoreCards()
    {
        //True if there are still cards left
        //         if (getNumCardsInDeck() > 0)
        if (getNumCardsInDeck() > 0)
            return true;
        //If there are no cards, return false
        else
            return false;
    }

    /**
     * Shuffle the deck and reset the number of cards.
     */
    public void shuffle()
    {
        //Shuffles each card
        for (int i = 0; i < 52; i++)
        {
            //Randomly selects one of the cards in the deck
            int randIndex = (int)(Math.random() * deck.length);

            //Swaps the chosen card with the first card
            Card temp = deck[randIndex];
            deck[randIndex] = deck[i];
            deck[i] = temp;
        }
    }
}
