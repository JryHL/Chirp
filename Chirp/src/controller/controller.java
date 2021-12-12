package controller;

import application.ChirpApplication;
import model.ChirpSystem;
import model.Post;
import model.User;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.sql.Date;

public class controller {
	static ChirpSystem system = ChirpApplication.getChirpSystem();
	static controller ctrl = new controller();
	public static controller getController() {
		return ctrl;
	}
	
	public void makePost(String text, User poster) {
		try {
		new Post(text, 
				new java.sql.Date(System.currentTimeMillis()),
				new java.sql.Time(System.currentTimeMillis()),
				system,
				poster);
		} catch (RuntimeException e) {
			//TODO: Respond to runtime exception
		}
		
	}
	
	/**
	 * Gets 10 top posts in the list of posts; does not use any sort of efficient sorting
	 * 
	 * @return
	 */
	//TODO: Efficient sorting of posts
	public LinkedList<Post> getPosts() {
		 List<Post> posts = system.getPosts();
		 LinkedList<Post> topPosts = new LinkedList<Post>();
		 for (int i = 0; i < 10; i++) {
			 topPosts.add(posts.get(i));
		 }
		 return topPosts;
	}
}
