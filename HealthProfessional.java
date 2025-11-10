/**
 * Base class for all health professionals
 * Demonstrates inheritance and encapsulation principles
 */
public class HealthProfessional {

    private int id;
    private String name;
    private String department;

    /**
     * Default constructor with default values
     */
    public HealthProfessional() {
        this.id = 0;
        this.name = "Unknown";
        this.department = "General";
    }

    /**
     * Parameterized constructor with validation
     */
    public HealthProfessional(int id, String name, String department) {
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        this.id = id;
        this.name = name.trim();
        this.department = (department != null) ? department.trim() : "General";
    }

    /**
     * Print all details to console
     */
    public void printDetails() {
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Department: " + this.department);
    }

    /**
     * Returns professional type - designed for overriding by subclasses
     */
    public String getProfessionalType() {
        return "Generic Health Professional";
    }

    // Getter and Setter methods
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        if (id < 0) {
            System.out.println("Warning: ID cannot be negative");
            return;
        }
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = (department != null) ? department.trim() : "General";
    }

    @Override
    public String toString() {
        return String.format("HealthProfessional{id=%d, name='%s', department='%s'}",
                id, name, department);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HealthProfessional that = (HealthProfessional) obj;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
