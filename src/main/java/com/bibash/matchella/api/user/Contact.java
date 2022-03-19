package com.bibash.matchella.api.user;

import com.bibash.matchella.core.component.TextEncryptorConverter;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(updatable = false)
	@Convert(converter = TextEncryptorConverter.class)
	private String email;
	
	@Convert(converter = TextEncryptorConverter.class)
	@Column(columnDefinition="mediumtext", updatable = false)
	private String message;
	
	private Date date;
	
	private boolean hidden;
}
