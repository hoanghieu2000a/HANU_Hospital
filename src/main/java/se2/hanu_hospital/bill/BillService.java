package se2.hanu_hospital.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import se2.hanu_hospital.billline.BillLineService;
import se2.hanu_hospital.billline.MedicalBillLine;
import se2.hanu_hospital.billline.ServiceBillLine;
import se2.hanu_hospital.equipment.Equipment;
import se2.hanu_hospital.medical_procedure.MedicalProcedure;
import se2.hanu_hospital.medicine.Medicine;
import se2.hanu_hospital.medicine.MedicineService;
import se2.hanu_hospital.prescription.Prescription;
import se2.hanu_hospital.record.Record;
import se2.hanu_hospital.staff.doctor.model.Doctor;
import se2.hanu_hospital.staff.doctor.service.DoctorService;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final BillLineService billLineService;
    private final MedicineService medicineService;
    private final DoctorService doctorService;

    private Calendar cal1 = Calendar.getInstance();
    private Calendar cal2 = Calendar.getInstance();
    private LocalDate currentDate = LocalDate.now();


    @Autowired
    public BillService(BillRepository billRepository, BillLineService billLineService, MedicineService medicineService, DoctorService doctorService) {
        this.billRepository = billRepository;
        this.billLineService = billLineService;
        this.medicineService = medicineService;
        this.doctorService = doctorService;
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

    public Double getIncome() {
        double totalIncome = 0.0;
        for (Bill bill: billRepository.findAll()){
            LocalDate importDate = bill.getCreatedAt();
            cal1.set(importDate.getYear(), importDate.getMonthValue(), importDate.getDayOfMonth());
            cal2.set(currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth());

            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
                if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
                    totalIncome += bill.getTotalPrice();
                }
            }
        }

        return totalIncome;
    }

    public Double getExpend(){
        double totalExpend = 0.0;

        for(Medicine medicine: medicineService.getMedicine()){
            LocalDate importDate = medicine.getImportDate();
            cal1.set(importDate.getYear(), importDate.getMonthValue(), importDate.getDayOfMonth());
            cal2.set(currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth());

            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
                if(cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
                    totalExpend += medicine.getQuantity() * medicine.getImportPrice();
                }
            }
        }

        for (Doctor doctor: doctorService.getAllDoctors()){
            totalExpend += doctor.getSalary();
        }

        return totalExpend;
    }

    public Double getProfit(){
        return getIncome() - getExpend();
    }
}
