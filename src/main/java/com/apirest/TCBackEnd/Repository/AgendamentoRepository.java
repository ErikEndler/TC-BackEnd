package com.apirest.TCBackEnd.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.apirest.TCBackEnd.Models.Agendamento;
import com.apirest.TCBackEnd.Models.ItemEscala;

public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> {
	Iterable<Agendamento> findAllByCliente(long id);

	Iterable<Agendamento> findAllByServicoFuncionario(long id);

	@Query(value = "select count(*) from agendamento "
			+ "where horario between ?1 and ?2 or horarioFim between ?1 and ?2", nativeQuery = true)
	int qtdSimultaneos(LocalDateTime dataInicial, LocalDateTime datafinal);

	@Query(value = "select count(*) from agendamento "
			+ "where servicoFuncionario = ?3 and horario between ?1 and ?2 or horarioFim between ?1 and ?2", nativeQuery = true)
	int countChoques(LocalDateTime dataInicial, LocalDateTime datafinal, long servicoFuncionario_id);

	@Query(value = "select item_escala.* from item_escala " + "join escala on escala.id = item_escala.escala_id "
			+ "join servico on servico.id = escala.servico_id "
			+ "where escala.dia_semana= ?1 and servico.id= ?2", nativeQuery = true)
	List<ItemEscala> servicoEscalaDia(String dia_semana, long servico_id);

	@Query(value = "select * from agendamento where horario = ?1 order by horario ", nativeQuery = true)
	List<Agendamento> horariosDia(LocalDate data);

}
