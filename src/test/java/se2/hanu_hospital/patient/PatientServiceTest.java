package se2.hanu_hospital.patient;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se2.hanu_hospital.record.Record;
import se2.hanu_hospital.util.Gender;

@ContextConfiguration(classes = {ModelMapper.class, PatientService.class})
@ExtendWith(SpringExtension.class)
public class PatientServiceTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testCreate() {
        Patient patient = new Patient();
        patient.setDob(LocalDate.ofEpochDay(1L));
        patient.setRecords(new HashSet<Record>());
        patient.setGender(Gender.MALE);
        patient.setName("Name");
        patient.setPhoneNumber("4105551212");
        patient.setAddress("42 Main St");
        when(this.patientRepository.save((Patient) any())).thenReturn(patient);
        assertSame(patient, this.patientService.create(new Patient()));
        verify(this.patientRepository).save((Patient) any());
    }

    @Test
    public void testUpdateById() {
        Patient patient = new Patient();
        patient.setDob(LocalDate.ofEpochDay(1L));
        patient.setRecords(new HashSet<Record>());
        patient.setGender(Gender.MALE);
        patient.setName("Name");
        patient.setPhoneNumber("4105551212");
        patient.setAddress("42 Main St");

        Patient patient1 = new Patient();
        patient1.setDob(LocalDate.ofEpochDay(1L));
        patient1.setRecords(new HashSet<Record>());
        patient1.setGender(Gender.MALE);
        patient1.setName("Name");
        patient1.setPhoneNumber("4105551212");
        patient1.setAddress("42 Main St");
        when(this.patientRepository.save((Patient) any())).thenReturn(patient1);
        when(this.patientRepository.getPatientById((Long) any())).thenReturn(patient);
        when(this.patientRepository.existsById((Long) any())).thenReturn(true);
        assertSame(patient1, this.patientService.updateById(123L, new PatientPayload()));
        verify(this.patientRepository).save((Patient) any());
        verify(this.patientRepository).getPatientById((Long) any());
        verify(this.patientRepository).existsById((Long) any());
    }

    @Test
    public void testUpdateById2() {
        Patient patient = new Patient();
        patient.setDob(LocalDate.ofEpochDay(1L));
        patient.setRecords(new HashSet<Record>());
        patient.setGender(Gender.MALE);
        patient.setName("Name");
        patient.setPhoneNumber("4105551212");
        patient.setAddress("42 Main St");

        Patient patient1 = new Patient();
        patient1.setDob(LocalDate.ofEpochDay(1L));
        patient1.setRecords(new HashSet<Record>());
        patient1.setGender(Gender.MALE);
        patient1.setName("Name");
        patient1.setPhoneNumber("4105551212");
        patient1.setAddress("42 Main St");
        when(this.patientRepository.save((Patient) any())).thenReturn(patient1);
        when(this.patientRepository.getPatientById((Long) any())).thenReturn(patient);
        when(this.patientRepository.existsById((Long) any())).thenReturn(false);
        assertThrows(IllegalStateException.class, () -> this.patientService.updateById(123L, new PatientPayload()));
        verify(this.patientRepository).existsById((Long) any());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(this.patientRepository).deleteById((Long) any());
        this.patientService.deleteById(123L);
        verify(this.patientRepository).deleteById((Long) any());
    }

    @Test
    public void testFindAll() {
        ArrayList<Patient> patientList = new ArrayList<Patient>();
        when(this.patientRepository.findAll()).thenReturn(patientList);
        List<Patient> actualFindAllResult = this.patientService.findAll();
        assertSame(patientList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(this.patientRepository).findAll();
    }

    @Test
    public void testFindAll2() {
        PageImpl<Patient> pageImpl = new PageImpl<Patient>(new ArrayList<Patient>());
        when(this.patientRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        Page<Patient> actualFindAllResult = this.patientService.findAll(null);
        assertSame(pageImpl, actualFindAllResult);
        assertTrue(actualFindAllResult.toList().isEmpty());
        verify(this.patientRepository).findAll((Pageable) any());
    }

    @Test
    public void testIsPhoneNumberUnique() {
        when(this.patientRepository.existsByPhoneNumber(anyString())).thenReturn(true);
        assertTrue(this.patientService.isPhoneNumberUnique("4105551212"));
        verify(this.patientRepository).existsByPhoneNumber(anyString());
    }

    @Test
    public void testGetById() {
        Patient patient = new Patient();
        patient.setDob(LocalDate.ofEpochDay(1L));
        patient.setRecords(new HashSet<Record>());
        patient.setGender(Gender.MALE);
        patient.setName("Name");
        patient.setPhoneNumber("4105551212");
        patient.setAddress("42 Main St");
        Optional<Patient> ofResult = Optional.<Patient>of(patient);
        when(this.patientRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(patient, this.patientService.getById(123L));
        verify(this.patientRepository).findById((Long) any());
    }

    @Test
    public void testGetById2() {
        when(this.patientRepository.findById((Long) any())).thenReturn(Optional.<Patient>empty());
        assertThrows(IllegalStateException.class, () -> this.patientService.getById(123L));
        verify(this.patientRepository).findById((Long) any());
    }

    @Test
    public void testFindAllByKeyword() {
        PageImpl<Patient> pageImpl = new PageImpl<Patient>(new ArrayList<Patient>());
        when(this.patientRepository.findAllByNameLike(anyString(), (Pageable) any())).thenReturn(pageImpl);
        Page<Patient> actualFindAllByKeywordResult = this.patientService.findAllByKeyword("Keyword", null);
        assertSame(pageImpl, actualFindAllByKeywordResult);
        assertTrue(actualFindAllByKeywordResult.toList().isEmpty());
        verify(this.patientRepository).findAllByNameLike(anyString(), (Pageable) any());
    }

    @Test
    public void testFindByKeyword() {
        ArrayList<Patient> patientList = new ArrayList<Patient>();
        when(this.patientRepository.findAllByNameLike(anyString())).thenReturn(patientList);
        List<Patient> actualFindByKeywordResult = this.patientService.findByKeyword("Keyword");
        assertSame(patientList, actualFindByKeywordResult);
        assertTrue(actualFindByKeywordResult.isEmpty());
        verify(this.patientRepository).findAllByNameLike(anyString());
    }
}

