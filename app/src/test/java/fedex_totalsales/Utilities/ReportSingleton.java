package fedex_totalsales.Utilities;

import com.relevantcodes.extentreports.ExtentReports;
import java.io.File;

public class ReportSingleton {
    private static ExtentReports report;

    private ReportSingleton() {}

    public static ExtentReports getReport() {
        if (report == null) {
            report = new ExtentReports(System.getProperty("user.dir") + "/report/FedexReport.html", true);
            report.loadConfig(new File(System.getProperty("user.dir") + "/extent_customization_configs.xml"));
        }
        return report;
    }

    public static void closeReport() {
        if (report != null) {
            report.flush();
            report.close();
            report = null; // Reset the report instance to avoid reuse
        } else {
            System.err.println("Warning: Report instance is already null. Nothing to close.");
        }
    }

}