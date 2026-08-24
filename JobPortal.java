import java.util.ArrayList;
import java.util.Scanner;

class Job {

    int jobId;
    String title;
    String company;
    String location;
    double salary;
    String jobType;
    String skills;

    Job(int jobId, String title, String company,
        String location, double salary,
        String jobType, String skills) {

        this.jobId = jobId;
        this.title = title;
        this.company = company;
        this.location = location;
        this.salary = salary;
        this.jobType = jobType;
        this.skills = skills;
    }

    void displayJob() {

        System.out.println("\n------------------------------");
        System.out.println("Job ID      : " + jobId);
        System.out.println("Job Title   : " + title);
        System.out.println("Company     : " + company);
        System.out.println("Location    : " + location);
        System.out.println("Salary      : Rs." + salary);
        System.out.println("Job Type    : " + jobType);
        System.out.println("Skills      : " + skills);
        System.out.println("------------------------------");
    }
}

class Application {

    String candidateName;
    int jobId;
    String jobTitle;
    String status;

    Application(String candidateName, int jobId,
                String jobTitle) {

        this.candidateName = candidateName;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.status = "Applied";
    }

    void displayApplication() {

        System.out.println("\n------------------------------");
        System.out.println("Candidate : " + candidateName);
        System.out.println("Job ID    : " + jobId);
        System.out.println("Job       : " + jobTitle);
        System.out.println("Status    : " + status);
        System.out.println("------------------------------");
    }
}

public class JobPortal {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Job> jobs = new ArrayList<>();
    static ArrayList<Application> applications = new ArrayList<>();

    static int nextJobId = 1;

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("          JOB PORTAL SYSTEM");
        System.out.println("====================================");

        while (true) {

            System.out.println("\n1. Post Job");
            System.out.println("2. View All Jobs");
            System.out.println("3. Search Job");
            System.out.println("4. Apply for Job");
            System.out.println("5. View Applications");
            System.out.println("6. Update Application Status");
            System.out.println("7. Exit");

            System.out.print("\nEnter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {

                case 1:
                    postJob();
                    break;

                case 2:
                    viewJobs();
                    break;

                case 3:
                    searchJob();
                    break;

                case 4:
                    applyForJob();
                    break;

                case 5:
                    viewApplications();
                    break;

                case 6:
                    updateApplicationStatus();
                    break;

                case 7:
                    System.out.println(
                            "\nThank you for using Job Portal!"
                    );
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void postJob() {

        System.out.println("\n========== POST NEW JOB ==========");

        System.out.print("Enter job title: ");
        String title = sc.nextLine();

        System.out.print("Enter company name: ");
        String company = sc.nextLine();

        System.out.print("Enter location: ");
        String location = sc.nextLine();

        System.out.print("Enter salary: ");

        double salary;

        try {
            salary = Double.parseDouble(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid salary!");
            return;
        }

        System.out.print("Enter job type: ");
        String jobType = sc.nextLine();

        System.out.print("Enter required skills: ");
        String skills = sc.nextLine();

        Job job = new Job(
                nextJobId,
                title,
                company,
                location,
                salary,
                jobType,
                skills
        );

        jobs.add(job);

        System.out.println("\nJob posted successfully!");
        System.out.println("Job ID: " + nextJobId);

        nextJobId++;
    }

    static void viewJobs() {

        System.out.println("\n========== AVAILABLE JOBS ==========");

        if (jobs.isEmpty()) {
            System.out.println("No jobs available.");
            return;
        }

        for (Job job : jobs) {
            job.displayJob();
        }
    }

    static void searchJob() {

        System.out.println("\n========== SEARCH JOB ==========");

        System.out.print("Enter job title or skill: ");
        String search = sc.nextLine().toLowerCase();

        boolean found = false;

        for (Job job : jobs) {

            if (job.title.toLowerCase().contains(search)
                    || job.skills.toLowerCase().contains(search)
                    || job.company.toLowerCase().contains(search)) {

                job.displayJob();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching jobs found.");
        }
    }

    static void applyForJob() {

        System.out.println("\n========== APPLY FOR JOB ==========");

        if (jobs.isEmpty()) {
            System.out.println("No jobs available.");
            return;
        }

        System.out.print("Enter your name: ");
        String candidateName = sc.nextLine();

        System.out.print("Enter Job ID: ");

        int jobId;

        try {
            jobId = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Job ID!");
            return;
        }

        Job selectedJob = null;

        for (Job job : jobs) {

            if (job.jobId == jobId) {
                selectedJob = job;
                break;
            }
        }

        if (selectedJob == null) {
            System.out.println("Job not found!");
            return;
        }

        Application application = new Application(
                candidateName,
                selectedJob.jobId,
                selectedJob.title
        );

        applications.add(application);

        System.out.println(
                "Application submitted successfully!"
        );
    }

    static void viewApplications() {

        System.out.println("\n========== APPLICATIONS ==========");

        if (applications.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }

        for (Application application : applications) {
            application.displayApplication();
        }
    }

    static void updateApplicationStatus() {

        System.out.println(
                "\n========== UPDATE APPLICATION =========="
        );

        if (applications.isEmpty()) {
            System.out.println("No applications available.");
            return;
        }

        for (int i = 0; i < applications.size(); i++) {

            System.out.println(
                    (i + 1) + ". "
                    + applications.get(i).candidateName
                    + " - "
                    + applications.get(i).jobTitle
                    + " - "
                    + applications.get(i).status
            );
        }

        System.out.print("\nSelect application number: ");

        int number;

        try {
            number = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid number!");
            return;
        }

        if (number < 1 || number > applications.size()) {
            System.out.println("Invalid application!");
            return;
        }

        Application application = applications.get(number - 1);

        System.out.println("\n1. Applied");
        System.out.println("2. Shortlisted");
        System.out.println("3. Rejected");

        System.out.print("Enter new status: ");

        int status;

        try {
            status = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid choice!");
            return;
        }

        switch (status) {

            case 1:
                application.status = "Applied";
                break;

            case 2:
                application.status = "Shortlisted";
                break;

            case 3:
                application.status = "Rejected";
                break;

            default:
                System.out.println("Invalid status!");
                return;
        }

        System.out.println("Application status updated!");
    }
}