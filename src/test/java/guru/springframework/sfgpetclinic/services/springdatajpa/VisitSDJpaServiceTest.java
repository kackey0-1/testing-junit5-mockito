package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @DisplayName("Test Find All")
    @Test
    void findAll() {
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> foundVisits = service.findAll();
        assertThat(foundVisits).isNotNull();
        verify(visitRepository).findAll();
    }

    @DisplayName("Test Find By Id")
    @Test
    void findById() {
        Visit visit = new Visit();
        when(visitRepository.findById(1L)).thenReturn(Optional.of(visit));
        Visit foundVisit = service.findById(1L);
        assertThat(foundVisit).isNotNull();
        verify(visitRepository).findById(anyLong());
    }

    @DisplayName("Test Save")
    @Test
    void save() {
        Visit visit = new Visit();
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);
        Visit saved = service.save(new Visit());
        assertThat(saved).isNotNull();
        verify(visitRepository).save(any(Visit.class));
    }

    @DisplayName("Test Delete")
    @Test
    void delete() {
        Visit visit = new Visit();
        // voidメソッドのため、return検証不要
        service.delete(visit);
        verify(visitRepository).delete(any(Visit.class));
    }

    @DisplayName("Test Delete By Id")
    @Test
    void deleteById() {
        // voidメソッドのため、return検証不要
        service.deleteById(1l);
        verify(visitRepository).deleteById(anyLong());
    }
}