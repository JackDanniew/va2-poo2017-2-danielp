package br.unincor.controle;

import java.util.ArrayList;
import java.util.List;

import br.unincor.exception.PrecoZeradoException;
import br.unincor.model.Produto;
import br.unincor.model.Sanduiche;
import br.unincor.model.Sobremesa;
import br.unincor.view.Usuario;

public class Main {

	public static void main(String[] args) {
		Usuario view = new Usuario();
		CalculoPrecos calc = new CalculoPrecos();
		List<Produto> listProduto = new ArrayList<Produto>();
	
		Integer op = 0;
		while(op != -1 && op != 2) {
			op = view.exibeMenuPrincipal();
			
			try {
				if(op == 0) {
					//Sanduiche
					Sanduiche sanduiche = new Sanduiche("McLanche Feliz ", 100.0, true, true);
					calc.calculaPrecoFinal(sanduiche);
					listProduto.add(sanduiche);
				} else if(op == 1) {
					//Sobremesa
					Sobremesa sobremesa = new Sobremesa("McFLurry", 100.0, true);
					calc.calculaPrecoFinal(sobremesa);
					listProduto.add(sobremesa);
				}
			} catch (PrecoZeradoException pze) {
				view.exibeMsg(pze.getMessage());
			}
		}
		
		Integer opPagto = view.exibeMenuPagamento();
		Double valorFinal = 0.0;
		if(opPagto == 0) {
			//Cartão
			valorFinal = calc.pagtoDinheiro(listProduto);
		} else {
			//Boleto
			valorFinal = calc.pagtoCartao(listProduto);
		}
		
		view.exibeMsg("Resumo do pedido");
		for (Produto p : listProduto) {
			view.exibeMsg(p.verDados());
		}
		view.exibeMsg("Valor final: " + valorFinal);
	}

}
		
		/**
		 * 1. Exibir o menu principal, onde o usuário deverá escolher
		 * qual produto deseja (sanduíche ou sobremesa), também deve-se ter a
		 * opção de “Finalizar pedido”.
		 * 
		 * 2. De acordo com a opção escolhida receber todos os atributos do
		 * produto desejado (nome, preço, trio e dobro de recheio para sanduíche
		 * e nome, preço e adicionais para sobremesa).
		 * 
		 * 3. Após a criação do produto, adicioná-lo na lista de produtos.
		 * 
		 * 4. Exibir o menu de pagamento com as opções “Dinheiro” e “Cartão”.
		 * Caso não seja selecionada nenhuma opção, considerar que o pagamento
		 * será feito em dinheiro.
		 * 
		 * 5. Para cada produto da lista calcular o seu valor final (utilizando
		 * os métodos da classe CalculaPreco) e salvá-lo no atributo ‘preco’.
		 * Tratar a exceção de preço zerado.
		 * 
		 * 6. Somar o valor final de todos os produtos para obter o valor total do pedido.
		 * 
		 * 7. Ao final do processamento do pedido, exibir um resumo do pedido com o preço
		 * final de cada produto e valor final do pedido.
		 * 
		 */
					
