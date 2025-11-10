import java.util.ArrayList;
import java.util.Iterator;

/**
 * Main class for PROG2004 A1 - Health Appointment System
 * Demonstrates object-oriented programming principles
 * Implements Part 3 and Part 5 requirements
 */
public class AssignmentOne {

    // ArrayList to store appointments - Part 5 requirement
    private static ArrayList<Appointment> appointments = new ArrayList<>();

    /**
     * Main method - program entry point
     */
    public static void main(String[] args) {
        System.out.println("=== Health Service Appointment System ===");

        // Part 3: Using classes and objects
        demonstratePart3();

        // Part 5: Collection of appointments
        demonstratePart5();

        System.out.println("=== Program Completed ===");
    }

    /**
     * Part 3: Create and display health professional objects
     */
    private static void demonstratePart3() {
        System.out.println("\n// Part 3 – Using classes and objects");

        // Create three General Practitioners
        GeneralPractitioner gp1 = new GeneralPractitioner(101, "Dr. Smith",
                "General Medicine", true, 25);
        GeneralPractitioner gp2 = new GeneralPractitioner(102, "Dr. Johnson",
                "Family Medicine", true, 20);
        GeneralPractitioner gp3 = new GeneralPractitioner(103, "Dr. Brown",
                "Primary Care", false, 15);

        // Create two Specialists
        Specialist specialist1 = new Specialist(201, "Dr. Wilson",
                "Cardiology", "Heart Surgery", 12);
        Specialist specialist2 = new Specialist(202, "Dr. Davis",
                "Radiology", "MRI Diagnosis", 8);

        // Print all health professional details
        gp1.printHealthProfessionalDetails();
        gp2.printHealthProfessionalDetails();
        gp3.printHealthProfessionalDetails();
        specialist1.printHealthProfessionalDetails();
        specialist2.printHealthProfessionalDetails();

        // Demonstrate polymorphism
        demonstratePolymorphism(gp1, specialist1);

        System.out.println("---");
    }

    /**
     * Demonstrate polymorphism with HealthProfessional array
     */
    private static void demonstratePolymorphism(HealthProfessional doc1, HealthProfessional doc2) {
        System.out.println("Polymorphism Demonstration:");

        HealthProfessional[] professionals = {doc1, doc2};

        for (HealthProfessional prof : professionals) {
            System.out.println("Type: " + prof.getProfessionalType() +
                    ", Name: " + prof.getName());
        }
    }

    /**
     * Part 5: Demonstrate appointment collection management
     */
    private static void demonstratePart5() {
        System.out.println("\n// Part 5 – Collection of appointments");

        // Create doctor objects for appointments
        GeneralPractitioner gp = new GeneralPractitioner(101, "Dr. Smith",
                "General Medicine", true, 25);
        Specialist specialist = new Specialist(201, "Dr. Wilson",
                "Cardiology", "Heart Surgery", 12);

        // Create appointments
        createAppointment("John Doe", "0412345678", "09:00", gp);
        createAppointment("Jane Smith", "0498765432", "10:30", gp);
        createAppointment("Mike Johnson", "0432156789", "14:00", specialist);
        createAppointment("Sarah Wilson", "0444555666", "15:30", specialist);

        // Print existing appointments
        printExistingAppointments();

        // Cancel one appointment
        cancelBooking("0498765432");

        // Print updated appointments
        printExistingAppointments();

        System.out.println("---");
    }

    /**
     * Create new appointment and add to ArrayList
     * Polymorphic method - accepts any HealthProfessional subclass
     */
    public static void createAppointment(String patientName, String patientMobile,
                                         String timeSlot, HealthProfessional doctor) {
        // Input validation
        if (patientName == null || patientName.trim().isEmpty()) {
            System.out.println("Error: Patient name is required");
            return;
        }
        if (patientMobile == null || patientMobile.trim().isEmpty()) {
            System.out.println("Error: Patient mobile is required");
            return;
        }
        if (timeSlot == null || timeSlot.trim().isEmpty()) {
            System.out.println("Error: Time slot is required");
            return;
        }
        if (doctor == null) {
            System.out.println("Error: Doctor is required");
            return;
        }

        try {
            Appointment newAppointment = new Appointment(patientName, patientMobile, timeSlot, doctor);
            appointments.add(newAppointment);
            System.out.println("Appointment created: " + patientName);
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to create appointment: " + e.getMessage());
        }
    }

    /**
     * Display all existing appointments in the ArrayList
     */
    public static void printExistingAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No existing appointments.");
            return;
        }

        System.out.println("Existing appointments (" + appointments.size() + "):");
        for (Appointment appointment : appointments) {
            appointment.printAppointmentDetails();
        }
    }

    /**
     * Cancel appointment using patient's mobile number
     * Uses Iterator for safe removal from ArrayList
     */
    public static void cancelBooking(String patientMobile) {
        if (patientMobile == null || patientMobile.trim().isEmpty()) {
            System.out.println("Error: Patient mobile is required");
            return;
        }

        String cleanMobile = patientMobile.trim().replace(" ", "");
        Iterator<Appointment> iterator = appointments.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Appointment appointment = iterator.next();
            if (appointment.getPatientMobile().equals(cleanMobile)) {
                iterator.remove();
                System.out.println("Appointment cancelled for: " + cleanMobile);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No appointment found for: " + cleanMobile);
        }
    }
}