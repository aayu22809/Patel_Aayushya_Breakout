import type { RequestEvent } from '@sveltejs/kit';

export const sessionCookieName = 'auth-session';

/**
 * Simple implementation for the demo - in production, use proper crypto
 */
export function generateSessionToken() {
  const bytes = crypto.getRandomValues(new Uint8Array(18));
  return Array.from(bytes)
    .map(b => b.toString(16).padStart(2, '0'))
    .join('');
}

/**
 * Mock session validation for the demo
 */
export async function validateSessionToken(token: string) {
  // This is a simplified mock version
  return { session: null, user: null };
}

export type SessionValidationResult = Awaited<ReturnType<typeof validateSessionToken>>;

/**
 * Mock session invalidation for the demo
 */
export async function invalidateSession(sessionId: string) {
  // No-op for demo
}

/**
 * Set session cookie
 */
export function setSessionTokenCookie(event: RequestEvent, token: string, expiresAt: Date) {
  event.cookies.set(sessionCookieName, token, {
    expires: expiresAt,
    path: '/'
  });
}

/**
 * Delete session cookie
 */
export function deleteSessionTokenCookie(event: RequestEvent) {
  event.cookies.delete(sessionCookieName, {
    path: '/'
  });
}