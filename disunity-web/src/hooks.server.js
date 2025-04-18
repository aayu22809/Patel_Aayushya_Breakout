/** @type {import('@sveltejs/kit').Handle} */
export async function handle({ event, resolve }) {
	// Just pass through all requests in this demo
	return await resolve(event);
}