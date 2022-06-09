package com.example.application.it.elements;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.login.testbench.LoginFormElement;
import com.vaadin.flow.component.orderedlayout.testbench.VerticalLayoutElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.annotations.Attribute;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Attribute(name = "class", contains = "login-view")
public class LoginViewElement extends VerticalLayoutElement {

    public boolean login(String username, String password) {
        LoginFormElement form = $(LoginFormElement.class).first();
        form.getUsernameField().setValue(username);
        form.getPasswordField().setValue(password);
        form.getSubmitButton().click();
        $(TextFieldElement.class).id("firstName").setValue("John");
        $(TextFieldElement.class).id("lastName").setValue("Doe");
        $(ButtonElement.class).id("ok").click();



        // Return true if we end up on another page
        return !$(LoginViewElement.class).onPage().exists();
    }

}