package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.movie.Language;
import com.kpaw.sakilaspringbootrest.repository.LanguageRepository;
import com.kpaw.sakilaspringbootrest.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {

    private LanguageRepository languageRepository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository){
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Language> findAll() {
        return languageRepository.findAll();
    }

    @Override
    public Language findById(byte id) {
        Optional<Language> result = languageRepository.findById(id);
   /*     if (!result.isPresent()){
            throw new LanguageNotFoundException("Language with id " + id + " not found");
        }
    */
        Language language = result.get();
        return language;
    }

    @Override
    public void save(Language language) {
        languageRepository.save(language);
    }

    @Override
    public void deleteById(byte id) {
        languageRepository.deleteById(id);
    }
}
