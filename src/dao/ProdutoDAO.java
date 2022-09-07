package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import modelo.Produto;

public class ProdutoDAO {
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	//método que pesquisa o produto pelo ID
	public boolean pesquisarId(int id) {
		connection = new Conexao().conectar();
		sql = "select * from Produto where id = ?";
		boolean aux = false;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			aux = rs.next();
		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar ID do produto \n" + e);
		}
		
		return aux;
	}
	
	//metodo para inserir os dados do produto na base de dados 
	public void inserir(Produto produto) {
		connection = new Conexao().conectar();
		sql = "insert into Produto(id, preco, nome_do_produto) values(?, ?, ?)";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, produto.getId());
			ps.setDouble(2, produto.getPreco());
			ps.setString(3, produto.getNomeDoProduto());
			ps.execute();
		} catch (SQLException e) {	
			System.out.println("Erro ao inserir produto pelo id\n" + e);
		}			
	}
	
	//metodo para retornar os dados do bilhete cadastrado na base de dados  pelo id
	public Produto pesquisarDadosId(int id) {
		connection = new Conexao().conectar();
		sql = "select * from Produto where id = ?";
		Produto produto = null;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				produto = new Produto(rs.getInt("id"), rs.getDouble("preco"), rs.getString("nome_do_produto"));
				
			}
		} catch(SQLException e) { 
			System.out.println("Erro ao pesquisar o produto pelo id" + e);
		}
		
		return produto;
		
	}
		//metodo para retornar todos os dados de todos os produtos cadastrados
		public List<Produto> listar() {
			List<Produto> lista = new ArrayList<Produto>();
			connection = new Conexao().conectar();
			sql = "select * from java_produto";
			
			try {
				ps = connection.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					lista.add (new Produto(rs.getInt("id"), rs.getDouble("preco"), rs.getString("nome_do_produto")));
				}
			} catch (SQLException e) {
				System.out.println("Erro ao listar dados do bilhete\n" + e);
			}
			
			return lista;
		}		
		
		// método para alterar preço
		public void alterarPreco(int id, int preco) {
			connection = new Conexao().conectar();
			sql = "update Produto set preco = ? where id = ?";
			
			try {
				ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
				ps.setInt(2, preco);
				ps.execute();
			}
			catch(SQLException e) {
				System.out.println("Erro ao carregar o produto\n" + e);
			}
			
		
		
	}
	
	
}
