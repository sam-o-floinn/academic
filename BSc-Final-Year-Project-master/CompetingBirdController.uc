class CompetingBirdController extends AIController placeable;

/* An NPC bird that will wander the map slowly and eat any food it comes across.

* It has two states: wandering and pursuing.
* It wanders slowly, often circling at a point before moving on. It has no places where it cannot go to.

* If a food item comes into its cone of vision, it will accelerate and go to that item to eat it.
* If it sees a player carrying an item, it will chase after them. When it is close enough, it will barge them,
* causing them to lose a food item in their inventory . The player will be stunned for a second,
* long enough to be unable to get the food item again.
* Either the player pawn can give the item to this NPC, or the item will fall to the ground for the NPC to see and pick up.

* This bird cannot see any food items that are hidden in caches.

*/
var int StartingSpot;

state Wandering
{
  simulated event BeginState(name PreviousStateName)
  {
    setPhysics(PHYS_Flying);
  }
 //wander around. Suggested that I use A* search

 //If food item comes within X distance, set it as destination and eat i.

 //
}
/*
state Attacking
{
  function BeginState(Name PreviousStateName)
  {
    StartingSpot = Location;
  }

  function Tick(float DeltaTime)
  {
    bAttacking = true;

    if(Enemy == none)
    {
        GetEnemy();
    }

    if(Enemy != none)
    {
        Enemy.Bump(self, CollisionComponent, vect(0,0,0));

        if (VSize(Location - Enemy.Location) > AttackDistance)
        {
          GoToState('Seeking');
        }
    }
  }

  function EndState(name NextStateName)
  {
    SetTimer(1, false, 'EndAttack');
  }
}

state Pursing
{
  local int startingSpot;
  simulated event BeginState(name PreviousStateName)
  {
    setPhysics(PHYS_Flying);
    SetViewTarget(Player);
    startingSpot = Pawn.Location;
  }

  function Tick(float DeltaTime)
  {
    if (! (Player.InventorySlot.IsEmpty() ) )
    {
      if (Pawn.Location - Player.Pawn.Location = twelveInches)
      {
        Barge(Player);
      }
      else
      {
        Follow the player
      }
    }
  }
} */



function Barge()
{
  /*
  * 1. The player crow is frozen for one second.
  * 2. If there is an item in the player's inventory, give it to the magpie
  * 3. GoToState('Wandering');
  */
}