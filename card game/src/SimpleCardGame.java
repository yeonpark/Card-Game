import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * This class demonstrates documentation comments.
 *
 * @author Park Yeon
 * @version 1.0
 */
public class SimpleCardGame implements ActionListener {
	JFrame frame;

	int player_bet;
	int player_balance = 100;

	int count = -1;
	int count1 = -1;
	int count2 = -1;
	int count3 = -1;
	int count_replace = 0;

	static ArrayList<String> Card_Deck = new ArrayList<String>();
	static ArrayList<String> Special_Card = new ArrayList<String>();

	String dealer_card1, dealer_card2, dealer_card3;
	String player_card1, player_card2, player_card3;

	JPanel MainPanel = new JPanel();
	JPanel DealerPanel = new JPanel();
	JPanel PlayerPanel = new JPanel();
	JPanel RpCardBtnPanel = new JPanel();
	JPanel ButtonPanel = new JPanel();
	JPanel InfoPanel = new JPanel();

	JLabel label_Image1 = new JLabel();
	JLabel label_Image2 = new JLabel();
	JLabel label_Image3 = new JLabel();
	JLabel label_Image4 = new JLabel();
	JLabel label_Image5 = new JLabel();
	JLabel label_Image6 = new JLabel();
	JLabel label_bet = new JLabel("Bet: $");
	JLabel label_info = new JLabel("Please place your bet!");
	JLabel label_money = new JLabel("Amount of money you have: $" + player_balance);

	JButton btn_rpcard1 = new JButton("Replace Card 1");
	JButton btn_rpcard2 = new JButton("Replace Card 2");
	JButton btn_rpcard3 = new JButton("Replace Card 3");
	JButton btn_start = new JButton("Start");
	JButton btn_result = new JButton("Result");

	JTextField txt_inputbet = new JTextField(10);

	ImageIcon Image1 = new ImageIcon("card_back.gif");
	ImageIcon Image2 = new ImageIcon("card_back.gif");
	ImageIcon Image3 = new ImageIcon("card_back.gif");
	ImageIcon Image4 = new ImageIcon("card_back.gif");
	ImageIcon Image5 = new ImageIcon("card_back.gif");
	ImageIcon Image6 = new ImageIcon("card_back.gif");

	JMenuBar MenuBar = new JMenuBar();
	JMenu Control = new JMenu("Control");
	JMenuItem Exit = new JMenuItem("Exit");
	JMenu Help = new JMenu("Help");
	JMenuItem Instruction = new JMenuItem("Instruction");
	
	/**
	* This method allows the game to run.
	*
	* @param args Unused.
	*/
	public static void main(String[] args) {
		SimpleCardGame gui = new SimpleCardGame();
		Shuffle();
		gui.go();
	}
	
	/**
	*This method displays the graphic interface on the screen with arranged layouts.
	*
	*@param args Unused.
	*/
	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(MainPanel);
		frame.setTitle("A Simple Card Game");
		frame.setSize(400, 700);
		frame.setVisible(true);
		frame.setJMenuBar(MenuBar);

		MenuBar.add(Control);
		MenuBar.add(Help);
		Control.add(Exit);
		Help.add(Instruction);
		Exit.addActionListener(this);
		Instruction.addActionListener(this);

		MainPanel.setLayout(new GridLayout(5, 1));
		MainPanel.add(DealerPanel);
		MainPanel.add(PlayerPanel);
		MainPanel.add(RpCardBtnPanel);
		MainPanel.add(ButtonPanel);
		MainPanel.add(InfoPanel);

		DealerPanel.add(label_Image1);
		DealerPanel.add(label_Image2);
		DealerPanel.add(label_Image3);

		PlayerPanel.add(label_Image4);
		PlayerPanel.add(label_Image5);
		PlayerPanel.add(label_Image6);

		RpCardBtnPanel.add(btn_rpcard1);
		RpCardBtnPanel.add(btn_rpcard2);
		RpCardBtnPanel.add(btn_rpcard3);

		ButtonPanel.add(label_bet);
		ButtonPanel.add(txt_inputbet);
		ButtonPanel.add(btn_start);
		ButtonPanel.add(btn_result);

		InfoPanel.add(label_info);
		InfoPanel.add(label_money);

		DealerPanel.setBackground(Color.green);
		PlayerPanel.setBackground(Color.green);
		RpCardBtnPanel.setBackground(Color.green);

		label_Image1.setIcon(Image1);
		label_Image2.setIcon(Image2);
		label_Image3.setIcon(Image3);
		label_Image4.setIcon(Image4);
		label_Image5.setIcon(Image5);
		label_Image6.setIcon(Image6);

		btn_rpcard1.addActionListener(this);
		btn_rpcard2.addActionListener(this);
		btn_rpcard3.addActionListener(this);
		btn_start.addActionListener(this);
		btn_result.addActionListener(this);

	}
	
	/**
	*This method creates a deck with 52 distinct cards.
	*
	*@param args Unused.
	*/
	public static void Shuffle() {
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 13; j++) {
				String card = Integer.toString(i) + Integer.toString(j);
				Card_Deck.add(card);
				if (j > 10) {
					Special_Card.add(card);
				}
			}
		}
	}

	/**
	*This method allows the player to make a new bet.
	*
	*@param args Unused.
	*/
	public void NewGame() {
		Card_Deck.clear();
		Special_Card.clear();
		Shuffle();
		label_Image1.setIcon(Image1);
		label_Image2.setIcon(Image2);
		label_Image3.setIcon(Image3);
		label_Image4.setIcon(Image4);
		label_Image5.setIcon(Image5);
		label_Image6.setIcon(Image6);

	}

	/**
	*This method deals three random card to both the player and dealer.
	*
	*@param args Unused.
	*@return returns the number code of the card
	*/
	public static String Deal() {
		Random random = new Random();
		String card = Card_Deck.get(random.nextInt(Card_Deck.size()));
		Card_Deck.remove(card);
		return card;
	}

	/**
	*This method responses to the player's action and give corresponding results.
	*
	*@param args Unused.
	*/
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == Exit) {
			System.exit(10);
		}

		if (event.getSource() == Instruction) {
			JFrame DialogFrame = new JFrame("Message");
			JOptionPane.showMessageDialog(DialogFrame,
					"Rules to determine who has better cards:\r\n" + "J, Q, K are regarded as special cards.\r\n"
							+ "Rule 1: The one with more special cards wins.\r\n"
							+ "Rule 2: If both have the same number of special cards, add the face values of the other "
							+ "card(s) and take the remainder after dividing the sum by 10. The one with a bigger "
							+ "remainder wins. (Note: Ace = 1).  \r\n"
							+ "Rule 3: The dealer wins if both rule 1 and rule 2 cannot distinguish the winner.");
		}
		if (count == -1) {
			String player_bet_string = txt_inputbet.getText();
			if (!player_bet_string.equals("")) {
				if (player_bet_string.contains(".") || player_bet_string.contains("-")) {
					JFrame Dialog = new JFrame("Message");
					JOptionPane.showMessageDialog(Dialog, "WARNING: The bet you place must be a positive integer!");
				}
				if (Integer.parseInt(player_bet_string) > player_balance) {
					JFrame Dialog2 = new JFrame("Message");
					JOptionPane.showMessageDialog(Dialog2, "WARNING: You only have $" + player_balance + "!");
				} else {
					player_bet = Integer.parseInt(player_bet_string);
					count = 0;
				}
			}
		}
		if (event.getSource() == btn_start && count == 0) {

			player_card1 = Deal();
			System.out.println(player_card1);
			label_Image4.setIcon(new ImageIcon("card_" + player_card1 + ".gif"));
			player_card2 = Deal();
			System.out.println(player_card2);
			label_Image5.setIcon(new ImageIcon("card_" + player_card2 + ".gif"));
			player_card3 = Deal();
			System.out.println(player_card3);
			label_Image6.setIcon(new ImageIcon("card_" + player_card3 + ".gif"));

			label_info.setText("Your current bet is: $" + player_bet);
			label_money.setText("Amount of money you have: $" + player_balance);
			count++;
		}

		if (event.getSource() == btn_rpcard1 && count1 == -1 && (count_replace == 0 || count_replace == 1)) {
			player_card1 = Deal();
			System.out.println(player_card1);
			label_Image4.setIcon(new ImageIcon("card_" + player_card1 + ".gif"));
			count_replace++;
			count1++;
		}

		if (event.getSource() == btn_rpcard2 && count2 == -1 && (count_replace == 0 || count_replace == 1)) {
			player_card2 = Deal();
			System.out.println(player_card2);
			label_Image5.setIcon(new ImageIcon("card_" + player_card2 + ".gif"));
			count_replace++;
			count2++;
		}

		if (event.getSource() == btn_rpcard3 && count3 == -1 && (count_replace == 0 || count_replace == 1)) {
			player_card3 = Deal();
			System.out.println(player_card3);
			label_Image6.setIcon(new ImageIcon("card_" + player_card3 + ".gif"));
			count_replace++;
			count3++;
		}

		if (event.getSource() == btn_result && count >= 1) {
			int dealer_special = 0;
			int player_special = 0;
			int dealer_sum = 0;
			int player_sum = 0;
			dealer_card1 = Deal();
			System.out.println(dealer_card1);
			label_Image1.setIcon(new ImageIcon("card_" + dealer_card1 + ".gif"));
			dealer_card2 = Deal();
			System.out.println(dealer_card2);
			label_Image2.setIcon(new ImageIcon("card_" + dealer_card2 + ".gif"));
			dealer_card3 = Deal();
			System.out.println(dealer_card3);
			label_Image3.setIcon(new ImageIcon("card_" + dealer_card3 + ".gif"));
			if (Special_Card.contains(dealer_card1)) {
				dealer_special += 1;
			}

			if (Special_Card.contains(dealer_card2)) {
				dealer_special += 1;
			}
			if (Special_Card.contains(dealer_card3)) {
				dealer_special += 1;
			}
			if (Special_Card.contains(player_card1)) {
				player_special += 1;
			}
			if (Special_Card.contains(player_card2)) {
				player_special += 1;
			}
			if (Special_Card.contains(player_card3)) {
				player_special += 1;
			}
			System.out.println(dealer_special);
			System.out.println(player_special);
			if (dealer_special > player_special) {
				JFrame DialogFrame = new JFrame("Message");
				JOptionPane.showMessageDialog(DialogFrame, "Sorry! The Dealer wins this round!");
				player_balance = player_balance - player_bet;
				label_info.setText("Please place your bet!");

				label_money.setText("Amount of money you have: $" + player_balance);

				count = -1;
				count1 = -1;
				count2 = -1;
				count3 = -1;
				count_replace = 0;
				NewGame();
			}
			if (dealer_special < player_special) {
				JFrame DialogFrame = new JFrame("Message");
				JOptionPane.showMessageDialog(DialogFrame, "Congrauations! You win this round!");
				player_balance = player_balance + player_bet;
				label_info.setText("Please place your bet!");
				label_money.setText("Amount of money you have: $" + player_balance);
				count = -1;
				count1 = -1;
				count2 = -1;
				count3 = -1;
				count_replace = 0;
				NewGame();
			}
			if (dealer_special == player_special) {
				if (!Special_Card.contains(dealer_card1)) {
					dealer_sum += Integer.parseInt(dealer_card1.substring(1, dealer_card1.length()));
				}
				System.out.println(dealer_card1.substring(1, dealer_card1.length()));
				if (!Special_Card.contains(dealer_card2)) {
					dealer_sum += Integer.parseInt(dealer_card2.substring(1, dealer_card2.length()));
				}
				System.out.println(dealer_card2.substring(1, dealer_card2.length()));
				if (!Special_Card.contains(dealer_card3)) {
					dealer_sum += Integer.parseInt(dealer_card3.substring(1, dealer_card3.length()));
				}
				System.out.println(dealer_card3.substring(1, dealer_card3.length()));
				if (!Special_Card.contains(player_card1)) {
					player_sum += Integer.parseInt(player_card1.substring(1, player_card1.length()));
				}
				System.out.println(player_card1.substring(1, player_card1.length()));
				if (!Special_Card.contains(player_card2)) {
					player_sum += Integer.parseInt(player_card2.substring(1, player_card2.length()));
				}
				System.out.println(player_card2.substring(1, player_card2.length()));
				if (!Special_Card.contains(player_card3)) {
					player_sum += Integer.parseInt(player_card3.substring(1, player_card3.length()));
				}
				System.out.println(player_card3.substring(1, player_card3.length()));
				System.out.println(dealer_sum);
				System.out.println(player_sum);

				int dealer_remainder = dealer_sum % 10;
				int player_remainder = player_sum % 10;

				if (dealer_remainder >= player_remainder) {
					JFrame DialogFrame = new JFrame("Message");
					JOptionPane.showMessageDialog(DialogFrame, "Sorry! The Dealer wins this round!");
					player_balance = player_balance - player_bet;
					label_info.setText("Please place your bet!");

					label_money.setText("Amount of money you have: $" + player_balance);

					count = -1;
					count1 = -1;
					count2 = -1;
					count3 = -1;
					count_replace = 0;
					NewGame();
				}
				if (dealer_remainder < player_remainder) {
					JFrame DialogFrame = new JFrame("Message");
					JOptionPane.showMessageDialog(DialogFrame, "Congrauations! You win this round!");
					player_balance = player_balance + player_bet;
					label_info.setText("Please place your bet!");
					label_money.setText("Amount of money you have: $" + player_balance);
					count = -1;
					count1 = -1;
					count2 = -1;
					count3 = -1;
					count_replace = 0;
					NewGame();
				}

			}
			if (player_balance <= 0) {
				label_info.setText("You have no more money! Please start a new game!");
				label_money.setText("");
				JFrame DialogFrame = new JFrame("Message");
				JOptionPane.showMessageDialog(DialogFrame,
						"Game Over! \r\n" + "You have no more money! \r\n" + "Please start a new game!");
				count = -1;
				count1 = -1;
				count2 = -1;
				count3 = -1;
				count_replace = 0;
				NewGame();
			}

		}

	}
}
