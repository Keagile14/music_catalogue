import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class musicDatabase {

    private Connection conn;

    public musicDatabase(String dbFile) throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
    }


    public int addSong(String title,String artist, String genre) throws SQLException{

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, id);
            
        }
    }
}