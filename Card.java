
/**
 * Creates a card w/ a face value and suit.
 * 
 * @author Tyler Jiang
 * @version 5/28/2015
 */
public class Card
{
    //Instance variables

    //Face value of card
    private int faceValue;
    public static final int ACE = 1, JACK = 11, QUEEN = 12, KING = 13;

    //Suit of the card
    private int suit;
    public static final int CLUBS = 1, DIAMONDS = 2, HEARTS = 3, SPADES = 4;

    private String [] faceArray = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    //     private String[] faceArray = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

    private String faceName, suitName;

    /**
     * Default constructor for Card
     */
    public Card()
    {
        //Generate the name of the card
        faceValue = (int) (1 + Math.random() * 13);
        setFaceName();
        suit = (int) (1 + Math.random() * 4);
        setSuitName();
    }

    /**
     * Constructor for objects of class Card
     */
    public Card(int n, int s)
    {
        //Initialise instance variables
        faceValue = n;
        setFaceName();
        suit = s;
        setSuitName();
    }

    /**
     * Sets the face name of the card.
     */
    private void setFaceName()
    {
        faceName = faceArray[faceValue - 1];
    }

    /**
     * Sets the suit name of the card.
     */
    private void setSuitName()
    {
        //Using ASCII
        if (suit == CLUBS)
            suitName = "♣";
        else if (suit == DIAMONDS)
            suitName = "♦";
        else if (suit == HEARTS)
            suitName = "♥";
        else
            suitName = "♠";

        //Using text
        //         if (suit == CLUBS)
        //             suitName = "Clubs";
        //         else if (suit == DIAMONDS)
        //             suitName = "Diamonds";
        //         else if (suit == HEARTS)
        //             suitName = "Hearts";
        //         else
        //             suitName = "Spades";
    }

    /**
     * Gets the face value of the card.
     */
    public int getFaceValue()
    {
        return faceValue;
    }

    /**
     * Gets the suit int.
     */
    public int getSuit()
    {
        return suit;
    }

    /**
     * Gets the face name of the card.
     */
    public String getFaceName()
    {
        return faceName;
    }

    /**
     * Gets the suit name.
     */
    public String getSuitName()
    {
        return suitName;
    }

    /**
     * Prints the card.
     */
    public String toString()
    {
        return (faceName +" of " +suitName);
    }

    /**
     * Compares one card to another one.
     */
    public boolean isHigherThan(Card card2)
    {
        if (getFaceValue() > card2.getFaceValue())
            return true;
        else
            return false;
    }
}
