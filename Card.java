import java.awt.Image;
import java.util.*;

public class Card {
	private String faceName;
	private String suit;
	private String image;
	public Card(String faceName, String suit, String image) {
		setFaceName(faceName);
		this.suit = suit;
		this.image = image;
	}
	
	public String getFaceName() {
		return faceName;
	}
	public void setFaceName(String faceName) {
		List<String> validFaceNames = getValidFaceName();
		
		if(validFaceNames.contains(faceName))
			this.faceName = faceName;
		else
			throw new IllegalArgumentException("Invalid face name");
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		List<String> validSuits = getValidSuits();
		
		if(validSuits.contains(suit))
			this.suit = suit;
		else 
			throw new IllegalArgumentException("Invalid suit name");
	}
	public String getImageName() {
		return image;
	}
	
	public static List<String> getValidFaceName(){
		return Arrays.asList("2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace");
	}
	
	public static List<String> getValidSuits(){
		return Arrays.asList("Clubs", "Diamonds", "Hearts", "Spades");
	}
	public static List<String> getValidImages(){
		return Arrays.asList("2_of_clubs.png", "2_of_diamonds.png", "2_of_hearts.png", "2_of_spades.png", 
				"3_of_clubs.png", "3_of_diamonds.png", "3_of_hearts.png", "3_of_spades.png",
				"4_of_clubs.png","4_of_diamonds.png","4_of_hearts.png","4_of_spades.png",
				"5_of_clubs.png","5_of_diamonds.png","5_of_hearts.png","5_of_spades.png",
				"6_of_clubs.png","6_of_diamonds.png","6_of_hearts.png","6_of_spades.png",
				"7_of_clubs.png","7_of_diamonds.png","7_of_hearts.png","7_of_spades.png",
				"8_of_clubs.png","8_of_diamonds.png","8_of_hearts.png","8_of_spades.png",
				"9_of_clubs.png","9_of_diamonds.png","9_of_hearts.png","9_of_spades.png",
				"10_of_clubs.png","10_of_diamonds.png","10_of_hearts.png","10_of_spades.png",
				"jack_of_clubs.png","jack_of_diamonds.png","jack_of_hearts.png","jack_of_spades.png",
				"king_of_clubs.png","king_of_diamonds.png","king_of_hearts.png","king_of_spades.png",
				"queen_of_clubs.png","queen_of_diamonds.png","queen_of_hearts.png","queen_of_spades.png",
				"ace_of_clubs.png","ace_of_diamonds.png","ace_of_hearts.png","ace_of_spades.png");
	}
	
	@Override
	public String toString() {
		return faceName + " of " + suit;
	}
	
}
