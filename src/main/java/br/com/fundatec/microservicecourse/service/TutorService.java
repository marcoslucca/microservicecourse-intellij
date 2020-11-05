package br.com.fundatec.microservicecourse.service;

import br.com.fundatec.microservicecourse.domain.Cachorro;
import br.com.fundatec.microservicecourse.domain.Tutor;
import br.com.fundatec.microservicecourse.exception.NotFoundException;
import br.com.fundatec.microservicecourse.repository.TutorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public List<Tutor> findAll() {
        List<Tutor> resultado = tutorRepository.findAll();
        return resultado;
    }

    public Tutor findByNome(String nome) {
        Optional<Tutor> resultado = tutorRepository.findByNome(nome);

        if (resultado.isEmpty()) {
            throw new NotFoundException("Tutor was not found");
        }

        return resultado.get();
    }

    public List<Cachorro> findAllCachorros(String nome) {
        return tutorRepository.findAllCachorros(nome);
    }

    public Tutor salvaTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }
}
