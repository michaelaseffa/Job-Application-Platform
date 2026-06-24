import java.io.Serializable;

public class Applicant extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String description;
    private String portfolioLink;
    private String appliedJob;

    public Applicant(String name, String email) {
        super(name, email);
        this.description = "";
        this.portfolioLink = "";
        this.appliedJob = "";
    }

    public Applicant(String name, String email, int age, String description, String portfolioLink) {
        super(name, email, age);
        this.description = description;
        this.portfolioLink = portfolioLink;
        this.appliedJob = "";
    }

    @Override
    public void displayInfo() {
        System.out.println("\n--- Applicant Details ---");
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Age: " + getAge());
        System.out.println("Description: " + (description.isEmpty() ? "Not provided" : description));
        System.out.println("Portfolio: " + (portfolioLink.isEmpty() ? "Not provided" : portfolioLink));
        System.out.println("Applied for: " + (appliedJob.isEmpty() ? "None" : appliedJob));
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getPortfolioLink() { return portfolioLink; }
    public void setPortfolioLink(String portfolioLink) { this.portfolioLink = portfolioLink; }
    public String getAppliedJob() { return appliedJob; }
    public void setAppliedJob(String appliedJob) { this.appliedJob = appliedJob; }
}