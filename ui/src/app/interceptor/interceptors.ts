import { HTTP_INTERCEPTORS } from '@angular/common/http';

import { ErrorInterceptor } from './error.interceptor';
import { AuthInterceptor } from './auth.interceptor';
import { CorsInterceptor } from './cors.interceptor';
import { RestInterceptor } from './rest.interceptor';

export const HttpInterceptorProviders = [
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: RestInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: CorsInterceptor, multi: true },
];

