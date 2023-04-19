package se.iths.webshop.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;


import java.util.List;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    ShopService service;

    public String generateConfirmationEmailBody(List<OrderLine> orderLines, double totalPrice) {
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append("Thank you for your order!\n\n");
        bodyBuilder.append("Order Details:\n");

        for (OrderLine orderLine : orderLines) {
            bodyBuilder.append("Product: ").append(orderLine.getProduct().getName()).append("\n");
            bodyBuilder.append("Quantity: ").append(orderLine.getQuantity()).append("\n");
            bodyBuilder.append("Price: ").append(orderLine.getProduct().getPrice()).append("\n");
            bodyBuilder.append("Subtotal: ").append(orderLine.getSubTotal()).append("\n");
            bodyBuilder.append("\n");
        }

        bodyBuilder.append("Total Price: ").append(totalPrice).append("\n");

        return bodyBuilder.toString();
    }


    public void sendemail(String email) {
        double total =  service.calculateTotal(service.getShoppingCart());
        String emailBody = generateConfirmationEmailBody(service.getShoppingCart(), total);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Thank you for your purchase!");
        message.setText(emailBody);
        javaMailSender.send(message);
    }
}
