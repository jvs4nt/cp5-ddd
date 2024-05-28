package fiap.ads.models;

public class Card {

    private int id;
    private String name;
    private int elixir;
    private String rarity;
    private int cardLevel;

    public Card() {
    }

    public Card(int id, String name, int elixir, String rarity, int cardLevel) {
        this.id = id;
        this.name = name;
        this.elixir = elixir;
        this.rarity = rarity;
        this.cardLevel = cardLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getElixir() {
        return elixir;
    }

    public void setElixir(int elixir) {
        this.elixir = elixir;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(int cardLevel) {
        this.cardLevel = cardLevel;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", elixir=" + elixir +
                ", rarity='" + rarity + '\'' +
                ", cardLevel=" + cardLevel +
                '}';
    }
}
