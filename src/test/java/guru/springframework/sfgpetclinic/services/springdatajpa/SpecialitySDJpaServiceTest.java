package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void testDeleteByObject() {
        Speciality speciality = new Speciality();
        service.delete(speciality);
        // 引数の検証
        verify(specialtyRepository).delete(any(Speciality.class));

    }

    @Test
    void findByIdTest() {
        Speciality speciality = new Speciality();
        // 返り値の検証
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));
        Speciality foundSpeciality = service.findById(1L);
        // 値の検証
        assertThat(foundSpeciality).isNotNull();
        // 実行の検証&実行結果の検証
        verify(specialtyRepository).findById(anyLong());

    }

    @Test
    void testDeleteById() {
        service.deleteById(1l);
        service.deleteById(1l);
        // 実行の検証&実行結果の検証
        verify(specialtyRepository, times(2)).deleteById(1l);
     //   verify(specialtyRepository, atLeastOnce()).deleteById(1l);
    }

    @Test
    void testDeleteByIdAtMost() {
        service.deleteById(1l);
        service.deleteById(1l);
        verify(specialtyRepository, atMost(5)).deleteById(1l);
    }

    @Test
    void testDeleteByIdNever() {
        service.deleteById(1l);
        service.deleteById(1l);
        verify(specialtyRepository, atMost(5)).deleteById(1l);
        verify(specialtyRepository, never()).deleteById(5l);
    }

    @Test
    void testDelete() {
        service.delete(new Speciality());
    }
}