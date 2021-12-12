/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package model;
import java.io.Serializable;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 17 "../main.ump"
public class User implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByEmail = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String email;
  private String passwordHash;
  private String firstName;
  private String lastName;
  private boolean isAdmin;

  //User Associations
  private List<Post> postedPosts;
  private List<Post> likedPosts;
  private ChirpSystem chirpSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aEmail, String aPasswordHash, String aFirstName, String aLastName, boolean aIsAdmin, ChirpSystem aChirpSystem)
  {
    passwordHash = aPasswordHash;
    firstName = aFirstName;
    lastName = aLastName;
    isAdmin = aIsAdmin;
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    postedPosts = new ArrayList<Post>();
    likedPosts = new ArrayList<Post>();
    boolean didAddChirpSystem = setChirpSystem(aChirpSystem);
    if (!didAddChirpSystem)
    {
      throw new RuntimeException("Unable to create user due to chirpSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    String anOldEmail = getEmail();
    if (anOldEmail != null && anOldEmail.equals(aEmail)) {
      return true;
    }
    if (hasWithEmail(aEmail)) {
      return wasSet;
    }
    email = aEmail;
    wasSet = true;
    if (anOldEmail != null) {
      usersByEmail.remove(anOldEmail);
    }
    usersByEmail.put(aEmail, this);
    return wasSet;
  }

  public boolean setPasswordHash(String aPasswordHash)
  {
    boolean wasSet = false;
    passwordHash = aPasswordHash;
    wasSet = true;
    return wasSet;
  }

  public boolean setFirstName(String aFirstName)
  {
    boolean wasSet = false;
    firstName = aFirstName;
    wasSet = true;
    return wasSet;
  }

  public boolean setLastName(String aLastName)
  {
    boolean wasSet = false;
    lastName = aLastName;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsAdmin(boolean aIsAdmin)
  {
    boolean wasSet = false;
    isAdmin = aIsAdmin;
    wasSet = true;
    return wasSet;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithEmail(String aEmail)
  {
    return usersByEmail.get(aEmail);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
  }

  public String getPasswordHash()
  {
    return passwordHash;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public boolean getIsAdmin()
  {
    return isAdmin;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsAdmin()
  {
    return isAdmin;
  }
  /* Code from template association_GetMany */
  public Post getPostedPost(int index)
  {
    Post aPostedPost = postedPosts.get(index);
    return aPostedPost;
  }

  public List<Post> getPostedPosts()
  {
    List<Post> newPostedPosts = Collections.unmodifiableList(postedPosts);
    return newPostedPosts;
  }

  public int numberOfPostedPosts()
  {
    int number = postedPosts.size();
    return number;
  }

  public boolean hasPostedPosts()
  {
    boolean has = postedPosts.size() > 0;
    return has;
  }

  public int indexOfPostedPost(Post aPostedPost)
  {
    int index = postedPosts.indexOf(aPostedPost);
    return index;
  }
  /* Code from template association_GetMany */
  public Post getLikedPost(int index)
  {
    Post aLikedPost = likedPosts.get(index);
    return aLikedPost;
  }

  public List<Post> getLikedPosts()
  {
    List<Post> newLikedPosts = Collections.unmodifiableList(likedPosts);
    return newLikedPosts;
  }

  public int numberOfLikedPosts()
  {
    int number = likedPosts.size();
    return number;
  }

  public boolean hasLikedPosts()
  {
    boolean has = likedPosts.size() > 0;
    return has;
  }

  public int indexOfLikedPost(Post aLikedPost)
  {
    int index = likedPosts.indexOf(aLikedPost);
    return index;
  }
  /* Code from template association_GetOne */
  public ChirpSystem getChirpSystem()
  {
    return chirpSystem;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPostedPosts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Post addPostedPost(String aContent, Date aDate, Time aTime, ChirpSystem aChirpSystem)
  {
    return new Post(aContent, aDate, aTime, aChirpSystem, this);
  }

  public boolean addPostedPost(Post aPostedPost)
  {
    boolean wasAdded = false;
    if (postedPosts.contains(aPostedPost)) { return false; }
    User existingPosters = aPostedPost.getPosters();
    boolean isNewPosters = existingPosters != null && !this.equals(existingPosters);
    if (isNewPosters)
    {
      aPostedPost.setPosters(this);
    }
    else
    {
      postedPosts.add(aPostedPost);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePostedPost(Post aPostedPost)
  {
    boolean wasRemoved = false;
    //Unable to remove aPostedPost, as it must always have a posters
    if (!this.equals(aPostedPost.getPosters()))
    {
      postedPosts.remove(aPostedPost);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPostedPostAt(Post aPostedPost, int index)
  {  
    boolean wasAdded = false;
    if(addPostedPost(aPostedPost))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPostedPosts()) { index = numberOfPostedPosts() - 1; }
      postedPosts.remove(aPostedPost);
      postedPosts.add(index, aPostedPost);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePostedPostAt(Post aPostedPost, int index)
  {
    boolean wasAdded = false;
    if(postedPosts.contains(aPostedPost))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPostedPosts()) { index = numberOfPostedPosts() - 1; }
      postedPosts.remove(aPostedPost);
      postedPosts.add(index, aPostedPost);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPostedPostAt(aPostedPost, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLikedPosts()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addLikedPost(Post aLikedPost)
  {
    boolean wasAdded = false;
    if (likedPosts.contains(aLikedPost)) { return false; }
    likedPosts.add(aLikedPost);
    if (aLikedPost.indexOfLiker(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aLikedPost.addLiker(this);
      if (!wasAdded)
      {
        likedPosts.remove(aLikedPost);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeLikedPost(Post aLikedPost)
  {
    boolean wasRemoved = false;
    if (!likedPosts.contains(aLikedPost))
    {
      return wasRemoved;
    }

    int oldIndex = likedPosts.indexOf(aLikedPost);
    likedPosts.remove(oldIndex);
    if (aLikedPost.indexOfLiker(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aLikedPost.removeLiker(this);
      if (!wasRemoved)
      {
        likedPosts.add(oldIndex,aLikedPost);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLikedPostAt(Post aLikedPost, int index)
  {  
    boolean wasAdded = false;
    if(addLikedPost(aLikedPost))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLikedPosts()) { index = numberOfLikedPosts() - 1; }
      likedPosts.remove(aLikedPost);
      likedPosts.add(index, aLikedPost);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLikedPostAt(Post aLikedPost, int index)
  {
    boolean wasAdded = false;
    if(likedPosts.contains(aLikedPost))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLikedPosts()) { index = numberOfLikedPosts() - 1; }
      likedPosts.remove(aLikedPost);
      likedPosts.add(index, aLikedPost);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLikedPostAt(aLikedPost, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setChirpSystem(ChirpSystem aChirpSystem)
  {
    boolean wasSet = false;
    if (aChirpSystem == null)
    {
      return wasSet;
    }

    ChirpSystem existingChirpSystem = chirpSystem;
    chirpSystem = aChirpSystem;
    if (existingChirpSystem != null && !existingChirpSystem.equals(aChirpSystem))
    {
      existingChirpSystem.removeUser(this);
    }
    chirpSystem.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    usersByEmail.remove(getEmail());
    for(int i=postedPosts.size(); i > 0; i--)
    {
      Post aPostedPost = postedPosts.get(i - 1);
      aPostedPost.delete();
    }
    ArrayList<Post> copyOfLikedPosts = new ArrayList<Post>(likedPosts);
    likedPosts.clear();
    for(Post aLikedPost : copyOfLikedPosts)
    {
      aLikedPost.removeLiker(this);
    }
    ChirpSystem placeholderChirpSystem = chirpSystem;
    this.chirpSystem = null;
    if(placeholderChirpSystem != null)
    {
      placeholderChirpSystem.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "passwordHash" + ":" + getPasswordHash()+ "," +
            "firstName" + ":" + getFirstName()+ "," +
            "lastName" + ":" + getLastName()+ "," +
            "isAdmin" + ":" + getIsAdmin()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "chirpSystem = "+(getChirpSystem()!=null?Integer.toHexString(System.identityHashCode(getChirpSystem())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 21 "../main.ump"
  private static final long serialVersionUID = 2645995L ;

  
}