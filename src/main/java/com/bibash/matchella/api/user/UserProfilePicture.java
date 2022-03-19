package com.bibash.matchella.api.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
public class UserProfilePicture {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonIgnore
	@OneToOne
	private User user;

	@Column(columnDefinition = "mediumtext")
	private String data;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		UserProfilePicture that = (UserProfilePicture) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return 0;
	}
}
