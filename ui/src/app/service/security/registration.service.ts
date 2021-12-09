import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { BasicApiService } from '../data/basic-api.service';
import { ApiConfigService } from '../api-config.service';

import { UsernamePasswordRequest } from 'src/app/model/security/username-password-request';

@Injectable({
    providedIn: 'root',
})
export class RegistrationService extends BasicApiService {
    /**
     * This property is used to store the URI for the registration endpoint.
     */
    private registerEndpoint: string = '/register';

    /**
     * This property is used to store the URI for the registration endpoint.
     */
    private confirmEndpoint: string = '/confirm';

    /**
     * This property is used to store the URI for the registration endpoint.
     */
    private resendEndpoint: string = '/resend';

    constructor(
        protected config: ApiConfigService,
        protected http: HttpClient
    ) {
        super(config, http);
    }

    /**
     * This method is used to send the registration request to the API.
     *
     * @param data a RegistrationRequest object.
     */
    register(data: UsernamePasswordRequest): Observable<any> {
        const registerUrl = this.apiBaseUrl + this.registerEndpoint;
        return this.http.post(registerUrl, data);
    }

    /**
     * This method is used to send the registration confirmation request to the API.
     *
     * @param code a string containing the code for the user.
     */
    confirm(code: string): Observable<any> {
        const confirmUrl = this.apiBaseUrl + this.confirmEndpoint;
        const requestUrl = confirmUrl + '/' + code;
        return this.http.get(requestUrl);
    }

    /**
     * This method is used to send a resend confirmation request to the API.
     *
     * @param username the username to trigger the resend for.
     */
    resend(username: string) {
        const resendUrl = this.apiBaseUrl + this.resendEndpoint;
        return this.http.post(resendUrl, { username: username });
    }
}
