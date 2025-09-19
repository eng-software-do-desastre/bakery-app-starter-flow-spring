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
 * Entry point of the Bakery application.
 * <p>
 * This class configures and launches the Spring Boot application.
 * It also serves as a servlet initializer when the application is deployed
 * in a traditional servlet container (WAR deployment).
 * </p>
 *
 * <h2>Configuration details:</h2>
 * <ul>
 *   <li>
 *     {@link SpringBootApplication} – Configures component scanning, auto-configuration,
 *     and allows explicit package scanning by providing base classes
 *     such as {@link SecurityConfiguration}, {@link MainView},
 *     {@link Application}, and {@link UserService}.
 *     The {@link ErrorMvcAutoConfiguration} is excluded to disable the
 *     default Spring Boot error handling.
 *   </li>
 *   <li>
 *     {@link EnableJpaRepositories} – Enables Spring Data JPA repositories,
 *     restricted to the package of {@link UserRepository}.
 *   </li>
 *   <li>
 *     {@link EntityScan} – Ensures JPA entities are discovered, restricted
 *     to the package of {@link User}.
 *   </li>
 * </ul>
 *
 * <h2>Deployment modes:</h2>
 * <ul>
 *   <li>Standalone application: started with the {@link #main(String[])} method.</li>
 *   <li>Servlet container deployment: initialized through
 *       {@link #configure(SpringApplicationBuilder)}.</li>
 * </ul>
 */
@SpringBootApplication(scanBasePackageClasses = {
        SecurityConfiguration.class,
        MainView.class,
        Application.class,
        UserService.class
}, exclude = ErrorMvcAutoConfiguration.class)
@EnableJpaRepositories(basePackageClasses = { UserRepository.class })
@EntityScan(basePackageClasses = { User.class })
public class Application extends SpringBootServletInitializer {

    /**
     * Main entry point when running the application as a standalone
     * Spring Boot application.
     *
     * @param args command-line arguments passed at startup
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Configures the application when deployed to a servlet container.
     * <p>
     * This method is invoked automatically when the application is packaged
     * as a WAR and deployed to external application servers like Tomcat or Jetty.
     * </p>
     *
     * @param application the {@link SpringApplicationBuilder} used to configure the app
     * @return the configured application builder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
