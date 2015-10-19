import java.util.*;


public class Pokerhand {
	LinkedList<Card> hand;
	int cardspecial;
	int cardspecial2;
	boolean[] handvalue;
	boolean done;
	// 0: straight flush, 1: flush, 2: straight, 3: 4kind, 4: fullhouse, 5: 3kind, 6: 2pair, 7:pair
	
	public Pokerhand(int[] numbers, char[] suits){
		hand = new LinkedList<Card>();
		cardspecial = cardspecial2 = 0;
		done = false;
		handvalue = new boolean[8];
		for (int i = 0; i < 7; i++) {
			handvalue[i] = false;
		}
		
		for (int i = 0; i < 5; i++) {
			Card temp = new Card(numbers[i], suits[i]);
			hand.add(temp);
		}
		
		Collections.sort(hand, new CardComp());
		
		calculateHandValue();
	}
	
	private void calculateHandValue() {
		checkFlush();
		if (!done) {
			checkStraight();			
		}

		if (!done) {
			checkDuplicates();
		}
		
		if (handvalue[1] && handvalue[2] && true) {
			handvalue[0] = true;
		}
		
	}
	
	private void checkFlush() {
		boolean isFlush = true;
		char temp = hand.get(0).Suit;
		
		for (int i = 1; i < 5; i++) {
			isFlush = (temp == hand.get(i).Suit && isFlush);
		}
		
		if (isFlush) {
			handvalue[1] = true;
			cardspecial = hand.get(4).Number;
			done = true;
		}
	}
	
	private void checkStraight() {
		boolean isStraight = true;
		
		int temp = hand.get(0).Number;
		temp++;
		
		for (int i = 1; i < 5; i++) {
			if (temp != hand.get(i).Number) {
				isStraight = false;
			} else {
				temp++;
			}
		}
		
		if (hand.get(4).Number == 13) {
			if (hand.get(0).Number == 1) {
				isStraight = true;
			}
		}
		
		if (isStraight) {
			handvalue[2] = true;
			cardspecial = hand.get(4).Number;
			done = true;
		}
		
	}
	
	private void checkDuplicates() {
		HashMap<Integer, Integer> map = new HashMap();
		
		for (int i = 0; i < hand.size(); i++) {
			int temp = hand.get(i).Number;
			
			if (!map.containsKey(temp)) {
				map.put(temp, 1);
			} else {
				map.put(temp, map.get(temp)+1);
			}
		}

		Set<Integer> handset = map.keySet();
		Iterator it = handset.iterator();
				
		if (map.containsValue(4)) {
			handvalue[3] = true;
			for (int abc : handset) {
				if (map.get(abc) == 4) {
					cardspecial = abc;
				}
			}
		} else if (map.containsValue(3)) {
			if (map.containsValue(2)) {
				handvalue[4] = true;
			} else {
				handvalue[5] = true;
			}
			
			for (int abc : handset) {
				if (map.get(abc) == 3) {
					cardspecial = abc;
				}
				if (map.get(abc) == 2) {
					cardspecial2 = abc;
				}
			}
		} else if (map.containsValue(2)) {
			int paircounter = 0;
			int highcard = 0;
			int othercard = 0;
			for (int abc : handset) {
				if (map.get(abc) == 2) {
					paircounter++;
					if (abc > highcard) {
						othercard = highcard;
						highcard = abc;
					}
				}
			}
			
			if (paircounter >= 2) {
				handvalue[6] = true;
			} else {
				handvalue[7] = true;
			}
			
			cardspecial = highcard;
			cardspecial2 = othercard;
		} else {
			cardspecial = hand.get(4).Number;
		}
	}
}