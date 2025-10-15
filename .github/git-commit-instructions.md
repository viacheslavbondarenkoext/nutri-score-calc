# Commit message format

Provide a well-structured commit message that clearly states the main action, a short summary, and a detailed description. This helps reviewers and changelogs.

Required format (subject + body):

<Main Action>: <Short summary>

<Detailed description>

Rules:
- <Main Action> should be a single verb describing the primary action, for example: Added, Fixed, Updated, Refactored, Removed, Docs, Chore, Rename, Build.
- <Short summary> is a concise (preferably <= 50 chars) description of what changed.
- Leave a blank line between the subject (first line) and the body.
- The detailed description can be multiple paragraphs and should explain what changed, why, and any important consequences (breaking changes, migration steps).
- If applicable, include references to issue/PR numbers and testing notes.

Template (copy & fill):

Refactor: Merge modules into single Spring Boot application

What:
- Short summary of the change in one or two lines.

Why:
- Reasoning and motivation for the change.

Impact / Notes:
- Any breaking changes, migrations, or compatibility notes.
- How to verify or test the change.

Related:
- Closes #123, Fixes #456 (if applicable)

Example commit messages:

Added: Add NutriScore calculator service

What:
- Introduced a new service that computes NutriScore for products.

Why:
- Needed to centralize calculation logic and make it reusable by the API.

Impact / Notes:
- No breaking changes. Unit tests added.

Related:
- Closes #78

Fixes: Update README with Windows build/run instructions

What:
- Rewrote the README section showing how to build and run the jar on Windows.

Why:
- Previous instructions referenced a multi-module layout; the project was merged into a single module.

Impact / Notes:
- Users should now build the root project with `mvn clean package` and run the produced `target\NutriScoreCalc-1.0-SNAPSHOT.jar`.

Related:
- N/A

Tips:
- Keep the subject line imperative and concise (e.g., "Add", "Fix", "Refactor").
- Use the body to explain reasoning, not to repeat code changes.
- When in doubt, include a short "How to test" subsection in the body.
