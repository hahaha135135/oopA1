/**
 * Specialist class - extends HealthProfessional
 * Represents medical specialists with specific expertise
 */
public class Specialist extends HealthProfessional {

    private String specialty;
    private int yearsOfSpecialization;

    /**
     * Default constructor
     */
    public Specialist() {
        super();
        this.specialty = "Not Specified";
        this.yearsOfSpecialization = 0;
    }

    /**
     * Parameterized constructor
     */
    public Specialist(int id, String name, String department,
                      String specialty, int yearsOfSpecialization) {
        super(id, name, department);
        this.specialty = (specialty != null) ? specialty.trim() : "Not Specified";

        if (yearsOfSpecialization < 0) {
            this.yearsOfSpecialization = 0;
        } else {
            this.yearsOfSpecialization = yearsOfSpecialization;
        }
    }

    /**
     * Override to provide specific type information
     */
    @Override
    public String getProfessionalType() {
        return "Specialist";
    }

    /**
     * Print detailed information including specialty and experience
     */
    public void printHealthProfessionalDetails() {
        System.out.println("The health professional details are:");
        System.out.println("Type: " + getProfessionalType());
        super.printDetails();
        System.out.println("Specialty: " + specialty);
        System.out.println("Years of Specialization: " + yearsOfSpecialization + " years");
        System.out.println("Expertise Level: " + getExpertiseLevel());
        System.out.println("------------------------");
    }

    /**
     * Determine expertise level based on years of experience
     */
    public String getExpertiseLevel() {
        if (yearsOfSpecialization >= 15) return "Expert Level";
        if (yearsOfSpecialization >= 8) return "Senior";
        if (yearsOfSpecialization >= 3) return "Qualified";
        return "Junior";
    }

    /**
     * Check if specialist accepts referrals for specific condition
     */
    public boolean acceptReferral(String condition) {
        return condition != null &&
                condition.toLowerCase().contains(specialty.toLowerCase());
    }

    // Getters and Setters
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        if (specialty != null && !specialty.trim().isEmpty()) {
            this.specialty = specialty.trim();
        }
    }

    public int getYearsOfSpecialization() {
        return yearsOfSpecialization;
    }

    public void setYearsOfSpecialization(int yearsOfSpecialization) {
        if (yearsOfSpecialization >= 0) {
            this.yearsOfSpecialization = yearsOfSpecialization;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Specialist{id=%d, name='%s', specialty='%s', yearsOfSpecialization=%d}",
                getId(), getName(), specialty, yearsOfSpecialization
        );
    }
}
