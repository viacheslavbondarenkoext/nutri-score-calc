# NutriScoreCalc
Application to calculate nutri score

## Requirements
- Java JDK 21+ installed and JAVA_HOME set
- Maven 3.6+ installed and available on PATH
- A Windows `cmd.exe` prompt (instructions below are cmd.exe examples)

## Build and run (Windows)
The project can be built and run in two common layouts. Pick the section that matches your repository layout.

### 1) Single-artifact (current merged Spring Boot application)
Build and run from a `cmd.exe` prompt opened at the project root.

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


### 2) Multi-module / legacy layout (module artifacts)
If your repository uses the older multi-module layout (modules in subfolders such as `controller`, `database`, `logic`, `web-spring`), use one of the options below to build and run the runnable module.

Notes: In a multi-module project typically only the web module (for example `web-spring`) produces an executable Spring Boot JAR. Other modules (`database`, `logic`, `controller`) are often libraries and will produce non-executable JARs in their `target/` folders.

Option A — build and run from the module directory (simplest):

```cmd
cd web-spring
mvn clean package
java -jar target\web-spring-1.0-SNAPSHOT.jar
```

Option B — build the web module (and its module dependencies) from the project root without changing directories:

```cmd
mvn -pl web-spring -am clean package
java -jar web-spring\target\web-spring-1.0-SNAPSHOT.jar
```

(Explanation: `-pl web-spring` selects the `web-spring` module; `-am` also builds modules it depends on.)

If the produced artifact name or version differs, locate the JAR with:

```cmd
dir web-spring\target\*.jar
```

If the web module does not produce an executable (fat) jar you can run it with Maven:

```cmd
mvn -pl web-spring spring-boot:run
```

or run the module's main class with `java -cp` (advanced — only if you know the main class and classpath layout).


### 3) Quarkus-based module (if applicable)
If one of your modules is a Quarkus application (for example `web-quarkus`), use the commands below. These are Windows `cmd.exe` examples and assume the runnable module is named `web-quarkus` — if your module has a different name, substitute it accordingly.

Quick development (fast rebuilds / hot reload):

```cmd
cd web-quarkus
mvn quarkus:dev
```

Build for JVM (produces a runnable jar or a quarkus-app directory):

```cmd
cd web-quarkus
mvn -DskipTests clean package
```

How to run the packaged app (artifact layout may vary by Quarkus packaging):
- Default (quarkus-app layout):

```cmd
java -jar target\quarkus-app\quarkus-run.jar
```

- Legacy / single runner jar (some setups):

```cmd
java -jar target\*-runner.jar
```

If you don't know the exact filename, list the target directory to find the produced artifact:

```cmd
dir web-quarkus\target\*.jar
dir web-quarkus\target\quarkus-app\*
```

Build a native executable (optional — requires GraalVM/native toolchain or the Quarkus cloud/native build service):

```cmd
cd web-quarkus
mvn -DskipTests -Pnative package
```

If the native build succeeds you'll get an executable under `target` (Windows: `target\yourapp-runner.exe`) — run it directly:

```cmd
target\yourapp-runner.exe
```

Notes for Quarkus
- `mvn quarkus:dev` is the fastest way to iterate during development.
- Native builds require extra toolchain setup (GraalVM native-image) or a remote/native builder; expect longer build times and additional prerequisites.
- If your project is multi-module and the Quarkus module depends on internal modules, build from the repository root with `mvn -pl web-quarkus -am clean package` (similar to the multi-module Maven examples above).

Notes & troubleshooting
- On first build Maven will download dependencies; this may take a few minutes.
- If the JAR name differs (version changed), check the module `target\` directory and use that filename.
- Ensure `JAVA_HOME` points to a JDK 21+ installation. Run `java -version` and `mvn -v` to verify.
- If you have both a merged root artifact and module artifacts in the repo, prefer the layout that matches how the project was cloned or prepared locally (single artifact vs multi-module). If you are unsure, `mvn -v` and inspecting the top-level `pom.xml` will indicate whether the project is an aggregator or a single Spring Boot application.
