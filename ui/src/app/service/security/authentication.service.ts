import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, shareReplay } from 'rxjs/operators';

import { BasicApiService } from '../data/basic-api.service';
import { ApiConfigService } from '../api-config.service';
import { DateService } from '../date.service';

import { JwtRequest } from '../../model/security/jwt-request';
import { JwtResponse } from '../../model/security/jwt-response';
import { AuthStore } from 'src/app/value-store';

@Injectable({
    providedIn: 'root',
})
export class AuthenticationService extends BasicApiService {
    /**
     * This property is used to store the string to use to store the JWT token.
     */
    private TOKEN: string = AuthStore.token;

    /**
     * This property is used to store the string to use to store the expires at from the login request.
     */
    private EXPIRES_AT: string = AuthStore.expiry;

    /**
     * This property is used to store the URI for the registration endpoint.
     */
    private baseUrl: string = '/authenticate';

    /**
     * This property is used to store the full URL for the registration endpoint.
     */
    private url: string;

    constructor(
        protected config: ApiConfigService,
        protected http: HttpClient,
        protected dateService: DateService
    ) {
        super(config, http);
        this.url = this.apiBaseUrl + this.baseUrl;
        this.log(`url set to ${this.url}`, 'AuthenticationService');
    }

    /**
     * This method is used to perform the login request to the login endpoint.
     *
     * @param user the username to use for the request.
     * @param pass the password to use for the request.
     * @returns an Observable containing a JwtResponse object.
     */
    login(user: string, pass: string): Observable<JwtResponse> {
        const jwtRequest: JwtRequest = { username: user, password: pass };
        return this.http.post<JwtResponse>(this.url, jwtRequest).pipe(
            tap((resp: JwtResponse) => this.setSession(resp)),
            shareReplay()
        );
    }

    /**
     * This method is used to store the JWT Token from the login request into local storage.
     *
     * @param authResult a JwtResponse object to store.
     */
    private setSession(authResult: JwtResponse): void {
        const expiresAt = authResult.expirationDate;
        console.log('Token expires at ' + expiresAt);
        console.log(
            'Token date and time is ' +
                this.dateService.getShortDateAndTimeDisplay(expiresAt)
        );
        localStorage.setItem(this.TOKEN, authResult.token);
        localStorage.setItem(
            this.EXPIRES_AT,
            JSON.stringify(expiresAt.valueOf())
        );
    }

    /**
     * This method is used to perform the log out action and remove the JWT Token from local storage.
     */
    logout(): void {
        localStorage.clear();
    }

    /**
     * This method is used to determine in the user is currently logged in with a valid JWT Token.
     *
     * @returns a boolean of logged in state.
     */
    public isLoggedIn(): boolean {
        const expiry = this.getExpiration();
        if (expiry !== 0 && Date.now() < this.getExpiration()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is used to determine in the user is currently logged out.
     *
     * @returns a boolean of logged out state.
     */
    isLoggedOut(): boolean {
        return !this.isLoggedIn();
    }

    /**
     * This method is used to retrieve the expiration of the JWT Token.
     *
     * @returns a number for the expiration for the current JWT Token.
     */
    getExpiration(): number {
        const expiration = localStorage.getItem(this.EXPIRES_AT);
        if (expiration != null) {
            const expiresAt = JSON.parse(expiration);
            return expiresAt;
        } else {
            return 0;
        }
    }

    /**
     * This method is used to retrieve the full JWT token.
     *
     * @returns a string containing the JWT token.
     */
    getToken(): string | null {
        const token = localStorage.getItem(this.TOKEN);
        return token;
    }
}
