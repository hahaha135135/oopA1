import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Appointment class - manages patient appointments with health professionals
 * Demonstrates polymorphism through HealthProfessional field
 */
public class Appointment {

    private String patientName;
    private String patientMobile;
    private String timeSlot;
    private HealthProfessional doctor;
    private String status;

    // Validation patterns
    private static final Pattern MOBILE_PATTERN = Pattern.compile("^04[0-9]{8}$");
    private static final Pattern TIME_PATTERN = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");

    /**
     * Default constructor
     */
    public Appointment() {
        this.patientName = "Unknown";
        this.patientMobile = "0400000000";
        this.timeSlot = "09:00";
        this.doctor = null;
        this.status = "SCHEDULED";
    }

    /**
     * Parameterized constructor with validation
     */
    public Appointment(String patientName, String patientMobile,
                       String timeSlot, HealthProfessional doctor) {

        // Validate all input parameters
        if (!isValidName(patientName)) {
            throw new IllegalArgumentException("Invalid patient name: " + patientName);
        }
        if (!isValidMobile(patientMobile)) {
            throw new IllegalArgumentException("Invalid mobile number: " + patientMobile);
        }
        if (!isValidTimeSlot(timeSlot)) {
            throw new IllegalArgumentException("Invalid time format: " + timeSlot);
        }
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor cannot be null");
        }

        this.patientName = patientName.trim();
        this.patientMobile = patientMobile.trim().replace(" ", "");
        this.timeSlot = timeSlot;
        this.doctor = doctor;  // Polymorphic assignment
        this.status = "SCHEDULED";
    }

    // Validation methods
    private boolean isValidName(String name) {
        return name != null && name.trim().length() >= 2;
    }

    private boolean isValidMobile(String mobile) {
        if (mobile == null) return false;
        String cleanMobile = mobile.trim().replace(" ", "");
        Matcher matcher = MOBILE_PATTERN.matcher(cleanMobile);
        return matcher.matches();
    }

    private boolean isValidTimeSlot(String time) {
        if (time == null) return false;
        Matcher matcher = TIME_PATTERN.matcher(time.trim());
        return matcher.matches();
    }

    /**
     * Print appointment details with formatted output
     */
    public void printAppointmentDetails() {
        System.out.println("=== Appointment Details ===");
        System.out.println("Patient: " + patientName);
        System.out.println("Mobile: " + patientMobile);
        System.out.println("Time: " + timeSlot);
        System.out.println("Doctor: " + doctor.getName());
        System.out.println("Doctor ID: " + doctor.getId());
        System.out.println("Doctor Type: " + doctor.getProfessionalType());
        System.out.println("Status: " + status);
        System.out.println("===========================");
    }

    /**
     * Cancel this appointment
     */
    public void cancel() {
        if ("CANCELLED".equals(this.status)) {
            System.out.println("Appointment already cancelled");
            return;
        }
        this.status = "CANCELLED";
        System.out.println("Appointment cancelled: " + patientName);
    }

    /**
     * Confirm this appointment
     */
    public void confirm() {
        this.status = "CONFIRMED";
    }

    /**
     * Check if appointment is still valid (not cancelled)
     */
    public boolean isValid() {
        return !"CANCELLED".equals(this.status);
    }

    /**
     * Get summary string for this appointment
     */
    public String getAppointmentSummary() {
        return String.format("%s - %s with %s",
                timeSlot, patientName, doctor.getName());
    }

    // Getters and Setters
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        if (isValidName(patientName)) {
            this.patientName = patientName.trim();
        }
    }

    public String getPatientMobile() {
        return patientMobile;
    }

    public void setPatientMobile(String patientMobile) {
        if (isValidMobile(patientMobile)) {
            this.patientMobile = patientMobile.trim().replace(" ", "");
        }
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        if (isValidTimeSlot(timeSlot)) {
            this.timeSlot = timeSlot;
        }
    }

    public HealthProfessional getDoctor() {
        return doctor;
    }

    public void setDoctor(HealthProfessional doctor) {
        if (doctor != null) {
            this.doctor = doctor;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format(
                "Appointment{patient='%s', mobile='%s', time='%s', doctor=%s}",
                patientName, patientMobile, timeSlot, doctor.getName()
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Appointment that = (Appointment) obj;
        return patientMobile.equals(that.patientMobile);
    }

    @Override
    public int hashCode() {
        return patientMobile.hashCode();
    }
}