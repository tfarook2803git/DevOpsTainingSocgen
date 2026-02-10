# System Information Utility

This project contains a Java program to display detailed system information including RAM and disk space details, along with a GitHub Actions pipeline to run it automatically.

## Files

- **SystemInfo.java** - Java source code that retrieves and displays system information
- **.github/workflows/system-info.yml** - GitHub Actions workflow file

## System Information Displayed

### RAM Information
- Total RAM
- Used RAM
- Free RAM
- RAM Usage Percentage

### Disk Space Information
- For each drive:
  - Total Space
  - Usable Space
  - Free Space
  - Used Space
  - Disk Usage Percentage

### Other Information
- OS Name, Version, and Architecture
- Available Processors
- Java Version

## How to Use Locally

### Prerequisites
- Java 11 or higher installed on your system
- Maven 3.6.0 or higher

### Build and Run

```bash
# Build the project
mvn clean package

# Run the program
java -cp target/system-info.jar SystemInfo

# Or run using Maven
mvn exec:java -Dexec.mainClass="SystemInfo"
```

## GitHub Actions Pipeline

The workflow (`system-info.yml`) runs on:
- Every push to main/master branches
- Every pull request to main/master branches
- Daily schedule (00:00 UTC)
- Manual trigger via workflow_dispatch

The pipeline tests the code on:
- Ubuntu (latest)
- Windows (latest)
- macOS (latest)

With Java versions:
- Java 11
- Java 17

### Setup Instructions

1. Push this repository to GitHub
2. Ensure the `.github/workflows/system-info.yml` file is in your repository
3. Navigate to the Actions tab in your GitHub repository
4. The workflow will automatically run on push/pull request or can be manually triggered

## Example Output

```
======== SYSTEM INFORMATION ========

--- RAM INFORMATION ---
Total RAM: 15.94 GB
Used RAM: 8.45 GB
Free RAM: 7.49 GB
RAM Usage: 53.00%

--- DISK SPACE INFORMATION ---
Drive: C:\
  Total Space: 500.00 GB
  Usable Space: 250.00 GB
  Free Space: 250.00 GB
  Used Space: 250.00 GB
  Disk Usage: 50.00%

--- OTHER SYSTEM INFORMATION ---
OS Name: Windows 10
OS Version: 10.0
OS Architecture: x86_64
Available Processors: 8
Java Version: 11.0.15
```

## Notes

- The program uses Java Management Extensions (JMX) to retrieve detailed system information
- Disk information is retrieved for all available drives
- The format bytes function converts bytes to human-readable format (B, KB, MB, GB, TB)
