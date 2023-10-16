package br.com.fiap.gereciadorcoleta.coleta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColetaService {

    @Autowired
    ColetaRepository repository;

    public List<Coleta> findAll() {
        return repository.findAll();
    }

    public boolean delete(Long id) {
        var coleta = repository.findById(id);

        if (coleta.isEmpty())
            return false;

        repository.deleteById(id);
        return true;
    }

    public void save(Coleta coleta) {
        repository.save(coleta);
    }

}
