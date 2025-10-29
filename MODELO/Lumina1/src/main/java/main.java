import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/lumina_bd?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "Piudz2012";

        // desde JDBC 4 no es estrictamente necesario Class.forName, pero puedes ponerlo
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // no debería pasar si la dependencia está bien
            e.printStackTrace();
        }

        String sql = "SELECT id_usuario FROM usuarios";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_usuario");
                System.out.println(id + " - ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
