package com.apirest.TCBackEnd.DTO;

import java.util.ArrayList;
import java.util.List;

import com.apirest.TCBackEnd.Models.Escala;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EscalaDTO {

	private long id;
	private String diaSemana;
	private ServicoFuncionarioDTO servicoFuncionario;
	private List<ItemEscalaDTO> itensEscala;

	public static EscalaDTO escalaResposta(Escala escala) {
		return new EscalaDTO(escala.getId(), escala.getDiaSemana(),
				ServicoFuncionarioDTO.ServicoFuncionarioResposta(escala.getServicoFuncionario()));
	}

	public static EscalaDTO escalaRespostaItemEscala(Escala escala) {
		return new EscalaDTO(escala.getId(), escala.getDiaSemana(),
				ServicoFuncionarioDTO.ServicoFuncionarioResposta(escala.getServicoFuncionario()),
				ItemEscalaDTO.listarResposta(escala.getItensEscala()));
	}

	// Recebe uma lista de escalas e transforma a lista para o formato de resposta
	public static List<EscalaDTO> listarResposta(Iterable<Escala> listaEscala) {
		// Cria a lista que sera retornada
		List<EscalaDTO> listaDTO = new ArrayList<EscalaDTO>();
		// Faz um for na lista recebida no metodo
		for (Escala escala : listaEscala) {
			listaDTO.add(escalaRespostaItemEscala(escala));
		}
		return listaDTO;
	}

	public EscalaDTO(String diaSemana, ServicoFuncionarioDTO servicoFuncionario) {
		super();
		this.diaSemana = diaSemana;
		this.servicoFuncionario = servicoFuncionario;
	}

	public EscalaDTO(long id, String diaSemana, ServicoFuncionarioDTO servicoFuncionario) {
		super();
		this.id = id;
		this.diaSemana = diaSemana;
		this.servicoFuncionario = servicoFuncionario;
	}

}
