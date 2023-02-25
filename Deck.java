import java.util.ArrayList;
import java.util.List;

public class Deck {
	
	private ArrayList<Card> deck;
	
	public Deck(ArrayList<Card> deck) {
		this.deck = deck;
	}
	
	public Deck() {
		List<String> suits = Card.getValidSuits();
		
		List<String> faceNames = Card.getValidFaceName();
		
		List<String> images = Card.getValidImages();
		
		int i = 0;
		deck = new ArrayList<>();
		for(String faceName:faceNames) {
			for(String suit:suits) {
				deck.add(new Card(faceName, suit, images.get(i)));
				i++;
			}
		}
		
	}
	
	public void deckShuffle() {
		
	}
	
	public int getSize() {
		return deck.size();
	}
	
	public Card dealCard() {
		if(deck.size()  == 0) {
			return null;
		}
		Card randomCard = deck.get((int)(Math.random()*(deck.size()) + 0));
		deck.remove(randomCard);
		
		return randomCard;
	}
	
	
	public String toString() {
		for(int a = 0; a < deck.size(); a++)
			System.out.println(deck.get(a));
		return "";
	}

}
