package se2.hanu_hospital.model.patient;


import javax.persistence.*;


@Entity
@Table(name = "patient_details")
public class PatientDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "bloodtype")
    private String bloodtype;

    @Column(name = "height")
    private int heigth;

    @Column(name = "weight")
    private int weigth;

    @OneToOne
    @MapsId
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public PatientDetails() {
    }

    public PatientDetails(long id, String bloodtype, int heigth, int weigth, Patient patient) {
        this.id = id;
        this.bloodtype = bloodtype;
        this.heigth = heigth;
        this.weigth = weigth;
        this.patient = patient;
    }

    public PatientDetails(String bloodtype, int heigth, int weigth, Patient patient) {
        this.bloodtype = bloodtype;
        this.heigth = heigth;
        this.weigth = weigth;
        this.patient = patient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "PatientDetails [bloodtype=" + bloodtype + ", heigth=" + heigth + ", id=" + id + ", patient=" + patient
                + ", weigth=" + weigth + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bloodtype == null) ? 0 : bloodtype.hashCode());
        result = prime * result + heigth;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((patient == null) ? 0 : patient.hashCode());
        result = prime * result + weigth;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PatientDetails other = (PatientDetails) obj;
        if (bloodtype == null) {
            if (other.bloodtype != null)
                return false;
        } else if (!bloodtype.equals(other.bloodtype))
            return false;
        if (heigth != other.heigth)
            return false;
        if (id != other.id)
            return false;
        if (patient == null) {
            if (other.patient != null)
                return false;
        } else if (!patient.equals(other.patient))
            return false;
        if (weigth != other.weigth)
            return false;
        return true;
    }

    
    
}
