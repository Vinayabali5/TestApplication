import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

import { ApiConfigService } from '../api-config.service';
import { BasicApiService } from './basic-api.service';

import { School } from '../../model/school';
import { LookupDataService } from './lookup-data-service';

@Injectable({
    providedIn: 'root',
})
export class SchoolService extends BasicApiService implements LookupDataService<School[]> {
    private baseUrl: string = '/schools';
    private url: string;

    constructor(
        protected config: ApiConfigService,
        protected http: HttpClient
    ) {
        super(config, http);
        this.url = this.apiBaseUrl + this.baseUrl;
        this.log(`url set to ${this.url}`, 'SchoolService');
    }

    getAll(): Observable<School[]> {
        return this.http.get<School[]>(this.url).pipe(
            tap((_) => this.log('fetched titles', 'SchoolService')),
            catchError(this.handleError<School[]>('getSchools', [], 'SchoolService'))
        );
    }
}
