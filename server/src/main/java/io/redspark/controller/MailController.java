package io.redspark.controller;

import static io.redspark.controller.ControllerConstants.MAIL;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.redspark.service.SmtpMailSender;

@RestController
@RequestMapping(MAIL)
public class MailController {

  private static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);

  @Autowired
  private SmtpMailSender mailSender;

  @Transactional
  @RequestMapping(method = RequestMethod.GET)
  public void sendMail(@RequestParam(value = "to", required = true) String to) {
    try {
      mailSender.send(to, "Code test mail", "Hello");
    } catch (MessagingException e) {
      LOGGER.error("Problem sending mail.", e);
    }
  }
}