# NutriScoreCalc
Application to calculate nutri score

## Requirements
- Java JDK 21+ installed and JAVA_HOME set
- Maven 3.6+ installed and available on PATH
- A Windows `cmd.exe` prompt (instructions below are cmd.exe examples)

## Build and run (Windows)
The project has been refactored into a single Spring Boot application (root `pom.xml`). Build and run from a `cmd.exe` prompt opened at the project root.

1) Build the project (from project root):

```cmd
mvn clean package
```

If you prefer to skip tests during build for speed:

```cmd
mvn -DskipTests clean package
```

2) Run the produced JAR. The root artifact produced by the POM is named `NutriScoreCalc-1.0-SNAPSHOT.jar` and is created under `target`:

```cmd
java -jar target\NutriScoreCalc-1.0-SNAPSHOT.jar
```

To run on a different port (example `9090`):

```cmd
java -jar target\NutriScoreCalc-1.0-SNAPSHOT.jar --server.port=9090
```

Alternative: run directly with Maven (development mode):

```cmd
mvn spring-boot:run
```

Where to access the app after it starts:
- Web UI: http://localhost:8080/
- REST API base: http://localhost:8080/api/products

Notes & troubleshooting
- On first build Maven will download dependencies; this may take a few minutes.
- If the JAR name differs (version changed), check `target\` for the produced artifact and use that filename.
- Ensure `JAVA_HOME` points to a JDK 21+ installation. Run `java -version` and `mvn -v` to verify.
- If you previously built module artifacts (the project used to be multi-module), those module POMs are no longer used by the merged build—build the root project as shown above.
