import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { ApiConfigService } from '../api-config.service';
import { BasicApiService } from './basic-api.service';

import { Qualification } from '../../model/qualification';
import { LookupDataService } from './lookup-data-service';

@Injectable({
    providedIn: 'root',
})
export class QualificationService extends BasicApiService implements LookupDataService<Qualification[]> {
    private baseUrl: string = '/qualifications';
    private url: string;

    constructor(
        protected config: ApiConfigService,
        protected http: HttpClient
    ) {
        super(config, http);
        this.url = this.apiBaseUrl + this.baseUrl;
        this.log(`url set to ${this.url}`, 'QualificationService');
    }

    getAll(): Observable<Qualification[]> {
        return this.http.get<Qualification[]>(this.url).pipe(
            tap((_) => this.log('fetched all', 'QualificationService')),
            catchError(this.handleError<Qualification[]>('getAll', []))
        );
    }
}
