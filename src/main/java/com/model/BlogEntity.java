package com.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "blogs")
public class BlogEntity {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, length = 255)
	    private String title;

	    @Column(nullable = false, columnDefinition = "TEXT")
	    private String content;

	    private LocalDateTime createdAt = LocalDateTime.now();

	    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	    private List<CommentEntity> comments;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public List<CommentEntity> getComments() {
			return comments;
		}

		public void setComments(List<CommentEntity> comments) {
			this.comments = comments;
		}

		public BlogEntity(Long id, String title, String content, LocalDateTime createdAt,
				List<CommentEntity> comments) {
			super();
			this.id = id;
			this.title = title;
			this.content = content;
			this.createdAt = createdAt;
			this.comments = comments;
		}

		public BlogEntity() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "BlogEntity [id=" + id + ", title=" + title + ", content=" + content + ", createdAt=" + createdAt
					+ ", comments=" + comments + "]";
		}
	    
	    
}


