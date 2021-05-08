package se2.hanu_hospital.staff.doctor.doctorMapper;

import se2.hanu_hospital.staff.doctor.model.Doctor;

public class DoctorDTOAdapter {
    public static DoctorDTO convertToDoctorDTO(Doctor doctor){
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctor.getId());
        doctorDTO.setName(doctor.getName());
        doctorDTO.setPhone(doctor.getPhone());
        doctorDTO.setEmail(doctor.getEmail());
        doctorDTO.setDob(doctor.getDob());
        doctorDTO.setSalary(doctor.getSalary());
        doctorDTO.setSpeciality(doctorDTO.getSpeciality());
        doctorDTO.setAvailable(doctor.isAvailable());

        return doctorDTO;
    }
}
