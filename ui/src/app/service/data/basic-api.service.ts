import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { environment } from 'src/environments/environment';

import { ApiConfigService } from '../api-config.service';

@Injectable({
    providedIn: 'root',
})
export class BasicApiService {
    /**
     * This property is used to turn on debugging options.
     */
    private DEBUG: boolean = false;

    /**
     * This property is used to store the Base URL for the API.
     */
    protected apiBaseUrl?: string;

    constructor(
        protected config: ApiConfigService,
        protected http: HttpClient
    ) {
        if (this.DEBUG) {
            console.log(this.constructor.name);
        }
        this.apiBaseUrl = config.config?.apiUrl;
    }

    /**
     * This method is used to log various outputs to the console.
     *
     * @param message the message to appear in the console.
     * @param caller the caller of the log method.
     */
    protected log(message: string, caller = 'BasicApiService'): void {
        console.log(`${caller}: ${message}`);
    }

    /**
     * Handle Http operation that failed. Let the app continue.
     *
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
    protected handleError<T>(
        operation = 'operation',
        result?: T,
        caller?: string
    ): any {
        return (error: any): Observable<T> => {
            if (this.DEBUG) {
                console.error(error);
            }
            this.log(`${operation} failed: ${error.message}`, caller);
            return of(result as T);
        };
    }
}
