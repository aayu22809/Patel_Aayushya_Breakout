<script>
	import { goto } from '$app/navigation';

	function handlePlay() {
		goto('/play');
	}
</script>

<div class="flex min-h-screen flex-col items-center justify-center bg-gray-900 text-white">
	<div class="text-center">
		<h1 class="mb-6 text-5xl font-bold">Disunity Game</h1>
		<p class="mb-8 text-xl">Welcome to the LJAAG Game Experience</p>
		<button
			class="rounded-lg bg-blue-600 px-6 py-3 text-lg font-medium text-white transition-colors hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
			on:click={handlePlay}
		>
			Play Game
		</button>
	</div>
</div>