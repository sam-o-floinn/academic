class CrowGameInfo extends UTDeathmatch;

var int ApplesLeft;

simulated function PostBeginPlay()
{
  super.PostBeginPlay();
}

function ScoreObjective(PlayerReplicationInfo Scorer, int Score)
{
  super.ScoreObjective(Scorer, Score);
}

defaultproperties
{
  DefaultInventory(0)=None
  ApplesLeft=10
  bScoreDeaths=false
  DefaultPawnClass=class'CrowPawn'
  PlayerControllerClass=class'AsTheCrowFlies.CrowPlayerController'
}