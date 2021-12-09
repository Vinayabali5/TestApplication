import { Injectable } from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor,
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class RestInterceptor implements HttpInterceptor {

    DEBUG = false;

    constructor() {}

    intercept(
        request: HttpRequest<unknown>,
        next: HttpHandler
    ): Observable<HttpEvent<unknown>> {
        if (this.DEBUG) console.log('RESTInterceptor: ' + request.url);

        const restHeaders = request.headers.append(
            'Content-Type',
            'application/json'
        );

        const cloned = request.clone({
            headers: restHeaders,
        });

        return next.handle(cloned);
    }
}
