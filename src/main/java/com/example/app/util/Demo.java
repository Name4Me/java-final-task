package com.example.app.util;

import com.example.app.model.TestResult;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        String query = "SELECT users.email, q.name, a.score  FROM users \n" +
                "JOIN assignments a ON users.id = a.userId \n" +
                "JOIN quizzes q ON a.quizId = q.id\n" +
                "WHERE a.status = 2 AND a.startedAt > CURDATE() - INTERVAL 7 DAY\n" +
                "ORDER BY a.finishedAt";

        List<TestResult> items = new ArrayList<>();
        try(Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/app?useUnicode=true&serverTimezone=UTC","testuser","testpass");
            Statement stmt=con.createStatement()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try(ResultSet rs=stmt.executeQuery(query)) {
                while(rs.next()) items.add(new TestResult(rs.getString("email"), rs.getString("name"), rs.getInt("score")));
            }
            new Pdf().createPdf("results/simple_table.pdf", items);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
