import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UsernamePasswordCodeRequest } from 'src/app/model/security/username-password-code-request';
import { ApiConfigService } from '../api-config.service';
import { BasicApiService } from '../data/basic-api.service';

@Injectable({
    providedIn: 'root'
})
export class PasswordResetService extends BasicApiService {
    /**
     * This property is used to store the URI for the password reset initialise endpoint.
     */
    private initialResetEndpoint: string = '/password-reset/initialise';

    /**
    * This property is used to store the URI for the password reset finalise endpoint.
    */
    private finaliseResetEndpoint: string = '/password-reset/finalise';

    constructor(
        protected config: ApiConfigService,
        protected http: HttpClient
    ) {
        super(config, http);
    }

    /**
     * This method is used to send a reset password request to the API.
     *
     * @param username the username to trigger the resend for.
     */
    initialReset(username: string) {
        const resendUrl = this.apiBaseUrl + this.initialResetEndpoint;
        return this.http.post(resendUrl, { username: username });
    }

    /**
     * This method is used to send a reset password finalise request to the API.
     */
    finalise(details: UsernamePasswordCodeRequest) {
        const resendUrl = this.apiBaseUrl + this.finaliseResetEndpoint;
        return this.http.post(resendUrl, details);
    }
}
