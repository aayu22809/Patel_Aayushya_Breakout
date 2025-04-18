// Mock database module to prevent errors when real database isn't available
// This file provides a mock database interface that won't throw errors 
// when the actual database isn't configured

/**
 * @typedef {Object} MockDb
 * @property {Function} query - Mock query function
 * @property {Function} select - Mock select function
 * @property {Function} insert - Mock insert function
 */

/** @type {MockDb} */
const mockDb = {
  query: () => Promise.resolve([]),
  select: () => ({
    from: () => ({ 
      where: () => Promise.resolve([]) 
    })
  }),
  insert: () => ({ 
    values: () => Promise.resolve([]) 
  }),
  // Add other methods as needed
};

// Export the mock database
export const db = mockDb;