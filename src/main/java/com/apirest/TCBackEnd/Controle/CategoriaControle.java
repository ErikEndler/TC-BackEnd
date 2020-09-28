package com.apirest.TCBackEnd.Controle;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apirest.TCBackEnd.DTO.CategoriaDTO;
import com.apirest.TCBackEnd.Models.Categoria;
import com.apirest.TCBackEnd.Repository.CategoriaRepository;
import com.apirest.TCBackEnd.Util.ResourceNotFoundException;

@Service
public class CategoriaControle extends GenericControl<Categoria, CategoriaDTO, CategoriaRepository> {

	@Override
	protected void verificaSalvar(CategoriaDTO dto) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void verificUpdate(CategoriaDTO dto) {
		verificaExixte(dto.getId());
	}

	@Override
	protected void verificaListAll() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void verificaList(long id) {
		verificaExixte(id);
	}

	@Override
	protected void verificaDeletar(long id) {
		verificaExixte(id);
	}

	@Override
	protected Categoria transformaSalvar(CategoriaDTO dto) {
		return new Categoria(dto.getNome(), dto.getDescricao());
	}

	@Override
	protected Categoria transformaEditar(CategoriaDTO dto) {
		return new Categoria(dto.getId(), dto.getNome(), dto.getDescricao());
	}

	// -------------------------METODOS AUXILIARES ----------------------------
	private void verificaExixte(long id) {
		Optional<Categoria> categoria = repositorio.findById(id);
		categoria
				.orElseThrow(() -> new ResourceNotFoundException(MenssagemErro() + " nao encontrado para o ID: " + id));
	}

	protected String MenssagemErro() {
		String msg = "Categoria";
		return msg;
	}

}