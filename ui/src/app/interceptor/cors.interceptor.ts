import { Injectable } from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor,
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class CorsInterceptor implements HttpInterceptor {

    DEBUG = false;

    constructor() {}

    intercept(
        request: HttpRequest<unknown>,
        next: HttpHandler
    ): Observable<HttpEvent<unknown>> {
        if (this.DEBUG) console.log('CorsInterceptor: ' + request.url);

        if (request.method === 'POST') {
            if (this.DEBUG) console.log('Adding CORS Headers');
            const corsHeaders = request.headers;
            corsHeaders
                .append('Access-Control-Allow-Origin', '*')
                .append('Control-Allow-Origin', '*');
            const cloned = request.clone({
                headers: corsHeaders,
            });
            return next.handle(cloned);
        } else {
            return next.handle(request);
        }
    }
}
