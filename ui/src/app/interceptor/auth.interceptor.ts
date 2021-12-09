import { Injectable } from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthStore } from '../value-store';
import { AuthenticationService } from '../service/security/authentication.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    DEBUG = false;

    constructor() {}

    intercept(
        request: HttpRequest<any>,
        next: HttpHandler
    ): Observable<HttpEvent<any>> {
        if (this.DEBUG) console.log('AuthInterceptor: ' + request.url);

        if (request.url.endsWith('/applications')) {
            const token = localStorage.getItem(AuthStore.token);
            if (token !== null) {
                if (this.DEBUG) console.log('Token Found: ' + token);

                const cloned = request.clone({
                    headers: request.headers.append(
                        'Authorization',
                        'Bearer ' + token
                    ),
                });

                return next.handle(cloned);
            } else {
                return next.handle(request);
            }
        } else {
            return next.handle(request);
        }

    }
}
