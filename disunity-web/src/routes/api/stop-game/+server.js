import { json } from '@sveltejs/kit';
import { gameManager } from '$lib/server/game-manager';

/** @type {import('./$types').RequestHandler} */
export async function POST() {
	try {
		const result = gameManager.stopGame();
		return json(result);
	} catch (error) {
		console.error('Error stopping game:', error);
		return json({ 
			success: false, 
			error: error instanceof Error ? error.message : String(error) 
		}, { status: 500 });
	}
}