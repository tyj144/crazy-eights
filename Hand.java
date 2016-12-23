
/**
 * Write a description of class Hand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hand
{
    //Instance variables
    private final int HAND_MAX = 20;
    private Card[] hand = new Card[HAND_MAX];
    private int lastCard;
    private Deck deck = new Deck();

    /**
     * Constructor for objects of class Hand
     */
    public Hand()
    {
        // initialise instance 
        lastCard = -1;
    }

    /**
     * 
     */
    public Card play(int position)
    {
        //Store the chosen card in a card
        Card cardPlayed = hand[position];

        //Fill the hole of the played card
        hand[position] = hand[lastCard];
        lastCard--;

        return cardPlayed;
    }

    /**
     * Discard all cards from the hand, making the hand empty.
     */
    public void clear()
    {
        //Clear all the cards
        lastCard = -1;
    }

    /**
     * Add the card c to the hand.  c should be non-null.
     * @throws NullPointerException if c is null.
     */
    public void addCard(Card c)
    {
        //Increment the index and adds the card
        lastCard++;
        hand[lastCard] = c;
    }

    /**
     * If the specified card is in the hand, it is removed.
     */
    public void removeCard(Card c)
    {
        boolean hasCard = false;    //true if the user has the card
        int i;  //index used

        //Go through the hand and see if it has the card c
        for (i = 0; i < lastCard + 1; i++)
        {
            if (hand[i].getFaceValue() == c.getFaceValue() && hand[i].getSuit() == c.getSuit())
            {
                hasCard = true;
                break;
            }
        }

        //If they have the card, remove it
        if (hasCard)
        {
            //Fill hole after removing the card (overwrite the card)
            hand[i] = hand[lastCard];
            lastCard--;
            sortByValue();
        }
    }

    /**
     * Remove the card in the specified position from the
     * hand.  Cards are numbered counting from zero.
     * @throws IllegalArgumentException if the specified 
     *    position does not exist in the hand.
     */
    public void removeCard(int position)
    {
        //See if the position is a valid position
        if (position < 0 || position > lastCard)
            System.out.println("That position is not in your hand.");
        else
        {
            hand[position] = hand[lastCard];
            lastCard--;
            sortByValue();
        }
    }

    /**
     * Return the number of cards in the hand.
     */
    public int getCardCount()
    {
        return lastCard + 1;
    }

    /**
     * Get the card from the hand in given position, where 
     * positions are numbered starting from 0.
     * @throws IllegalArgumentException if the specified 
     *    position does not exist in the hand.
     */
    public Card getCard(int position)
    {
        return hand[position];
    }

    /**
     * Sorts the cards in the hand so that cards of the same 
     * suit are grouped together, and within a suit the cards 
     * are sorted by value.
     */
    public void sortBySuit()
    {
        //Sort by suit
        for (int i = 0; i < lastCard; i++)
        {
            for (int j = i + 1; j < lastCard + 1; j++)
            {
                //If the card's suit is greater, swap
                if (hand[i].getSuit() > hand[j].getSuit())
                {
                    Card temp = hand[i];
                    hand[i] = hand[j];
                    hand[j] = temp;
                }
            }
        }

        //Sort by value
        for (int i2 = 0; i2 < lastCard; i2++)
        {
            for (int j2 = i2 + 1; j2 < lastCard + 1; j2++)
            {
                if (hand[i2].getSuit() == hand[j2].getSuit() && hand[i2].getFaceValue() > hand[j2].getFaceValue())
                {
                    Card temp = hand[i2];
                    hand[i2] = hand[j2];
                    hand[j2] = temp;
                }
                //Already sorted by suit so don't need to keep going within the suit
                else if (hand[i2].getSuit() != hand[j2].getSuit())
                    break;
            }
        }
    }

    /**
     * Sorts the cards in the hand so that cards are sorted into
     * order of increasing value.  Cards with the same value 
     * are sorted by suit. Note that aces are considered
     * to have the lowest value.
     */
    public void sortByValue()
    {
        //Sort by value
        for (int i = 0; i < lastCard; i++)
        {
            for (int j = i + 1; j < lastCard + 1; j++)
            {
                if (hand[i].getFaceValue() > hand[j].getFaceValue())
                {
                    Card temp = hand[i];
                    hand[i] = hand[j];
                    hand[j] = temp;
                }
            }
        }

        //Sort by suit
        for (int i2 = 0; i2 < lastCard; i2++)
        {
            for (int j2 = i2 + 1; j2 < lastCard + 1; j2++)
            {
                if (hand[i2].getFaceValue() == hand[j2].getFaceValue() && hand[i2].getSuit() > hand[j2].getSuit())
                {
                    Card temp = hand[i2];
                    hand[i2] = hand[j2];
                    hand[j2] = temp;
                }
                //Already sorted by value so don't need to keep going within the value
                else if (hand[i2].getFaceValue() != hand[j2].getFaceValue())
                    break;
            }
        }
    }

    /**
     * Prints the hand that the user has.
     */
    public void display()
    {
        //         //Make sure it's sorted by suit
        //         sortBySuit();

        //Variables
        int i;  //index for the hand

        //Print the hand
        System.out.print("\t");
        for (i = 0; i < lastCard + 1; i++)
        {
            //Format the tabs evenly
            String card = (i + 1) + " - " + hand[i];

            if (card.length() < 16)
                System.out.print(card + "\t\t");
            else 
                System.out.print(card + "\t");

            //Returns the line for every 3 cards
            if ((i + 1) % 3 == 0 && (i + 1) != lastCard + 1)
                System.out.print("\n\t");
        }

        //Returns the line if the line hasn't been returned earlier (line has to be returned due to use of print instead of println)
        if ((i + 1) != lastCard + 1)
            System.out.println();
    }
}
