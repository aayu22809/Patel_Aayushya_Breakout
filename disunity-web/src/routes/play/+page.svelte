<script>
	import { onMount, onDestroy } from 'svelte';
	import { goto } from '$app/navigation';
	/** @type {HTMLDivElement} */
	let gameContainerRef;
	let gameReady = false;
	let gameRunning = false;
	let gameStarting = false;
	let errorMessage = '';
	/** @type {ReturnType<typeof setInterval>|null} */
	let statusInterval = null;

	onMount(() => {
		// Set game ready state when component mounts
		gameReady = true;
	});

	onDestroy(() => {
		// Ensure game is stopped when navigating away
		if (gameRunning) {
			stopGame();
		}

		// Clear any refresh intervals
		if (statusInterval) {
			clearInterval(statusInterval);
		}
	});

	async function startGame() {
		if (!gameReady || gameRunning || gameStarting) return;

		try {
			gameStarting = true;
			errorMessage = '';

			// Run the Java application by making a request to our backend endpoint
			const response = await fetch('/api/start-game', { method: 'POST' });
			const data = await response.json();

			if (data.success) {
				console.log('Game started:', data);
				gameRunning = true;

				// Set up status refresh interval
				setupStatusRefresh();
			} else {
				console.error('Error response from server:', data);
				errorMessage = data.error || 'Failed to start game';
			}
		} catch (error) {
			console.error('Error starting game:', error);
			errorMessage = 'Failed to connect to game server';
		} finally {
			gameStarting = false;
		}
	}

	async function stopGame() {
		if (!gameRunning) return;

		try {
			// Stop the game process
			const response = await fetch('/api/stop-game', { method: 'POST' });
			const data = await response.json();

			if (data.success) {
				console.log('Game stopped:', data);

				// Clear the refresh interval
				if (statusInterval) {
					clearInterval(statusInterval);
					statusInterval = null;
				}

				// Refresh the iframe one more time to show stopped status
				refreshStatusFrame();
			} else {
				console.error('Error stopping game:', data);
			}
		} catch (error) {
			console.error('Error stopping game:', error);
		} finally {
			gameRunning = false;
		}
	}

	function setupStatusRefresh() {
		// Refresh the status iframe every 3 seconds
		if (statusInterval) {
			clearInterval(statusInterval);
		}

		refreshStatusFrame(); // Initial refresh

		statusInterval = setInterval(() => {
			refreshStatusFrame();
		}, 3000);
	}

	function refreshStatusFrame() {
		// ts pmo bruh
		// Find the iframe in the game container
		const iframe = gameContainerRef?.querySelector('iframe');
		if (iframe) {
			// Add a timestamp to force reload and avoid caching
			iframe.src = `/api/game-stream?t=${Date.now()}`;
		}
	}

	function goBack() {
		if (gameRunning) {
			stopGame();
		}
		goto('/');
	}
</script>

// Main window for running // TODO: Add auth
<div class="flex min-h-screen flex-col bg-gray-800 text-white">
	<header class="flex items-center justify-between border-b border-gray-700 bg-gray-900 p-4">
		<h1 class="text-2xl font-bold">LJAAG Game</h1>
		<button class="rounded bg-gray-700 px-4 py-2 hover:bg-gray-600" on:click={goBack}>
			Back to Home
		</button>
	</header>

	<div class="flex flex-grow flex-col items-center justify-center p-4">
		{#if !gameRunning}
			<div class="mb-8 max-w-lg rounded-lg bg-gray-900 p-8 text-center">
				<h2 class="mb-4 text-3xl font-bold">Ready to Play?</h2>
				<p class="mb-6">
					Click the button below to start the game. The game will launch in a separate window
					outside of this browser.
				</p>
				<button
					class="rounded-lg bg-blue-600 px-6 py-3 text-lg font-medium text-white transition-colors hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 disabled:opacity-50"
					on:click={startGame}
					disabled={!gameReady || gameStarting}
				>
					{#if gameStarting}
						Starting Game...
					{:else if !gameReady}
						Loading...
					{:else}
						Start Game
					{/if}
				</button>

				{#if errorMessage}
					<p class="mt-4 text-red-500">{errorMessage}</p>
				{/if}
			</div>
		{:else}
			<div class="max-w-2xl rounded-lg bg-gray-900 p-8 text-center">
				<h2 class="mb-4 text-3xl font-bold">Game Launched!</h2>

				<div class="mb-6 rounded-lg bg-yellow-800 p-4 text-yellow-100">
					<p class="font-semibold">Important: The game is running in a separate window</p>
					<p class="mt-2 text-sm">
						Look for the Java application window that should have opened on your desktop
					</p>
				</div>

				<!-- Game status iframe -->
				<div
					class="mb-6 overflow-hidden rounded-lg border border-gray-700"
					bind:this={gameContainerRef}
				>
					<iframe
						src="/api/game-stream"
						title="Game Status"
						class="h-[300px] w-full"
						frameborder="0"
					></iframe>
				</div>

				<div class="mb-6 rounded-lg bg-gray-800 p-4">
					<h3 class="mb-2 text-xl font-semibold">Game Controls</h3>
					<div class="mb-3 flex justify-center gap-2">
						<span class="inline-block rounded bg-gray-700 px-4 py-2 font-mono">W</span>
						<span class="inline-block rounded bg-gray-700 px-4 py-2 font-mono">A</span>
						<span class="inline-block rounded bg-gray-700 px-4 py-2 font-mono">S</span>
						<span class="inline-block rounded bg-gray-700 px-4 py-2 font-mono">D</span>
					</div>
					<p>or Arrow keys to move the character</p>
				</div>

				<p class="mb-6 text-gray-300">
					When you're finished playing, you can stop the game using the button below:
				</p>

				<button
					class="rounded bg-red-600 px-6 py-3 text-lg font-medium hover:bg-red-700"
					on:click={stopGame}
				>
					Stop Game
				</button>
			</div>
		{/if}
	</div>
</div>
