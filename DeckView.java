import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.util.concurrent.TimeUnit;
import java.awt.SystemColor;
import java.awt.Dimension;
import javax.swing.border.EtchedBorder;

//@SuppressWarnings("serial")
public class DeckView extends JFrame {

	
	private JPanel contentPane;
	
	
	//number displays
	private JLabel cardDisplay;
	private JLabel playerNumberLabel;
	
	
	//dealer and player card displays
	private JLabel dealerCard1;
	private JLabel dealerCard2;
	private JLabel dealerCard3;
	private JLabel dealerCard4;
	
	private JLabel playerCard1;
	private JLabel playerCard2;
	private JLabel playerCard3;
	private JLabel playerCard4;
	private JLabel playerCard5;
	private JLabel playerCard6;
	private JLabel playerCard7;
	private JLabel playerCard8;
	
	private boolean play = false;
	private int i = 0;
	private int d = 0;
	
	//dealer and player card total numbers
	private int playerNumber;
	private int dealerNumber;

	/**
	 * Launch the application.
	 */
	
	//arrays to hold numbers for player and dealer
	private ArrayList<JLabel> dealerList = new ArrayList<>();
	private ArrayList<Integer> dealerNumberList = new ArrayList<>();
	
	private ArrayList<JLabel> playerList = new ArrayList<>();
	private ArrayList<Integer> playerNumberList = new ArrayList<>();
	
	private ArrayList<Card> playerCards = new ArrayList<>();
	private ArrayList<Card> dealerCards = new ArrayList<>();
	
	private JLabel cardCount;
	private JLabel dealerNumberLabel;
	
	Deck deck = new Deck();


	private JButton playAgainBtn;
	private JButton standBtn; 
	private JButton hitBtn;
	private JLabel dealerCard5;
	private JLabel dealerCard6;
	private JLabel dealerCard7;
	
	public void endGame() {
		playAgainBtn.setVisible(true);
		standBtn.setVisible(false);
		hitBtn.setVisible(false);
		dealerNumberLabel.setVisible(true);
		
		if(playerNumber > 21) {
			cardDisplay.setText("Busted!");
			while(dealerNumber < 17) {
				dealerTurn();
				if(deck.getSize() == 0) {
					break;
				}
			}
			Image card = new ImageIcon(this.getClass().getResource(dealerCards.get(1).getImageName())).getImage();
			dealerList.get(1).setIcon(new ImageIcon(card));
		}
		else if(dealerNumber == playerNumber) {
			cardDisplay.setText("Draw");
		}
		else if (playerNumber == 21) {
			cardDisplay.setText("21!");
			while(dealerNumber < 17) {
				dealerTurn();
				if(deck.getSize() == 0) {
					break;
				}
			}
			Image card = new ImageIcon(this.getClass().getResource(dealerCards.get(1).getImageName())).getImage();
			dealerList.get(1).setIcon(new ImageIcon(card));
		}
		else if(playerNumber > dealerNumber) {
			cardDisplay.setText("You win");
			while(dealerNumber < 17) {
				dealerTurn();
				if(deck.getSize() == 0) {
					break;
				}
			}
			Image card = new ImageIcon(this.getClass().getResource(dealerCards.get(1).getImageName())).getImage();
			dealerList.get(1).setIcon(new ImageIcon(card));
		}
		else if(dealerNumber > 21) {
			cardDisplay.setText("Dealer busted.");
		}
		else if(dealerNumber > playerNumber) {
			cardDisplay.setText("Dealer wins");
		}
		
		
	}
	
	public void stand() {
		while(dealerNumber < 17) {
			dealerTurn();
			if(deck.getSize() == 0) {
				break;
			}
		}
		Image card = new ImageIcon(this.getClass().getResource(dealerCards.get(1).getImageName())).getImage();
		dealerList.get(1).setIcon(new ImageIcon(card));
		endGame();
	}
	
	public void dealerTurn() {
		
		if(deck.getSize() == 0) {
			cardDisplay.setText("Out of cards :3");
			System.out.println("no more cards");
			return;
		}
		
		if(d == 1) {
			Image card = new ImageIcon(this.getClass().getResource("card_back.png")).getImage();
			dealerList.get(d).setIcon(new ImageIcon(card));
			dealerList.get(d).setVisible(true);
			Card randomCard = deck.dealCard();
			
			dealerCards.add(randomCard);
			addCard(dealerNumberList, randomCard, dealerCards);
			
			cardCount.setText(Integer.toString(deck.getSize()));
			dealerNumber = tallyCards(dealerCards, dealerNumberList);
			dealerNumberLabel.setText("" + dealerNumber);
			
			d++;
			return;
		}
		
		//setting variables
		Card randomCard = deck.dealCard();
		Image card = new ImageIcon(this.getClass().getResource(randomCard.getImageName())).getImage();
		
		//setting image and adding to lists
		dealerList.get(d).setIcon(new ImageIcon(card));
		dealerList.get(d).setVisible(true);
		dealerCards.add(randomCard);
		addCard(dealerNumberList, randomCard, dealerCards);
		
		//setting text
		cardCount.setText(Integer.toString(deck.getSize()));
		dealerNumber = tallyCards(dealerCards, dealerNumberList);
		dealerNumberLabel.setText("" + dealerNumber);
		
		System.out.println("Dealer card is : " + dealerCards.get(d).toString());
		d++;
	}
	
	public void playerTurn() {
		
		if(deck.getSize() == 0) {
			cardDisplay.setText("Out of cards :3");
			System.out.println("no more cards");
			return;
		}
		
		//setting variables
		Card randomCard = deck.dealCard();
		Image card = new ImageIcon(this.getClass().getResource(randomCard.getImageName())).getImage();
		
		//setting image and adding to lists
		playerList.get(i).setIcon(new ImageIcon(card));
		playerList.get(i).setVisible(true);
		playerCards.add(randomCard);
		addCard(playerNumberList, randomCard, playerCards);
		
		//setting text
		cardCount.setText(Integer.toString(deck.getSize()));
		playerNumber = tallyCards(playerCards, playerNumberList);
		playerNumberLabel.setText("" + playerNumber);
		
		//cardDisplay.setText(playerCards.get(i).toString());
		i++;
		System.out.println(playerNumberList);
		
		
	}
	
	public void addCard(ArrayList<Integer> list, Card card, ArrayList<Card> cardList) {
		
		
		String face = card.getFaceName();
		if(face == "Queen" || face == "King" || face == "Jack") {
			list.add(10);
		}
		else if(face == "Ace") {
			list.add(11);
		}
		else {
			list.add(Integer.parseInt(face));
		}
		tallyCards(cardList, list);

	}
	
	public int tallyCards(ArrayList<Card> cardList,ArrayList <Integer> list) {
		
		int sum = 0;
		
		//finds the sum
		for(int j = 0; j < list.size(); j++) {
			sum += list.get(j);
		}
		
		while(list.contains(11) && sum > 21) {
			list.set(list.indexOf(11), 1);
		}
		sum = 0;
		for(int j = 0; j < list.size(); j++) {
			sum += list.get(j);
		}
		
		
		return sum;
		
	}
	
	public void blackjack() {
		
		//burnt card
		deck.dealCard();
		
		//first round of dealing cards
		playerTurn();
		dealerTurn();
		
		//second round of dealing cards
		playerTurn();
		dealerTurn();
		
		if(playerNumber == 21) {
			endGame();
		}
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeckView frame = new DeckView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DeckView() {
		setTitle("Deck of Cards");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1900, 1000);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(11, 77, 53));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cardDisplay = new JLabel("");
		cardDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		cardDisplay.setFont(new Font("Algerian", Font.PLAIN, 60));
		cardDisplay.setForeground(new Color(255, 255, 255));
		cardDisplay.setBounds(724, 400, 450, 100);
		contentPane.add(cardDisplay);
		
		
		//HUD
		cardCount = new JLabel(Integer.toString((deck.getSize())));
		cardCount.setForeground(new Color(255, 255, 255));
		cardCount.setFont(new Font("Algerian", Font.PLAIN, 60));
		cardCount.setHorizontalAlignment(SwingConstants.CENTER);
		cardCount.setBounds(1546, 413, 100, 75);
		contentPane.add(cardCount);
		
		//buttons
		JButton standBtn = new JButton("Stand");
		this.standBtn = standBtn;
		standBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dealerNumberLabel.setVisible(true);
				stand();
			}
		});
		standBtn.setFont(new Font("Algerian", Font.PLAIN, 38));
		standBtn.setBounds(1225, 400, 200, 75);
		contentPane.add(standBtn);
		standBtn.setVisible(false);
		
		JButton hitBtn = new JButton("Hit");
        hitBtn.setPreferredSize(new Dimension(61, 23));
        hitBtn.setMinimumSize(new Dimension(61, 23));
        hitBtn.setMaximumSize(new Dimension(61, 23));
        this.hitBtn = hitBtn;
        hitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerTurn();
                if(playerNumber > 21) {
                    cardDisplay.setText("BUSTED!!!!");
                    dealerNumberLabel.setVisible(true);
                    endGame();
                    return;
                }
                else if(playerNumber == 21) {
                    endGame();
                    dealerNumberLabel.setVisible(true);
                    return;
                }
                                
            }
        });
		
        
		JButton playAgainBtn = new JButton("Play");
		this.playAgainBtn = playAgainBtn;
		playAgainBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(play == false) {
					play = true;
					playAgainBtn.setText("Play Again");
					hitBtn.setVisible(true);
					standBtn.setVisible(true);
					playAgainBtn.setVisible(false);
					dealerNumberLabel.setVisible(false);
					blackjack();
				}
				else {
					playAgainBtn.setVisible(false);
					hitBtn.setVisible(true);
					standBtn.setVisible(true);
					Image back = new ImageIcon(this.getClass().getResource("cardGame.jpg")).getImage();
					playerCard1.setVisible(false);
					playerCard2.setVisible(false);
					playerCard3.setVisible(false);
					playerCard4.setVisible(false);
					playerCard5.setVisible(false);
					playerCard6.setVisible(false);
					playerCard7.setVisible(false);
					playerCard8.setVisible(false);
					
					dealerCard1.setVisible(false);
					dealerCard2.setVisible(false);
					dealerCard3.setVisible(false);
					dealerCard4.setVisible(false);
					dealerCard5.setVisible(false);
					dealerCard6.setVisible(false);
					dealerCard7.setVisible(false);
					
					dealerNumberLabel.setVisible(false);
					
					playerNumber = 0;
					playerNumberList.clear();
					playerCards.clear();
					dealerNumber = 0;
					dealerNumberList.clear();
					dealerCards.clear();
					i = 0;
					d = 0;
					playerNumberLabel.setText("" + playerNumber);
					dealerNumberLabel.setText("" + dealerNumber);
					
					blackjack();
				}
				
			}
		});
		
		playAgainBtn.setFont(new Font("Algerian", Font.PLAIN, 38));
		playAgainBtn.setBounds(820, 875, 260, 75);
		contentPane.add(playAgainBtn);
		
		hitBtn.setFont(new Font("Algerian", Font.PLAIN, 38));
        hitBtn.setBounds(475, 400, 200, 75);
        contentPane.add(hitBtn);
        hitBtn.setVisible(false);
		
		
		//players cards
		playerCard1 = new JLabel("");
		Image img2 = new ImageIcon(this.getClass().getResource("card_back.png")).getImage();
		playerCard1.setIcon(new ImageIcon(img2));
		playerCard1.setBounds(300, 600, 165, 240);
		contentPane.add(playerCard1);
		playerList.add(playerCard1);
		playerCard1.setVisible(false);
		
		playerCard2 = new JLabel("");
		playerCard2.setBounds(500, 600, 165, 240);
		contentPane.add(playerCard2);
		playerList.add(playerCard2);
		
		playerCard3 = new JLabel("");
		playerCard3.setBounds(700, 600, 165, 240);
		contentPane.add(playerCard3);
		playerList.add(playerCard3);
		
		playerCard4 = new JLabel("");
		playerCard4.setBounds(900, 600, 165, 240);
		contentPane.add(playerCard4);
		playerList.add(playerCard4);
		
		playerCard5 = new JLabel("");
		playerCard5.setBounds(1100, 600, 165, 240);
		contentPane.add(playerCard5);
		playerList.add(playerCard5);
		
		playerCard6 = new JLabel("");
		playerCard6.setBounds(1300, 600, 165, 240);
		contentPane.add(playerCard6);
		playerList.add(playerCard6);
		
		playerCard7 = new JLabel("");
		playerCard7.setBounds(1500, 600, 165, 240);
		contentPane.add(playerCard7);
		playerList.add(playerCard7);
		
		playerCard8 = new JLabel("");
		playerCard8.setBounds(1700, 601, 165, 240);
		contentPane.add(playerCard8);
		playerList.add(playerCard8);
		
		
		
		
		//dealer cards
		dealerCard1 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("card_back.png")).getImage();
		dealerCard1.setIcon(new ImageIcon(img));
		dealerCard1.setBounds(300, 85, 165, 240);
		contentPane.add(dealerCard1);
		dealerList.add(dealerCard1);
		dealerCard1.setVisible(false);
				
				
		dealerCard2 = new JLabel("");
		dealerCard2.setBounds(500, 85, 165, 240);
		contentPane.add(dealerCard2);
		dealerList.add(dealerCard2);
				
		dealerCard3 = new JLabel("");
		dealerCard3.setBounds(700, 85, 165, 240);
		contentPane.add(dealerCard3);
		dealerList.add(dealerCard3);
		
		dealerCard4 = new JLabel("");
		dealerCard4.setBounds(900, 85, 165, 240);
		contentPane.add(dealerCard4);
		dealerList.add(dealerCard4);
		
		dealerCard5 = new JLabel("");
		dealerCard5.setBounds(1100, 85, 165, 240);
		contentPane.add(dealerCard5);
		dealerList.add(dealerCard5);
		
		dealerCard6 = new JLabel("");
		dealerCard6.setBounds(1300, 85, 165, 240);
		contentPane.add(dealerCard6);
		dealerList.add(dealerCard6);
		
		dealerCard7 = new JLabel("");
		dealerCard7.setBounds(1500, 85, 165, 240);
		contentPane.add(dealerCard7);
		dealerList.add(dealerCard7);
		
		
		//player total number
		playerNumberLabel = new JLabel("");
		playerNumberLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		playerNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerNumberLabel.setForeground(new Color(255, 255, 255));
		playerNumberLabel.setFont(new Font("Algerian", Font.PLAIN, 60));
		playerNumberLabel.setBounds(910, 511, 100, 75);
		contentPane.add(playerNumberLabel);
		
		dealerNumberLabel = new JLabel("0");
		dealerNumberLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		dealerNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dealerNumberLabel.setForeground(new Color(255, 255, 255));
		dealerNumberLabel.setFont(new Font("Algerian", Font.PLAIN, 60));
		dealerNumberLabel.setBounds(910, 329, 100, 75);
		contentPane.add(dealerNumberLabel);
		dealerNumberLabel.setVisible(false);
		
		JLabel background = new JLabel("");
		img = new ImageIcon(this.getClass().getResource("table.png")).getImage();
		background.setIcon(new ImageIcon(img));
		background.setBounds(0, 0, 1920, 1080);
		contentPane.add(background);
		
		
		
	}
}
