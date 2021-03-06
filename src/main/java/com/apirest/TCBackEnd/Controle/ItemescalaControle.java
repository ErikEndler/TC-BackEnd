package com.apirest.TCBackEnd.Controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.TCBackEnd.DTO.ItemEscalaDTO;
import com.apirest.TCBackEnd.Models.Escala;
import com.apirest.TCBackEnd.Models.ItemEscala;
import com.apirest.TCBackEnd.Repository.EscalaRepository;
import com.apirest.TCBackEnd.Repository.ItemEscalaRepository;
import com.apirest.TCBackEnd.Util.DataHora;
import com.apirest.TCBackEnd.Util.Error.ResourceNotFoundException;

@Service
public class ItemescalaControle extends GenericControl<ItemEscala, ItemEscalaDTO, ItemEscalaRepository> {

	@Autowired
	EscalaRepository escalaRepository;
	@Autowired
	DataHora datahora;
	@Autowired
	ServicoControle servicoControle;

	public List<ItemEscala> itensEscalaCompletas(long funcionarioId, List<Long> servicosId) {
		List<ItemEscala> listaEscalas = new ArrayList<>();
		for (long servico : servicosId) {
			listaEscalas = verificaERetorna(funcionarioId, servico, listaEscalas);
		}
		return listaEscalas;
	}

	private List<ItemEscala> verificaERetorna(long funcionarioId, long servicoId, List<ItemEscala> listaEscalas) {
		servicoControle.verifiaExiste(servicoId);
		List<ItemEscala> itensEscala = repositorio
				.findByEscalaServicoFuncionarioFuncionarioIdAndEscalaServicoFuncionarioServicoId(funcionarioId,
						servicoId);
		if (itensEscala.size() == 0) {
			return listaEscalas;
		} else {
			itensEscala.forEach(item -> listaEscalas.add(item));
			return listaEscalas;
		}
	}

	public Iterable<ItemEscala> listarPorservico(long id) {
		verificaEscala(id);
		return repositorio.findAllByEscalaId(id);
	}

	@Override
	protected void verificaSalvar(ItemEscalaDTO dto) {
		verificaEscala(dto.getEscala());
		verificaSobreposiçao(dto);
	}

	@Override
	protected ItemEscala verificUpdate(ItemEscalaDTO dto) {
		ItemEscala retorno = verificaExiste(dto.getId()).get();
		verificaEscala(dto.getEscala());
		return retorno;
	}

	@Override
	protected void verificaListAll() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void verificaList(long id) {
		verificaExiste(id);
	}

	@Override
	protected void verificaDeletar(long id) {
		verificaExiste(id);
	}

	@Override
	protected ItemEscala transformaSalvar(ItemEscalaDTO dto) {
		return new ItemEscala(verificaEscala(dto.getEscala()), datahora.stringEmHora(dto.getHrInicial()),
				datahora.stringEmHora(dto.getHrFinal()));
	}

	@Override
	protected ItemEscala transformaEditar(ItemEscalaDTO dto) {
		return new ItemEscala(dto.getId(), verificaEscala(dto.getEscala()), datahora.stringEmHora(dto.getHrInicial()),
				datahora.stringEmHora(dto.getHrFinal()));
	}

	// ----------------------------------------------------------------

	private Optional<ItemEscala> verificaExiste(long id) {
		Optional<ItemEscala> retorno = repositorio.findById(id);
		retorno.orElseThrow(() -> new ResourceNotFoundException("ItemEscala nao encontrado para o ID: " + id));
		return retorno;
	}

	// verifica e retorna a escala
	private Escala verificaEscala(long id) {
		if (id == 0) {
			throw new ResourceNotFoundException("Campo Escala não informado corretamente !! \"NULO\"");

		}
		Optional<Escala> escala = escalaRepository.findById(id);
		return escala.orElseThrow(() -> new ResourceNotFoundException("ItemEscala nao encontrado para o ID: " + id));
	}

	@Override
	protected void posSalvar(ItemEscala itemEscala) {
		// TODO Auto-generated method stub

	}

	private void verificaSobreposiçao(ItemEscalaDTO dto) {
		int qtd = repositorio.escalaByHrInicialAndHrfinal(datahora.stringEmHora(dto.getHrInicial()),
				datahora.stringEmHora(dto.getHrFinal()), dto.getEscala());
		System.out.println("QTD da busca de comflito : " + qtd);
		if (qtd > 0) {
			throw new EntityNotFoundException("Escala com horario conflitante");
		}
	}
}
