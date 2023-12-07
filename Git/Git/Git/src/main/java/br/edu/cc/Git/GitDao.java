package br.edu.cc.Git;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GitDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //Dao para obter
    public Git obter(int id_user) throws Exception{
        String sqlSelect = "select ID_USER, USERNAME, NAME FROM GIT WHERE ID_USER = ?";
        try(Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sqlSelect);) {
            ps.setInt(1, id_user);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Git g = new Git();
                    g.setId_user(rs.getInt("id_user"));
                    g.setUsername(rs.getString("username"));
                    g.setName(rs.getString("name"));
                    return g;
                }
            }
        throw new Exception("Id_user não encontrado na tabela");
        }
    }

    //Dao para listar
    public List<Git> listar() throws Exception {
        String sqlSelect = "select ID_USER, USERNAME, NAME from GIT";
        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sqlSelect);) {
            List<Git> gits = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Git g = new Git();
                    g.setId_user(rs.getInt("id_user"));
                    g.setUsername(rs.getString("username"));
                    g.setName(rs.getString("name"));
                    gits.add(g);
                }
            }
            return gits;
        }
    }

    //Dao pra inserir
    public Git incluir(Git g) throws Exception {
        String sqlInsert = "INSERT INTO GIT (ID_USER, USERNAME, NAME)"
                + "VALUES (?, ?, ?)";
        try (Connection con = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sqlInsert);) {
            ps.setInt(1, g.getId_user());
            ps.setString(2, g.getUsername());
            ps.setString(3, g.getName());
            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Git inserido com sucesso.");
                return g;
            }
            ps.close();
            throw new Exception("Erro na inserção.");
        }
    }

}
