package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTests {

    String firstName = "Lana";

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void successfulRegistrationTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        $("#firstName").val(firstName);
        $("#lastName").val("Zaklinska");
        $("#userEmail").val("lana@zaklinska.com");
        $("[name=gender][value=Female]").parent().click();
        $("#userNumber").val("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2005");
        $(".react-datepicker__day--028:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).scrollTo().click();
        //$("#uploadPicture").uploadFile(new File("src/test/resources//img/Sea.JPG"));
        $("#uploadPicture").uploadFromClasspath("./img/Sea.JPG");
        $("#currentAddress").setValue("Some street address, 1");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").scrollIntoView(true).click();

//Check results
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x("//table//tr[1]/td[2]").shouldHave(text("Lana Zaklinska"));
        $x("//table//tr[2]/td[2]").shouldHave(text("lana@zaklinska.com"));
        $x("//table//tr[3]/td[2]").shouldHave(text("Female"));
        $x("//table//tr[4]/td[2]").shouldHave(text("1234567890"));
        $x("//table//tr[5]/td[2]").shouldHave(text("28 July,2005"));
        $x("//table//tr[6]/td[2]").shouldHave(text("Maths"));
        $x("//table//tr[7]/td[2]").shouldHave(text("Reading"));
        $x("//table//tr[8]/td[2]").shouldHave(text("Sea.JPG"));
        $x("//table//tr[9]/td[2]").shouldHave(text("Some street address, 1"));
        $x("//table//tr[10]/td[2]").shouldHave(text("NCR Delhi"));
    }
}
