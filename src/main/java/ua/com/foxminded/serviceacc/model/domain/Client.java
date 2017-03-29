package ua.com.foxminded.serviceacc.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@Table(name = "clients")
public class Client implements Serializable, Identifiable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger("Client : ");
	@Id
	@Column(name = "id", columnDefinition = "serial", insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "second_name")
	private String secondName;

	@Column(name = "status")
	private int status;

	@Column(name = "level")
	private int level;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getStatusAsString() {
		if (status == 0) {
			return "Active";
		} else {
			return "Frozen";
		}
	}

	public void setStatusAsString(String status) {
		if (status.equals("Active")) {
			this.status = 0;
		} else if (status.equals("Frozen")) {
			this.status = 1;
		} else {
			this.status = -1;
		}
	}

	public String getLevelAsString() {
		if (level == 0) {
			return "Applicant";
		}
		if (level == 1) {
			return "Beginner";
		}
		if (level == 2) {
			return "Regular";
		}
		if (level == 3) {
			return "Graduate";
		} else {
			return "WTF?";
		}
	}

	public void setLevelAsString(String level) {
		log.info("setLevelAsString(" + level + ")");
		if (level.equals("Applicant")) {
			this.level = 0;
		} else if (level.equals("Beginner")) {
			this.level = 1;
		} else if (level.equals("Regular")) {
			this.level = 2;
		} else if (level.equals("Graduate")) {
			this.level = 3;
		} else {
			this.level = -1;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
