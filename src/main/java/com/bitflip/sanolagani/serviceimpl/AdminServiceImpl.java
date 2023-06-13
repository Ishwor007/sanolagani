package com.bitflip.sanolagani.serviceimpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bitflip.sanolagani.models.Company;
import com.bitflip.sanolagani.models.UnverifiedCompanyDetails;
import com.bitflip.sanolagani.repository.CompanyRepo;
import com.bitflip.sanolagani.repository.UnverifiedCompanyRepo;
import com.bitflip.sanolagani.service.AdminService;
import java.security.SecureRandom;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AdminServiceImpl implements AdminService {
	private static final String character = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int pwd_length = 10;
    @Autowired
    private JavaMailSender mailSender;
	@Autowired
	UnverifiedCompanyRepo unverified_repo;
	@Autowired
	CompanyRepo company_repo;
	private UnverifiedCompanyDetails unverified_details;
	@Override
	public void saveUnverifiedCompany(UnverifiedCompanyDetails un_company) { 
		this.unverified_details=un_company;
		unverified_repo.save(un_company);
	}

	@Override
	public List<UnverifiedCompanyDetails> fetchAll() {
		List<UnverifiedCompanyDetails> details = unverified_repo.findAll();
		return details;
	}

	@Override
	public void deleteData(int id) {
		unverified_repo.deleteById(id);
		
	}

	@Override
	public void saveVerifiedCompany(int id,Company company) {
		String plain_password = generatePassword();
		String encodedPassword = encodePassword(plain_password);
	     unverified_details = unverified_repo.getById(id);
	      sendPasswordEmail(unverified_details.getEmail(), plain_password);
	     company.setFname(unverified_details.getFname());
	     company.setLname(unverified_details.getLname());
	     company.setCompanyname(unverified_details.getCompanyname());
	     company.setEmail(unverified_details.getEmail());
	     company.setPhnum(unverified_details.getPhnum());
	     company.setSector(unverified_details.getSector());
	     company.setWebsiteurl(unverified_details.getWebsiteurl());
	     company.setPreviouslyraisedcapital(unverified_details.getRaisedcapital());
	     company.setPrice_per_share(unverified_details.getPrice_per_share());
	     company.setTimespanforraisingcapital(unverified_details.getTimespanforraisingcapital());
	     company.setFilename(unverified_details.getFilename());
	     company.setPan_image_name(unverified_details.getPan_image_name());
	     company.setCitizenship_fname(unverified_details.getCitizenship_fname());
	     company.setCitizenship_bname(unverified_details.getCitizenship_bname());
	     company.setMaximum_quantity(unverified_details.getMaximum_quantity());
	     company.setPassword(encodedPassword);
	     company_repo.save(company);
	     unverified_repo.deleteById(id);
	}

	public static String generatePassword() {
	      StringBuilder sb = new StringBuilder();
	        SecureRandom random = new SecureRandom();

	        for (int i = 0; i < pwd_length; i++) {
	            int randomIndex = random.nextInt(character.length());
	            sb.append(character.charAt(randomIndex));
	        }

	        return sb.toString();
	    }
	 public static String encodePassword(String plainPassword) {
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        return encoder.encode(plainPassword);
	    }
	 public  void sendPasswordEmail(String to,String password) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject("Company Registered Sucessfully");
	        message.setText("your company is sucessfully registered and the authentication details is email:"
	        		      +to+" password:"+ password+". Regards:seetal raya from sanolagani project");
	        mailSender.send(message);
	        
	    }
}
