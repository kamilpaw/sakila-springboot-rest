package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.location.Staff;
import com.kpaw.sakilaspringbootrest.repository.location.StaffRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyByte;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class StaffServiceImplTest {

    @Mock
    StaffRepository staffRepository;

    @InjectMocks
    StaffServiceImpl service;

    Staff staff, staff1;
    List<Staff> list;


    @BeforeEach
    void setUp() {
        staff = new Staff();
        staff1 = new Staff();
        list = new ArrayList<>();
        list.add(staff);
        list.add(staff1);
    }

    @Test
    void findAll() {
        given(staffRepository.findAll()).willReturn(list);
        service.findAll();
        then(staffRepository).should().findAll();
        assertEquals(2, staffRepository.findAll().size());
    }

    @Test
    void findById() {
        given(staffRepository.findById(anyByte())).willReturn(Optional.of(staff));
        service.findById(anyByte());
        then(staffRepository).should().findById(anyByte());
        assertThat(staffRepository.findById(anyByte())).isNotEmpty();
    }

    @Test
    void save() {
        service.save(staff);
        then(staffRepository).should().save(staff);
    }

    @Test
    void deleteById() {
        service.deleteById((byte) 1);
        then(staffRepository).should().deleteById((byte) 1);

    }
}