/**
 * General Practitioner class - extends HealthProfessional
 * Adds specific attributes and behaviors for GPs
 */
public class GeneralPractitioner extends HealthProfessional {

    private boolean canPrescribeMedication;
    private int maxPatientsPerDay;

    /**
     * Default constructor
     */
    public GeneralPractitioner() {
        super();
        this.canPrescribeMedication = true;
        this.maxPatientsPerDay = 20;
    }

    /**
     * Parameterized constructor
     */
    public GeneralPractitioner(int id, String name, String department,
                               boolean canPrescribeMedication, int maxPatientsPerDay) {
        super(id, name, department);
        this.canPrescribeMedication = canPrescribeMedication;

        if (maxPatientsPerDay <= 0) {
            this.maxPatientsPerDay = 20;
        } else {
            this.maxPatientsPerDay = maxPatientsPerDay;
        }
    }

    /**
     * Override to provide specific type information
     */
    @Override
    public String getProfessionalType() {
        return "General Practitioner";
    }

    /**
     * Print detailed information including type and all attributes
     */
    public void printHealthProfessionalDetails() {
        System.out.println("The health professional details are:");
        System.out.println("Type: " + getProfessionalType());
        super.printDetails();
        System.out.println("Can Prescribe Medication: " + canPrescribeMedication);
        System.out.println("Max Patients Per Day: " + maxPatientsPerDay);
        System.out.println("------------------------");
    }

    /**
     * Check if this GP can accept more patients
     */
    public boolean canAcceptNewPatient(int currentAppointments) {
        return currentAppointments < maxPatientsPerDay;
    }

    /**
     * Get description of prescription authority
     */
    public String getPrescriptionAuthority() {
        return canPrescribeMedication ?
                "This doctor has prescription authority" :
                "This doctor cannot prescribe medication";
    }

    // Getters and Setters
    public boolean canPrescribeMedication() {
        return canPrescribeMedication;
    }

    public void setCanPrescribeMedication(boolean canPrescribeMedication) {
        this.canPrescribeMedication = canPrescribeMedication;
    }

    public int getMaxPatientsPerDay() {
        return maxPatientsPerDay;
    }

    public void setMaxPatientsPerDay(int maxPatientsPerDay) {
        if (maxPatientsPerDay > 0) {
            this.maxPatientsPerDay = maxPatientsPerDay;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "GeneralPractitioner{id=%d, name='%s', canPrescribeMedication=%s, maxPatientsPerDay=%d}",
                getId(), getName(), canPrescribeMedication, maxPatientsPerDay
        );
    }
}
