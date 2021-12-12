/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package model;
import java.io.Serializable;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 5 "../main.ump"
public class ChirpSystem implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ChirpSystem Associations
  private List<User> users;
  private List<Post> posts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ChirpSystem()
  {
    users = new ArrayList<User>();
    posts = new ArrayList<Post>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetMany */
  public Post getPost(int index)
  {
    Post aPost = posts.get(index);
    return aPost;
  }

  public List<Post> getPosts()
  {
    List<Post> newPosts = Collections.unmodifiableList(posts);
    return newPosts;
  }

  public int numberOfPosts()
  {
    int number = posts.size();
    return number;
  }

  public boolean hasPosts()
  {
    boolean has = posts.size() > 0;
    return has;
  }

  public int indexOfPost(Post aPost)
  {
    int index = posts.indexOf(aPost);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser(String aEmail, String aPasswordHash, String aFirstName, String aLastName, boolean aIsAdmin)
  {
    return new User(aEmail, aPasswordHash, aFirstName, aLastName, aIsAdmin, this);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    ChirpSystem existingChirpSystem = aUser.getChirpSystem();
    boolean isNewChirpSystem = existingChirpSystem != null && !this.equals(existingChirpSystem);
    if (isNewChirpSystem)
    {
      aUser.setChirpSystem(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a chirpSystem
    if (!this.equals(aUser.getChirpSystem()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPosts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Post addPost(String aContent, Date aDate, Time aTime, User aPosters)
  {
    return new Post(aContent, aDate, aTime, this, aPosters);
  }

  public boolean addPost(Post aPost)
  {
    boolean wasAdded = false;
    if (posts.contains(aPost)) { return false; }
    ChirpSystem existingChirpSystem = aPost.getChirpSystem();
    boolean isNewChirpSystem = existingChirpSystem != null && !this.equals(existingChirpSystem);
    if (isNewChirpSystem)
    {
      aPost.setChirpSystem(this);
    }
    else
    {
      posts.add(aPost);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePost(Post aPost)
  {
    boolean wasRemoved = false;
    //Unable to remove aPost, as it must always have a chirpSystem
    if (!this.equals(aPost.getChirpSystem()))
    {
      posts.remove(aPost);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPostAt(Post aPost, int index)
  {  
    boolean wasAdded = false;
    if(addPost(aPost))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPosts()) { index = numberOfPosts() - 1; }
      posts.remove(aPost);
      posts.add(index, aPost);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePostAt(Post aPost, int index)
  {
    boolean wasAdded = false;
    if(posts.contains(aPost))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPosts()) { index = numberOfPosts() - 1; }
      posts.remove(aPost);
      posts.add(index, aPost);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPostAt(aPost, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (posts.size() > 0)
    {
      Post aPost = posts.get(posts.size() - 1);
      aPost.delete();
      posts.remove(aPost);
    }
    
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 10 "../main.ump"
  private static final long serialVersionUID = 21203601L ;

  
}