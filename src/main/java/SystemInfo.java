import java.io.File;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class SystemInfo {
    public static void main(String[] args) {
        // Get Operating System MXBean for detailed system information
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        
        // Get Runtime for memory information
        Runtime runtime = Runtime.getRuntime();
        
        System.out.println("======== SYSTEM INFORMATION ========");
        System.out.println();
        
        // RAM Information
        System.out.println("--- RAM INFORMATION ---");
        long totalMemory = osBean.getTotalPhysicalMemorySize();
        long freeMemory = osBean.getFreePhysicalMemorySize();
        long usedMemory = totalMemory - freeMemory;
        
        System.out.println("Total RAM: " + formatBytes(totalMemory));
        System.out.println("Used RAM: " + formatBytes(usedMemory));
        System.out.println("Free RAM: " + formatBytes(freeMemory));
        System.out.println("RAM Usage: " + String.format("%.2f%%", (usedMemory * 100.0) / totalMemory));
        System.out.println();
        
        // Disk Space Information
        System.out.println("--- DISK SPACE INFORMATION ---");
        File[] roots = File.listRoots();
        for (File root : roots) {
            System.out.println("Drive: " + root.getAbsolutePath());
            System.out.println("  Total Space: " + formatBytes(root.getTotalSpace()));
            System.out.println("  Usable Space: " + formatBytes(root.getUsableSpace()));
            System.out.println("  Free Space: " + formatBytes(root.getFreeSpace()));
            long usedSpace = root.getTotalSpace() - root.getFreeSpace();
            System.out.println("  Used Space: " + formatBytes(usedSpace));
            System.out.println("  Disk Usage: " + String.format("%.2f%%", (usedSpace * 100.0) / root.getTotalSpace()));
            System.out.println();
        }
        
        // Other System Information
        System.out.println("--- OTHER SYSTEM INFORMATION ---");
        System.out.println("OS Name: " + System.getProperty("os.name"));
        System.out.println("OS Version: " + System.getProperty("os.version"));
        System.out.println("OS Architecture: " + System.getProperty("os.arch"));
        System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println();
    }
    
    // Helper method to format bytes into human-readable format
    private static String formatBytes(long bytes) {
        if (bytes <= 0) return "0 B";
        final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(bytes) / Math.log10(1024));
        return String.format("%.2f %s", bytes / Math.pow(1024, digitGroups), units[digitGroups]);
    }
}
