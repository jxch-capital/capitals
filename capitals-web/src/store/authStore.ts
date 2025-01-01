import {defineStore} from 'pinia';
import type {User} from "oidc-client-ts";

export const useAuthStore = defineStore('auth', {
    state: (): { user: User | null } => ({
        user: null,
    }),
    persist: false,
    actions: {
        loadUser(user: User) {
            this.user = user;
        },
        removeUser() {
            this.user = null;
        },
        isLoggedIn() {
            return this.user != null;
        },
        getCurrentUser() {
            return this.user;
        },
        getAuthHeader() {
            if (this.user) {
                return `${this.user.token_type} ${this.user.access_token}`;
            } else {
                return null;
            }
        },
    },
});

