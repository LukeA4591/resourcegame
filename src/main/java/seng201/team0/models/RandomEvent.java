package seng201.team0.models;

/**
 * Represents the types of random events that can occur in a game.
 */
public enum RandomEvent {
    /**
     * Breaks a player's tower.
     * Higher chance of breaking towers using in last round.
     */
    ENEMY_AIR_STRIKE,
    /**
     * Enemy ambush reduces the reload speed of an ammunition tower.
     */
    ENEMY_AMBUSH,
    /**
     * Communications breakdown reduces the reload speed of a troop tower.
     */
    COMMUNICATIONS_BREAKDOWN,
    /**
     * medical supply line sabotage reduces the reload speed of a medkit tower.
     */
    MEDICAL_SUPPLY_LINE_SABOTAGE
}
