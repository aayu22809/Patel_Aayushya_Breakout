import { gameManager } from '$lib/server/game-manager';

/** @type {import('./$types').RequestHandler} */
export function GET() {
	// Get the game status
	const status = gameManager.getGameStatus();
	
	// Generate HTML with current status
	const html = `
		<!DOCTYPE html>
		<html>
		<head>
			<meta charset="utf-8">
			<title>Game Stream</title>
			<style>
				body, html {
					margin: 0;
					padding: 0;
					background: #000;
					color: #fff;
					font-family: system-ui, sans-serif;
					height: 100%;
					overflow: hidden;
					display: flex;
					align-items: center;
					justify-content: center;
					flex-direction: column;
				}
				h2 {
					margin-bottom: 1rem;
				}
				p {
					margin-bottom: 2rem;
				}
				.status {
					padding: 0.5rem 1rem;
					border-radius: 4px;
					background: ${status.running ? '#2a5a2a' : '#5a2a2a'};
					margin-bottom: 1rem;
				}
				.controls {
					background: #222;
					padding: 1rem;
					border-radius: 4px;
					max-width: 80%;
				}
				.key {
					background: #444;
					padding: 0.25rem 0.5rem;
					border-radius: 4px;
					font-family: monospace;
				}
			</style>
		</head>
		<body>
			<h2>Game Status</h2>
			<div class="status">
				${status.running 
					? `Game is running (PID: ${status.pid})`
					: 'Game is not running'
				}
			</div>
			<p>${status.running 
				? 'The game is running in a separate window outside this browser.'
				: 'The game is currently not running. Press Start Game to launch it.'
			}</p>
			<div class="controls">
				<h3>Controls:</h3>
				<p>Movement: <span class="key">W</span> <span class="key">A</span> <span class="key">S</span> <span class="key">D</span> or Arrow Keys</p>
			</div>
		</body>
		</html>
	`;

	return new Response(html, {
		headers: {
			'Content-Type': 'text/html'
		}
	});
}