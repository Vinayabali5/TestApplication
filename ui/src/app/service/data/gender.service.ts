import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { ApiConfigService } from '../api-config.service';
import { BasicApiService } from './basic-api.service';

import { Gender } from '../../model/gender';
import { LookupDataService } from './lookup-data-service';

@Injectable({
    providedIn: 'root',
})
export class GenderService extends BasicApiService implements LookupDataService<Gender[]> {
    private baseUrl: string = '/genders';
    private url: string;

    constructor(
        protected config: ApiConfigService,
        protected http: HttpClient
    ) {
        super(config, http);
        this.url = this.apiBaseUrl + this.baseUrl;
        this.log(`url set to ${this.url}`, 'GenderService');
    }

    getAll(): Observable<Gender[]> {
        return this.http.get<Gender[]>(this.url).pipe(
            tap((_) => this.log('fetched all', 'GenderService')),
            catchError(this.handleError<Gender[]>('getAll', []))
        );
    }
}
