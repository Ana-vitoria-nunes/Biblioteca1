package org.example.sevice;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.connection.Connect.fazerConexao;

public class BookService {
    private Statement statement;
    public BookService() {
        try {
            statement = fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void inserirLivro(String name, String author, String releaseDate, String code) {
        String sql = "INSERT INTO livros (name, author, release_date, code) VALUES ('" +
                name + "', '" + author + "', '" + releaseDate + "', '" + code + "')";
        try {
            statement.executeUpdate(sql);
            System.out.println("Livro '" + name + "' foi adicionado com sucesso no banco!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultaTodosLivros() {
        String sql = "SELECT * FROM livros";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        " | Nome: " + resultSet.getString("name") +
                        " | Autor: " + resultSet.getString("author") +
                        " | Data de Lançamento: " + resultSet.getString("release_date") +
                        " | Código: " + resultSet.getString("code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultaLivroPorId(int id) {
        String sql = "SELECT * FROM livros WHERE id = " + id;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        " | Nome: " + resultSet.getString("name") +
                        " | Autor: " + resultSet.getString("author") +
                        " | Data de Lançamento: " + resultSet.getString("release_date") +
                        " | Código: " + resultSet.getString("code"));
            } else {
                System.out.println("Livro com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void alterarLivro(int id, String name, String author, String releaseDate, String code) {
        String sql = "UPDATE livros SET name = '" + name + "', author = '" + author +
                "', release_date = '" + releaseDate + "', code = '" + code + "' WHERE id = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Livro com ID " + id + " foi alterado com sucesso.");
            } else {
                System.out.println("Livro com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletarLivro(int id) {
        String sql = "DELETE FROM livros WHERE id = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Livro com ID " + id + " foi deletado com sucesso.");
            } else {
                System.out.println("Livro com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}