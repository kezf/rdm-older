package org.miser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动程序
 *
 * @author Barry
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("org.miser.**.mapper")
public class Application {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Application.class, args);
        System.out.println(
                "  ______  ____  __  __   ____ _____  _    ____ _______\n" +
                " / /  _ \\|  _ \\|  \\/  | / ___|_   _|/ \\  |  _ \\_   _\\ \\\n" +
                "/ /| |_) | | | | |\\/| | \\___ \\ | | / _ \\ | |_) || |  \\ \\\n" +
                "\\ \\|  _ <| |_| | |  | |  ___) || |/ ___ \\|  _ < | |  / /\n" +
                " \\_\\_| \\_\\____/|_|  |_| |____/ |_/_/   \\_\\_| \\_\\|_| /_/\n");
    }
}