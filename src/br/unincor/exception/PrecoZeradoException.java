package br.unincor.exception;

import br.unincor.model.Produto;

/**
 * Receber um objeto "Produto" via
 * construtor e implementar o método
 * 'getMessage()' retornando o texto:
 * 
 * "O produto <nome do produto> está com o preço zerado!"
 *
 */
public class PrecoZeradoException extends Exception {

	private Produto p;
	
	public PrecoZeradoException(Produto p) {
		super();
		this.p = p;
	}

	@Override
	public String getMessage() {
		return "O produto " + this.p.getNome() + " está com o preço zerado!";
	}

}
