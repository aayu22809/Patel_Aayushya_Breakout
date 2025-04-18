import { json } from '@sveltejs/kit';
import { gameManager } from '$lib/server/game-manager';

/** @type {import('./$types').RequestHandler} */
export async function POST() {
	try {
		// Use the game manager to start the game
		const result = gameManager.startGame();
		
		if (result.success) {
			return json(result);
		} else {
			return json(result, { status: 500 });
		}
	} catch (error) {
		console.error('Error starting game:', error);
		return json({ 
			success: false, 
			error: error instanceof Error ? error.message : String(error) 
		}, { status: 500 });
	}
}