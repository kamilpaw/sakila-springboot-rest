package com.kpaw.sakilaspringbootrest.serviceimpl;


import ch.qos.logback.core.rolling.helper.RenameUtil;
import com.kpaw.sakilaspringbootrest.domain.rent.Rental;
import com.kpaw.sakilaspringbootrest.exception.EntityNotFoundExc;
import com.kpaw.sakilaspringbootrest.repository.rent.RentalRepository;
import com.kpaw.sakilaspringbootrest.service.RentalService;
import com.kpaw.sakilaspringbootrest.web.mapper.DTOMapper;
import com.kpaw.sakilaspringbootrest.web.model.dtos.RentalDTO;
import com.kpaw.sakilaspringbootrest.web.model.pages.RentalPagedList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalServiceImpl implements RentalService {

    private RentalRepository rentalRepository;
    private DTOMapper mapper;

    public RentalServiceImpl(RentalRepository rentalRepository, DTOMapper mapper){
        this.rentalRepository = rentalRepository;
        this.mapper = mapper;
    }

    @Override
    public RentalPagedList findAll(PageRequest pageRequest) {
        Page<Rental> rentalPage = rentalRepository.findAll(pageRequest);
        return new RentalPagedList(rentalPage.getContent().stream().map(mapper::toRentalDto).collect(Collectors.toList()),
                PageRequest.of(rentalPage.getPageable().getPageNumber(), rentalPage.getPageable().getPageSize()),
                rentalPage.getTotalElements());
    }

    @Override
    public RentalDTO findById(int id) {
        Optional<Rental> result = rentalRepository.findById(id);
        if(result.isEmpty()){
            throw new EntityNotFoundExc("Rental", id);
        }
        return mapper.toRentalDto(result.get());
    }

    @Override
    public void save(RentalDTO rentalDTO) {
        rentalRepository.save(mapper.toRental(rentalDTO));
    }

    @Override
    public void deleteById(int id) {
        rentalRepository.deleteById(id);
    }

    @Override
    public RentalPagedList findRentalsByCustomerId(int customerId, PageRequest pageRequest) {
        Page<Rental> rentalPage = rentalRepository.findRentalsByCustomerId(customerId, pageRequest);
        return new RentalPagedList(rentalPage.getContent().stream().map(mapper::toRentalDto).collect(Collectors.toList()),
                PageRequest.of(rentalPage.getPageable().getPageNumber(), rentalPage.getPageable().getPageSize()),
                rentalPage.getTotalElements());
    }
}
