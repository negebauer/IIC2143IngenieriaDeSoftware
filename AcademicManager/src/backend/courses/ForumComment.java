package backend.courses;

import java.util.Date;

import backend.users.User;

public class ForumComment {

	public User creator;
	public Date createdAt;
	public String comment;
	
	public ForumComment(User creator, String comment) {
		this.creator = creator;
		createdAt = new Date();
		this.comment = comment;
	}
	
	public ForumComment(User creator, String comment, Date createdAt) {
		this.creator = creator;
		this.createdAt = createdAt;
		this.comment = comment;
	}
}
