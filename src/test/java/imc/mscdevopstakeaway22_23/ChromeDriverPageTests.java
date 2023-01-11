package imc.mscdevopstakeaway22_23;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.bonigarcia.wdm.WebDriverManager;


import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChromeDriverPageTests {

    //    private WebDriver webDriver;
//
//    driver = new ChromeDriver();
    @Value("${local.server.port}")
    private int port;

    //    an alternative really good resource:   https://github.com/bonigarcia/webdrivermanager
    WebDriver webDriver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-debugging-port=42227");
        options.addArguments("--headless");
        webDriver = new ChromeDriver(options);
    }

    @AfterEach
    void teardown() {
        webDriver.quit();
    }





    @Test
    public void testingPageContents() {



//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\scmimc\\Documents\\Tools\\chromedriver_win32\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-debugging-port=42227");
//        options.addArguments("--headless");
//        this.webDriver = new ChromeDriver(options);

//        WebDriverManager.firefoxdriver().setup();
//        webDriver = new FirefoxDriver();

        this.webDriver.get("http://localhost:" + Integer.toString(port) + "/Home");
        assertTrue(webDriver.findElement(By.cssSelector("main h1")).getText().contains("HomePage"));

        this.webDriver.get("http://localhost:" + Integer.toString(port) + "/Admin/AddItem");
        this.webDriver.findElement(By.name("username")).sendKeys("user");
        this.webDriver.findElement(By.name("password")).sendKeys("password");
        this.webDriver.findElement(By.id("SignInButton")).click();
        assertTrue(webDriver.findElement(By.cssSelector("main h1")).getText().contains("ADMIN Pages"));

//        this.webDriver.get("http://localhost:" + Integer.toString(port) + "/Admin/AddItem");
        this.webDriver.findElement(By.name("name")).sendKeys("sausage");
        this.webDriver.findElement(By.name("description")).sendKeys("long and thin");
        this.webDriver.findElement(By.name("price")).sendKeys("20");
        assertTrue(webDriver.findElements(By.xpath("/html/body/main/form/button")).size() > 0);
        this.webDriver.findElement(By.xpath("/html/body/main/form/button")).click();
        this.webDriver.get("http://localhost:" + Integer.toString(port) + "/Menu");
        assertTrue(webDriver.findElement(By.cssSelector("main table")).getText().contains("sausage"));

        this.webDriver.get("http://localhost:" + Integer.toString(port) + "/Menu");
        assertTrue(webDriver.findElement(By.cssSelector("table")).getText().contains("mockDBChips"));

    }


}
