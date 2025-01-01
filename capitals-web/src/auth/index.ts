import {UserManager} from 'oidc-client-ts';
import {useStore} from '@/store';

const oidcConfig = {
    authority: import.meta.env.VITE_OAUTH2_AUTHORITY_URL,
    client_id: import.meta.env.VITE_OAUTH2_CLIENT_ID,
    client_secret: import.meta.env.VITE_OAUTH2_CLIENT_SECRET,
    redirect_uri: import.meta.env.VITE_OAUTH2_REDIRECT_URI,
    post_logout_redirect_uri: import.meta.env.VITE_OAUTH2_POST_LOGOUT_REDIRECT_URI,
    response_type: import.meta.env.VITE_OAUTH2_RESPONSE_TYPE,
    scope: import.meta.env.VITE_OAUTH2_SCOPE,
};

const userManager = new UserManager(oidcConfig);

userManager.events.addUserLoaded((user) => {
    useStore.useAuthStore().loadUser(user);
});
userManager.events.addUserUnloaded(() => {
    useStore.useAuthStore().removeUser();
});

const auth = {
    login() {
        return userManager.signinRedirect();
    },
    handleCallback() {
        return userManager.signinRedirectCallback();
    },
    logout() {
        return userManager.signoutRedirect();
    },
    getUser() {
        return userManager.getUser();
    },
};

export default auth;
