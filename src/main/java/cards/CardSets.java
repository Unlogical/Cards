package cards;

import cards.models.CardSet;
import cards.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by cyou on 12/7/15.
 */
public class CardSets {

    private final ConnectionProvider connectionProvider;

    public CardSets(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public CardSet getCardSet(int cardsetId){
        try {
            PreparedStatement getcardset = connectionProvider.getConnection().prepareStatement("select * from card_sets where id = ?");
            getcardset.setInt(1, cardsetId);
            ResultSet resultSet = getcardset.executeQuery();
            if(resultSet.next()){
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");// TODO: change to name
                String date = resultSet.getString("date");
                boolean privacy = resultSet.getBoolean("privacy");
                String description  = resultSet.getString("description");
                String cover  = resultSet.getString("cover");
                return new CardSet(cardsetId, title, privacy, author, date, description, cover);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
