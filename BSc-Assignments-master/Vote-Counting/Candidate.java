/* @author Sam O'Floinn, 109539794. Created for Assignment A4, CS2504, Kieran Herley.
 * This class creates a candidate to be elected in an election.
 * Here, we define their name and party, and also the methods for users to retrieve these.
 * */

public class Candidate 
{
  /* The candidate's name and party are defined at object-creation time by the user. */
  public Candidate(String name, String party)
  {
    candidateName = name;
    candidateParty = party;
  }
  
  /* These two getter methods retrieve both the candidate's name and party. */
  public String getName()
  {
    return candidateName;
  }
  
  public String getParty()
  {
    return candidateParty;
  }
  
  private String candidateName;
  private String candidateParty;
}