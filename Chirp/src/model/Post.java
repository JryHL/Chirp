/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package model;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

// line 34 "../main.ump"
public class Post implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Post Attributes
  private String content;
  private Date date;
  private Time time;

  //Post Associations
  private ChirpSystem chirpSystem;
  private User posters;
  private List<User> likers;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Post(String aContent, Date aDate, Time aTime, ChirpSystem aChirpSystem, User aPosters)
  {
    content = aContent;
    date = aDate;
    time = aTime;
    boolean didAddChirpSystem = setChirpSystem(aChirpSystem);
    if (!didAddChirpSystem)
    {
      throw new RuntimeException("Unable to create post due to chirpSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddPosters = setPosters(aPosters);
    if (!didAddPosters)
    {
      throw new RuntimeException("Unable to create postedPost due to posters. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    likers = new ArrayList<User>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setContent(String aContent)
  {
    boolean wasSet = false;
    content = aContent;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setTime(Time aTime)
  {
    boolean wasSet = false;
    time = aTime;
    wasSet = true;
    return wasSet;
  }

  public String getContent()
  {
    return content;
  }

  public Date getDate()
  {
    return date;
  }

  public Time getTime()
  {
    return time;
  }
  /* Code from template association_GetOne */
  public ChirpSystem getChirpSystem()
  {
    return chirpSystem;
  }
  /* Code from template association_GetOne */
  public User getPosters()
  {
    return posters;
  }
  /* Code from template association_GetMany */
  public User getLiker(int index)
  {
    User aLiker = likers.get(index);
    return aLiker;
  }

  public List<User> getLikers()
  {
    List<User> newLikers = Collections.unmodifiableList(likers);
    return newLikers;
  }

  public int numberOfLikers()
  {
    int number = likers.size();
    return number;
  }

  public boolean hasLikers()
  {
    boolean has = likers.size() > 0;
    return has;
  }

  public int indexOfLiker(User aLiker)
  {
    int index = likers.indexOf(aLiker);
    return index;
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
      existingChirpSystem.removePost(this);
    }
    chirpSystem.addPost(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPosters(User aPosters)
  {
    boolean wasSet = false;
    if (aPosters == null)
    {
      return wasSet;
    }

    User existingPosters = posters;
    posters = aPosters;
    if (existingPosters != null && !existingPosters.equals(aPosters))
    {
      existingPosters.removePostedPost(this);
    }
    posters.addPostedPost(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLikers()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addLiker(User aLiker)
  {
    boolean wasAdded = false;
    if (likers.contains(aLiker)) { return false; }
    likers.add(aLiker);
    if (aLiker.indexOfLikedPost(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aLiker.addLikedPost(this);
      if (!wasAdded)
      {
        likers.remove(aLiker);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeLiker(User aLiker)
  {
    boolean wasRemoved = false;
    if (!likers.contains(aLiker))
    {
      return wasRemoved;
    }

    int oldIndex = likers.indexOf(aLiker);
    likers.remove(oldIndex);
    if (aLiker.indexOfLikedPost(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aLiker.removeLikedPost(this);
      if (!wasRemoved)
      {
        likers.add(oldIndex,aLiker);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLikerAt(User aLiker, int index)
  {  
    boolean wasAdded = false;
    if(addLiker(aLiker))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLikers()) { index = numberOfLikers() - 1; }
      likers.remove(aLiker);
      likers.add(index, aLiker);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLikerAt(User aLiker, int index)
  {
    boolean wasAdded = false;
    if(likers.contains(aLiker))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLikers()) { index = numberOfLikers() - 1; }
      likers.remove(aLiker);
      likers.add(index, aLiker);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLikerAt(aLiker, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ChirpSystem placeholderChirpSystem = chirpSystem;
    this.chirpSystem = null;
    if(placeholderChirpSystem != null)
    {
      placeholderChirpSystem.removePost(this);
    }
    User placeholderPosters = posters;
    this.posters = null;
    if(placeholderPosters != null)
    {
      placeholderPosters.removePostedPost(this);
    }
    ArrayList<User> copyOfLikers = new ArrayList<User>(likers);
    likers.clear();
    for(User aLiker : copyOfLikers)
    {
      aLiker.removeLikedPost(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "content" + ":" + getContent()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "time" + "=" + (getTime() != null ? !getTime().equals(this)  ? getTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "chirpSystem = "+(getChirpSystem()!=null?Integer.toHexString(System.identityHashCode(getChirpSystem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "posters = "+(getPosters()!=null?Integer.toHexString(System.identityHashCode(getPosters())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 38 "../main.ump"
  private static final long serialVersionUID = 2493632L ;

  
}