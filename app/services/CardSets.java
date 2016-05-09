package cards;

import cards.models.CardSet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by cyou on 12/7/15.
 */
public class CardSets {

    private final ConnectionProvider connectionProvider;
    private final Users users;

    public CardSets(ConnectionProvider connectionProvider, Users users) {
        this.connectionProvider = connectionProvider;
        this.users = users;
    }

    public CardSet getCardSet(long cardsetId){
        try {
            PreparedStatement getcardset = connectionProvider.getConnection().prepareStatement("select * from card_sets where id = ?");
            getcardset.setLong(1, cardsetId);
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

    public CardSet createCardSet(long authorId, boolean privacy, String title, String description){
        try {
            PreparedStatement createcardset = connectionProvider.getConnection().prepareStatement("insert into card_sets (title, privacy, author, description) values (?, ?, ?, ?)");
            createcardset.setString(1, title);
            createcardset.setBoolean(2, privacy);
            createcardset.setLong(3, authorId);
            createcardset.setString(4, description);
            createcardset.execute();

            ResultSet generatedKeys = createcardset.getGeneratedKeys();
            if(generatedKeys.next()){
                long cardsetId = generatedKeys.getLong(1);
                String authorLogin = users.idToLogin(authorId);
                return new CardSet(cardsetId, title, privacy, authorLogin, "now", description, "");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}