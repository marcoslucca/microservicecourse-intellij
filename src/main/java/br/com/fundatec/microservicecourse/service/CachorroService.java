package br.com.fundatec.microservicecourse.service;

import br.com.fundatec.microservicecourse.domain.Cachorro;
import br.com.fundatec.microservicecourse.gateway.ClinicaGateway;
import br.com.fundatec.microservicecourse.gateway.resources.CollectionProjection;
import br.com.fundatec.microservicecourse.gateway.resources.Consulta;
import br.com.fundatec.microservicecourse.gateway.resources.ConsultaRequest;
import br.com.fundatec.microservicecourse.repository.CachorroRepository;
import br.com.fundatec.microservicecourse.repository.TutorRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CachorroService {

    @Autowired
    private CachorroRepository cachorroRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private ClinicaGateway clinicaGateway;

    public Optional<Cachorro> findById(Long id) {
        return cachorroRepository.findById(id);
    }

    public Optional<Cachorro> findByNome(String nome) {
        return cachorroRepository.findByNome(nome);
    }

    public List<Cachorro> findAll() {
        return cachorroRepository.findAll();
    }

    public Cachorro save(Cachorro cachorro) {
        return cachorroRepository.saveAndFlush(cachorro);
    }

    public Consulta adicionaConsulta() {
        ConsultaRequest consultaRequest = new ConsultaRequest();
        consultaRequest.setDate(LocalDate.now().toString());
        consultaRequest.setVet("Marcos");
        Consulta consulta = clinicaGateway.createConsulta(consultaRequest);

        System.out.println("Imprimindo consultas");
        CollectionProjection consultas = clinicaGateway.getConsultas();
        consultas.getItems().forEach(a -> System.out.println(a.toString()));
        return consulta;
    }
}
