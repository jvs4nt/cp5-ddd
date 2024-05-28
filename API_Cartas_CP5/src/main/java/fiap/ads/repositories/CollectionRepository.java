package fiap.ads.repositories;

import java.text.MessageFormat;
import java.util.List;
import fiap.ads.models.Collection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;



import static fiap.ads.Main.LOGGER;

public class CollectionRepository {

    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    public static final String USER = "rm554328";
    public static final String PASSWORD = "fiap24";

    public CollectionRepository(){
    }

    public List<Collection> getCollections() {
        var collectionList = new ArrayList<Collection>();

        try {
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stmt = conn.prepareStatement("SELECT * FROM COLLECTION");
            var rs = stmt.executeQuery(); {
                while (rs.next()) {
                    var collection = new Collection();
                    collection.setId(rs.getInt("ID"));
                    collection.setName(rs.getString("NAME"));
                    collection.setRegion(rs.getString("REGION"));
                    collectionList.add(collection);
                }
                LOGGER.info("Collections returned successful");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Error when searching collections: {0}", e.getMessage()));
        }
        return collectionList;
    }

    public Collection getCollection(int id) {
        Collection collection = null;

        try (
                var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
                var stmt = conn.prepareStatement("SELECT * FROM COLLECTION WHERE id = ?");){
            stmt.setInt(1,id);
            var rs = stmt.executeQuery();
            if (rs.next()){
                collection = new Collection();
                collection.setId(rs.getInt("ID"));
                collection.setName(rs.getString("NAME"));
                collection.setRegion(rs.getString("REGION"));
            }
            LOGGER.info("Collection returned successful");
        }
        catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Error when searching the collection", e.getMessage()));
        }
        return collection;
    }

    public int createCollection(Collection collection) {
        try{
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stmt = conn.prepareStatement("INSERT INTO COLLECTION VALUES (NAME, REGION) VALUES (?, ?)");
            stmt.setString(1, collection.getName());
            stmt.setString(2, collection.getRegion());
            return stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Error when creating the collection", e.getMessage()));
        }
        return 0;
    }

    public int updateCollection(int id, Collection collection){
        try {
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stmt = conn.prepareStatement(
                    "UPDATE COLLECTION SET NAME = ?, REGION = ? WHERE ID = ?");
            {
                stmt.setString(1, collection.getName());
                stmt.setString(2, collection.getRegion());
                return stmt.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Error when updating the collection", e.getMessage()));
        }
        return 0;
    }

    public int deleteConnection (int id) {
        try {
            var conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            var stmt = conn.prepareStatement(
                    "DELETE COLLECTION WHERE ID = ?");
            {
                stmt.setInt(1, id);
                return stmt.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Error when deleting the collection", e.getMessage()));
        }
        return 0;
    }

}
