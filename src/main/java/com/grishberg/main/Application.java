package com.grishberg.main;

/**
 * Created by grishberg on 23.02.17.
 * main application
 */

import com.grishberg.services.accounts.AccountService;
import com.grishberg.services.accounts.AccountServiceInMemoryImpl;
import com.grishberg.services.sync.SprintService;
import com.grishberg.services.sync.SyncServiceMemoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.grishberg")
public class Application {
    public static void main(String[] args) {
        log.info("--------------");
        log.info("Poker planning server v.1.0.2");
        log.info("--------------");
        SpringApplication.run(Application.class, args);
    }

    //---- DI ------

    @Bean(name = "accountService")
    public AccountService accountService() {
        return new AccountServiceInMemoryImpl();
    }

    @Bean(name = "syncService")
    @Autowired
    public SprintService syncService() {
        return new SyncServiceMemoryImpl();
    }
}
