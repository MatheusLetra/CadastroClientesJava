package br.com.matheusletra.cadastroclientes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Connection conexao;
    static String status;
    
    public static Connection getConexao() {
        if (conexao == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadastroclientes", "root", "");
                status = "Conexão realizada com sucesso!";
            } catch (ClassNotFoundException | SQLException e) {
                status = "Erro ao Estabelecer a Conexão";
            }
        }
        return conexao;
    }

    public static void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
            }
        }
    }
}

