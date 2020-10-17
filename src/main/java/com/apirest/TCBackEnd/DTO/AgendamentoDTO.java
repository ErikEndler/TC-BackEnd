package com.apirest.TCBackEnd.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.apirest.TCBackEnd.Models.Agendamento;
import com.apirest.TCBackEnd.Models.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoDTO {

	private long id;

	private long clienteId;
	private long servicoId;
	private LocalDateTime horario;
	private Boolean notificacao;
	private String obs;

	public static AgendamentoDTO agendamentoResposta(Agendamento agendamento) {
		return new AgendamentoDTO(agendamento.getId(), agendamento.getCliente().getId(),
				agendamento.getServico().getId(), agendamento.getHorario(), agendamento.getNotificacao(),
				agendamento.getObs());
	}

	// Recebe uma lista de Agendamentos e transforma a lista para o formato de
	// resposta
	public static Iterable<AgendamentoDTO> listarResposta(Iterable<Agendamento> listaAgendamentos) {
		// Cria a lista que sera retornada
		List<AgendamentoDTO> listaDTO = new ArrayList<AgendamentoDTO>();
		// Faz um for na lista recebida no metodo
		for (Agendamento categoria : listaAgendamentos) {
			listaDTO.add(agendamentoResposta(categoria));
		}
		return listaDTO;
	}

}