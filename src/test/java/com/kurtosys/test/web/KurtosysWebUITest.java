
package com.kurtosys.test.web;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Portia.Siluma
 */
public class KurtosysWebUITest {
     private WebDriver webDriver;

    @Before
    public void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        webDriver = new ChromeDriver(chromeOptions);
    }

    @Test
    public void testFillForm() {
        webDriver.get("https://www.kurtosys.com/");
        WebElement resourcesLink
                = webDriver.findElement(By.id("kurtosys-menu-item-59810"));

        Actions actions = new Actions(webDriver);
        actions.moveToElement(resourcesLink);

        WebElement whitePapersLink
                = webDriver.findElement(By.xpath("//*[@id=\"kurtosys-menu-item-59810\"]/div/div/div/div/section/div/div/div[2]/div/div/div[2]/div/ul/li[4]/a"));
        actions.moveToElement(whitePapersLink);
        actions.click().build().perform();

        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 2);
        webDriverWait.until(ExpectedConditions.titleContains("White Papers"));

        WebElement ucitsWhitePaper = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/section[2]/div/div/div/div/div/div/div/div/article[3]/div/div[1]/p/a"));
        actions.moveToElement(ucitsWhitePaper);
        actions.click().build().perform();

        webDriver.switchTo().frame(0);

        WebElement firstNameInput = webDriver.findElement(By.id("18882_234474pi_18882_234474"));
        WebElement lastNameInput = webDriver.findElement(By.id("18882_234476pi_18882_234476"));
        WebElement companyInput = webDriver.findElement(By.id("18882_234480pi_18882_234480"));
        WebElement industryInput = webDriver.findElement(By.id("18882_234482pi_18882_234482"));

        webDriverWait.until(ExpectedConditions.elementToBeClickable(firstNameInput)).sendKeys("Portia");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(lastNameInput)).sendKeys("Siluma");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(companyInput)).sendKeys("Kurtosys");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(industryInput)).sendKeys("Information Technology");

        WebElement sendMeCopyButton = webDriver.findElement(By.xpath("/html/body/form/p[2]"));
        sendMeCopyButton.submit();

        takeScreeenshot();

        WebElement error = webDriver.findElement(By.cssSelector(".error"));
        String actualErrorMessage = error.getText();
        String expectedErrorMessage = "This field is required.";

        assertTrue(actualErrorMessage.contains(expectedErrorMessage));
    }

    private void takeScreeenshot() {
        File errorScreenShot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        String path = "src/test/screenshots/submitform_error.png";

        try {
            FileUtils.copyFile(errorScreenShot, new File(path));
        } catch (IOException e) {
            fail("Failed to capture screenshot"); 
        }

    }
}
