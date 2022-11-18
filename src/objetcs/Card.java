package objetcs;

public class Card {
    private final int number; // 2-10, 11 = Jack, 12 = Queen, 13 = King, 14 = As
    private final int type; //1 Hearts, 2 Diamonds, 3 Spades, 4 Clubs

    public Card(int number, int type) {
        this.number = number;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public int getType() {
        return type;
    }
}
