package controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DateContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String currentDate = now.format(dateFormatter);
        String currentTime = now.format(timeFormatter);

        sce.getServletContext().setAttribute("currentDate", currentDate);
        sce.getServletContext().setAttribute("currentTime", currentTime);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
