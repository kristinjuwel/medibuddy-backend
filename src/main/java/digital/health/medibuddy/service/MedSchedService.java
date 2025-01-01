package digital.health.medibuddy.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import digital.health.medibuddy.model.Carer;
import digital.health.medibuddy.model.MedSched;
import digital.health.medibuddy.model.Medicine;
import digital.health.medibuddy.model.User;
import digital.health.medibuddy.repository.CarerRepository;
import digital.health.medibuddy.repository.MedSchedRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

@Service
public class MedSchedService {
	private final MedSchedRepository medSchedRepo;
	private final CarerRepository carerRepo;
    private final JavaMailSender javaMailSender;
	public MedSchedService(MedSchedRepository medSchedRepo, CarerRepository carerRepo, JavaMailSender javaMailSender) {
		super();
		this.medSchedRepo = medSchedRepo;
		this.carerRepo = carerRepo;
		this.javaMailSender = javaMailSender;
	}

	public MedSched addMedSched(MedSched medSched) {
		medSched.setTaken(false);
		return medSchedRepo.save(medSched);
	}
    
	public MedSched updateMedSched(Long schedId, MedSched updatedSched) {
        MedSched existingSched = medSchedRepo.findById(schedId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule with ID " + schedId + " not found"));

        if (updatedSched.getDay() != null) existingSched.setDay(updatedSched.getDay());
        if (updatedSched.getTime() != null) existingSched.setTime(updatedSched.getTime());
        if (updatedSched.getTimeTaken() != null) existingSched.setTimeTaken(updatedSched.getTimeTaken());
        if (updatedSched.getTaken() != null) existingSched.setTaken(updatedSched.getTaken());
        if (updatedSched.getQtyTaken() != null) existingSched.setQtyTaken(updatedSched.getQtyTaken());
        if (updatedSched.getAction() != null) existingSched.setAction(updatedSched.getAction());

        return medSchedRepo.save(existingSched);
    }
	
	public void deleteMedSched(Long schedId) {
        MedSched medSched = medSchedRepo.findBySchedId(schedId);
        if (medSched != null) {
        	medSchedRepo.delete(medSched);
        } else {
            throw new RuntimeException("Schedule not found with ID: " + schedId);
        }
    }
	
	private void notifyCarer(List<Carer> carers, String date, String time, Medicine med, String action) throws MessagingException, IOException {
	    List<String> carerEmails = carers.stream()
	            .map(Carer::getEmail)
	            .collect(Collectors.toList());
	    List<String> carerNames = carers.stream()
	            .map(carer -> carer.getFirstName() + " " + carer.getLastName())
	            .collect(Collectors.toList());

	    String medName = med.getName();
	    String dose = med.getDose();
	    String instructions = med.getInstructions();
	    User user = med.getUser();
	    String name = user.getFirstName() + " " + user.getLastName();

	    for (int i = 0; i < carerEmails.size(); i++) {
	        String carerEmail = carerEmails.get(i);
	        String carerName = carerNames.get(i);

	        String subject = carerName + " missed " + medName;

	        String containerMessage = "<h1>Hello " + carerName + "!</h1>" +
	                "<p>" + name + " missed taking " + medName + " today (" + date + ") at " + time + ".</p>" +
	                "<p>The doctor instructed: " + instructions + "</p>" +
	                "<p>The dosage for " + medName + " is: " + dose + ".</p>" +
	                "<p>" + name + " will be " + action + ".</p>" +
	                "<p>Thank you!</p>";

	        String htmlContent = "<!DOCTYPE html>" +
	                "<html lang='en'>" +
	                "<head>" +
	                "<meta charset='UTF-8'>" +
	                "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
	                "<style>" +
	                "body { font-family: Arial, Helvetica, sans-serif; background: #ffffff; font-size: 14px; }" +
	                ".container { max-width: 680px; margin: 0 auto; padding: 45px 30px 60px; background: #f4f7ff; border-radius: 30px; }" +
	                "h1 { font-size: 25px; font-weight: 800; color: #800000; text-align: center; }" + 
	                "h2 { font-size: 20px; font-weight: 500; color: #800000; text-align: center; }" + 
	                "p { font-size: 16px; font-weight: 500; color: #800000; line-height: 1.5; }" + 
	                ".footer { text-align: center; margin-top: 20px; color: #800000; font-size: 14px; }" +
	                "img { width: 50px; height: auto; vertical-align: middle; display: inline-block; pointer-events: none; -webkit-user-drag: none; user-select: none; }" +
	                "</style>" +
	                "</head>" +
	                "<body>" +
	                "<div class='container'>" +
	                "<div style='display: flex; align-items: center;'>" +
	                "<img src='cid:logo' alt='Logo'>" +
	                "<h1 style='font-size: 20px; display: inline-block; margin: 0; padding-left: 10px; padding-top: 10px'>MediBuddy</h1>" +
	                "</div>" + "<br>" +
	                containerMessage +
	                "</div>" +
	                "<div class='footer'>" +
	                "<p>Need help? Ask at <a href='mailto:dailydairy@gmail.com' style='color: #800000; text-decoration: none;'>medibuddy@gmail.com</a></p>" +
	                "<p>&copy; 2024 MediBuddy. All rights reserved.</p>" +
	                "</div>" +
	                "</body>" +
	                "</html>";

	        MimeMessage message = javaMailSender.createMimeMessage();

	        message.setRecipients(MimeMessage.RecipientType.TO, carerEmail);
	        message.setSubject(subject);

	        MimeBodyPart htmlPart = new MimeBodyPart();
	        htmlPart.setContent(htmlContent, "text/html; charset=utf-8");

	        MimeBodyPart imagePart = new MimeBodyPart();
	        imagePart.attachFile("src/main/resources/static/logo.png"); 
	        imagePart.setContentID("<logo>");
	        imagePart.setDisposition(MimeBodyPart.INLINE);

	        MimeMultipart multipart = new MimeMultipart();
	        multipart.addBodyPart(htmlPart);
	        multipart.addBodyPart(imagePart);

	        message.setContent(multipart);

	        javaMailSender.send(message);
	    }
	}

	public void checkIfTaken(Long schedId, Long userId, boolean isTaken, String qtyTaken, String action) throws MessagingException, IOException {
        MedSched sched = medSchedRepo.findById(schedId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule with ID " + schedId + " not found"));

        sched.setTaken(isTaken);
        if (isTaken) {
            sched.setTimeTaken(LocalDateTime.now(ZoneId.of("Asia/Manila")));
            sched.setQtyTaken(qtyTaken);
        } else {
        	sched.setAction(action);
        	String date = sched.getDay().toString();
        	String time = sched.getTime().toString();
        	Medicine med = sched.getMedicine();
        	List<Carer> carers = carerRepo.findByUserId(userId);
            
			if (carers != null && !carers.isEmpty()) {
			    notifyCarer(carers, date, time, med, action);
			}
		}

        medSchedRepo.save(sched);
    }

    public MedSched findBySchedId(Long schedId) {
        return medSchedRepo.findBySchedId(schedId);
    }
    
    public List<MedSched> findByMedId(Long medId) {
        return medSchedRepo.findByMedId(medId);
    }
}
