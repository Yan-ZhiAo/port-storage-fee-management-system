import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.*;

public class HashPasswords {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sun?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        String user = "root";
        String password = "2522250483wmy";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT id, password FROM account WHERE password NOT LIKE ?"; // 确保表名与实际情况一致
            PreparedStatement selectStmt = conn.prepareStatement(sql);
            selectStmt.setString(1, "$2a$"); // BCrypt散列通常以此字符开头
            ResultSet rs = selectStmt.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("id");
                String plainPassword = rs.getString("password");
                String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

                String updateSql = "UPDATE account SET password = ? WHERE id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, hashedPassword);
                updateStmt.setInt(2, userId);
                updateStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}