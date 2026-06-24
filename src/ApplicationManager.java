import java.io.*;
import java.util.*;

public class ApplicationManager {
    private List<Applicant> applicants;
    private static final String DATA_FILE = "applicants.ser";
    private static final String LOG_FILE = "applications.txt";

    public ApplicationManager() {
        applicants = new ArrayList<>();
    }

    public void submitApplication(Applicant applicant, String jobTitle) throws InvalidApplicationException {
        if (applicant == null || jobTitle == null  ||jobTitle.isEmpty()) {
            throw new InvalidApplicationException("Invalid application data!");
        }
        if (applicant.getAge() < 18) {
            throw new InvalidApplicationException("Age must be 18 or above!");
        }

        applicant.setAppliedJob(jobTitle);
        applicants.add(applicant);
        sendConfirmation(applicant.getEmail(), applicant.getName(), jobTitle);
    }

    public static void sendConfirmation(String email, String name, String job) {
        System.out.println("\n========================================");
        System.out.println("📧 NOTIFICATION SENT TO: " + email);
        System.out.println("========================================");
        System.out.println("Dear " + name + ",");
        System.out.println("Your application for " + job + " was successful!");
        System.out.println("Please stand by for further notifications.");
        System.out.println("Thank you for applying!");
        System.out.println("========================================\n");
    }

    public void saveData() {
        ObjectOutputStream oos = null;
        PrintWriter pw = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE));
            oos.writeObject(applicants);

            pw = new PrintWriter(new FileWriter(LOG_FILE));
            pw.println("=== Job Applications Log ===");
            pw.println("Total Applicants: " + applicants.size());
            pw.println();

            for (Applicant app : applicants) {
                pw.println("Name: " + app.getName());
                pw.println("Email: " + app.getEmail());
                pw.println("Age: " + app.getAge());
                pw.println("Job: " + app.getAppliedJob());
                pw.println("Description: " + app.getDescription());
                pw.println("Portfolio: " + app.getPortfolioLink());
                pw.println("---");
            }

            System.out.println("\n✅ Data saved successfully!");

        } catch (IOException e) {
            System.err.println("❌ Error saving: " + e.getMessage());
        } finally {
            try {
                if (oos != null) oos.close();
                if (pw != null) pw.close();
            } catch (IOException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void loadData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("No existing data found.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            applicants = (List<Applicant>) ois.readObject();
            System.out.println("Data loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }

    public void displayApplicants() {
        if (applicants.isEmpty()) {
            System.out.println("\nNo applicants yet.");
            return;
        }
        System.out.println("\n=== All Applicants ===");
        for (Applicant app : applicants) {
            app.displayInfo();
            System.out.println("------------------------");
        }
    }
}