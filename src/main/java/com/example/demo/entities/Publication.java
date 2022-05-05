package com.example.demo.entities;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.enumeradores.TypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publication")
public class Publication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "data_creation")
	private Date creationDate;

	@Enumerated(EnumType.ORDINAL)
	private TypeEnum type;

	@ManyToOne
	@JoinColumn(name = "creator")
	private User creator;

	@ManyToOne
	@JoinColumn(name = "reference")
	@JsonIgnoreProperties({ "files", "comments", "likes" })
	private Publication reference;

	@Column(name = "visualizations")
	private Long visualizations;
	@OneToMany(mappedBy = "publication")
	@JsonIgnoreProperties({ "publication" })
	private List<Photo> files;

	@OneToMany(mappedBy = "reference")
	@JsonIgnoreProperties({ "reference" })
	private List<Publication> comments;

	@OneToMany(mappedBy = "publication")
	@JsonIgnoreProperties({ "publication" })
	private List<Like> likes;

	public Like getEntity() {
		return null;
	}

	public Map<String, Object> getProperties() {
		return null;
	}

}
