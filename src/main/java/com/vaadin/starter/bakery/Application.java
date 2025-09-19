package com.vaadin.starter.bakery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.vaadin.starter.bakery.app.security.SecurityConfiguration;
import com.vaadin.starter.bakery.backend.data.entity.User;
import com.vaadin.starter.bakery.backend.repositories.UserRepository;
import com.vaadin.starter.bakery.backend.service.UserService;
import com.vaadin.starter.bakery.ui.MainView;

/**
 * Entry point of the Vaadin Bakery application.
 * <p>
 * This class bootstraps the Spring Boot application, configures
 * JPA repositories, entity scanning, and sets up the servlet initializer
 * for deployment to external servlet containers.
 * </p>
 *
 * <h2>Configuration</h2>
 * <ul>
 *   <li>Scans for Spring components in {@link SecurityConfiguration},
 *       {@link MainView}, {@link Application}, and {@link UserService} packages.</li>
 *   <li>Enables JPA repositories defined in {@link UserRepository}.</li>
 *   <li>Scans for JPA entities in {@link User}.</li>
 *   <li>Excludes the default {@link ErrorMvcAutoConfiguration} since
 *       Vaadin provides its own error handling.</li>
 * </ul>
 *
 * <h2>Deployment</h2>
 * <p>
 * Supports both embedded (standalone JAR) and external servlet container (WAR)
 * deployments through {@link SpringBootServletInitializer}.
 * </p>
 */
@SpringBootApplication(
    scanBasePackageClasses = {
        SecurityConfiguration.class,
        MainView.class,
        Application.class,
        UserService.class
    },
    exclude = ErrorMvcAutoConfiguration.class
)
@EnableJpaRepositories(basePackageClasses = { UserRepository.class })
@EntityScan(basePackageClasses = { User.class })
public class Application extends SpringBootServletInitializer {

    /**
     * Main method that launches the Spring Boot application.
     *
     * @param args command-line arguments passed at startup
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Configures the application when deployed as a WAR file to an
     * external servlet container.
     *
     * @param application the {@link SpringApplicationBuilder} used to configure the app
     * @return the application builder with the sources set to this application class
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
