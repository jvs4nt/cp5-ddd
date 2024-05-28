package fiap.ads.repositories;

import fiap.ads.models.Card;
import org.apache.log4j.Logger;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static fiap.ads.Main.LOGGER;

public class CardRepository {
    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    public static final String USER = "rm554328";
    public static final String PASSWORD = "fiap24";

    public CardRepository(){
    }

    public List<Card> getCards() {
        var cardList = new ArrayList<Card>();

        try {
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stmt = conn.prepareStatement("SELECT * FROM CARD");
            var rs = stmt.executeQuery(); {
                while (rs.next()) {
                    var card = new Card();
                    card.setId(rs.getInt("ID"));
                    card.setName(rs.getString("NAME"));
                    card.setElixir(rs.getInt("ELIXIR"));
                    card.setRarity(rs.getString("RARITY"));
                    card.setCardLevel(rs.getInt("CARDLEVEL"));
                    cardList.add(card);
                }
                LOGGER.info("Cards returned successful");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Error when searching cards: {0}", e.getMessage()));

        }
        return cardList;
    }

    public Card getCard(int id) {
        Card card = null;

        try (
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stmt = conn.prepareStatement("SELECT * FROM CARD WHERE id = ?");){
            stmt.setInt(1,id);
            var rs = stmt.executeQuery();
            if (rs.next()){
                card = new Card();
                card.setId(rs.getInt("ID"));
                card.setName(rs.getString("NAME"));
                card.setElixir(rs.getInt("ELIXIR"));
                card.setRarity(rs.getString("RARITY"));
                card.setCardLevel(rs.getInt("LEVEL"));
            }
            LOGGER.info("Card returned successful");
        }
        catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Error when searching the card", e.getMessage()));
        }
        return card;
    }

    public int createCard(Card card) {
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stmt = conn.prepareStatement("INSERT INTO CARD VALUES (ID, NAME, ELIXIR, RARITY, LEVEL) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, card.getId());
            stmt.setString(2, card.getName());
            stmt.setInt(3, card.getElixir());
            stmt.setString(4, card.getRarity());
            stmt.setInt(5, card.getCardLevel());
            return stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Error when creating the card", e.getMessage()));
        }
        return 0;
    }

    public int updateCard(int id, Card card){
        try {
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stmt = conn.prepareStatement(
                    "UPDATE CARD SET NAME = ?, ELIXIR = ?, RARITY = ?, LEVEL = ? WHERE ID = ?");
            {
                stmt.setString(1, card.getName());
                stmt.setInt(2, card.getElixir());
                stmt.setString(3, card.getRarity());
                stmt.setInt(4, card.getCardLevel());
                return stmt.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Error when updating the card", e.getMessage()));
        }
        return 0;
    }

    public int deleteCard (int id) {
        try {
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stmt = conn.prepareStatement(
                    "DELETE CARD WHERE ID = ?");
            {
                stmt.setInt(1, id);
                return stmt.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Error when deleting the card", e.getMessage()));
        }
        return 0;
    }

}

