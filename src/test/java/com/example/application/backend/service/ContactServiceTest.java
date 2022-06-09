package com.example.application.backend.service;


import com.example.application.data.entity.Company;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Status;
import com.example.application.data.service.CompanyService;
import com.example.application.data.service.ContactService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.TransactionScoped;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactServiceTest {

    @Autowired
    private ContactService contactService;
    @Autowired
    private CompanyService companyService;
    private List<Company> companies;
    private Contact marcUsher;
    private Company company1;
    private Company company2;

    @Before
    public void setupData() {

        marcUsher = new Contact();
        marcUsher.setFirstName("Marc");
        marcUsher.setLastName("Usher");
        marcUsher.setEmail("marc@usher.com");
        Status status = new Status("Not contacted");
        marcUsher.setStatus(status);

    }

    @Test
    @TransactionScoped
    public void testSaveContact() {
        List<Contact> contactList = contactService.findAll("Alexa");
        Assert.assertEquals(0,contactList.size());

        Contact AlexaBliss = new Contact();
        Status status = contactService.findAllStatuses().get(0);

        AlexaBliss.setFirstName("Alexa");
        AlexaBliss.setLastName("Bliss");
        AlexaBliss.setEmail("Alexa@xyz.com");
        AlexaBliss.setStatus(status);
        Company company2 = new Company();
        company2.setName("Baracuda");

        companyService.save(company2);
        List<Company> companyListAll = companyService.findAll();
        companyListAll.forEach(company -> System.out.println("company:" + company.getName()));

        List<Company> companyList = companyService.findAll("Baracuda");

        if (companyList != null) {
            AlexaBliss.setCompany(companyList.get(0));
            contactService.save(AlexaBliss);
        }
        List<Contact> createdContactList = contactService.findAll("Alexa");
        Contact contact = createdContactList.get(0);
        Assert.assertEquals(contact.getFirstName(), "Alexa");
        Assert.assertEquals(contact.getCompany().getName(), "Baracuda");
    }

}
