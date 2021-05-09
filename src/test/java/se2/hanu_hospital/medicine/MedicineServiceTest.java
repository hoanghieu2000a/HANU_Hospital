package se2.hanu_hospital.medicine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MedicineService.class})
@ExtendWith(SpringExtension.class)
public class MedicineServiceTest {
    @MockBean
    private MedicineRepository medicineRepository;

    @Autowired
    private MedicineService medicineService;

    @Test
    public void testGetMedicine() {
        ArrayList<Medicine> medicineList = new ArrayList<Medicine>();
        when(this.medicineRepository.findAll()).thenReturn(medicineList);
        List<Medicine> actualMedicine = this.medicineService.getMedicine();
        assertSame(medicineList, actualMedicine);
        assertTrue(actualMedicine.isEmpty());
        verify(this.medicineRepository).findAll();
    }

    @Test
    public void testDeleteMedicine() {
        doNothing().when(this.medicineRepository).deleteById((Long) any());
        when(this.medicineRepository.existsById((Long) any())).thenReturn(true);
        this.medicineService.deleteMedicine(123L);
        verify(this.medicineRepository).deleteById((Long) any());
        verify(this.medicineRepository).existsById((Long) any());
    }

    @Test
    public void testDeleteMedicine2() {
        doNothing().when(this.medicineRepository).deleteById((Long) any());
        when(this.medicineRepository.existsById((Long) any())).thenReturn(false);
        assertThrows(IllegalStateException.class, () -> this.medicineService.deleteMedicine(123L));
        verify(this.medicineRepository).existsById((Long) any());
    }

    @Test
    public void testGetProfit() {
        Medicine medicine = new Medicine();
        medicine.setExpireDate(LocalDate.ofEpochDay(1L));
        medicine.setSellPrice(10.0);
        medicine.setId(123L);
        medicine.setImportPrice(10.0);
        medicine.setImportDate(LocalDate.ofEpochDay(1L));
        medicine.setName("Name");
        medicine.setQuantity(1);
        Optional<Medicine> ofResult = Optional.<Medicine>of(medicine);
        when(this.medicineRepository.findById((Long) any())).thenReturn(ofResult);
        verify(this.medicineRepository).findById((Long) any());
    }

    @Test
    public void testGetProfit2() {
        when(this.medicineRepository.findById((Long) any())).thenReturn(Optional.<Medicine>empty());
        verify(this.medicineRepository).findById((Long) any());
    }

    @Test
    public void testIsExpired() {
        Medicine medicine = new Medicine();
        medicine.setExpireDate(LocalDate.ofEpochDay(1L));
        medicine.setSellPrice(10.0);
        medicine.setId(123L);
        medicine.setImportPrice(10.0);
        medicine.setImportDate(LocalDate.ofEpochDay(1L));
        medicine.setName("Name");
        medicine.setQuantity(1);
        Optional<Medicine> ofResult = Optional.<Medicine>of(medicine);
        when(this.medicineRepository.findById((Long) any())).thenReturn(ofResult);
        assertTrue(this.medicineService.isExpired(123L));
        verify(this.medicineRepository).findById((Long) any());
    }

    @Test
    public void testIsExpired2() {
        when(this.medicineRepository.findById((Long) any())).thenReturn(Optional.<Medicine>empty());
        assertThrows(IllegalStateException.class, () -> this.medicineService.isExpired(123L));
        verify(this.medicineRepository).findById((Long) any());
    }

    @Test
    public void testGetMedicineById() {
        Medicine medicine = new Medicine();
        medicine.setExpireDate(LocalDate.ofEpochDay(1L));
        medicine.setSellPrice(10.0);
        medicine.setId(123L);
        medicine.setImportPrice(10.0);
        medicine.setImportDate(LocalDate.ofEpochDay(1L));
        medicine.setName("Name");
        medicine.setQuantity(1);
        Optional<Medicine> ofResult = Optional.<Medicine>of(medicine);
        when(this.medicineRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(medicine, this.medicineService.getMedicineById(123L));
        verify(this.medicineRepository).findById((Long) any());
    }

    @Test
    public void testGetMedicineById2() {
        when(this.medicineRepository.findById((Long) any())).thenReturn(Optional.<Medicine>empty());
        assertThrows(IllegalStateException.class, () -> this.medicineService.getMedicineById(123L));
        verify(this.medicineRepository).findById((Long) any());
    }

    @Test
    public void testGetMedicineByName() {
        Medicine medicine = new Medicine();
        medicine.setExpireDate(LocalDate.ofEpochDay(1L));
        medicine.setSellPrice(10.0);
        medicine.setId(123L);
        medicine.setImportPrice(10.0);
        medicine.setImportDate(LocalDate.ofEpochDay(1L));
        medicine.setName("Name");
        medicine.setQuantity(1);
        when(this.medicineRepository.existsById((Long) any())).thenReturn(true);
        when(this.medicineRepository.findByNameContaining(anyString())).thenReturn(medicine);
        assertSame(medicine, this.medicineService.getMedicineByName("Name"));
        verify(this.medicineRepository).findByNameContaining(anyString());
        verify(this.medicineRepository).existsById((Long) any());
    }

    @Test
    public void testGetMedicineByName2() {
        Medicine medicine = new Medicine();
        medicine.setExpireDate(LocalDate.ofEpochDay(1L));
        medicine.setSellPrice(10.0);
        medicine.setId(123L);
        medicine.setImportPrice(10.0);
        medicine.setImportDate(LocalDate.ofEpochDay(1L));
        medicine.setName("Name");
        medicine.setQuantity(1);
        when(this.medicineRepository.existsById((Long) any())).thenReturn(false);
        when(this.medicineRepository.findByNameContaining(anyString())).thenReturn(medicine);
        assertThrows(IllegalStateException.class, () -> this.medicineService.getMedicineByName("Name"));
        verify(this.medicineRepository).findByNameContaining(anyString());
        verify(this.medicineRepository).existsById((Long) any());
    }

    @Test
    public void testGetSellPrice() {
        Medicine medicine = new Medicine();
        medicine.setExpireDate(LocalDate.ofEpochDay(1L));
        medicine.setSellPrice(10.0);
        medicine.setId(123L);
        medicine.setImportPrice(10.0);
        medicine.setImportDate(LocalDate.ofEpochDay(1L));
        medicine.setName("Name");
        medicine.setQuantity(1);
        Optional<Medicine> ofResult = Optional.<Medicine>of(medicine);
        when(this.medicineRepository.findById((Long) any())).thenReturn(ofResult);
        verify(this.medicineRepository).findById((Long) any());
    }

    @Test
    public void testGetSellPrice2() {
        when(this.medicineRepository.findById((Long) any())).thenReturn(Optional.<Medicine>empty());
        verify(this.medicineRepository).findById((Long) any());
    }
}

