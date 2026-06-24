import java.io.*;
import java.util.*;

public class JobDatabase {
    private Map<String, List<String>> jobs;
    private static final String FILE_NAME = "jobs.ser";

    public JobDatabase() {
        jobs = new HashMap<>();
        initializeJobs();
    }

    private void initializeJobs() {
        List<String> softwareJobs = new ArrayList<>();
        softwareJobs.add("Full Stack Developer");
        softwareJobs.add("Mobile Developer");
        softwareJobs.add("DevOps Engineer");

        List<String> civilJobs = new ArrayList<>();
        civilJobs.add("Structural Engineer");
        civilJobs.add("Transportation Engineer");
        civilJobs.add("Environmental Engineer");

        List<String> mechanicalJobs = new ArrayList<>();
        mechanicalJobs.add("Automotive Engineer");
        mechanicalJobs.add("Aerospace Engineer");
        mechanicalJobs.add("Robotics Engineer");

        jobs.put("Software Engineering", softwareJobs);
        jobs.put("Civil Engineering", civilJobs);
        jobs.put("Mechanical Engineering", mechanicalJobs);
    }

    public List<String> getCategories() {
        return new ArrayList<>(jobs.keySet());
    }

    public List<String> getJobsByCategory(String category) {
        List<String> result = jobs.get(category);
        if (result == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(result);
    }

    public void saveToFile() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(jobs);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                jobs = (Map<String, List<String>>) ois.readObject();
            }
        }
    }
}