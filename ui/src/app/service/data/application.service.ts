import { HttpClient, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EMPTY, Observable, Observer, Subscription } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

import { ApiConfigService } from '../api-config.service';
import { BasicApiService } from './basic-api.service';

import { Application } from '../../model/application';
import { AuthenticationService } from '../security/authentication.service';
import { DataStore } from 'src/app/value-store';

@Injectable({
    providedIn: 'root',
})
export class ApplicationService extends BasicApiService {
    private baseUrl: string = '/applications';
    private url: string;

    constructor(
        protected config: ApiConfigService,
        protected http: HttpClient,
        protected authService: AuthenticationService
    ) {
        super(config, http);
        this.url = this.apiBaseUrl + this.baseUrl;
        this.log(`url set to ${this.url}`, 'ApplicationService');
    }

    /**
     * This method is used to send the application data to the API for saving.
     *
     * @param application the application data to save.
     * @returns a Subscription for the API call.
     */
    save(application: any): Observable<Application> {
        console.log(application);
        return this.http.post<Application>(this.url, application).pipe(
            tap((res) => {
                this.log('saving application', 'ApplicationService');
                localStorage.setItem(
                    DataStore.application,
                    JSON.stringify(res)
                );
            })
        );
    }

    /**
     * 
     * This method is used to send the debug data to the API for debug report saving.
     */
     debug(debugData: any): Observable<any> {
        console.log(debugData);
        return this.http.post<any>(this.url + '/debug', debugData).pipe(
            tap((res) => {
                this.log('saving debug report', 'ApplicationService');
            }),           
        );
    }

    /**
     * This method is used to retrieve the currently logged in users application form from the API.
     *
     * @returns an Observable for the HTTP request.
     */
    get(): Observable<Application> {
        console.log('Retrieving any existing application data.');
        localStorage.removeItem(DataStore.application);

        return this.http.get<Application>(this.url).pipe(
            tap((res) => {
                this.log('fetched application', 'ApplicationService');
                localStorage.setItem(
                    DataStore.application,
                    JSON.stringify(res)
                );
            })
        );
    }
}
