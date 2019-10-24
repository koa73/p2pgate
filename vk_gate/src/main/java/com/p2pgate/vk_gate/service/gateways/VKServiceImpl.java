package com.p2pgate.vk_gate.service.gateways;

import com.p2pgate.lib.domain.PayAccept;
import com.p2pgate.vk_gate.client.CpgMailClient;
import com.p2pgate.vk_gate.rest.exception.PayApiException;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Qualifier("vk")
public class VKServiceImpl implements GateService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${userenv.url}")
    private String url;

    @Value("${userenv.username}")
    private String username;

    @Value("${userenv.pwd}")
    private String password;

    @Value("${userenv.pin}")
    private String pin;

    @Value("${callback.pay}")
    private String payCallback;

    @Value("${p2p-gateways.mail.callback}")
    private String gateCallback;

    @Autowired
    private WebDriver driver;

    @Autowired
    private  WebDriverWait wait;

    @Autowired
    private CpgMailClient client;


    @Override
    public String refillWalletConfirm(String md, String PaRes, String payment_id) throws PayApiException {

        try {
            final String url = client.sendRefillConfirm(gateCallback+payment_id, md, PaRes);
            final String body = client.getRefillState(url);
            final JSONObject jsonObject = new JSONObject(body);

            if (jsonObject.getString("transaction_status").equals("CHARGED")){

                final Map<String, Object> data = jsonObject.getJSONObject("payment_info").toMap();

                return data.get("amount").toString();

            } else {

                log.error("Payment ID : "+ payment_id + " amount refill not confirmed;/");
                return null;
            }


        } catch (IOException e){
            throw  new PayApiException(url, 500, " Can't send 3DSec refill confirm. "+e.getMessage());

        } catch (InterruptedException e){

            throw  new PayApiException(url, 500, " Can't get refill state. "+e.getMessage());
        }
    }

    @Override
    public String refillWallet(PayAccept request) throws PayApiException {

        try {
            driver.get(url);

            final WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@id=\"quick_login_button\"] | //iframe[@id=\"fXD\"] ")));

            // Login to server
            if (element.getTagName().equals("button")) {

                final WebElement email = driver.findElement(By.id("quick_email"));
                final WebElement pass = driver.findElement(By.id("quick_pass"));
                email.sendKeys(username);
                pass.sendKeys(password);
                element.click();
                driver.switchTo().frame(driver.findElement(By.id("fXD")));

            } else {
                // switch if was logged before
                driver.switchTo().frame(element);
            }

            // Choose wallet refill operation
            driver.findElement(By.className("MainActions_itemRefill_32_5w")).click();
            // Enter operation sum
            driver.findElement(By.className("money-input-form_inputText_1ALFk")).sendKeys(request.getSum().toString());
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[class*='Button_big_2Py5L']"))).click();
            // Send pin code
            driver.findElement(By.className("NumbersInput_item_2-aQb"));
            new Actions(driver).sendKeys(pin).perform();

            // Choose card value frame
            final WebElement i_refill = driver.findElement(By.className("refill_iframe_3iq-M"));
            wait.until(ExpectedConditions.visibilityOf(i_refill));
            driver.switchTo().frame(driver.findElement(By.className("refill_iframe_3iq-M")));

            // Get session data
            final WebElement form = driver.findElement(By.id("cpg_form"));
            final List<WebElement> inputs = form.findElements(
                    By.xpath("//input[@name=\"session_id\"] | //input[@name=\"signature\"]"));

            Map<String, String> secValue = new HashMap<>();
            secValue.put(inputs.get(0).getAttribute("name"), inputs.get(0).getAttribute("value"));
            secValue.put(inputs.get(1).getAttribute("name"), inputs.get(1).getAttribute("value"));

            // Request PaReq values

            String body = client.get3DSReq(secValue.get("session_id"), secValue.get("signature"),
                    client.getSessionUri(request, secValue));

            final JSONObject jsonObject = new JSONObject(body);
            final Map<String, Object> data = jsonObject.getJSONObject("threeds_data").toMap();
            return getPage(data, jsonObject.getString("acs_url"));

        } catch (NoSuchElementException e){

            log.error("VK login page changed markup. "+e);
            throw new PayApiException(request.getPayid(), 500, "Service unavailable.");

        } catch (TimeoutException e){

            log.error("VK login page changed markup. "+e);
            throw new PayApiException(request.getPayid(), 500, "Page anchor access error.");

        } catch (Exception e){

            log.error("VK login page changed markup. -- "+e);
            throw new PayApiException(request.getPayid(), 500, e.getMessage());
        }
    }


    // Prepare user 3DSecure auth page
    private String getPage(Map<String, Object> data, String acs_url){

        final String payID = data.get("TermUrl").toString().replaceFirst(".*(/[^/?]+).*", "$1");
        return  "<html>" +
                "<body OnLoad=\"OnLoadEvent();\">" +
                "<form name=\"mainform\" action=\"" + acs_url + "\" method=\"POST\">" +
                "<input type=\"hidden\" name=\"PaReq\" value=\""+ data.get("PaReq") + "\">" +
                "<input type=\"hidden\" name=\"TermUrl\" value=\""+payCallback+payID+"\">" +
                "<input type=\"hidden\" name=\"MD\" value=\""+ data.get("MD") + "\">" +
                "</form>" +
                "<SCRIPT LANGUAGE=\"Javascript\">" +
                "function OnLoadEvent() {" +
                "document.mainform.submit();" +
                "}" +
                "</SCRIPT>" +
                "</body>" +
                "</html>";
    }

}
