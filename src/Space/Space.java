/*
 * Interface Space
 *
 * Middleware between Aquarium and Entity
 * Give possibility for Entity to communicate with Aquarium for
 * object position manipulation in the screen
 */

public interface Space {
  // Give Entity possibility to move in Space or derived class of Space
	boolean moveTo(int entityId, int entityType, double newX, double newY);

	// Give Entity possibility to remove/consume in Space or derived class of Space
	void remove(int entityId, int entityType);

	// Give Entity information about another Entity existence by its id
	boolean isExist(int entityId, int entityType);
}
