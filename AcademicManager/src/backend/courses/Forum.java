package backend.courses;

import java.util.ArrayList;

public class Forum {

	public ArrayList<ForumPost> posts;
	
	public Forum() {
		posts = new ArrayList<ForumPost>();
	}
	
	public void addPost(ForumPost post) {
		posts.add(post);
	}
}
