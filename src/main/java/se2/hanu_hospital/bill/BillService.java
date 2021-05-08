package se2.hanu_hospital.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import se2.hanu_hospital.billline.BillLine;
import se2.hanu_hospital.billline.BillLineService;
import se2.hanu_hospital.billline.MedicalBillLine;
import se2.hanu_hospital.billline.ServiceBillLine;
import se2.hanu_hospital.equipment.Equipment;
import se2.hanu_hospital.medical_procedure.MedicalProcedure;
import se2.hanu_hospital.prescription.Prescription;
import se2.hanu_hospital.record.Record;
import se2.hanu_hospital.record.RecordService;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class BillService {
    @Autowired
    private final BillRepository billRepository;
    @Autowired
    private final BillLineService billLineService;

    public BillService(BillRepository billRepository, BillLineService billLineService) {
        this.billRepository = billRepository;
        this.billLineService = billLineService;
    }

    public Bill getBill(long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Department does not exist!"));
    }

    @Transient
    public void addBill(Record record) {
        Bill bill = new Bill();
        double totalPrice = 0.0;

        bill.setRecord(record);
        for (Prescription prescription : record.getPrescriptionMedicine()) {
            MedicalBillLine billLine = new MedicalBillLine();
            billLine.setPrescription(prescription);
            totalPrice += billLine.setPrice(prescription.getMedicine().getSellPrice() * prescription.getDosage());
            billLine.setBill(bill);

            billLineService.addBillLine(billLine);
        }

        for (MedicalProcedure medicalProcedure: record.getMedicalProcedure()){
            ServiceBillLine billLine = new ServiceBillLine();
            billLine.setMedicalProcedure(medicalProcedure);

            double totalMedicalProcedurePrice = 0.0;
            for (Equipment equipment: medicalProcedure.getEquipments()){
                totalMedicalProcedurePrice += equipment.getPrice();
            }

            totalPrice += billLine.setPrice(totalMedicalProcedurePrice);
            billLine.setBill(bill);
            billLineService.addBillLine(billLine);
        }

        bill.setTotalPrice(totalPrice);
        billRepository.save(bill);
    }

    public List<Bill> getAllBill() {
        return billRepository.findAll();
    }
}
