import java.util.*;

public class PokerComparer {

	public static void main(String[] args) {
		int[] p1n = {3, 3, 3, 4, 5};
		char[] p1s = {'S', 'C', 'D', 'H', 'H'};
		Pokerhand player1 = new Pokerhand(p1n, p1s);

		int[] p2n = {1, 10, 11, 12, 13};
		char[] p2s = {'D', 'C', 'S', 'H','H'};
		Pokerhand player2 = new Pokerhand(p2n, p2s);
		
		compareHands(player1, player2);
	}

	public static void compareHands(Pokerhand p1, Pokerhand p2) {
		for (int i = 0; i < 8; i++) {
			boolean p1score = p1.handvalue[i];
			boolean p2score = p2.handvalue[i];
			
			if (p1score != p2score) {
				if (p1score) {
					System.out.println("Player 1 wins");
					break;
				} else {
					System.out.println("Player 2 wins");
					break;
				}
			} else if (p1score && p2score && true) {
					if (p1.cardspecial > p2.cardspecial) {
						System.out.println("Player 1 Wins");
						break;
					} else if (p2.cardspecial > p1.cardspecial) {
						System.out.println("Player 2 Wins");
						break;
					} else {
						if (i == 4 || i == 6) {
							if (p1.cardspecial2 > p2.cardspecial2) {
								System.out.println("Player 1 Wins");
								break;
							} else if (p2.cardspecial2 > p1.cardspecial2) {
								System.out.println("Player 2 Wins");
								break;
							}
						}
						System.out.println("Hands Tie");
						break;
					}
			}
				
			if (i == 8) {
				System.out.println("Hands Tie");
			}
		}
		System.out.println("Ruh Roh!");
	}
}
