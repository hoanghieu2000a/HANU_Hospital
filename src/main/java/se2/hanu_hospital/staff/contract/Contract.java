package se2.hanu_hospital.staff.contract;

import java.util.Date;

public class Contract {
    private Long contractId;
    private Long staffId;
    private Double salary;
    private Date startDate;
    private String contractDuration;
    private ContractStatus contractStatus;

    public Contract(Long contractId, Long staffId, Double salary, Date startDate, String contractDuration, ContractStatus contractStatus) {
        this.contractId = contractId;
        this.staffId = staffId;
        this.salary = salary;
        this.startDate = startDate;
        this.contractDuration = contractDuration;
        this.contractStatus = contractStatus;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contractId=" + contractId +
                ", staffId=" + staffId +
                ", salary=" + salary +
                ", startDate=" + startDate +
                ", contractDuration='" + contractDuration + '\'' +
                ", contractStatus=" + contractStatus +
                '}';
    }

    public boolean termsAndCondition() {
        return false;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getContractDuration() {
        return contractDuration;
    }

    public void setContractDuration(String contractDuration) {
        this.contractDuration = contractDuration;
    }

    public ContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }
}
