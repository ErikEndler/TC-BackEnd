package com.apirest.TCBackEnd.DTO;

import java.util.ArrayList;
import java.util.List;

import com.apirest.TCBackEnd.Models.Agendamento;
import com.apirest.TCBackEnd.Util.DataHora;
import com.apirest.TCBackEnd.Util.StatusAgendamento;

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

	private UsuarioDTO cliente;
	private ServicoFuncionarioDTO servicoFuncionario;
	private String horarioInicio; // LocalDateTime
	private String horarioFim; // LocalDateTime
	private Boolean notificacao;
	private String obs;
	private StatusAgendamento status;

	static DataHora dataHora = new DataHora();

	public static AgendamentoDTO agendamentoResposta(Agendamento agendamento) {
		return new AgendamentoDTO(agendamento.getId(), UsuarioDTO.usuarioResposta(agendamento.getCliente()),
				ServicoFuncionarioDTO.ServicoFuncionarioResposta(agendamento.getServicoFuncionario()),
				dataHora.dateTimeEmString(agendamento.getHorario()),
				dataHora.dateTimeEmString(agendamento.getHorarioFim()), agendamento.getNotificacao(),
				agendamento.getObs(), agendamento.getStatus());
	}

	// Recebe uma lista de Agendamentos e transforma a lista para o formato de
	// resposta
	public static List<AgendamentoDTO> listarResposta(Iterable<Agendamento> listaAgendamentos) {
		// Cria a lista que sera retornada
		List<AgendamentoDTO> listaDTO = new ArrayList<AgendamentoDTO>();
		// Faz um for na lista recebida no metodo
		for (Agendamento categoria : listaAgendamentos) {
			listaDTO.add(agendamentoResposta(categoria));
		}
		return listaDTO;
	}

}
