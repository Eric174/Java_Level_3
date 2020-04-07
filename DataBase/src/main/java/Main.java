import java.io.*;
import java.sql.*;

public class Main {
    private static Connection connection;
    private static Statement statement;

    public static String TABLE = "students";

    public static void main(String[] args) {
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            dropTable(TABLE);
            createTable(TABLE, new String[]{"id INTEGER PRIMARY KEY AUTOINCREMENT","name TEXT","score TEXT"});
            for (int i = 1; i <= 5; i++) {
                insert("students", new String[]{"name", "score"}, new String[]{"BOB" + i, String.valueOf(10 * i)});
            }
            select("SELECT id, name, score from " + TABLE);
            update(TABLE, "score", "1299", "id", "1");
            System.out.println();
            select("SELECT id, name, score from " + TABLE);
            updateFromFile("upd.txt");
            System.out.println();
            select("SELECT id, name, score from " + TABLE);
            delete(TABLE,"id","1");
            System.out.println();
            select("SELECT id, name, score from " + TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
        statement = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable(String table, String[] fields) throws SQLException {
        StringBuilder query = new StringBuilder("CREATE TABLE " + table + " (");
        for (String field : fields) {
            query.append(field).append(",");
        }
        query.replace(query.length()-1, query.length(),")");
        statement.executeUpdate(query.toString());
    }

    public static void dropTable(String table) throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS " + table);
    }

    public static int select(String query) throws SQLException {
        StringBuilder str;
        int count = 0;
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        while (rs.next()) {
            str = new StringBuilder();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                str.append(rs.getString(rsmd.getColumnName(i+1))).append(" ");
            }
            System.out.println(str);
            count++;
        }
        return count;
    }

    public static void insert(String table, String[] fields, String[] values) throws SQLException {
        if (fields.length != values.length) {
            return;
        }

        StringBuilder str = new StringBuilder("INSERT INTO " + table + "(");

        for (String field : fields) {
            str.append(field).append(",");
        }
        str.replace(str.length()-1, str.length(), ")");
        str.append(" VALUES (");
        for (String value : values) {
            str.append("'").append(value).append("',");
        }
        str.replace(str.length()-1, str.length(), ")");
        statement.executeUpdate(str.toString());
    }

    public static void delete(String table) throws SQLException {
        statement.executeUpdate("DELETE FROM " + table);
    }

    public static void delete(String table, String field, String value) throws SQLException {
        statement.executeUpdate("DELETE FROM " + table + " WHERE " + field + " = '" + value + "'");
    }

    public static void update(String table, String field, String value, String whereField, String whereValue) throws SQLException {
        String str = "UPDATE " + table + " SET " + field + "='" + value + "' " +
                "WHERE " + whereField + "='" + whereValue + "'";
        //System.out.println(str);
        statement.executeUpdate(str);
    }

    public static void updateFromFile(String path) throws IOException, SQLException {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String readLine;

        while ((readLine = reader.readLine()) != null) {
            String[] array = readLine.split(" {2}");
            if (array.length == 3) {
                update(TABLE,"score", array[2], "id", array[0]);
            }
        }
    }
}
