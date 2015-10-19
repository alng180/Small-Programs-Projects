import java.util.Comparator;

public class CardComp implements Comparator<Card> {
	public int compare(Card carda, Card cardb) {
		return carda.Number - cardb.Number;
	}
}