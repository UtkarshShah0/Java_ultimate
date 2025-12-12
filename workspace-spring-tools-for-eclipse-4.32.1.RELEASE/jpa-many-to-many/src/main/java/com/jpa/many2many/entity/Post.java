package com.jpa.many2many.entity;

import java.util.Date;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private String content;
	@SuppressWarnings("deprecation")
	@Temporal(TemporalType.TIMESTAMP)
	private Date postedAt = new Date();
	@SuppressWarnings("deprecation")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedAt = new Date();
	

@ManyToMany(fetch = FetchType.EAGER,
        cascade = {CascadeType.PERSIST, CascadeType.MERGE})
@JoinTable(
    name = "post_tags",
    joinColumns = {@JoinColumn(name = "post_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")
    })

//@ToString.Exclude
@JsonIgnore
private Set<Tag> tags = new HashSet<>();

public Post(String title, String description, String content) {
	this.title = title;
	this.description = description;
	this.content = content;
}

@Override
public String toString() {
	return "Post [id=" + id + ", title=" + title + ", description=" + description + ", content=" + content
			+ ", postedAt=" + postedAt + ", lastUpdatedAt=" + lastUpdatedAt + ", tags=" + tags + "]";
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public Date getPostedAt() {
	return postedAt;
}

public void setPostedAt(Date postedAt) {
	this.postedAt = postedAt;
}

public Date getLastUpdatedAt() {
	return lastUpdatedAt;
}

public Post() {
	super();
}

public void setLastUpdatedAt(Date lastUpdatedAt) {
	this.lastUpdatedAt = lastUpdatedAt;
}

public Set<Tag> getTags() {
	return tags;
}

public void setTags(Set<Tag> tags) {
	this.tags = tags;
}

 
}
