package form;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.util.List;
import dao.ProdutoDAO;
import modelo.Produto;

public class FormPrincipal {

	public void menuPrincipal() {
		int opcao = 0;
		
		do {
			try {
				opcao = parseInt(showInputDialog(gerarMenuAdmin()));
				switch(opcao) {
					case 1:
						cadastrarProduto();
						break;
					case 2:
						listarProdutos();
						break;
					case 3:
						consultarProdutos();
						break;
				}
			}
			catch(NumberFormatException e) {
				showMessageDialog(null, "A opção deve ser um número entre 1 e 4\n" + e);
			}
		} while(opcao != 4);
		
	}

	private void cadastrarProduto() {
		ProdutoDAO dao = new ProdutoDAO();
		int id;
		double preco;
		String nomeDoProduto;
		
		id = parseInt(showInputDialog("id do produto"));
		if(dao.pesquisarId(id)) {
			showMessageDialog(null, "id já esta atrelado a um produto");
		} else {
			nomeDoProduto = showInputDialog("Nome do Produto:");
			preco = Double.parseDouble(showInputDialog("Preço do produto:"));
			dao.inserir(new Produto(id, preco, nomeDoProduto));
		}
	}
		
	private void listarProdutos() {
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> lista = dao.listar();
		String aux = "";
		
		for(Produto produto : lista) {
			aux += produto + "\n";
		}
		showMessageDialog(null, aux);
		
	}

	private void consultarProdutos() {
		ProdutoDAO dao = new ProdutoDAO();
		int id = parseInt(showInputDialog("id do produto"));
		
		Produto produto = dao.pesquisarDadosId(id);
		if(produto == null) {
			showMessageDialog(null, "id não cadastrado");
		} else {
			showMessageDialog(null, produto); // bilhete.toString();
		}
		
	}

	private Object gerarMenuAdmin() {
		String menu = "Escolha uma operação:\n";
		menu += "1. cadastrar Produto\n";
		menu += "2. listar Produtos\n";
		menu += "3. consultar Produtos\n";
		menu += "4. Sair";
		return menu;
	}
		
}
