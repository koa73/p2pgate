package com.p2pgate.vk_gate.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebDriverConfig {

    private String remoutePath;

    @Autowired
    public WebDriverConfig(Environment environment) {

        System.setProperty("webdriver.chrome.driver", environment.getProperty("webdriver.chrome.driver"));
        System.setProperty("webdriver.gecko.driver",environment.getProperty("webdriver.gecko.driver"));
        remoutePath = environment.getProperty("webdriver.remoute");
        //System.setProperty("webdriver.chrome.logfile", "/usr/local/p2p-gate/log/chromedriver.log");
        //System.setProperty("webdriver.chrome.verboseLogging", "true");
    }

    @Bean
    public WebDriver webDriver() throws MalformedURLException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--blink-settings=imagesEnabled=false");
        options.addArguments("--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);

        // for RemoteDriver only
        //DesiredCapabilities dc = DesiredCapabilities.chrome();
        //dc.setCapability(ChromeOptions.CAPABILITY, options);
        //WebDriver driver = new RemoteWebDriver(new URL(remoutePath), dc);
        //
        //WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); // Page load timeout
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS); // JS execute timeout
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }


    @Bean
    public WebDriverWait webDriverWait(WebDriver driver){
        return new WebDriverWait(driver, 5);

    }

}