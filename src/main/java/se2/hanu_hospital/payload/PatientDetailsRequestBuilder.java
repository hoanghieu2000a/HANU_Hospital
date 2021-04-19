package se2.hanu_hospital.payload;



public class PatientDetailsRequestBuilder {
    
    private String bloodtype;

    private int heigth;

    private int weigth;

    private Long patientId;

    public PatientDetailsRequestBuilder(String bloodtype, int heigth, int weigth, Long patientId) {
        this.bloodtype = bloodtype;
        this.heigth = heigth;
        this.weigth = weigth;
        this.patientId = patientId;
    }

    public PatientDetailsRequestBuilder(String bloodtype, int heigth, int weigth) {
        this.bloodtype = bloodtype;
        this.heigth = heigth;
        this.weigth = weigth;
    }

    public PatientDetailsRequestBuilder() {
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public int getWeigth() {
        return weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    
    
}
