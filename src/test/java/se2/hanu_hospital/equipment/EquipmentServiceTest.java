package se2.hanu_hospital.equipment;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se2.hanu_hospital.medical_procedure.MedicalProcedure;

@ContextConfiguration(classes = {EquipmentService.class})
@ExtendWith(SpringExtension.class)
public class EquipmentServiceTest {
    @MockBean
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentService equipmentService;

    @Test
    public void testCreate() {
        Equipment equipment = new Equipment();
        equipment.setQuantity(1);
        equipment.setMedicalProcedure(new HashSet<MedicalProcedure>());
        equipment.setId(123L);
        equipment.setName("Name");
        equipment.setPrice(10.0);
        when(this.equipmentRepository.save((Equipment) any())).thenReturn(equipment);
        assertSame(equipment, this.equipmentService.create(new Equipment()));
        verify(this.equipmentRepository).save((Equipment) any());
    }

    @Test
    public void testUpdateById() {
        when(this.equipmentRepository.getFacilityById((Long) any())).thenThrow(new IllegalStateException("foo"));
        when(this.equipmentRepository.existsById((Long) any())).thenReturn(true);
        assertThrows(IllegalStateException.class, () -> this.equipmentService.updateById(125L, new EquipmentPayload()));
        verify(this.equipmentRepository).getFacilityById((Long) any());
        verify(this.equipmentRepository).existsById((Long) any());
    }

    @Test
    public void testUpdateById2() {
        Equipment equipment = new Equipment();
        equipment.setQuantity(1);
        equipment.setMedicalProcedure(new HashSet<MedicalProcedure>());
        equipment.setId(123L);
        equipment.setName("Name");
        equipment.setPrice(10.0);

        Equipment equipment1 = new Equipment();
        equipment1.setQuantity(1);
        equipment1.setMedicalProcedure(new HashSet<MedicalProcedure>());
        equipment1.setId(123L);
        equipment1.setName("Name");
        equipment1.setPrice(10.0);
        when(this.equipmentRepository.save((Equipment) any())).thenReturn(equipment1);
        when(this.equipmentRepository.getFacilityById((Long) any())).thenReturn(equipment);
        when(this.equipmentRepository.existsById((Long) any())).thenReturn(true);
        EquipmentPayload equipmentPayload = mock(EquipmentPayload.class);
        when(equipmentPayload.getQuantity()).thenReturn(1);
        when(equipmentPayload.getPrice()).thenReturn(10.0);
        when(equipmentPayload.getName()).thenReturn("Name");
        assertSame(equipment1, this.equipmentService.updateById(123L, equipmentPayload));

    }

    @Test
    public void testUpdateById3() {
        Equipment equipment = new Equipment();
        equipment.setQuantity(1);
        equipment.setMedicalProcedure(new HashSet<MedicalProcedure>());
        equipment.setId(123L);
        equipment.setName("Name");
        equipment.setPrice(10.0);

        Equipment equipment1 = new Equipment();
        equipment1.setQuantity(1);
        equipment1.setMedicalProcedure(new HashSet<MedicalProcedure>());
        equipment1.setId(123L);
        equipment1.setName("Name");
        equipment1.setPrice(10.0);
        when(this.equipmentRepository.save((Equipment) any())).thenReturn(equipment1);
        when(this.equipmentRepository.getFacilityById((Long) any())).thenReturn(equipment);
        when(this.equipmentRepository.existsById((Long) any())).thenReturn(false);
        EquipmentPayload equipmentPayload = mock(EquipmentPayload.class);
        when(equipmentPayload.getQuantity()).thenReturn(1);
        when(equipmentPayload.getPrice()).thenReturn(10.0);
        when(equipmentPayload.getName()).thenReturn("foo");
        assertThrows(IllegalStateException.class, () -> this.equipmentService.updateById(123L, equipmentPayload));
        verify(this.equipmentRepository).existsById((Long) any());
    }

    @Test
    public void testUpdateById4() {
        Equipment equipment = new Equipment();
        equipment.setQuantity(1);
        equipment.setMedicalProcedure(new HashSet<MedicalProcedure>());
        equipment.setId(123L);
        equipment.setName("Name");
        equipment.setPrice(10.0);

        Equipment equipment1 = new Equipment();
        equipment1.setQuantity(1);
        equipment1.setMedicalProcedure(new HashSet<MedicalProcedure>());
        equipment1.setId(123L);
        equipment1.setName("Name");
        equipment1.setPrice(10.0);
        when(this.equipmentRepository.save((Equipment) any())).thenReturn(equipment1);
        when(this.equipmentRepository.getFacilityById((Long) any())).thenReturn(equipment);
        when(this.equipmentRepository.existsById((Long) any())).thenReturn(true);
        EquipmentPayload equipmentPayload = mock(EquipmentPayload.class);
        when(equipmentPayload.getQuantity()).thenReturn(0);
        when(equipmentPayload.getPrice()).thenReturn(10.0);
        when(equipmentPayload.getName()).thenReturn("foo");
        assertSame(equipment1, this.equipmentService.updateById(123L, equipmentPayload));
        verify(equipmentPayload, times(2)).getName();
        verify(equipmentPayload).getQuantity();
        verify(equipmentPayload, times(2)).getPrice();
        verify(this.equipmentRepository).save((Equipment) any());
        verify(this.equipmentRepository).getFacilityById((Long) any());
        verify(this.equipmentRepository).existsById((Long) any());
    }

    @Test
    public void testUpdateById5() {
        Equipment equipment = new Equipment();
        equipment.setQuantity(1);
        equipment.setMedicalProcedure(new HashSet<MedicalProcedure>());
        equipment.setId(123L);
        equipment.setName("Name");
        equipment.setPrice(10.0);

        Equipment equipment1 = new Equipment();
        equipment1.setQuantity(1);
        equipment1.setMedicalProcedure(new HashSet<MedicalProcedure>());
        equipment1.setId(123L);
        equipment1.setName("Name");
        equipment1.setPrice(10.0);
        when(this.equipmentRepository.save((Equipment) any())).thenReturn(equipment1);
        when(this.equipmentRepository.getFacilityById((Long) any())).thenReturn(equipment);
        when(this.equipmentRepository.existsById((Long) any())).thenReturn(true);
        EquipmentPayload equipmentPayload = mock(EquipmentPayload.class);
        when(equipmentPayload.getQuantity()).thenReturn(1);
        when(equipmentPayload.getPrice()).thenReturn(0.0);
        when(equipmentPayload.getName()).thenReturn("foo");
        assertSame(equipment1, this.equipmentService.updateById(123L, equipmentPayload));
        verify(equipmentPayload, times(2)).getName();
        verify(equipmentPayload, times(2)).getQuantity();
        verify(equipmentPayload).getPrice();
        verify(this.equipmentRepository).save((Equipment) any());
        verify(this.equipmentRepository).getFacilityById((Long) any());
        verify(this.equipmentRepository).existsById((Long) any());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(this.equipmentRepository).deleteById((Long) any());
        this.equipmentService.deleteById(123L);
        verify(this.equipmentRepository).deleteById((Long) any());
    }

    @Test
    public void testFindAll() {
        ArrayList<Equipment> equipmentList = new ArrayList<Equipment>();
        when(this.equipmentRepository.findAll()).thenReturn(equipmentList);
        List<Equipment> actualFindAllResult = this.equipmentService.findAll();
        assertSame(equipmentList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(this.equipmentRepository).findAll();
    }

    @Test
    public void testFindAll2() {
        PageImpl<Equipment> pageImpl = new PageImpl<Equipment>(new ArrayList<Equipment>());
        when(this.equipmentRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        Page<Equipment> actualFindAllResult = this.equipmentService.findAll(null);
        assertSame(pageImpl, actualFindAllResult);
        assertTrue(actualFindAllResult.toList().isEmpty());
        verify(this.equipmentRepository).findAll((Pageable) any());
    }

    @Test
    public void testGetById() {
        Equipment equipment = new Equipment();
        equipment.setQuantity(1);
        equipment.setMedicalProcedure(new HashSet<MedicalProcedure>());
        equipment.setId(123L);
        equipment.setName("Name");
        equipment.setPrice(10.0);
        Optional<Equipment> ofResult = Optional.<Equipment>of(equipment);
        when(this.equipmentRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(equipment, this.equipmentService.getById(123L));
        verify(this.equipmentRepository).findById((Long) any());
    }

    @Test
    public void testFindByName() {
        ArrayList<Equipment> equipmentList = new ArrayList<Equipment>();
        when(this.equipmentRepository.findByName(anyString())).thenReturn(equipmentList);
        List<Equipment> actualFindByNameResult = this.equipmentService.findByName("Name");
        assertSame(equipmentList, actualFindByNameResult);
        assertTrue(actualFindByNameResult.isEmpty());
        verify(this.equipmentRepository).findByName(anyString());
    }

    @Test
    public void testUpdateEquipment() {
        Equipment equipment = new Equipment();
        equipment.setQuantity(1);
        equipment.setMedicalProcedure(new HashSet<MedicalProcedure>());
        equipment.setId(123L);
        equipment.setName("Name");
        equipment.setPrice(10.0);
        Optional<Equipment> ofResult = Optional.<Equipment>of(equipment);

        Equipment equipment1 = new Equipment();
        equipment1.setQuantity(1);
        equipment1.setMedicalProcedure(new HashSet<MedicalProcedure>());
        equipment1.setId(123L);
        equipment1.setName("Name");
        equipment1.setPrice(10.0);
        when(this.equipmentRepository.save((Equipment) any())).thenReturn(equipment1);
        when(this.equipmentRepository.findById((Long) any())).thenReturn(ofResult);
        this.equipmentService.updateEquipment(123L, new Equipment());
        verify(this.equipmentRepository).save((Equipment) any());
        verify(this.equipmentRepository).findById((Long) any());
    }

    @Test
    public void testUpdateEquipment2() {
        Equipment equipment = new Equipment();
        equipment.setQuantity(1);
        equipment.setMedicalProcedure(new HashSet<MedicalProcedure>());
        equipment.setId(123L);
        equipment.setName("Name");
        equipment.setPrice(10.0);
        when(this.equipmentRepository.save((Equipment) any())).thenReturn(equipment);
        when(this.equipmentRepository.findById((Long) any())).thenReturn(Optional.<Equipment>empty());
        assertThrows(IllegalStateException.class, () -> this.equipmentService.updateEquipment(123L, new Equipment()));
        verify(this.equipmentRepository).findById((Long) any());
    }

    @Test
    public void testUpdateEquipment3() {
        Equipment equipment = new Equipment();
        equipment.setQuantity(1);
        equipment.setMedicalProcedure(new HashSet<MedicalProcedure>());
        equipment.setId(123L);
        equipment.setName("Name");
        equipment.setPrice(10.0);
        Optional<Equipment> ofResult = Optional.<Equipment>of(equipment);

        Equipment equipment1 = new Equipment();
        equipment1.setQuantity(1);
        equipment1.setMedicalProcedure(new HashSet<MedicalProcedure>());
        equipment1.setId(123L);
        equipment1.setName("Name");
        equipment1.setPrice(10.0);
        when(this.equipmentRepository.save((Equipment) any())).thenReturn(equipment1);
        when(this.equipmentRepository.findById((Long) any())).thenReturn(ofResult);
        Equipment equipment2 = mock(Equipment.class);
        when(equipment2.getMedicalProcedure()).thenReturn(new HashSet<MedicalProcedure>());
        this.equipmentService.updateEquipment(123L, equipment2);
        verify(equipment2).getMedicalProcedure();
        verify(this.equipmentRepository).save((Equipment) any());
        verify(this.equipmentRepository).findById((Long) any());
    }
}

