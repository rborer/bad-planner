package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.StringUtils;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
public class User extends Model {

	@Required
	public String firstname;

	@Required
	public String lastname;

	@Required
	@Email
	public String email;

	@Required
	public String password;

	@Required
	public Boolean validated;

	public User(final String email, final String firstname,
			final String lastname, final String password) {
		super();
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = hashPassword(password);
		this.validated = Boolean.FALSE;
	}

	public void setPassword(final String password) {
		this.password = hashPassword(password);
	}

	public static User connect(final String email, final String password) {
		final String hashedPassword = hashPassword(password);
		final User user = find("byEmail", email).first();

		if (user != null && StringUtils.equals(user.password, hashedPassword)) {
			return user;
		} else {
			return null;
		}
	}

	private static String hashPassword(final String password) {
		if (StringUtils.isEmpty(password)) {
			return "";
		}

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(password.getBytes());
			final byte[] mb = md.digest();
			StringBuffer out = new StringBuffer();
			;
			for (int i = 0; i < mb.length; i++) {
				final byte temp = mb[i];
				String s = Integer.toHexString(new Byte(temp));
				while (s.length() < 2) {
					s = "0" + s;
				}
				s = s.substring(s.length() - 2);
				out.append(s);
			}

			return out.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
