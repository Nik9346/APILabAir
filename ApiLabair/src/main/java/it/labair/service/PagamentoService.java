package it.labair.service;

import it.labair.helper.Risposta;
import it.labair.model.Pagamento;
import jakarta.servlet.http.HttpServletRequest;

public interface PagamentoService {
	Risposta addPagamento(Pagamento pagamento, HttpServletRequest request);
}
