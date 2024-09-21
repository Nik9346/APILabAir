package it.labair.dto;

import java.time.LocalDate;
import java.util.List;

public class OrdineDto {

	private int id;
	private LocalDate data;
	private double importo;
	private List<ScarpaOrdinataDto> scarpeOrdinate;
	private IndirizzoDto indirizzo;
	private PagamentoDto pagamento;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public double getImporto() {
		return importo;
	}
	public void setImporto(double importo) {
		this.importo = importo;
	}
	public List<ScarpaOrdinataDto> getScarpeOrdinate() {
		return scarpeOrdinate;
	}
	public void setScarpeOrdinate(List<ScarpaOrdinataDto> scarpeOrdinate) {
		this.scarpeOrdinate = scarpeOrdinate;
	}
	public IndirizzoDto getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(IndirizzoDto indirizzo) {
		this.indirizzo = indirizzo;
	}
	public PagamentoDto getPagamento() {
		return pagamento;
	}
	public void setPagamento(PagamentoDto pagamento) {
		this.pagamento = pagamento;
	}
}
