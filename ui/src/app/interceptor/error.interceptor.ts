import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

/**
 * This interceptor is used to capture errors from the HTTP requests.
 */
@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

    DEBUG = false;

    constructor() {}

    intercept(
        request: HttpRequest<any>,
        next: HttpHandler
    ): Observable<HttpEvent<any>> {
        if (this.DEBUG) console.log('CorsInterceptor: ' + request.url);

        return next.handle(request).pipe(
            catchError((err) => {
                if (
                    [401, 403].includes(err.status)
//                    && this.authenticationService.isLoggedIn()
                ) {
                    // auto logout if 401 or 403 response returned from api
//                    this.authenticationService.logout();
                    if (this.DEBUG) console.log('User not authenticated. Finalising Logout.');
                    localStorage.clear();
                }

                return next.handle(request);
            })
        );
    }
}
