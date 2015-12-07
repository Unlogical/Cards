package cards;

import cards.models.Card;
import cards.models.CardSet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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

    public List<CardSet> getUsersCardsets(long userId){
        List<CardSet> cardsets = new LinkedList<>();
        try {
            PreparedStatement query = connectionProvider.getConnection().prepareStatement("select * from card_sets where author = ?");
            ResultSet result = query.executeQuery();
            while(result.next()){
                long id = result.getLong(1);
                String title = result.getString(2);
                boolean privacy = result.getBoolean(3);
                long authorId = result.getLong(4);
                String author = users.idToLogin(authorId);
                String description = result.getString(5);
                String image = result.getString(6);
                CardSet cardset = new CardSet(id, title, privacy, author, "", description, image);
                cardsets.add(cardset);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return cardsets;
    }


}