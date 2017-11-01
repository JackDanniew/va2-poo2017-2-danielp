package br.unincor.controle;

import java.util.List;

import br.unincor.exception.PrecoZeradoException;

import br.unincor.model.Produto;
import br.unincor.model.Sanduiche;
import br.unincor.model.Sobremesa;

public class CalculoPrecos {
	
	/**
	 * Primeiro deve-se testar se o preço do produto não está 'null'
	 * 		- Caso o preço esteja zerado ('null'), lançar uma PrecoZeradoException
	 * 
	 * Realizar o cálculo do preço final do produto seguindo as regras:
	 * 
	 * Sanduiche:
	 * 		- Trio: se for TRUE acrescenta R$XX,XX no preço (valor referente a batata e bebida)
	 * 		- Dobro de recheio: ser for TRUE acresce o preço em XX%
	 * 
	 * Sobremesa:
	 * 		- Adicionais: se for TRUE acresce o preço em XX%
	 * 
	 */
	public void calculaPrecoFinal(Produto p) throws PrecoZeradoException {
		if(p.getPreco() != null && p.getPreco() != 0) {
			//Preço do produto está ok!
			
			if(p instanceof Sanduiche) {
				Sanduiche sanduiche = (Sanduiche)p;
				
				if(sanduiche.getDobroRecheio()) {
					sanduiche.setPreco(sanduiche.getPreco()*1.4);
				} else {
					sanduiche.setPreco(sanduiche.getPreco());
				}
				
				if(sanduiche.getTrio()) {
					sanduiche.setPreco(sanduiche.getPreco()+20.00);
				} else {
					sanduiche.setPreco(sanduiche.getPreco());
				}
			
			} else if(p instanceof Sobremesa) {
				Sobremesa sobremesa = (Sobremesa)p;
				
				if(sobremesa.getAdicionais()) {
					sobremesa.setPreco(sobremesa.getPreco()*1.05);
				} else {
					sobremesa.setPreco(sobremesa.getPreco());
				}
			}
			
		} else {
			//Produto está com o preço zerado!
			throw new PrecoZeradoException(p);
		}
	}
	
	/**
	 * No pagamento em dinheiro após o calculo final do preço, dar desconto de XX%.
	 */
	public Double pagtoDinheiro(List<Produto> listProduto){
		Double somaPreco = 0.0;
		
		for (Produto p : listProduto) {
			somaPreco += p.getPreco();
		}
		
		return somaPreco*0.95;
	}
	
	/**
	 * No pagamento em cartão após o calculo final do preço, acrescer XX% no valor do preço.
	 */
	public Double pagtoCartao(List<Produto> listProduto){
		Double somaPreco = 0.0;
		
		for (Produto p : listProduto) {
			somaPreco += p.getPreco();
		}
		
		return somaPreco*1.1;
	}

}
