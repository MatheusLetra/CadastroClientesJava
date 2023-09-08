
package br.com.matheusletra.cadastroclientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteRepository implements IPadraoRepository {
    
    Connection con = Conexao.getConexao();
    ArrayList<Cliente> clientes = new ArrayList<>();
    String sql = "";
    public static String status = "";


    @Override
    public void inserir(Object o) {
        Cliente cliente = (Cliente) o;
        try {
            sql = "INSERT INTO clientes (nome, endereco, email) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, cliente.getNome());
                ps.setString(2, cliente.getEndereco());
                ps.setString(3, cliente.getEmail());
                
                ps.execute();
                ps.close();
                
                status = "Registro incluido com sucesso!";
            }
            
        } catch (SQLException e) {
            status = "Nao foi possivel incluir o registro, erro:" 
                   + e.getMessage()
                   + "SQL EXECUTADO: "
                   + sql;
        }
    }

    @Override
    public void alterar(Object o) {
        Cliente cliente = (Cliente) o;
        try {
            sql = "UPDATE clientes set nome = ? , endereco = ? , email = ? WHERE codigo = ? ";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, cliente.getNome());
                ps.setString(2, cliente.getEndereco());
                ps.setString(3, cliente.getEmail());
                ps.setInt(4, cliente.getCodigo());
                
                ps.execute();
                ps.close();
                
                status = "Registro alterado com sucesso!";
            }
            
        } catch (SQLException e) {
            status = "Nao foi possivel alterar o registro, erro:" 
                   + e.getMessage()
                   + "SQL EXECUTADO: "
                   + sql;
        }
    }

    @Override
    public void remover(Object o) {
        Cliente cliente = (Cliente) o;
        try {
            sql = "DELETE FROM clientes WHERE codigo = ? ";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, cliente.getCodigo());
                                
                ps.execute();
                ps.close();
                
                status = "Registro excluido com sucesso!";
            }
            
        } catch (SQLException e) {
            status = "Nao foi possivel excluir o registro, erro:" 
                   + e.getMessage()
                   + "SQL EXECUTADO: "
                   + sql;
        }
    }

    @Override
    public ArrayList getAll() {
        clientes.clear();
        try {
            sql = "SELECT * FROM clientes";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigo(resultSet.getInt("codigo"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setEndereco(resultSet.getString("endereco"));
                cliente.setEmail(resultSet.getString("email"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            status = e.getMessage();
        }
        
        return clientes;
    }

    @Override
    public Object getById(int id) {
        Cliente cliente = null;
        try {
            sql = "select * from clientes where codigo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            status = e.getMessage();
        }
        return cliente;
    }
    
}
