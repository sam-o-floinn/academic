/* @author Sam O'Floinn, 109539794
 * This class creates a ballot paper for a single voter to use. A ballot paper contains all the candidates
 * and lets the voter assign a preference to each of them.*/

public class BallotPaper
{
  /*Assigns a preference to a given candidate. */
  public void setPreference(int n, Candidate c)
  {
    ballot.put(n, c);
    //System.out.println(c.getName() + " was given this paper's #" + n + " preference!");
  }

  /*Retrieves the candidate this paper gave a given preference to. */
  public Candidate getPreference(int n)
  {
    Candidate theRightCandidate = ballot.get(n);
    //System.out.println("This voter gave " + theRightCandidate.getName() + " a preference of " + n);
    return theRightCandidate;
  }

  ArrayBasedMap<Integer, Candidate> ballot = new ArrayBasedMap<Integer, Candidate>();  //we use a map and not a list since maps are the only way we can keep track of which candidate receives which vote.
}