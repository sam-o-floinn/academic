public class VotingDay
{
  public static void main (String[] args)
  {
    System.out.println("Today is voting day! Who do we have up to elect?");
    ArrayBasedList<Candidate> candidateList = new ArrayBasedList<Candidate> ();
    Candidate cand = new Candidate("Dustin the Turkey", "RTE");
    Candidate candTwo = new Candidate("Michelle Malarkey", "Labour");
    Candidate candThree = new Candidate("Justin Case", "Green");
    Candidate candFour = new Candidate("Bertie-Bertie-Bertie", "Fianna Fáil");

    System.out.println(cand.getName() );
    System.out.println(cand.getParty() );
    BallotPaper paper = new BallotPaper();

    paper.setPreference(1, cand);
    paper.setPreference(2, candTwo);
    paper.setPreference(3, candFour);
    paper.setPreference(4, candThree);
    
    BallotPaper evilPaper = new BallotPaper();
    evilPaper.setPreference(1, candTwo);
    evilPaper.setPreference(2, candThree);
    evilPaper.setPreference(3, cand);
    evilPaper.setPreference(4, candFour);

    BallotPaper newerPaper = new BallotPaper();
    newerPaper.setPreference(1, candThree);
    newerPaper.setPreference(2, cand);
    newerPaper.setPreference(3, candFour);
    newerPaper.setPreference(4, candTwo);
    
    BallotPaper morePapers = new BallotPaper();
    morePapers.setPreference(1, cand);
    morePapers.setPreference(2, candTwo);
    morePapers.setPreference(3, candThree);
    morePapers.setPreference(4, candFour);
    /*Compiling all of the ballot papers into one list, to enter into the VoteCounter class. */
    ArrayBasedList<BallotPaper> voteCollection = new ArrayBasedList<BallotPaper>();
    voteCollection.add(paper);
    voteCollection.add(evilPaper);
    voteCollection.add(newerPaper);
    voteCollection.add(morePapers);
    (voteCollection.get(0) ).getPreference(1);

    /*Adding all the candidates to our list of candidates.*/
    candidateList.add(cand);
    candidateList.add(candTwo);
    candidateList.add(candThree);
    candidateList.add(candFour);

    System.out.println("");
    for (int i = 1; i <= candidateList.size(); i++)
    {
      paper.getPreference(i);
    }
    System.out.println("");
    VoteCounting counter = new VoteCounting(candidateList);
    counter.determineResult(voteCollection);
  }
}