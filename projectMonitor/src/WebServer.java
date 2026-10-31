
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class WebServer {

    public static void main(String[] args) throws IOException {

        // Create project manager and sample projects
        ProjectManager projectManager = new ProjectManager();

       projectManager.addProject(new Project(101, "Project Monitor",
    "Academic Project Management System", "In Progress", 60, "2026-11-15"));

projectManager.addProject(new Project(102, "Library Management System",
    "Manage library books and student records", "Pending", 30, "2026-11-25"));

        AtomicInteger nextProjectId = new AtomicInteger(103);

        HttpServer server = HttpServer.create(
            new InetSocketAddress(8080), 0
        );


        server.createContext("/projects", exchange -> {

            if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                sendResponse(exchange, 405, "Method not allowed");
                return;
            }

            String formData = new String(
                exchange.getRequestBody().readAllBytes(),
                StandardCharsets.UTF_8
            );

            Map<String, String> form = parseFormData(formData);

            String name = form.getOrDefault("name", "").trim();
            String description = form.getOrDefault("description", "").trim();
            String deadline = form.getOrDefault("deadline", "").trim();

            if (name.isEmpty() || description.isEmpty() || deadline.isEmpty()) {
                sendResponse(exchange, 400, "Please fill in all project fields.");
                return;
            }

       Project project = new Project(
    nextProjectId.getAndIncrement(),
    name,
    description,
    "Pending",
    0,
    deadline
);

            projectManager.addProject(project);

System.out.println("New project added: " + name);
System.out.println("Deadline: " + deadline);
System.out.println("Total projects now: " + projectManager.getProjects().size());

exchange.getResponseHeaders().set("Location", "/projects.html");
exchange.sendResponseHeaders(303, -1);
exchange.close();
});
        
        server.createContext("/", exchange -> {

            String path = exchange.getRequestURI().getPath();

            if (path.equals("/")) {
                path = "/index.html";
            }

            if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                sendResponse(exchange, 405, "Method not allowed");
                return;
            }

            Path publicDirectory = Path.of("public")
                .toAbsolutePath().normalize();

            Path file = publicDirectory.resolve(
                path.substring(1)
            ).normalize();

            if (!file.startsWith(publicDirectory)) {
                sendResponse(exchange, 403, "Access denied");
                return;
            }

            if (!Files.exists(file) || Files.isDirectory(file)) {
                sendResponse(exchange, 404, "Page not found");
                return;
            }

            String contentType = "text/html; charset=UTF-8";

            if (path.endsWith(".css")) {
                contentType = "text/css; charset=UTF-8";
            }

            String pageContent = Files.readString(
                file, StandardCharsets.UTF_8
            );

            if (path.equals("/projects.html")) {

                StringBuilder rows = new StringBuilder();

                for (Project project : projectManager.getProjects()) {

                    rows.append("<tr>");

                    rows.append("<td>")
                        .append(escapeHtml(project.getProjectName()))
                        .append("</td>");

                    rows.append("<td>")
                        .append(escapeHtml(project.getDescription()))
                        .append("</td>");

                    rows.append("<td><span class=\"badge blue-badge\">")
                        .append(escapeHtml(project.getStatus()))
                        .append("</span></td>");

                    rows.append("<td>")
                        .append(project.getProgress())
                        .append("%")
                        .append("<div class=\"mini-progress\">")
                        .append("<span style=\"width:")
                        .append(project.getProgress())
                        .append("%\"></span></div>")
                        .append("</td>");

                    rows.append("<td>")
    .append(escapeHtml(project.getDeadline()))
    .append("</td>");
                    rows.append("</tr>");
                }

                pageContent = pageContent.replace(
                    "<!-- PROJECT_ROWS -->",
                    rows.toString()
                );
            }

            byte[] content = pageContent.getBytes(StandardCharsets.UTF_8);

            exchange.getResponseHeaders().set(
                "Content-Type", contentType
            );

            exchange.sendResponseHeaders(200, content.length);

            try (OutputStream output = exchange.getResponseBody()) {
                output.write(content);
            }
        });

        server.start();

        System.out.println("Project Monitor is running!");
        System.out.println("Open http://localhost:8080");
        System.out.println("\n===== PROJECTS LOADED =====");
        projectManager.displayProjects();
    }

    private static Map<String, String> parseFormData(String formData)
            throws IOException {

        Map<String, String> form = new HashMap<>();

        for (String pair : formData.split("&")) {

            String[] parts = pair.split("=", 2);

            String key = URLDecoder.decode(
                parts[0], StandardCharsets.UTF_8
            );

            String value = parts.length > 1
                ? URLDecoder.decode(parts[1], StandardCharsets.UTF_8)
                : "";

            form.put(key, value);
        }

        return form;
    }

    private static String escapeHtml(String value) {

        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }

    private static void sendResponse(
            HttpExchange exchange, int status, String message)
            throws IOException {

        byte[] content = message.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set(
            "Content-Type", "text/plain; charset=UTF-8"
        );

        exchange.sendResponseHeaders(status, content.length);

        try (OutputStream output = exchange.getResponseBody()) {
            output.write(content);
        }
    }
}